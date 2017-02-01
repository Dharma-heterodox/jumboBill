package org.recap.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.recap.RecapConstants;
import org.recap.model.deAccession.DeAccessionRequest;
import org.recap.model.jpa.ItemChangeLogEntity;
import org.recap.model.jpa.ItemEntity;
import org.recap.model.search.BibliographicMarcForm;
import org.recap.repository.jpa.ItemChangeLogDetailsRepository;
import org.recap.repository.jpa.ItemDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeshbabuk on 19/10/16.
 */
@Service
public class CollectionServiceUtil {

    Logger logger = LoggerFactory.getLogger(CollectionServiceUtil.class);

    @Value("${server.protocol}")
    String serverProtocol;

    @Value("${scsb.url}")
    String scsbUrl;

    @Autowired
    ItemChangeLogDetailsRepository itemChangeLogDetailsRepository;

    @Autowired
    ItemDetailsRepository itemDetailsRepository;

    public void updateCGDForItem(BibliographicMarcForm bibliographicMarcForm) {
        String statusResponse = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity requestEntity = new HttpEntity<>(getHttpHeaders());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serverProtocol + scsbUrl + RecapConstants.SCSB_UPDATE_CGD_URL)
                    .queryParam(RecapConstants.CGD_UPDATE_ITEM_BARCODE, bibliographicMarcForm.getBarcode())
                    .queryParam(RecapConstants.OWNING_INSTITUTION, bibliographicMarcForm.getOwningInstitution())
                    .queryParam(RecapConstants.OLD_CGD, bibliographicMarcForm.getCollectionGroupDesignation())
                    .queryParam(RecapConstants.NEW_CGD, bibliographicMarcForm.getNewCollectionGroupDesignation())
                    .queryParam(RecapConstants.CGD_CHANGE_NOTES, bibliographicMarcForm.getCgdChangeNotes());

            ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, String.class);
            statusResponse = responseEntity.getBody();
            if (RecapConstants.SUCCESS.equals(statusResponse)) {
                bibliographicMarcForm.setSubmitted(true);
                bibliographicMarcForm.setMessage(RecapConstants.CGD_UPDATE_SUCCESSFUL);
            } else {
                bibliographicMarcForm.setErrorMessage(RecapConstants.CGD_UPDATE_FAILED + "-" + statusResponse.replace(RecapConstants.FAILURE + "-", ""));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            bibliographicMarcForm.setErrorMessage(RecapConstants.CGD_UPDATE_FAILED + "-" + e.getMessage());
        }
    }

    public void deAccessionItem(BibliographicMarcForm bibliographicMarcForm) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            DeAccessionRequest deAccessionRequest = new DeAccessionRequest();
            String itemBarcode = bibliographicMarcForm.getBarcode();
            deAccessionRequest.setItemBarcodes(Arrays.asList(itemBarcode));
            HttpEntity<DeAccessionRequest> requestEntity = new HttpEntity<>(deAccessionRequest, getHttpHeaders());
            String resultMessage = restTemplate.postForObject(serverProtocol + scsbUrl + RecapConstants.SCSB_DEACCESSION_URL, requestEntity, String.class);
            if (StringUtils.isNotBlank(resultMessage)) {
                if (resultMessage.contains(RecapConstants.SUCCESS)) {
                    String userName = RecapConstants.GUEST;
                    Date lastUpdatedDate = new Date();
                    List<ItemEntity> itemEntities = itemDetailsRepository.findByBarcode(itemBarcode);
                    saveItemChangeLogEntity(itemEntities, userName, lastUpdatedDate, RecapConstants.DEACCESSION, bibliographicMarcForm.getDeaccessionNotes());
                    bibliographicMarcForm.setSubmitted(true);
                    bibliographicMarcForm.setMessage(RecapConstants.DEACCESSION_SUCCESSFUL);
                } else if (resultMessage.contains(RecapConstants.REQUESTED_ITEM_DEACCESSIONED)) {
                    bibliographicMarcForm.setSubmitted(true);
                    String failureMessage = resultMessage.replace(RecapConstants.FAILURE + " -", "");
                    bibliographicMarcForm.setErrorMessage(RecapConstants.DEACCESSION_FAILED + " - " + failureMessage);
                } else {
                    String failureMessage = resultMessage.replace(RecapConstants.FAILURE + " -", "");
                    bibliographicMarcForm.setErrorMessage(RecapConstants.DEACCESSION_FAILED + " - " + failureMessage);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            bibliographicMarcForm.setErrorMessage(RecapConstants.DEACCESSION_FAILED + " - " + e.getMessage());
        }
    }

    private void saveItemChangeLogEntity(List<ItemEntity> itemEntities, String userName, Date lastUpdatedDate, String operationType, String notes) {
        if (CollectionUtils.isNotEmpty(itemEntities)) {
            for (ItemEntity itemEntity : itemEntities) {
                ItemChangeLogEntity itemChangeLogEntity = new ItemChangeLogEntity();
                itemChangeLogEntity.setUpdatedBy(userName);
                itemChangeLogEntity.setUpdatedDate(lastUpdatedDate);
                itemChangeLogEntity.setOperationType(operationType);
                itemChangeLogEntity.setRecordId(itemEntity.getItemId());
                itemChangeLogEntity.setNotes(notes);
                itemChangeLogDetailsRepository.save(itemChangeLogEntity);
            }
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        return headers;
    }
}