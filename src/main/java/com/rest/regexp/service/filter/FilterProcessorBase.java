package com.rest.regexp.service.filter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.service.filter.FilterProcessor;
import java.util.ArrayList;
import java.util.List;


/**
 * Base implementation for FilterProccesor
 */
public class FilterProcessorBase implements FilterProcessor {

  private String pattern;
  private List<String> expressions;

  public FilterProcessorBase(String pattern) {
    this.pattern = pattern;
    expressions = new ArrayList<>();
  }

  @Override
  public String getPattern() {
    return pattern;
  }

  @Override
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public List<String> getExpressions() {
    return expressions;
  }

  @Override
  public List<String> process() {
    return expressions;
  }
}
