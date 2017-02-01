package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.RecapConstants;
import org.recap.model.search.DeaccessionItemResultsRow;
import org.recap.model.search.ReportsForm;
import org.recap.repository.jpa.RequestItemDetailsRepository;
import org.recap.util.ReportsUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by rajeshbabuk on 21/10/16.
 */
public class ReportsControllerUT extends BaseControllerUT {

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    ReportsController reportsController;

    @Mock
    RequestItemDetailsRepository requestItemDetailsRepository;

    @Mock
    ReportsUtil reportsUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(reportsController).build();
    }

    @Test
    public void reports() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/reports")
                .param("model", String.valueOf(model)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertTrue(status == 200);
    }

    @Test
    public void ilBdRequest() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setRequestType(RecapConstants.REPORTS_REQUEST);
        reportsForm.setRequestFromDate("11/01/2016");
        reportsForm.setRequestToDate("12/01/2016");
        reportsForm.setShowBy(RecapConstants.REPORTS_IL_BD);
        ModelAndView modelAndView = reportsController.reportCounts(reportsForm,model);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RecapConstants.SIMPLE_DATE_FORMAT_REPORTS);
        String fromDate = reportsForm.getRequestFromDate();
        String toDate = reportsForm.getRequestToDate();
        Date requestFromDate = simpleDateFormat.parse(fromDate);
        Date requestToDate = simpleDateFormat.parse(toDate);
        reportsUtil.populateILBDCountsForRequest(reportsForm, requestFromDate, requestToDate);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void partnersRequest() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setRequestType(RecapConstants.REPORTS_REQUEST);
        reportsForm.setRequestFromDate("11/01/2016");
        reportsForm.setRequestToDate("12/01/2016");
        reportsForm.setShowBy(RecapConstants.REPORTS_PARTNERS);
        ModelAndView modelAndView = reportsController.reportCounts(reportsForm,model);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RecapConstants.SIMPLE_DATE_FORMAT_REPORTS);
        String fromDate = reportsForm.getRequestFromDate();
        String toDate = reportsForm.getRequestToDate();
        Date requestFromDate =simpleDateFormat.parse(fromDate);
        Date requestToDate = simpleDateFormat.parse(toDate);
        reportsUtil.populatePartnersCountForRequest(reportsForm, requestFromDate, requestToDate);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void requestType() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setRequestType(RecapConstants.REPORTS_REQUEST);
        reportsForm.setRequestFromDate("11/01/2016");
        reportsForm.setRequestToDate("12/01/2016");
        reportsForm.setShowBy(RecapConstants.REPORTS_REQUEST_TYPE);
        ModelAndView modelAndView = reportsController.reportCounts(reportsForm,model);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(RecapConstants.SIMPLE_DATE_FORMAT_REPORTS);
        String fromDate = reportsForm.getRequestFromDate();
        String toDate = reportsForm.getRequestToDate();
        Date requestFromDate =simpleDateFormat.parse(fromDate);
        Date requestToDate = simpleDateFormat.parse(toDate);
        reportsUtil.populateRequestTypeInformation(reportsForm, requestFromDate, requestToDate);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void accessionDeaccessionReports() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        reportsForm.setRequestType(RecapConstants.REPORTS_REQUEST);
        reportsForm.setRequestFromDate("11/01/2016");
        reportsForm.setRequestToDate("12/01/2016");
        reportsForm.setShowBy(RecapConstants.REPORTS_ACCESSION_DEACCESSION);
        String fromDate = reportsForm.getAccessionDeaccessionFromDate();
        String toDate = reportsForm.getAccessionDeaccessionToDate();
        ModelAndView modelAndView = reportsController.reportCounts(reportsForm,model);
        reportsUtil.populateAccessionDeaccessionItemCounts(reportsForm);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }


    @Test
    public void cgdCounts() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        ModelAndView modelAndView = reportsController.cgdCounts(reportsForm,model);
        reportsUtil.populateCGDItemCounts(reportsForm);
        assertNotNull(modelAndView);
        assertEquals("reports :: #cgdTable",modelAndView.getViewName());
    }

    @Test
    public void deaccessionInformation() throws Exception {
        ReportsForm reportsForm = new ReportsForm();
        List<DeaccessionItemResultsRow> deaccessionItemResultsRowList = new ArrayList<>();
        ModelAndView modelAndView = reportsController.deaccessionInformation(reportsForm,model);
        when((reportsUtil.deaccessionReportFieldsInformation(reportsForm))).thenReturn(deaccessionItemResultsRowList);
        assertNotNull(deaccessionItemResultsRowList);
        assertNotNull(modelAndView);
        assertEquals("reports :: #deaccessionInformation",modelAndView.getViewName());

    }

    @Test
    public void searchPrevious() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        ModelAndView modelAndView = reportsController.searchPrevious(reportsForm,model);
        assertNotNull(modelAndView);
        assertEquals("reports :: #deaccessionInformation",modelAndView.getViewName());
    }

    @Test
    public void searchNext() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        ModelAndView modelAndView = reportsController.searchNext(reportsForm,model);
        assertNotNull(modelAndView);
        assertEquals("reports :: #deaccessionInformation",modelAndView.getViewName());
    }

    @Test
    public void searchFirst() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        ModelAndView modelAndView = reportsController.searchFirst(reportsForm,model);
        assertNotNull(modelAndView);
        assertEquals("reports :: #deaccessionInformation",modelAndView.getViewName());
    }

    @Test
    public void searchLast() throws Exception{
        ReportsForm reportsForm = new ReportsForm();
        ModelAndView modelAndView = reportsController.searchLast(reportsForm,model);
        assertNotNull(modelAndView);
        assertEquals("reports :: #deaccessionInformation",modelAndView.getViewName());
    }
}
