package com.rest.regexp.service.filter.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorDecorator;
import java.util.List;

/**
 * Process nameFilter that contents regular expression like "[series of letters]"
 */
public class SeriesFilterProcessorDecorator extends FilterProcessorDecorator {

  private final String SERIES_ALPHABETIC_PATTERN = ".*\\[\\p{IsAlphabetic}+\\].*";

  public SeriesFilterProcessorDecorator(FilterProcessor filterProcessor) {
    super(filterProcessor);
  }

  /**
   * Get List of BooleanExpression where column "name" from table "contacts" does not contain
   * any letter from regular expression like "[series of letters]"
   *
   * @return List of BooleanExpression where each of it formed by method {@link
   * #getByNotMatchNamePattern(String)}
   */
  public List<BooleanExpression> process() {
    this.getFilterProcessor().process();
    if (getPattern().matches(SERIES_ALPHABETIC_PATTERN)) {
      getExpressions().clear();
      addListBooleanExpression();
    }
    return this.getExpressions();
  }

  private void addListBooleanExpression() {
    int beginIndex = getPattern().indexOf("[") + 1;
    int endIndex = getPattern().indexOf("]");
    String series = getPattern().substring(beginIndex, endIndex);
    char[] chars = series.toCharArray();
    for (char c : chars) {
      StringBuilder builder = new StringBuilder(getPattern());
      builder.replace(beginIndex - 1, endIndex + 1, "" + c);
      String result=processNextSeries(builder.toString());
      this.getExpressions().add(getByNotMatchNamePattern(result));
    }
  }

  private String processNextSeries(String input) {
    final String REPLACEMENT_PATTERN = "\\[\\p{IsAlphabetic}+\\]";
    final String REPLACEMENT = ".+";
    while (input.matches(SERIES_ALPHABETIC_PATTERN)) {
      input = input.replaceAll(REPLACEMENT_PATTERN, REPLACEMENT);
    }
    return input;
  }
}
