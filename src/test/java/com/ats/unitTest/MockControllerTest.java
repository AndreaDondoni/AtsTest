package com.ats.unitTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ats.test.ApplicationInitializer;
import com.ats.test.controller.AtmService;
import com.ats.test.controller.ServiceController;
import com.ats.test.model.SearchCriteria;
import com.google.gson.Gson;

/**
 * @author Adondoni
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationInitializer.class)
public class MockControllerTest 
{	
    private MockMvc mockMvc;
    
    @InjectMocks
    private AtmService atmService;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ServiceController(atmService)).build();
    }
    
    @Test
    public void testListService() throws Exception 
    {
    	this.mockMvc.perform(get("/AtmProject/rest/atm/list")
    			.contextPath("/AtmProject")
    			.servletPath("/rest")
        	    .accept(MediaType.APPLICATION_JSON))
        	    .andExpect(status().isOk());
    }
    
    @Test
    public void testSearchService() throws Exception 
    {
    	Gson gson = new Gson();
    	
    	SearchCriteria search = new SearchCriteria();
    	search.setText("Van Nesstraat");
        String json = gson.toJson(search);
    	
		mockMvc.perform(post("/AtmProject/rest/atm/search").contextPath("/AtmProject").servletPath("/rest")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json))
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.result[0].address.street").value("Van Nesstraat"))
				.andExpect(jsonPath("$.result[0].address.city").value("Haarlem"))
				.andExpect(jsonPath("$.result[0]..type").value("ING"));
    }
    
    @Test
    public void testSearchService_notFound() throws Exception 
    {
    	Gson gson = new Gson();
    	
    	SearchCriteria search = new SearchCriteria();
    	search.setText("xxxx");
        String json = gson.toJson(search);
    	
		mockMvc.perform(post("/AtmProject/rest/atm/search").contextPath("/AtmProject").servletPath("/rest")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json))
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("no ATMs Point found!"));
    }
}
