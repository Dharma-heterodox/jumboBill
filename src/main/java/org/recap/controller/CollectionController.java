package org.recap.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.codehaus.jettison.json.JSONException;
import org.recap.RecapConstants;
import org.recap.model.search.*;
import org.recap.model.userManagement.UserDetailsForm;
import org.recap.security.UserManagement;
import org.recap.util.CollectionServiceUtil;
import org.recap.util.MarcRecordViewUtil;
import org.recap.util.SearchUtil;
import org.recap.util.UserAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by rajeshbabuk on 12/10/16.
 */

@Controller
public class CollectionController {

    Logger logger = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    SearchUtil searchUtil;

    @Autowired
    MarcRecordViewUtil marcRecordViewUtil;

    @Autowired
    CollectionServiceUtil collectionServiceUtil;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @RequestMapping("/collection")
    public String collection(Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        boolean authenticated=userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_COLLECTION_URL,(UsernamePasswordToken)session.getAttribute(UserManagement.USER_TOKEN));
        if(authenticated)
        {
            CollectionForm collectionForm = new CollectionForm();
            model.addAttribute("collectionForm", collectionForm);
            model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.COLLECTION);
            return "searchRecords";
        }else{
            return UserManagement.unAuthorizedUser(session,"Collection",logger);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/collection", method = RequestMethod.POST, params = "action=displayRecords")
    public ModelAndView displayRecords(@Valid @ModelAttribute("collectionForm") CollectionForm collectionForm,
                                       BindingResult result,
                                       Model model) throws Exception {
        searchAndSetResults(collectionForm);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.COLLECTION);
        return new ModelAndView("searchRecords", "collectionForm", collectionForm);
    }

    @ResponseBody
    @RequestMapping(value = "/collection", method = RequestMethod.POST, params = "action=openMarcView")
    public ModelAndView openMarcView(@Valid @ModelAttribute("collectionForm") CollectionForm collectionForm,
                                     BindingResult result,
                                     Model model,HttpServletRequest request) throws Exception {

        UserDetailsForm userDetailsForm=userAuthUtil.getUserDetails(request.getSession(),UserManagement.BARCODE_RESTRICTED_PRIVILEGE);
        BibliographicMarcForm bibliographicMarcForm = marcRecordViewUtil.buildBibliographicMarcForm(collectionForm.getBibId(), collectionForm.getItemId(),userDetailsForm);
        populateCollectionForm(collectionForm, bibliographicMarcForm);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.COLLECTION);
        return new ModelAndView("collection :: #collectionUpdateModal", "collectionForm", collectionForm);
    }

    @ResponseBody
    @RequestMapping(value = "/collection", method = RequestMethod.POST, params = "action=collectionUpdate")
    public ModelAndView collectionUpdate(@Valid @ModelAttribute("collectionForm") CollectionForm collectionForm,
                                         BindingResult result,
                                         Model model) throws JSONException {
        if (RecapConstants.UPDATE_CGD.equalsIgnoreCase(collectionForm.getCollectionAction())) {
            collectionServiceUtil.updateCGDForItem(collectionForm);
        } else if (RecapConstants.DEACCESSION.equalsIgnoreCase(collectionForm.getCollectionAction())) {
            collectionServiceUtil.deAccessionItem(collectionForm);
        }
        collectionForm.setAllowEdit(true);
        return new ModelAndView("collection :: #itemDetailsSection", "collectionForm", collectionForm);
    }

    private void searchAndSetResults(CollectionForm collectionForm) throws Exception {
        trimBarcodes(collectionForm);
        buildResultRows(collectionForm);
        buildMissingBarcodes(collectionForm);
    }

    private String limitedBarcodes(CollectionForm collectionForm) {
        String[] barcodeArray = collectionForm.getItemBarcodes().split(",");
        if (barcodeArray.length > RecapConstants.BARCODE_LIMIT) {
            String[] limitBarcodeArray = Arrays.copyOfRange(barcodeArray, 0, RecapConstants.BARCODE_LIMIT);
            collectionForm.setIgnoredBarcodesErrorMessage(RecapConstants.BARCODE_LIMIT_ERROR + " - " + StringUtils.join(Arrays.copyOfRange(barcodeArray, RecapConstants.BARCODE_LIMIT, barcodeArray.length), ","));
            return StringUtils.join(limitBarcodeArray, ",");
        }
        return StringUtils.join(barcodeArray, ",");
    }

    private void trimBarcodes(CollectionForm collectionForm) {
        List<String> barcodeList = new ArrayList<>();
        String[] barcodeArray = collectionForm.getItemBarcodes().split(",");
        for (String barcode : barcodeArray) {
            if (StringUtils.isNotBlank(barcode)) {
                String itemBarcode = barcode.trim();
                barcodeList.add(itemBarcode);
            }
        }
        collectionForm.setItemBarcodes(StringUtils.join(barcodeList, ","));
    }

    private void buildResultRows(CollectionForm collectionForm) throws Exception {
        if (StringUtils.isNotBlank(collectionForm.getItemBarcodes())) {
            SearchRecordsRequest searchRecordsRequest = new SearchRecordsRequest();
            searchRecordsRequest.setFieldName(RecapConstants.BARCODE);
            searchRecordsRequest.setFieldValue(limitedBarcodes(collectionForm));

            SearchRecordsResponse searchRecordsResponse = searchUtil.requestSearchResults(searchRecordsRequest);
            List<SearchResultRow> searchResultRows = searchRecordsResponse.getSearchResultRows();
            collectionForm.setSearchResultRows(Collections.EMPTY_LIST);
            if (CollectionUtils.isNotEmpty(searchResultRows)) {
                collectionForm.setSearchResultRows(searchResultRows);
                collectionForm.setSelectAll(false);
            }
        } else {
            collectionForm.setErrorMessage(RecapConstants.NO_RESULTS_FOUND);
        }
        collectionForm.setShowResults(true);
    }

    private void buildMissingBarcodes(CollectionForm collectionForm) {
        Set<String> missingBarcodes = getMissingBarcodes(collectionForm);
        if (CollectionUtils.isNotEmpty(missingBarcodes)) {
            collectionForm.setBarcodesNotFoundErrorMessage(RecapConstants.BARCODES_NOT_FOUND + " - " + StringUtils.join(missingBarcodes, ","));
        }
    }

    private Set<String> getMissingBarcodes(CollectionForm collectionForm) {
        if (StringUtils.isNotBlank(collectionForm.getItemBarcodes())) {
            String[] barcodeArray = collectionForm.getItemBarcodes().split(",");
            if (barcodeArray.length > RecapConstants.BARCODE_LIMIT) {
                barcodeArray = Arrays.copyOfRange(barcodeArray, 0, RecapConstants.BARCODE_LIMIT);
            }
            Set<String> missingBarcodes = new HashSet<>(Arrays.asList(barcodeArray));
            for (SearchResultRow searchResultRow : collectionForm.getSearchResultRows()) {
                String barcode = searchResultRow.getBarcode();
                if (StringUtils.isBlank(barcode)) {
                    if (CollectionUtils.isNotEmpty(searchResultRow.getSearchItemResultRows())) {
                        SearchItemResultRow searchItemResultRow = searchResultRow.getSearchItemResultRows().get(0);
                        barcode = searchItemResultRow.getBarcode();
                        searchResultRow.setBarcode(barcode);
                        searchResultRow.setItemId(searchItemResultRow.getItemId());
                        searchResultRow.setCollectionGroupDesignation(searchItemResultRow.getCollectionGroupDesignation());
                    }
                }
                missingBarcodes.remove(barcode);
            }
            return missingBarcodes;
        }
        return Collections.EMPTY_SET;
    }

    private void populateCollectionForm(CollectionForm collectionForm, BibliographicMarcForm bibliographicMarcForm) {
        collectionForm.setTitle(bibliographicMarcForm.getTitle());
        collectionForm.setAuthor(bibliographicMarcForm.getAuthor());
        collectionForm.setPublisher(bibliographicMarcForm.getPublisher());
        collectionForm.setPublishedDate(bibliographicMarcForm.getPublishedDate());
        collectionForm.setOwningInstitution(bibliographicMarcForm.getOwningInstitution());
        collectionForm.setCallNumber(bibliographicMarcForm.getCallNumber());
        collectionForm.setMonographCollectionGroupDesignation(bibliographicMarcForm.getMonographCollectionGroupDesignation());
        collectionForm.setTag000(bibliographicMarcForm.getTag000());
        collectionForm.setControlNumber001(bibliographicMarcForm.getControlNumber001());
        collectionForm.setControlNumber005(bibliographicMarcForm.getControlNumber005());
        collectionForm.setControlNumber008(bibliographicMarcForm.getControlNumber008());
        collectionForm.setBibDataFields(bibliographicMarcForm.getBibDataFields());
        collectionForm.setAvailability(bibliographicMarcForm.getAvailability());
        collectionForm.setBarcode(bibliographicMarcForm.getBarcode());
        collectionForm.setLocationCode(bibliographicMarcForm.getLocationCode());
        collectionForm.setUseRestriction(bibliographicMarcForm.getUseRestriction());
        collectionForm.setCollectionGroupDesignation(bibliographicMarcForm.getCollectionGroupDesignation());
        collectionForm.setNewCollectionGroupDesignation(bibliographicMarcForm.getNewCollectionGroupDesignation());
        collectionForm.setCgdChangeNotes(bibliographicMarcForm.getCgdChangeNotes());
        collectionForm.setCustomerCode(bibliographicMarcForm.getCustomerCode());
        collectionForm.setDeaccessionType(bibliographicMarcForm.getDeaccessionType());
        collectionForm.setDeaccessionNotes(bibliographicMarcForm.getDeaccessionNotes());
        collectionForm.setDeliveryLocations(bibliographicMarcForm.getDeliveryLocations());
        collectionForm.setDeliveryLocation(bibliographicMarcForm.getDeliveryLocation());
        collectionForm.setShared(bibliographicMarcForm.isShared());
        collectionForm.setSubmitted(bibliographicMarcForm.isSubmitted());
        collectionForm.setMessage(bibliographicMarcForm.getMessage());
        collectionForm.setErrorMessage(bibliographicMarcForm.getErrorMessage());
        collectionForm.setCollectionAction(bibliographicMarcForm.getCollectionAction());
        collectionForm.setShowModal(true);
        collectionForm.setShowResults(true);
        collectionForm.setAllowEdit(bibliographicMarcForm.isAllowEdit());
    }

}
