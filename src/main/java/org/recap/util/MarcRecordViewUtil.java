package org.recap.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.recap.RecapConstants;
import org.recap.model.jpa.*;
import org.recap.model.search.BibDataField;
import org.recap.model.search.BibliographicMarcForm;
import org.recap.model.userManagement.UserDetailsForm;
import org.recap.repository.jpa.BibliographicDetailsRepository;
import org.recap.repository.jpa.CustomerCodeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rajeshbabuk on 17/10/16.
 */
@Service
public class MarcRecordViewUtil {

    @Autowired
    BibliographicDetailsRepository bibliographicDetailsRepository;

    @Autowired
    CustomerCodeDetailsRepository customerCodeDetailsRepository;

    public BibliographicMarcForm buildBibliographicMarcForm(Integer bibId, Integer itemId,UserDetailsForm userDetailsForm) {
        BibliographicMarcForm bibliographicMarcForm = new BibliographicMarcForm();
        bibliographicMarcForm.setCollectionAction(RecapConstants.UPDATE_CGD);
        BibliographicEntity bibliographicEntity = bibliographicDetailsRepository.findByBibliographicIdAndIsDeletedFalse(bibId);
        if (null == bibliographicEntity) {
            bibliographicMarcForm.setErrorMessage(RecapConstants.RECORD_NOT_AVAILABLE);
        } else {
            InstitutionEntity institutionEntity = bibliographicEntity.getInstitutionEntity();
            bibliographicMarcForm.setAllowEdit(true);
            if(userDetailsForm!=null &&  !userDetailsForm.isSuperAdmin() && !userDetailsForm.getLoginInstitutionId().equals(institutionEntity.getInstitutionId())) {
                bibliographicMarcForm.setErrorMessage(RecapConstants.ACCESS_RESTRICTED);
                bibliographicMarcForm.setAllowEdit(false);
            }
                bibliographicMarcForm.setBibId(bibliographicEntity.getBibliographicId());
                String bibContent = new String(bibliographicEntity.getContent());
                BibJSONUtil bibJSONUtil = new BibJSONUtil();
                List<Record> records = bibJSONUtil.convertMarcXmlToRecord(bibContent);
                Record marcRecord = records.get(0);
                setBibliographicMarcFormWithBibValues(marcRecord, bibJSONUtil, bibliographicMarcForm);
                bibliographicMarcForm.setContent(bibContent);
                if (null != institutionEntity) {
                    bibliographicMarcForm.setOwningInstitution(institutionEntity.getInstitutionCode());
                }
                List<ItemEntity> nonDeletedItemEntities = bibliographicDetailsRepository.getNonDeletedItemEntities(bibliographicEntity.getOwningInstitutionId(), bibliographicEntity.getOwningInstitutionBibId());
                if (CollectionUtils.isNotEmpty(nonDeletedItemEntities)) {
                    if (nonDeletedItemEntities.size() == 1 && RecapConstants.MONOGRAPH.equals(bibliographicMarcForm.getLeaderMaterialType())) {
                        CollectionGroupEntity collectionGroupEntity = nonDeletedItemEntities.get(0).getCollectionGroupEntity();
                        if (null != collectionGroupEntity) {
                            bibliographicMarcForm.setMonographCollectionGroupDesignation(collectionGroupEntity.getCollectionGroupCode());
                        }
                    }
                    bibliographicMarcForm.setCallNumber(nonDeletedItemEntities.get(0).getCallNumber());
                    if (null != itemId) {
                        for (ItemEntity itemEntity : nonDeletedItemEntities) {
                            if (itemEntity.getItemId().intValue() == itemId) {
                                bibliographicMarcForm.setItemId(itemEntity.getItemId());
                                bibliographicMarcForm.setBarcode(itemEntity.getBarcode());
                                bibliographicMarcForm.setUseRestriction(itemEntity.getUseRestrictions());
                                bibliographicMarcForm.setCallNumber(itemEntity.getCallNumber());
                                bibliographicMarcForm.setCustomerCode(itemEntity.getCustomerCode());
                                ItemStatusEntity itemStatusEntity = itemEntity.getItemStatusEntity();
                                if (null != itemStatusEntity) {
                                    bibliographicMarcForm.setAvailability(itemStatusEntity.getStatusCode());
                                }
                                CollectionGroupEntity collectionGroupEntity = itemEntity.getCollectionGroupEntity();
                                if (null != collectionGroupEntity) {
                                    bibliographicMarcForm.setCollectionGroupDesignation(collectionGroupEntity.getCollectionGroupCode());
                                    bibliographicMarcForm.setNewCollectionGroupDesignation(collectionGroupEntity.getCollectionGroupCode());
                                    if (RecapConstants.SHARED_CGD.equals(bibliographicMarcForm.getCollectionGroupDesignation())) {
                                        bibliographicMarcForm.setShared(Boolean.TRUE);
                                    }
                                }
                                if (StringUtils.isNotBlank(bibliographicMarcForm.getAvailability())) {
                                    if (RecapConstants.AVAILABLE.equals(bibliographicMarcForm.getAvailability())) {
                                        bibliographicMarcForm.setDeaccessionType(RecapConstants.PERMANENT_WITHDRAWAL_DIRECT);
                                    } else if (RecapConstants.NOT_AVAILABLE.equals(bibliographicMarcForm.getAvailability())) {
                                        bibliographicMarcForm.setDeaccessionType(RecapConstants.PERMANENT_WITHDRAWAL_INDIRECT);
                                    }
                                }
                                CustomerCodeEntity customerCodeEntity = customerCodeDetailsRepository.findByCustomerCode(bibliographicMarcForm.getCustomerCode());
                                if (null != customerCodeEntity && StringUtils.isNotBlank(customerCodeEntity.getDeliveryRestrictions())) {
                                    List<String> deliveryLocations = new ArrayList<>();
                                    List<CustomerCodeEntity> customerCodeEntities = customerCodeDetailsRepository.findByCustomerCodeIn(Arrays.asList(customerCodeEntity.getDeliveryRestrictions().split(",")));
                                    if (CollectionUtils.isNotEmpty(customerCodeEntities)) {
                                        for (CustomerCodeEntity custCodeEntity : customerCodeEntities) {
                                            deliveryLocations.add(custCodeEntity.getDescription());
                                        }
                                    }
                                    bibliographicMarcForm.setDeliveryLocations(deliveryLocations);
                                }
                            }
                        }
                        if (null == bibliographicMarcForm.getItemId()) {
                            bibliographicMarcForm.setErrorMessage(RecapConstants.RECORD_NOT_AVAILABLE);
                        }
                    }
                }
        }
        return bibliographicMarcForm;
    }

    private void setBibliographicMarcFormWithBibValues(Record marcRecord, BibJSONUtil bibJSONUtil, BibliographicMarcForm bibliographicMarcForm) {
        bibliographicMarcForm.setTitle(bibJSONUtil.getTitleDisplay(marcRecord));
        bibliographicMarcForm.setAuthor(bibJSONUtil.getAuthorDisplayValue(marcRecord));
        bibliographicMarcForm.setPublisher(bibJSONUtil.getPublisherValue(marcRecord));
        bibliographicMarcForm.setPublishedDate(bibJSONUtil.getPublicationDateValue(marcRecord));
        bibliographicMarcForm.setLeaderMaterialType(bibJSONUtil.getLeaderMaterialType(marcRecord.getLeader()));
        bibliographicMarcForm.setTag000(bibJSONUtil.getLeader(marcRecord));
        bibliographicMarcForm.setControlNumber001(bibJSONUtil.getControlFieldValue(marcRecord, "001"));
        bibliographicMarcForm.setControlNumber005(bibJSONUtil.getControlFieldValue(marcRecord, "005"));
        bibliographicMarcForm.setControlNumber008(bibJSONUtil.getControlFieldValue(marcRecord, "008"));
        bibliographicMarcForm.setBibDataFields(buildBibDataFields(marcRecord));
    }

    private List<BibDataField> buildBibDataFields(Record marcRecord) {
        List<BibDataField> bibDataFields = new ArrayList<>();
        List<DataField> marcDataFields = marcRecord.getDataFields();
        if (!CollectionUtils.isEmpty(marcDataFields)) {
            for (DataField marcDataField : marcDataFields) {
                BibDataField bibDataField = new BibDataField();
                bibDataField.setDataFieldTag(marcDataField.getTag());
                if (Character.isWhitespace(marcDataField.getIndicator1())) {
                    bibDataField.setIndicator1('_');
                } else {
                    bibDataField.setIndicator1(marcDataField.getIndicator1());
                }
                if (Character.isWhitespace(marcDataField.getIndicator2())) {
                    bibDataField.setIndicator2('_');
                } else {
                    bibDataField.setIndicator2(marcDataField.getIndicator2());
                }
                List<Subfield> subfields = marcDataField.getSubfields();
                if (!CollectionUtils.isEmpty(subfields)) {
                    StringBuffer buffer = new StringBuffer();
                    for (Subfield subfield : subfields) {
                        buffer.append("|").append(subfield.getCode());
                        buffer.append(" ").append(subfield.getData()).append(" ");
                    }
                    bibDataField.setDataFieldValue(buffer.toString());
                }
                bibDataFields.add(bibDataField);
            }
        }
        return bibDataFields;
    }
}
