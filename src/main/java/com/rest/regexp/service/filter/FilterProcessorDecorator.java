package com.rest.regexp.service.filter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.model.QContact;
import java.util.List;

/**
 * Base decorator that allows make chain of filters to find records in database
 */
public class FilterProcessorDecorator implements FilterProcessor{

  private FilterProcessor filterProcessor;

  public FilterProcessorDecorator(FilterProcessor filterProcessor){
    this.filterProcessor=filterProcessor;
  }

  public FilterProcessor getFilterProcessor() {
    return filterProcessor;
  }

  public void setFilterProcessor(FilterProcessor filterProcessor) {
    this.filterProcessor = filterProcessor;
  }

  @Override
  public String getPattern() {
    return filterProcessor.getPattern();
  }

  @Override
  public void setPattern(String pattern) {
    filterProcessor.setPattern(pattern);
  }

  @Override
  public List<BooleanExpression> getExpressions() {
    return filterProcessor.getExpressions();
  }

  @Override
  public List<BooleanExpression> process() {
    return filterProcessor.process();
  }

  protected BooleanExpression getByNotMatchNamePattern(String pattern) {
    return QContact.contact.name.matches(pattern).not();
  }
}
