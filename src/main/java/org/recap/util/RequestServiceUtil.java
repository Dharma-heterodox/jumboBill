package org.recap.util;

import org.apache.commons.lang3.StringUtils;
import org.recap.RecapConstants;
import org.recap.model.jpa.RequestItemEntity;
import org.recap.model.search.RequestForm;
import org.recap.repository.jpa.RequestItemDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by rajeshbabuk on 29/10/16.
 */
@Service
public class RequestServiceUtil {

    Logger logger = LoggerFactory.getLogger(RequestServiceUtil.class);

    @Autowired
    RequestItemDetailsRepository requestItemDetailsRepository;

    public Page<RequestItemEntity> searchRequests(RequestForm requestForm) {
        String patronBarcode = StringUtils.isNotBlank(requestForm.getPatronBarcode()) ? requestForm.getPatronBarcode().trim() : requestForm.getPatronBarcode();
        String itemBarcode = StringUtils.isNotBlank(requestForm.getItemBarcode()) ? requestForm.getItemBarcode().trim() : requestForm.getItemBarcode();
        Pageable pageable = new PageRequest(requestForm.getPageNumber(), requestForm.getPageSize(), Sort.Direction.ASC, RecapConstants.REQUEST_ID);

        Page<RequestItemEntity> requestItemEntities;
        if (StringUtils.isNotBlank(patronBarcode) && StringUtils.isNotBlank(itemBarcode)) {
            requestItemEntities = requestItemDetailsRepository.findByPatronBarcodeAndItemBarcode(pageable, patronBarcode, itemBarcode);
        } else if (StringUtils.isNotBlank(patronBarcode) && StringUtils.isBlank(itemBarcode)) {
            requestItemEntities = requestItemDetailsRepository.findByPatronBarcode(pageable, patronBarcode);
        } else if (StringUtils.isBlank(patronBarcode) && StringUtils.isNotBlank(itemBarcode)) {
            requestItemEntities = requestItemDetailsRepository.findByItemBarcode(pageable, itemBarcode);
        } else {
            requestItemEntities = requestItemDetailsRepository.findAll(pageable);
        }
        return requestItemEntities;
    }
}
