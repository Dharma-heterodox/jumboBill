package org.recap.model.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.RecapConstants;
import org.recap.repository.jpa.ReportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by SheikS on 8/8/2016.
 */
public class ReportDetailRepositoryUT extends BaseTestCase {

    @Autowired
    private ReportDetailRepository reportDetailRepository;

    @Test
    public void testSaveReportEntity() {

        ReportEntity savedReportEntity = saveReportEntity();

        assertNotNull(savedReportEntity);
        assertNotNull(savedReportEntity.getRecordNumber());
        List<ReportDataEntity> savedReportDataEntities = savedReportEntity.getReportDataEntities();
        for (Iterator<ReportDataEntity> iterator = savedReportDataEntities.iterator(); iterator.hasNext(); ) {
            ReportDataEntity savedReportDataEntity = iterator.next();
            System.out.println(savedReportDataEntity.getHeaderName() + " : " + savedReportDataEntity.getHeaderValue());
        }
    }

    @Test
    public void saveAndFindReportEntity() {
        ReportEntity reportEntity = saveReportEntity();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileName(reportEntity.getFileName());

        assertNotNull(reportEntities);
        ReportEntity reportEntity1 = reportEntities.get(0);
        assertNotNull(reportEntity1);
        assertNotNull(reportEntity1.getReportDataEntities());
    }

    private ReportEntity saveReportEntity() {
        List<ReportDataEntity> reportDataEntities = new ArrayList<>();

        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setFileName(RecapConstants.MATCHING_ALGO_FULL_FILE_NAME);
        reportEntity.setCreatedDate(new Date());
        reportEntity.setType(RecapConstants.MATCHING_TYPE);
        reportEntity.setInstitutionName(RecapConstants.ALL_INST);

        ReportDataEntity reportDataEntity1 = new ReportDataEntity();
        reportDataEntity1.setHeaderName("BibId");
        reportDataEntity1.setHeaderValue("1");
        reportDataEntities.add(reportDataEntity1);

        ReportDataEntity reportDataEntity2 = new ReportDataEntity();
        reportDataEntity2.setHeaderName("InstitutionId");
        reportDataEntity2.setHeaderValue("PUL");
        reportDataEntities.add(reportDataEntity2);

        ReportDataEntity reportDataEntity3 = new ReportDataEntity();
        reportDataEntity3.setHeaderName("Barcode");
        reportDataEntity3.setHeaderValue("103");
        reportDataEntities.add(reportDataEntity3);

        ReportDataEntity reportDataEntity4 = new ReportDataEntity();
        reportDataEntity4.setHeaderName("Oclc");
        reportDataEntity4.setHeaderValue("213654");
        reportDataEntities.add(reportDataEntity4);

        ReportDataEntity reportDataEntity5 = new ReportDataEntity();
        reportDataEntity5.setHeaderName("UseRestrictions");
        reportDataEntity5.setHeaderValue("In Library Use");
        reportDataEntities.add(reportDataEntity5);

        ReportDataEntity reportDataEntity6 = new ReportDataEntity();
        reportDataEntity6.setHeaderName("SummaryHoldings");
        reportDataEntity6.setHeaderValue("no.1 18292938");
        reportDataEntities.add(reportDataEntity6);

        reportEntity.setReportDataEntities(reportDataEntities);

        return reportDetailRepository.save(reportEntity);
    }

    @Test
    public void testFindByFileAndDateRange() throws Exception {
        ReportEntity reportEntity = saveReportEntity();
        Calendar cal = Calendar.getInstance();
        Date from = reportEntity.getCreatedDate();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        from = cal.getTime();
        Date to = reportEntity.getCreatedDate();
        cal.setTime(to);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        to = cal.getTime();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileAndDateRange(reportEntity.getFileName(), from, to);
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByFileAndType() throws Exception {
        ReportEntity reportEntity = saveReportEntity();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileNameAndType(reportEntity.getFileName(), reportEntity.getType());
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByTypeAndDateRange() throws Exception {
        ReportEntity reportEntity = saveReportEntity();
        Calendar cal = Calendar.getInstance();
        Date from = reportEntity.getCreatedDate();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        from = cal.getTime();
        Date to = reportEntity.getCreatedDate();
        cal.setTime(to);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        to = cal.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        List<ReportEntity> reportEntities = reportDetailRepository.findByTypeAndDateRange(reportEntity.getType(), simpleDateFormat.format(from), simpleDateFormat.format(to));
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByFileAndTypeAndDateRange() throws Exception {
        ReportEntity reportEntity = saveReportEntity();
        Calendar cal = Calendar.getInstance();
        Date from = reportEntity.getCreatedDate();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        from = cal.getTime();
        Date to = reportEntity.getCreatedDate();
        cal.setTime(to);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        to = cal.getTime();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileAndTypeAndDateRange(reportEntity.getFileName(), reportEntity.getType(), from, to);
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByFileNameAndInstitutionName() throws Exception {
        ReportEntity reportEntity = saveReportEntity();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileNameAndInstitutionName(reportEntity.getFileName(), reportEntity.getInstitutionName());
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByFileNameAndInstitutionNameAndType() throws Exception {
        ReportEntity reportEntity = saveReportEntity();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileNameAndInstitutionNameAndType(reportEntity.getFileName(), reportEntity.getInstitutionName(), reportEntity.getType());
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByFileAndInstitutionAndTypeAndDateRange() throws Exception {
        ReportEntity reportEntity = saveReportEntity();
        Calendar cal = Calendar.getInstance();
        Date from = reportEntity.getCreatedDate();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        from = cal.getTime();
        Date to = reportEntity.getCreatedDate();
        cal.setTime(to);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        to = cal.getTime();

        List<ReportEntity> reportEntities = reportDetailRepository.findByFileAndInstitutionAndTypeAndDateRange(reportEntity.getFileName(), reportEntity.getInstitutionName(), reportEntity.getType(), from, to);
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

    @Test
    public void testFindByInstitutionAndTypeAndDateRange() throws Exception {
        ReportEntity reportEntity = saveReportEntity();
        Calendar cal = Calendar.getInstance();
        Date from = reportEntity.getCreatedDate();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        from = cal.getTime();
        Date to = reportEntity.getCreatedDate();
        cal.setTime(to);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        to = cal.getTime();

        List<ReportEntity> reportEntities = reportDetailRepository.findByInstitutionAndTypeAndDateRange(reportEntity.getInstitutionName(), reportEntity.getType(), from, to);
        assertNotNull(reportEntities);
        assertNotNull(reportEntities.get(0));
    }

}