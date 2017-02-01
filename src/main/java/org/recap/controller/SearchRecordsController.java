package org.recap.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.recap.RecapConstants;
import org.recap.model.search.SearchRecordsRequest;
import org.recap.model.search.SearchRecordsResponse;
import org.recap.security.UserManagement;
import org.recap.util.CsvUtil;
import org.recap.util.SearchUtil;
import org.recap.util.UserAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rajeshbabuk on 6/7/16.
 */

@Controller
public class SearchRecordsController {

    Logger logger = LoggerFactory.getLogger(SearchRecordsController.class);

    @Value("${server.protocol}")
    String serverProtocol;

    @Value("${scsb.shiro}")
    String scsbShiro;


    @Autowired
    SearchUtil searchUtil;

    @Autowired
    private CsvUtil csvUtil;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @RequestMapping("/search")
    public String searchRecords(Model model, HttpServletRequest request) {
        HttpSession session=request.getSession();
        boolean authenticated=userAuthUtil.authorizedUser(RecapConstants.SCSB_SHIRO_SEARCH_URL,(UsernamePasswordToken)session.getAttribute(UserManagement.USER_TOKEN));
        if(authenticated)
        {
            SearchRecordsRequest searchRecordsRequest = new SearchRecordsRequest();
            model.addAttribute("searchRecordsRequest", searchRecordsRequest);
            model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
            return "searchRecords";
        }else{
            return UserManagement.unAuthorizedUser(session,"Search",logger);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=search")
    public ModelAndView search(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                  BindingResult result,
                                  Model model) {
        searchRecordsRequest.resetPageNumber();
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=previous")
    public ModelAndView searchPrevious(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                               BindingResult result,
                               Model model) {
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=next")
    public ModelAndView searchNext(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                   BindingResult result,
                                   Model model) {
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=first")
    public ModelAndView searchFirst(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                       BindingResult result,
                                       Model model) {
        searchRecordsRequest.resetPageNumber();
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=last")
    public ModelAndView searchLast(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                       BindingResult result,
                                       Model model) {
        searchRecordsRequest.setPageNumber(searchRecordsRequest.getTotalPageCount() - 1);
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=clear")
    public ModelAndView clear(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                              BindingResult result,
                              Model model) {

        searchRecordsRequest.setFieldValue("");
        searchRecordsRequest.setOwningInstitutions(new ArrayList<>());
        searchRecordsRequest.setCollectionGroupDesignations(new ArrayList<>());
        searchRecordsRequest.setAvailability(new ArrayList<>());
        searchRecordsRequest.setMaterialTypes(new ArrayList<>());
        searchRecordsRequest.setUseRestrictions(new ArrayList<>());
        searchRecordsRequest.setShowResults(false);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords");
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=newSearch")
    public ModelAndView newSearch(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                  BindingResult result,
                                  Model model) {
        searchRecordsRequest = new SearchRecordsRequest();
        model.addAttribute("searchRecordsRequest", searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords");
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=request")
    public ModelAndView requestRecords(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                  BindingResult result,
                                  Model model) {
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords");
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=export")
    public byte[] exportRecords(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest, HttpServletResponse response,
                                  BindingResult result,
                                  Model model) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileNameWithExtension = "ExportRecords_" + dateFormat.format(new Date()) + ".csv";
        File csvFile = csvUtil.writeSearchResultsToCsv(searchRecordsRequest.getSearchResultRows(), fileNameWithExtension);
        byte[] fileContent = IOUtils.toByteArray(new FileInputStream(csvFile));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameWithExtension + "\"");
        response.setContentLength(fileContent.length);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return fileContent;
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, params = "action=pageSizeChange")
    public ModelAndView onPageSizeChange(@Valid @ModelAttribute("searchRecordsRequest") SearchRecordsRequest searchRecordsRequest,
                                         BindingResult result,
                                         Model model) throws Exception {
        searchRecordsRequest.setPageNumber(getPageNumberOnPageSizeChange(searchRecordsRequest));
        searchAndSetResults(searchRecordsRequest);
        model.addAttribute(RecapConstants.TEMPLATE, RecapConstants.SEARCH);
        return new ModelAndView("searchRecords", "searchRecordsRequest", searchRecordsRequest);
    }

    public Integer getPageNumberOnPageSizeChange(SearchRecordsRequest searchRecordsRequest) {
        int totalRecordsCount;
        Integer pageNumber = searchRecordsRequest.getPageNumber();
        try {
            if (isEmptyField(searchRecordsRequest)) {
                totalRecordsCount = NumberFormat.getNumberInstance().parse(searchRecordsRequest.getTotalRecordsCount()).intValue();
            } else if (isItemField(searchRecordsRequest)) {
                totalRecordsCount = NumberFormat.getNumberInstance().parse(searchRecordsRequest.getTotalItemRecordsCount()).intValue();
            } else {
                totalRecordsCount = NumberFormat.getNumberInstance().parse(searchRecordsRequest.getTotalBibRecordsCount()).intValue();
            }
            int totalPagesCount = (int) Math.ceil((double) totalRecordsCount / (double) searchRecordsRequest.getPageSize());
            if (totalPagesCount > 0 && pageNumber >= totalPagesCount) {
                pageNumber = totalPagesCount - 1;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return pageNumber;
    }

    private boolean isEmptyField(SearchRecordsRequest searchRecordsRequest) {
        if (StringUtils.isBlank(searchRecordsRequest.getFieldName()) && StringUtils.isNotBlank(searchRecordsRequest.getFieldValue())) {
            return true;
        }
        return false;
    }

    private boolean isItemField(SearchRecordsRequest searchRecordsRequest) {
        if (StringUtils.isNotBlank(searchRecordsRequest.getFieldName())
                && (searchRecordsRequest.getFieldName().equalsIgnoreCase(RecapConstants.BARCODE) || searchRecordsRequest.getFieldName().equalsIgnoreCase(RecapConstants.CALL_NUMBER))) {
            return true;
        }
        return false;
    }

    private void searchAndSetResults(SearchRecordsRequest searchRecordsRequest) {
        boolean errorStatus=false;
        searchRecordsRequest.reset();
        searchRecordsRequest.setSearchResultRows(null);
        searchRecordsRequest.setShowResults(true);
        searchRecordsRequest.setSelectAll(false);

        SearchRecordsResponse searchRecordsResponse = searchUtil.requestSearchResults(searchRecordsRequest);
        searchRecordsRequest.setSearchResultRows(searchRecordsResponse.getSearchResultRows());
        searchRecordsRequest.setTotalRecordsCount(searchRecordsResponse.getTotalRecordsCount());
        searchRecordsRequest.setTotalBibRecordsCount(searchRecordsResponse.getTotalBibRecordsCount());
        searchRecordsRequest.setTotalItemRecordsCount(searchRecordsResponse.getTotalItemRecordsCount());
        searchRecordsRequest.setTotalPageCount(searchRecordsResponse.getTotalPageCount());
        searchRecordsRequest.setShowTotalCount(searchRecordsResponse.isShowTotalCount());
        searchRecordsRequest.setErrorMessage(searchRecordsResponse.getErrorMessage());

        if(CollectionUtils.isEmpty(searchRecordsRequest.getSearchResultRows())) {
            searchRecordsRequest.setTotalRecordsCount(String.valueOf(0));
            searchRecordsRequest.setTotalBibRecordsCount(String.valueOf(0));
            searchRecordsRequest.setTotalItemRecordsCount(String.valueOf(0));
            if (errorStatus){
                searchRecordsRequest.setErrorMessage(RecapConstants.SEARCH_RESULT_ERROR_INVALID_CHARACTERS);
            }else{
                if(searchRecordsRequest.getErrorMessage() == null ) {
                    searchRecordsRequest.setErrorMessage(RecapConstants.SEARCH_RESULT_ERROR_NO_RECORDS_FOUND);
                }
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1048576);
    }


}
