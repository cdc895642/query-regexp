package com.rest.regexp.service.filter;

import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;

/**
 * Base interface for process nameFilter
 */
public interface FilterProcessor {

  public String getPattern();

  public void setPattern(String pattern);

  public List<String> getExpressions();

  public List<String> process();
}
