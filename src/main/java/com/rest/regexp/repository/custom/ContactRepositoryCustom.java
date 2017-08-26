package com.rest.regexp.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.model.Contact;
import java.util.List;

/**
 * Use to find records using QueryDsl
 */
public interface ContactRepositoryCustom {

  public List<Contact> findByNamePattern(List<BooleanExpression> listExpressions);
}
