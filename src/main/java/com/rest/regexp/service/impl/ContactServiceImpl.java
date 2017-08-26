package com.rest.regexp.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.dto.ContactList;
import com.rest.regexp.model.Contact;
import com.rest.regexp.repository.ContactRepository;
import com.rest.regexp.service.ContactService;
import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorBase;
import com.rest.regexp.service.filter.impl.SeriesFilterProcessorDecorator;
import com.rest.regexp.service.filter.impl.TrimFilterProcessorDecorator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

  private ContactRepository contactRepository;

  @Autowired
  public void setContactRepository(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public ContactList getContactsByNotMatchNamePattern(String pattern) {
    FilterProcessor filterProcessor = new FilterProcessorBase(pattern);
    TrimFilterProcessorDecorator trimProcessor=new TrimFilterProcessorDecorator(filterProcessor);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        trimProcessor);
    List<BooleanExpression> listExpressions = seriesProcessor.process();
    List<Contact> contacts = contactRepository.findByNamePattern(listExpressions);
    return new ContactList() {{
      setContacts(filterContacts(contacts,pattern));
    }};
  }

  private List<Contact> filterContacts(List<Contact> contacts, String pattern) {
    return contacts.stream().filter(c -> !c.getName().matches(pattern))
        .collect(Collectors.toList());
  }
}
