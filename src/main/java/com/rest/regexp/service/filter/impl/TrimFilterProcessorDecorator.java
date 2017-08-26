package com.rest.regexp.service.filter.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorDecorator;
import java.util.List;

/**
 * Remove unnecessary letters from start and end of nameFilter
 */
public class TrimFilterProcessorDecorator extends FilterProcessorDecorator {

  public TrimFilterProcessorDecorator(FilterProcessor filterProcessor) {
    super(filterProcessor);
  }

  /**
   * Remove unnecessary letters from start and end of nameFilter
   *
   * @return List of String that contains processed nameFilter
   */
  public List<String> process() {
    this.getFilterProcessor().process();
    trimPattern();
    getExpressions().add(getPattern());
    return getExpressions();
  }

  private void trimPattern() {
    StringBuilder stringBuilder = new StringBuilder(getPattern());
    if (getPattern().startsWith("^")) {
      stringBuilder.replace(0, 1, "");
    }
    if (getPattern().endsWith("$")) {
      stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
    }
    setPattern(stringBuilder.toString());
  }
}
