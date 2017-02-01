package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.model.jpa.RequestItemEntity;
import org.recap.model.search.RequestForm;
import org.recap.repository.jpa.CustomerCodeDetailsRepository;
import org.recap.repository.jpa.InstitutionDetailsRepository;
import org.recap.repository.jpa.RequestTypeDetailsRepository;
import org.recap.util.RequestServiceUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by rajeshbabuk on 21/10/16.
 */
public class RequestControllerUT extends BaseControllerUT {
    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    RequestController requestController;

    @Mock
    RequestServiceUtil requestServiceUtil;

    @Mock
    InstitutionDetailsRepository institutionDetailsRepository;

    @Mock
    RequestTypeDetailsRepository requestTypeDetailsRepository;

    @Mock
    CustomerCodeDetailsRepository customerCodeDetailsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
    }

    @Test
    public void request() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/request")
                .param("model", String.valueOf(model)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertTrue(status == 200);
    }

    @Test
    public void searchRequests() throws Exception {
        RequestForm requestForm = new RequestForm();
        Page<RequestItemEntity> requestItemEntities = new PageImpl<RequestItemEntity>(new ArrayList<>());
        when(requestServiceUtil.searchRequests(requestForm)).thenReturn(requestItemEntities);
        ModelAndView modelAndView = requestController.searchRequests(requestForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void searchPrevious() throws Exception {
        RequestForm requestForm = new RequestForm();
        Page<RequestItemEntity> requestItemEntities = new PageImpl<RequestItemEntity>(new ArrayList<>());
        when(requestServiceUtil.searchRequests(requestForm)).thenReturn(requestItemEntities);
        ModelAndView modelAndView = requestController.searchPrevious(requestForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void searchNext() throws Exception {
        RequestForm requestForm = new RequestForm();
        Page<RequestItemEntity> requestItemEntities = new PageImpl<RequestItemEntity>(new ArrayList<>());
        when(requestServiceUtil.searchRequests(requestForm)).thenReturn(requestItemEntities);
        ModelAndView modelAndView = requestController.searchNext(requestForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void searchFirst() throws Exception {
        RequestForm requestForm = new RequestForm();
        Page<RequestItemEntity> requestItemEntities = new PageImpl<RequestItemEntity>(new ArrayList<>());
        when(requestServiceUtil.searchRequests(requestForm)).thenReturn(requestItemEntities);
        ModelAndView modelAndView = requestController.searchFirst(requestForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void searchLast() throws Exception {
        RequestForm requestForm = new RequestForm();
        Page<RequestItemEntity> requestItemEntities = new PageImpl<RequestItemEntity>(new ArrayList<>());
        when(requestServiceUtil.searchRequests(requestForm)).thenReturn(requestItemEntities);
        ModelAndView modelAndView = requestController.searchLast(requestForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void loadCreateRequest() throws Exception {
        when(institutionDetailsRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        when(requestTypeDetailsRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        when(customerCodeDetailsRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        ModelAndView modelAndView = requestController.loadCreateRequest(model,null);
        assertNotNull(modelAndView);
        assertEquals("request", modelAndView.getViewName());
    }

    @Test
    public void populateItem() throws Exception {
        RequestForm requestForm = new RequestForm();
        String response = requestController.populateItem(requestForm, bindingResult, model,null);
        assertNotNull(response);
    }
}
