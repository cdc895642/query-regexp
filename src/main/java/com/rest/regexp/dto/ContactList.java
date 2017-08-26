package com.rest.regexp.dto;

import com.rest.regexp.model.Contact;
import java.util.List;

/**
 * Object that return to user as response to request
 */
public class ContactList {
  private List<Contact> contacts;

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
}
