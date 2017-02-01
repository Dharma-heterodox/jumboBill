package org.recap.repository.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.RecapConstants;
import org.recap.model.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 28/10/16.
 */
public class RequestItemDetailsRepositoryUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    //Test for InterLibrary Requests

    @Test
    public void checkGetIlRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),4,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] requestingInstIds = {2,3};
        List<Integer> requestingInstIdsList = new ArrayList<>(Arrays.asList(requestingInstIds));
        long count = requestItemDetailsRepository.getIlRequestCounts(fromDate,toDate,1,requestingInstIdsList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetIlRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),3,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] requestingInstIds = {1,3};
        List<Integer> requestingInstIdsList = new ArrayList<>(Arrays.asList(requestingInstIds));
        long count = requestItemDetailsRepository.getIlRequestCounts(fromDate,toDate,2,requestingInstIdsList);
        assertNotNull(count);
        assertEquals(1,count);

    }

    @Test
    public void checkGetIlRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),2,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[]requestingInstIds= {1,2};
        List<Integer> requestingInstIdsList = new ArrayList<>(Arrays.asList(requestingInstIds));
        long count = requestItemDetailsRepository.getIlRequestCounts(fromDate,toDate,3,requestingInstIdsList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    //Test for Borrow Direct Requests

    @Test
    public void checkGetBDRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),5,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,1, RecapConstants.BORROW_DIRECT);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetBDRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),5,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,2,RecapConstants.BORROW_DIRECT);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetBDRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),5,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,3,RecapConstants.BORROW_DIRECT);
        assertNotNull(count);
        assertEquals(1,count);
    }

    // Test for Physical Requests

    @Test
    public void checkGetPhysicalPrivateRequestCountsForPUL() throws Exception{

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,1,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetPhysicalSharedAndOpenRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),5,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "BORROW DIRECT"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,1,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetPhysicalPrivateRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 3, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),5,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "BORROW DIRECT"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,2,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }


    @Test
    public void checkGetPhysicalSharedAndOpenRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),5,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "BORROW DIRECT"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,2,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetPhysicalPrivateRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 3, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),5,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "BORROW DIRECT"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,3,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetPhysicalSharedAndOpenRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),5,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"RETRIEVAL", "RECALL", "BORROW DIRECT"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,3,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    // Test for Edd Requests
    @Test
    public void checkGetEDDPrivateRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 3, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),4,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,1,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetEDDSharedAndOpenRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),4,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,1,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetEDDPrivateRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 3, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),4,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,2,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetEDDSharedAndOpenRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(3,3);
        saveRequestEntity(itemEntity.getItemId(),4,3,3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,2,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetEDDPrivateRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 3, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),4,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {3};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,3,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetEDDSharedAndOpenRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),4,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        Integer[] cgdId = {1,2};
        List<Integer> cgdIdList = new ArrayList<>(Arrays.asList(cgdId));
        String[] requestTypeId = {"EDD"};
        List<String> requestTypeIdList = new ArrayList<>(Arrays.asList(requestTypeId));
        long count = requestItemDetailsRepository.getPhysicalAndEDDCounts(fromDate,toDate,3,cgdIdList,requestTypeIdList);
        assertNotNull(count);
        assertEquals(1,count);
    }

    // Recall Requests

    @Test
    public void checkGetRecallRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),3,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,1, RecapConstants.RECALL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetRecallRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),3,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,2, RecapConstants.RECALL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetRecallRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),3,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,3, RecapConstants.RECALL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    // Test for Retrieval Requests
    @Test
    public void checkGetRetrievalRequestCountsForPUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 1, 1, "PUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),2,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,1, RecapConstants.RETRIEVAL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetRetrievalRequestCountsForCUL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 2, 1, "CUL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(1,1);
        saveRequestEntity(itemEntity.getItemId(),2,1,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,2, RecapConstants.RETRIEVAL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    @Test
    public void checkGetRetrievalRequestCountsForNYPL() throws Exception{
        BibliographicEntity bibliographicEntity = saveBibHoldingItemEntity(50000, 3, 1, "NYPL");
        ItemEntity itemEntity = bibliographicEntity.getItemEntities().get(0);
        savePatronEntity(2,2);
        saveRequestEntity(itemEntity.getItemId(),2,2,2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = simpleDateFormat.parse("2016-12-30 00:00:00");
        Date toDate = simpleDateFormat.parse("2020-12-31 23:59:59");
        long count = requestItemDetailsRepository.getBDHoldRecallRetrievalRequestCounts(fromDate,toDate,3, RecapConstants.RETRIEVAL);
        assertNotNull(count);
        assertEquals(1,count);
    }

    // Test for saving Bib , Holding , Item , RequestItem and Patron Entities

    private BibliographicEntity saveBibHoldingItemEntity(Integer itemid, Integer owningInstitutionId, Integer collectionGroupId, String ownInstItemId) throws Exception {
        Random random = new Random();
        String owningInstitutionBibId = String.valueOf(random.nextInt());
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent("Mock Bib Content".getBytes());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setCreatedBy("ut");
        bibliographicEntity.setLastUpdatedBy("ut");
        bibliographicEntity.setLastUpdatedDate(new Date());
        bibliographicEntity.setOwningInstitutionBibId(owningInstitutionBibId);
        bibliographicEntity.setOwningInstitutionId(owningInstitutionId);

        HoldingsEntity holdingsEntity = new HoldingsEntity();
        holdingsEntity.setContent("mock holdings".getBytes());
        holdingsEntity.setCreatedDate(new Date());
        holdingsEntity.setCreatedBy("ut");
        holdingsEntity.setLastUpdatedDate(new Date());
        holdingsEntity.setLastUpdatedBy("ut");
        holdingsEntity.setOwningInstitutionId(owningInstitutionId);
        holdingsEntity.setOwningInstitutionHoldingsId(String.valueOf(random.nextInt()));

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(itemid);
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
        itemEntity.setOwningInstitutionItemId(ownInstItemId);
        itemEntity.setDeleted(false);

        itemEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));

        bibliographicEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        bibliographicEntity.setItemEntities(Arrays.asList(itemEntity));

        BibliographicEntity savedBibliographicEntity = bibliographicDetailsRepository.saveAndFlush(bibliographicEntity);
        entityManager.refresh(savedBibliographicEntity);

        return savedBibliographicEntity;
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

    private void savePatronEntity(Integer patronId, Integer patronInstitutionID) throws Exception {
        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setPatronId(patronId);
        patronEntity.setInstitutionId(patronInstitutionID);
        patronEntity.setInstitutionIdentifier("test");
        patronEntity.setEmailId("testmail");
        patronDetailsRepository.save(patronEntity);
    }

}
