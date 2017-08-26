package com.rest.regexp;

import com.rest.regexp.dto.ContactList;
import com.rest.regexp.model.Contact;
import java.util.ArrayList;
import java.util.List;

/**
 * Provide test data for tests
 */
public abstract class TestData {

  public static List<Contact> getListContacts(String... names) {
    List<Contact> contactList = new ArrayList<>();
    for (String name : names) {
      Contact contact = new Contact();
      contact.setName(name);
      contactList.add(contact);
    }
    return contactList;
  }

  public static ContactList getContactList(String... names){
    List<Contact> contactList = getListContacts(names);
    return new ContactList(){{setContacts(contactList);}};
  }
}
