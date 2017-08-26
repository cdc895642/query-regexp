package com.rest.regexp.service;


import com.rest.regexp.dto.ContactList;

public interface ContactService {

  public ContactList getContactsByNotMatchNamePattern(String pattern);
}
