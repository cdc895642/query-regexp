package com.rest.regexp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rest.regexp.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Integration tests using h2 database
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql("/data-test.sql")
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ContactService contactService;

  @Test
  public void getContactsByNameRegexp_correctUrlAndRequestParamAndMethod_returnContacts()
      throws Exception {
    //Arrange
    final String FIRST_NAME = "Aaaa Bbbb";
    final int RESULT_CONTACTS_SIZE = 1;
    final String TESTED_URL = "/hello/contacts?nameFilter=[SVPI].*";

    //Act && Assert
    MvcResult mvcResult = mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.contacts", hasSize(RESULT_CONTACTS_SIZE)))
        .andExpect(jsonPath("$.contacts[0].name").value(FIRST_NAME));
  }
}
