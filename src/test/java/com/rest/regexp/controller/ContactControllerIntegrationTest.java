package com.rest.regexp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Sql("/data-test.sql")
  @Sql(scripts = "/clean-table-test.sql",
      executionPhase = AFTER_TEST_METHOD)
  @Test
  public void getContactsByNameRegexp_useFilterWithSeries_returnContacts()
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

  @Sql("/data-test.sql")
  @Sql(scripts = "/clean-table-test.sql",
      executionPhase = AFTER_TEST_METHOD)
  @Test
  public void getContactsByNameRegexp_useFilterWithLetter_returnContacts()
      throws Exception {
    //Arrange
    final String FIRST_NAME = "Petrov Petr";
    final int RESULT_CONTACTS_SIZE = 1;
    final String TESTED_URL = "/hello/contacts?nameFilter=.*a.*";

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

  @Sql("/data-test.sql")
  @Sql(scripts = "/clean-table-test.sql",
      executionPhase = AFTER_TEST_METHOD)
  @Test
  public void getContactsByNameRegexp_useFilterWithLetterAndSeries_returnContacts()
      throws Exception {
    //Arrange
    final int RESULT_CONTACTS_SIZE = 4;
    final String TESTED_URL = "/hello/contacts?nameFilter=A.*[btr]";

    //Act && Assert
    MvcResult mvcResult = mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.contacts", hasSize(RESULT_CONTACTS_SIZE)));
  }

  @Sql("/data-test.sql")
  @Sql(scripts = "/clean-table-test.sql",
      executionPhase = AFTER_TEST_METHOD)
  @Test
  public void getContactsByNameRegexp_useFilterWithIllegalCharacters_returnBadRequestStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/contacts?nameFilter=A.*([bt-r])";
    final int EXPECTED_HTTP_STATUS = 400;

    //Act && Assert
    MvcResult mvcResult = mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }

  @Test
  public void getContactsByNameRegexp_correctAllAndEmptyRequestParam_returnBadRequestStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/contacts?nameFilter";
    final int EXPECTED_HTTP_STATUS = 400;

    //Act && Assert
    mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }

  @Test
  public void getContactsByNameRegexp_WithoutRequestParam_returnBadRequestStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/contacts";
    final int EXPECTED_HTTP_STATUS = 400;

    //Act && Assert
    mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }

  @Test
  public void getContactsByNameRegexp_correctUrlAndRequestParamAndNotCorrectMethod_returnMethodNotAllowedStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/contacts?nameFilter=filter";
    final int EXPECTED_HTTP_STATUS = 405;

    //Act && Assert
    mockMvc.perform(post(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }

  @Test
  public void getContactsByNameRegexp_correctUrlAndMethodAndNotCorrectRequestParam_returnBadRequestStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/contacts?contactFilter=filter";
    final int EXPECTED_HTTP_STATUS = 400;

    //Act && Assert
    mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }

  @Test
  public void getContactsByNameRegexp_notCorrectUrl_returnNotFoundStatus()
      throws Exception {
    //Arrange
    final String TESTED_URL = "/hello/wrong-contacts?contactFilter=filter";
    final int EXPECTED_HTTP_STATUS = 404;

    //Act && Assert
    mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(EXPECTED_HTTP_STATUS));
  }
}
