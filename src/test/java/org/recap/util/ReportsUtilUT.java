package org.recap.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.*;
import org.recap.model.search.DeaccessionItemResultsRow;
import org.recap.model.search.ReportsForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by akulak on 30/12/16.
 */
public class ReportsUtilUT extends BaseTestCase {

    @Autowired
    ReportsUtil reportsUtil;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void populateILBDCountsForRequest() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");

        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity( 1, 1,false);
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),4,2,2);

        BibliographicEntity bibliographicEntity1 = saveBibHoldingItemEntity( 2, 1,false);
        ItemEntity itemEntity1 = bibliographicEntity1.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity1.getItemId(),3,3,3);

        BibliographicEntity bibliographicEntity2 = saveBibHoldingItemEntity(3, 1,false);
        ItemEntity itemEntity2 = bibliographicEntity2.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity2.getItemId(),2,2,2);


        BibliographicEntity bibliographicEntity3 = saveBibHoldingItemEntity(1, 1,false);
        ItemEntity itemEntity3 = bibliographicEntity3.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity3.getItemId(),5,2,2);

        BibliographicEntity bibliographicEntity4 = saveBibHoldingItemEntity( 2, 1,false);
        ItemEntity itemEntity4 = bibliographicEntity4.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity4.getItemId(),5,3,3);

        BibliographicEntity bibliographicEntity5 = saveBibHoldingItemEntity( 3, 1,false);
        ItemEntity itemEntity5 = bibliographicEntity5.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity5.getItemId(),5,1,1);


        reportsUtil.populateILBDCountsForRequest(reportsForm,fromDate,toDate);
        assertEquals(1,reportsForm.getIlRequestPulCount());
        assertEquals(1,reportsForm.getIlRequestCulCount());
        assertEquals(1,reportsForm.getIlRequestNyplCount());
        assertEquals(1,reportsForm.getBdRequestPulCount());
        assertEquals(1,reportsForm.getBdRequestCulCount());
        assertEquals(1,reportsForm.getBdRequestNyplCount());
    }

    @Test
    public void populatePartnersCountForRequest() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");

        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(1, 3,false);
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),5,2,2);

        BibliographicEntity bibliographicEntity1 = saveBibHoldingItemEntity( 1, 1,false);
        ItemEntity itemEntity1 = bibliographicEntity1.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity1.getItemId(),5,2,2);

        BibliographicEntity bibliographicEntity2 = saveBibHoldingItemEntity( 2, 3,false);
        ItemEntity itemEntity2 = bibliographicEntity2.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity2.getItemId(),5,3,3);

        BibliographicEntity bibliographicEntity3 = saveBibHoldingItemEntity( 2, 1,false);
        ItemEntity itemEntity3 = bibliographicEntity3.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity3.getItemId(),5,3,3);

        BibliographicEntity bibliographicEntity4 = saveBibHoldingItemEntity(3, 3,false);
        ItemEntity itemEntity4 = bibliographicEntity4.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity4.getItemId(),5,1,1);

        BibliographicEntity bibliographicEntity5 = saveBibHoldingItemEntity( 3, 1,false);
        ItemEntity itemEntity5 = bibliographicEntity5.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity5.getItemId(),5,1,1);

        BibliographicEntity bibliographicEntity6 = saveBibHoldingItemEntity(1, 3,false);
        ItemEntity itemEntity6 = bibliographicEntity6.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity6.getItemId(),4,2,2);

        BibliographicEntity bibliographicEntity7 = saveBibHoldingItemEntity(1, 1,false);
        ItemEntity itemEntity7 = bibliographicEntity7.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity7.getItemId(),4,2,2);

        BibliographicEntity bibliographicEntity8 = saveBibHoldingItemEntity( 2, 3,false);
        ItemEntity itemEntity8 = bibliographicEntity8.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity8.getItemId(),4,3,3);

        BibliographicEntity bibliographicEntity9 = saveBibHoldingItemEntity(2, 1,false);
        ItemEntity itemEntity9 = bibliographicEntity9.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity9.getItemId(),4,3,3);

        BibliographicEntity bibliographicEntity10 = saveBibHoldingItemEntity(3, 3,false);
        ItemEntity itemEntity10 = bibliographicEntity10.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity10.getItemId(),4,1,1);

        BibliographicEntity bibliographicEntity11 = saveBibHoldingItemEntity( 3, 1,false);
        ItemEntity itemEntity11 = bibliographicEntity11.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity11.getItemId(),4,1,1);

        reportsUtil.populatePartnersCountForRequest(reportsForm,fromDate,toDate);
        assertEquals(1,reportsForm.getPhysicalPrivatePulCount());
        assertEquals(1,reportsForm.getPhysicalPrivateCulCount());
        assertEquals(1,reportsForm.getPhysicalPrivateNyplCount());
        assertEquals(1,reportsForm.getPhysicalSharedPulCount());
        assertEquals(1,reportsForm.getPhysicalSharedCulCount());
        assertEquals(1,reportsForm.getEddPrivatePulCount());
        assertEquals(1,reportsForm.getEddPrivateCulCount());
        assertEquals(1,reportsForm.getEddPrivateNyplCount());
        assertEquals(1,reportsForm.getEddSharedOpenPulCount());
        assertEquals(1,reportsForm.getEddSharedOpenCulCount());
        assertEquals(1,reportsForm.getEddSharedOpenNyplCount());

    }

    @Test
    public void populateRequestTypeInformation() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");

        reportsForm.setReportRequestType(Arrays.asList("Retrieval"));
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity( 1, 1,false);
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3, 3);
        saveRequestEntity(itemEntity.getItemId(), 2, 3, 3);

        BibliographicEntity bibliographicEntity1 = saveBibHoldingItemEntity( 2, 1,false);
        ItemEntity itemEntity1 = bibliographicEntity1.getItemEntities().get(0);
        savePatronEntity(1, 1);
        saveRequestEntity(itemEntity1.getItemId(), 2, 1, 1);

        BibliographicEntity bibliographicEntity2 = saveBibHoldingItemEntity( 3, 1,false);
        ItemEntity itemEntity2 = bibliographicEntity2.getItemEntities().get(0);
        savePatronEntity(2, 2);
        saveRequestEntity(itemEntity2.getItemId(), 2, 2, 2);

        reportsUtil.populateRequestTypeInformation(reportsForm, fromDate, toDate);
        assertEquals(1, reportsForm.getRetrievalRequestPulCount());
        assertEquals(1, reportsForm.getRetrievalRequestCulCount());
        assertEquals(1, reportsForm.getRetrievalRequestNyplCount());

        reportsForm.setReportRequestType(Arrays.asList("Recall"));
        BibliographicEntity bibliographicEntity3 = saveBibHoldingItemEntity(1, 1,false);
        ItemEntity itemEntity3 = bibliographicEntity3.getItemEntities().get(0);
        savePatronEntity(2, 2);
        saveRequestEntity(itemEntity3.getItemId(), 3, 2, 2);

        BibliographicEntity bibliographicEntity4 = saveBibHoldingItemEntity(2, 1,false);
        ItemEntity itemEntity4 = bibliographicEntity4.getItemEntities().get(0);
        savePatronEntity(1, 1);
        saveRequestEntity(itemEntity4.getItemId(), 3, 1, 1);

        BibliographicEntity bibliographicEntity5 = saveBibHoldingItemEntity(3, 1,false);
        ItemEntity itemEntity5 = bibliographicEntity5.getItemEntities().get(0);
        savePatronEntity(2, 2);
        saveRequestEntity(itemEntity5.getItemId(), 3, 2, 2);
        reportsUtil.populateRequestTypeInformation(reportsForm, fromDate, toDate);

        assertEquals(1, reportsForm.getRecallRequestPulCount());
        assertEquals(1, reportsForm.getRecallRequestCulCount());
        assertEquals(1, reportsForm.getRecallRequestNyplCount());
    }


    @Test
    public void populateAccessionDeaccessionItemCounts() throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String requestedFromDate=simpleDateFormat.format(new Date());
        String requestedToDate= simpleDateFormat.format(new Date());

        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setAccessionDeaccessionFromDate(requestedFromDate);
        reportsForm.setAccessionDeaccessionToDate(requestedToDate);

        reportsUtil.populateAccessionDeaccessionItemCounts(reportsForm);
        assertNotNull(reportsForm);
        assertNotNull(reportsForm.getAccessionOpenPulCount());
        assertNotNull(reportsForm.getAccessionOpenCulCount());
        assertNotNull(reportsForm.getAccessionOpenNyplCount());
        assertNotNull(reportsForm.getAccessionSharedPulCount());
        assertNotNull(reportsForm.getAccessionSharedCulCount());
        assertNotNull(reportsForm.getAccessionSharedNyplCount());
        assertNotNull(reportsForm.getAccessionPrivatePulCount());
        assertNotNull(reportsForm.getAccessionPrivateCulCount());
        assertNotNull(reportsForm.getAccessionPrivateNyplCount());
        assertNotNull(reportsForm.getDeaccessionOpenPulCount());
        assertNotNull(reportsForm.getDeaccessionOpenCulCount());
        assertNotNull(reportsForm.getDeaccessionOpenNyplCount());
        assertNotNull(reportsForm.getDeaccessionSharedPulCount());
        assertNotNull(reportsForm.getDeaccessionSharedCulCount());
        assertNotNull(reportsForm.getDeaccessionSharedNyplCount());
        assertNotNull(reportsForm.getDeaccessionPrivatePulCount());
        assertNotNull(reportsForm.getDeaccessionPrivateCulCount());
        assertNotNull(reportsForm.getDeaccessionPrivateNyplCount());
    }

    @Test
    public void populateCGDItemCounts() throws Exception {
        ReportsForm reportsForm = new ReportsForm();

        reportsUtil.populateCGDItemCounts(reportsForm);
        assertNotNull(reportsForm);
        assertNotNull(reportsForm.getOpenPulCgdCount());
        assertNotNull(reportsForm.getOpenCulCgdCount());
        assertNotNull(reportsForm.getOpenNyplCgdCount());
        assertNotNull(reportsForm.getSharedPulCgdCount());
        assertNotNull(reportsForm.getSharedCulCgdCount());
        assertNotNull(reportsForm.getSharedNyplCgdCount());
        assertNotNull(reportsForm.getPrivatePulCgdCount());
        assertNotNull(reportsForm.getPrivateCulCgdCount());
        assertNotNull(reportsForm.getPrivateNyplCgdCount());
    }

    @Test
    public void deaccessionReportFieldsInformation() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        reportsForm.setAccessionDeaccessionFromDate(simpleDateFormat.format(new Date()));
        reportsForm.setAccessionDeaccessionToDate(simpleDateFormat.format(new Date()));
        List<DeaccessionItemResultsRow> deaccessionItemResultsRowList = reportsUtil.deaccessionReportFieldsInformation(reportsForm);
        assertNotNull(deaccessionItemResultsRowList);
    }

    private BibliographicEntity saveBibHoldingItemEntity(Integer owningInstitutionId, Integer collectionGroupId, boolean isDeleted) throws Exception {
        Random random = new Random();

        File bibContentFile = getBibContentFile();
        File holdingsContentFile = getHoldingsContentFile();
        String sourceBibContent = FileUtils.readFileToString(bibContentFile, "UTF-8");
        String sourceHoldingsContent = FileUtils.readFileToString(holdingsContentFile, "UTF-8");

        String owningInstitutionBibId= String.valueOf(random.nextInt());
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setBibliographicId(new Random().nextInt());
        bibliographicEntity.setContent(sourceBibContent.getBytes());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setCreatedBy("ut");
        bibliographicEntity.setLastUpdatedBy("ut");
        bibliographicEntity.setLastUpdatedDate(new Date());
        bibliographicEntity.setOwningInstitutionBibId(owningInstitutionBibId);
        bibliographicEntity.setOwningInstitutionId(owningInstitutionId);

        HoldingsEntity holdingsEntity = new HoldingsEntity();
        holdingsEntity.setContent(sourceHoldingsContent.getBytes());
        holdingsEntity.setCreatedDate(new Date());
        holdingsEntity.setCreatedBy("ut");
        holdingsEntity.setLastUpdatedDate(new Date());
        holdingsEntity.setLastUpdatedBy("ut");
        holdingsEntity.setOwningInstitutionId(owningInstitutionId);
        holdingsEntity.setOwningInstitutionHoldingsId(String.valueOf(new Random().nextInt()));

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(new Random().nextInt());
        itemEntity.setBarcode("b3");
        itemEntity.setCustomerCode("c1");
        itemEntity.setCallNumber("cn1");
        itemEntity.setCallNumberType("ct1");
        itemEntity.setItemAvailabilityStatusId(1);
        itemEntity.setCopyNumber(1);
        itemEntity.setOwningInstitutionId(owningInstitutionId);
        itemEntity.setCollectionGroupId(collectionGroupId);
        itemEntity.setCreatedDate(new Date());
        itemEntity.setCreatedBy("ut");
        itemEntity.setLastUpdatedDate(new Date());
        itemEntity.setLastUpdatedBy("ut");
        itemEntity.setUseRestrictions("no");
        itemEntity.setVolumePartYear("v3");
        itemEntity.setOwningInstitutionItemId(String.valueOf(new Random().nextInt()));
        itemEntity.setDeleted(isDeleted);

        itemEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        holdingsEntity.setItemEntities(Arrays.asList(itemEntity));

        bibliographicEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        bibliographicEntity.setItemEntities(Arrays.asList(itemEntity));

        BibliographicEntity savedBibliographicEntity = bibliographicDetailsRepository.saveAndFlush(bibliographicEntity);
        entityManager.refresh(savedBibliographicEntity);
        return savedBibliographicEntity;
    }

    private void savePatronEntity(Integer patronId, Integer patronInstitutionID) throws Exception {
        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setPatronId(patronId);
        patronEntity.setInstitutionId(patronInstitutionID);
        patronEntity.setInstitutionIdentifier("test");
        patronEntity.setEmailId("testmail");
        patronDetailsRepository.save(patronEntity);
    }

    private void saveRequestEntity(Integer itemId, Integer requestTypeId, Integer requestingInstID, Integer patronID) throws Exception {
        RequestItemEntity requestItemEntity = new RequestItemEntity();
        requestItemEntity.setItemId(itemId);
        requestItemEntity.setRequestTypeId(requestTypeId);
        requestItemEntity.setRequestingInstitutionId(requestingInstID);
        requestItemEntity.setRequestExpirationDate(new Date());
        requestItemEntity.setCreatedDate(new Date());
        requestItemEntity.setLastUpdatedDate(new Date());
        requestItemEntity.setPatronId(patronID);
        requestItemEntity.setStopCode("s1");
        requestItemDetailsRepository.save(requestItemEntity);
    }


    public File getBibContentFile() throws URISyntaxException {
        URL resource = getClass().getResource("BibContent.xml");
        return new File(resource.toURI());
    }

    public File getHoldingsContentFile() throws URISyntaxException {
        URL resource = getClass().getResource("HoldingsContent.xml");
        return new File(resource.toURI());
    }


}
