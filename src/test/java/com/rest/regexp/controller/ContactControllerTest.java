package com.rest.regexp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rest.regexp.TestData;
import com.rest.regexp.dto.ContactList;
import com.rest.regexp.model.Contact;
import com.rest.regexp.service.ContactService;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

  @MockBean
  private ContactService groupService;

  @Autowired
  private MockMvc mockMvc;

  private List<Contact>  contacts;

  @Test
  public void getGroupsFromUserLocation_registeredUserLocationWithGroups_returnGroups()
      throws Exception {
    //Arrange
    final String FIRST_NAME = "Vanya";
    final String SECOND_NAME = "Petya";
    final int RESULT_SET_SIZE = 2;
    final String TESTED_URL = "/groups/mylocation";
    final int EXPECTED_NUMBER_OF_INVOCATION = 1;
    ContactList contactList = TestData.getContactList(FIRST_NAME, SECOND_NAME);
    given(groupService.getContactsByNotMatchNamePattern(anyString()))
        .willReturn(contactList);
//
//    //Act && Assert
//    mockMvc.perform(get(TESTED_URL)
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$", hasSize(RESULT_SET_SIZE)))
//        .andExpect(jsonPath("$[0].name").value(FIRST_GROUP))
//        .andExpect(jsonPath("$[1].name").value(SECOND_GROUP));
//
//    //Assert
//    verify(groupService, times(EXPECTED_NUMBER_OF_INVOCATION))
//        .getGroupResourcesFromUserLocation(anyString());
//    verifyNoMoreInteractions(groupService);
  }
}
