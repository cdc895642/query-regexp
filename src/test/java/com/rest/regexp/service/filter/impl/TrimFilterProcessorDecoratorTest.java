package com.rest.regexp.service.filter.impl;

import static org.junit.Assert.assertEquals;

import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorBase;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TrimFilterProcessorDecoratorTest {
  @Test
  public void process_notEmptyListExpression_addNewExpressionList(){
    //Arrange
    final String PATTERN="abc[df]gh";
    final List<String> EXPECTED= Arrays.asList("abcdgh","abcfgh");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    filterProcessor.getExpressions().add("first");
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result=seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }
}
