package com.rest.regexp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rest.regexp.TestData;
import com.rest.regexp.dto.ContactList;
import com.rest.regexp.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

  @MockBean
  private ContactService contactService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getContactsByNameRegexp_correctUrlAndRequestParamAndMethod_returnContacts()
      throws Exception {
    //Arrange
    final String FIRST_NAME = "Vanya";
    final String SECOND_NAME = "Petya";
    final int RESULT_CONTACTS_SIZE = 2;
    final String TESTED_URL = "/hello/contacts?nameFilter=filter";
    final int EXPECTED_NUMBER_OF_INVOCATION = 1;
    ContactList contactList = TestData.getContactList(FIRST_NAME, SECOND_NAME);
    given(contactService.getContactsByNotMatchNamePattern(anyString()))
        .willReturn(contactList);

    //Act && Assert
    MvcResult mvcResult = mockMvc.perform(get(TESTED_URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.contacts", hasSize(RESULT_CONTACTS_SIZE)))
        .andExpect(jsonPath("$.contacts[0].name").value(FIRST_NAME))
        .andExpect(jsonPath("$.contacts[1].name").value(SECOND_NAME));

    //Assert
    verify(contactService, times(EXPECTED_NUMBER_OF_INVOCATION))
        .getContactsByNotMatchNamePattern(anyString());
    verifyNoMoreInteractions(contactService);
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
