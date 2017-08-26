package com.rest.regexp.repository.custom.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.model.Contact;
import com.rest.regexp.model.QContact;
import com.rest.regexp.repository.custom.ContactRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class ContactRepositoryImpl extends QueryDslRepositorySupport implements
    ContactRepositoryCustom {

  public ContactRepositoryImpl() {
    super(Contact.class);
  }

  @Override
  public List<Contact> findByNamePattern(List<BooleanExpression> listExpressions) {
    return from(QContact.contact).where(listExpressions.toArray(
        new Predicate[listExpressions.size()])).fetch();
  }


}
