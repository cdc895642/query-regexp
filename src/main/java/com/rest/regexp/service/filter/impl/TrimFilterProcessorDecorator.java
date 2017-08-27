package com.rest.regexp.service.filter.impl;

import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorDecorator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Remove unnecessary letters from start and end of nameFilter
 */
public class TrimFilterProcessorDecorator extends FilterProcessorDecorator {

  public TrimFilterProcessorDecorator(FilterProcessor filterProcessor) {
    super(filterProcessor);
  }

  /**
   * Remove unnecessary letters from start and end of nameFilter.
   *
   * @return List of String that contains processed nameFilter
   */
  public List<String> process() {
    this.getFilterProcessor().process();
    if (getExpressions().size() == 0) {
      String newPattern = trimPattern(getPattern());
      setPattern(newPattern);
      getExpressions().add(newPattern);
    } else {
      List<String> editedList = getExpressions().stream().map(this::trimPattern)
          .collect(Collectors.toList());
      getExpressions().clear();
      getExpressions().addAll(editedList);
    }
    return getExpressions();
  }

  private String trimPattern(String pattern) {
    StringBuilder stringBuilder = new StringBuilder(pattern);
    if (pattern.startsWith("^")) {
      stringBuilder.replace(0, 1, "");
    }
    if (pattern.endsWith("$")) {
      stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
    }
    return stringBuilder.toString();
  }
}
