package org.recap.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.BibliographicEntity;
import org.recap.model.jpa.HoldingsEntity;
import org.recap.model.jpa.ItemEntity;
import org.recap.model.search.BibliographicMarcForm;
import org.recap.model.userManagement.UserDetailsForm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 17/10/16.
 */
public class MarcRecordViewUtilUT extends BaseTestCase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MarcRecordViewUtil marcRecordViewUtil;

    @Test
    public void buildBibliographicMarcForm() throws Exception {
        BibliographicEntity bibliographicEntity = getBibEntityWithHoldingsAndItem();
        BibliographicEntity savedBibliographicEntity = bibliographicDetailsRepository.saveAndFlush(bibliographicEntity);
        entityManager.refresh(savedBibliographicEntity);
        assertNotNull(savedBibliographicEntity);
        assertNotNull(savedBibliographicEntity.getHoldingsEntities());
        assertNotNull(savedBibliographicEntity.getItemEntities());

        Integer bibId = savedBibliographicEntity.getBibliographicId();
        Integer itemId = savedBibliographicEntity.getItemEntities().get(0).getItemId();

        UserDetailsForm userDetailsForm= new UserDetailsForm();
        userDetailsForm.setSuperAdmin(false);
        userDetailsForm.setLoginInstitutionId(2);
        userDetailsForm.setRecapUser(false);

        BibliographicMarcForm bibliographicMarcForm = marcRecordViewUtil.buildBibliographicMarcForm(bibId, itemId,userDetailsForm);
        assertNotNull(bibliographicMarcForm);
        assertNotNull(bibliographicMarcForm.getBibId());
        assertNotNull(bibliographicMarcForm.getItemId());
        assertEquals(bibId, bibliographicMarcForm.getBibId());
        assertEquals(itemId, bibliographicMarcForm.getItemId());
        assertEquals("al-Ḥuṭayʼah : fī sīratihi wa-nafsīyatihi wa-shiʻrihi /", bibliographicMarcForm.getTitle());
        assertEquals("Ḥāwī, Īlīyā Salīm.   ", bibliographicMarcForm.getAuthor());
        assertEquals("Dār al-Thaqāfah,", bibliographicMarcForm.getPublisher());
        assertEquals("1970.", bibliographicMarcForm.getPublishedDate());
        assertEquals("PUL", bibliographicMarcForm.getOwningInstitution());
        assertEquals("123", bibliographicMarcForm.getBarcode());

    }

    public BibliographicEntity getBibEntityWithHoldingsAndItem() throws Exception {
        Random random = new Random();
        File bibContentFile = getBibContentFile();
        File holdingsContentFile = getHoldingsContentFile();
        String sourceBibContent = FileUtils.readFileToString(bibContentFile, "UTF-8");
        String sourceHoldingsContent = FileUtils.readFileToString(holdingsContentFile, "UTF-8");

        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent(sourceBibContent.getBytes());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setLastUpdatedDate(new Date());
        bibliographicEntity.setCreatedBy("tst");
        bibliographicEntity.setLastUpdatedBy("tst");
        bibliographicEntity.setOwningInstitutionId(1);
        bibliographicEntity.setOwningInstitutionBibId(String.valueOf(random.nextInt()));
        bibliographicEntity.setDeleted(false);

        HoldingsEntity holdingsEntity = new HoldingsEntity();
        holdingsEntity.setContent(sourceHoldingsContent.getBytes());
        holdingsEntity.setCreatedDate(new Date());
        holdingsEntity.setLastUpdatedDate(new Date());
        holdingsEntity.setCreatedBy("tst");
        holdingsEntity.setLastUpdatedBy("tst");
        holdingsEntity.setOwningInstitutionId(1);
        holdingsEntity.setOwningInstitutionHoldingsId(String.valueOf(random.nextInt()));
        holdingsEntity.setDeleted(false);

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setLastUpdatedDate(new Date());
        itemEntity.setOwningInstitutionItemId(String.valueOf(random.nextInt()));
        itemEntity.setOwningInstitutionId(1);
        itemEntity.setBarcode("123");
        itemEntity.setCallNumber("x.12321");
        itemEntity.setCollectionGroupId(1);
        itemEntity.setCallNumberType("1");
        itemEntity.setCustomerCode("123");
        itemEntity.setCreatedDate(new Date());
        itemEntity.setCreatedBy("tst");
        itemEntity.setLastUpdatedBy("tst");
        itemEntity.setItemAvailabilityStatusId(1);
        itemEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        itemEntity.setDeleted(false);

        holdingsEntity.setItemEntities(Arrays.asList(itemEntity));
        bibliographicEntity.setHoldingsEntities(Arrays.asList(holdingsEntity));
        bibliographicEntity.setItemEntities(Arrays.asList(itemEntity));

        return bibliographicEntity;
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
