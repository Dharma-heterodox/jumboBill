package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.model.search.BibliographicMarcForm;
import org.recap.model.search.CollectionForm;
import org.recap.model.userManagement.UserDetailsForm;
import org.recap.util.MarcRecordViewUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by rajeshbabuk on 12/10/16.
 */
public class CollectionControllerUT extends BaseControllerUT {

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    CollectionController collectionController;

    @Mock
    MarcRecordViewUtil marcRecordViewUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(collectionController).build();
    }

    @Test
    public void collection() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/collection")
                .param("model", String.valueOf(model)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertTrue(status == 200);
    }

    @Test
    public void displayRecords() throws Exception {
        CollectionForm collectionForm = new CollectionForm();
        collectionForm.setItemBarcodes("");
        ModelAndView modelAndView = collectionController.displayRecords(collectionForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("searchRecords", modelAndView.getViewName());
    }

    @Test
    public void openMarcRecord() throws Exception {
        CollectionForm collectionForm = new CollectionForm();
        UserDetailsForm userDetailsForm= new UserDetailsForm();
        userDetailsForm.setSuperAdmin(false);
        userDetailsForm.setLoginInstitutionId(2);
        userDetailsForm.setRecapUser(false);
        when(marcRecordViewUtil.buildBibliographicMarcForm(collectionForm.getBibId(), collectionForm.getItemId(),userDetailsForm)).thenReturn(new BibliographicMarcForm());
        ModelAndView modelAndView = collectionController.openMarcView(collectionForm, bindingResult, model,null);
        assertNotNull(modelAndView);
        assertEquals("collection :: #collectionUpdateModal", modelAndView.getViewName());
    }

    @Test
    public void collectionUpdate() throws Exception {
        CollectionForm collectionForm = new CollectionForm();
        ModelAndView modelAndView = collectionController.collectionUpdate(collectionForm, bindingResult, model);
        assertNotNull(modelAndView);
        assertEquals("collection :: #itemDetailsSection", modelAndView.getViewName());
    }
}
