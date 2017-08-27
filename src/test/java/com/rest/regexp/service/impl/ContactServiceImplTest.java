package com.rest.regexp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.rest.regexp.TestData;
import com.rest.regexp.dto.ContactList;
import com.rest.regexp.model.Contact;
import com.rest.regexp.repository.ContactRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContactServiceImplTest {

  @Mock
  private ContactRepository contactRepository;
  @InjectMocks
  private ContactServiceImpl contactService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getContactsByNotMatchNamePattern_patternNotEqualAnyEntity_returnEmptyList(){
    //Arrange
    final List<Contact> EXPECTED_CONTACTS = new ArrayList<>();
    when(contactRepository.findByNamePattern(anyList())).thenReturn(EXPECTED_CONTACTS);

    //Act
    ContactList contactList=contactService.getContactsByNotMatchNamePattern(anyString());

    //Assert
    assertEquals(EXPECTED_CONTACTS, contactList.getContacts());
  }

  @Test
  public void getContactsByNotMatchNamePattern_patternWiderThanList_returnListAsExpectedList(){
    //Arrange
    final List<Contact> DB_CONTACTS = TestData.getListContacts("Vasia", "Petya","Nata");
    final List<Contact> EXPECTED_CONTACTS = TestData.getListContacts("Vasia");
    final String PATTERN=".*t.*";
    when(contactRepository.findByNamePattern(anyList())).thenReturn(DB_CONTACTS);

    //Act
    ContactList contactList=contactService.getContactsByNotMatchNamePattern(PATTERN);

    //Assert
    assertEquals(EXPECTED_CONTACTS, contactList.getContacts());
  }

  @Test
  public void getContactsByNotMatchNamePattern_patternForDbList_returnListAsExpectedList(){
    //Arrange
    final List<Contact> EXPECTED_DB_CONTACTS = TestData.getListContacts("Vasia", "Petya");
    final String PATTERN=".*b.*";
    when(contactRepository.findByNamePattern(anyList())).thenReturn(EXPECTED_DB_CONTACTS);

    //Act
    ContactList contactList=contactService.getContactsByNotMatchNamePattern(PATTERN);

    //Assert
    assertEquals(EXPECTED_DB_CONTACTS, contactList.getContacts());
  }
}
