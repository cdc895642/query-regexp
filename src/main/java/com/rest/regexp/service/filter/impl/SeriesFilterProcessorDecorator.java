package com.rest.regexp.service.filter.impl;

import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorDecorator;
import java.util.List;

/**
 * Process nameFilter that contents regular expression like "[series of letters]"
 * Process only first expression in pattern
 */
public class SeriesFilterProcessorDecorator extends FilterProcessorDecorator {

  private final String SERIES_ALPHABETIC_PATTERN = ".*\\[\\p{IsAlphabetic}+\\].*";
  private final String CORRECT_SERIES_PATTERN = "\\p{IsAlphabetic}+";

  public SeriesFilterProcessorDecorator(FilterProcessor filterProcessor) {
    super(filterProcessor);
  }

  /**
   * Get List of strings expressions where each string does not contain
   * any letter from regular expression like ".*[series of letters].*"
   *
   * @return List of strings where each of it represent part of condition from where clause in query to database
   */
  public List<String> process() {
    this.getFilterProcessor().process();
    if (getPattern().matches(SERIES_ALPHABETIC_PATTERN)) {
      getExpressions().clear();
      addListExpression();
    }
    return this.getExpressions();
  }

  private void addListExpression() {
    int beginIndex = getPattern().indexOf("[") + 1;
    int endIndex = getPattern().indexOf("]");
    String series = getPattern().substring(beginIndex, endIndex);
    if (!checkCorrectSeries(series)){
      return;
    }
    char[] chars = series.toCharArray();
    for (char c : chars) {
      StringBuilder builder = new StringBuilder(getPattern());
      builder.replace(beginIndex - 1, endIndex + 1, "" + c);
      String result=builder.toString();
      this.getExpressions().add(result);
    }
  }

  private boolean checkCorrectSeries(String series){
    return series.matches(CORRECT_SERIES_PATTERN);
  }
}
