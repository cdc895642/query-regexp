package com.rest.regexp.service.filter.impl;

import static org.junit.Assert.assertEquals;

import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TrimFilterProcessorDecoratorTest {
  @Test
  public void process_notEmptyListExpression_editExistedExpressionList(){
    //Arrange
    final String PATTERN="^abcgh$";
    final List<String> INIT= Arrays.asList("^abcdgh$","^abcfgh");
    final List<String> EXPECTED= Arrays.asList("abcdgh","abcfgh");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    filterProcessor.getExpressions().addAll(INIT);
    TrimFilterProcessorDecorator trimProcessor = new TrimFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result=trimProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_emptyListExpression_addNewStringToExpressionList(){
    //Arrange
    final String PATTERN="^abcgh$";
    final List<String> EXPECTED= Arrays.asList("abcgh");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    TrimFilterProcessorDecorator trimProcessor = new TrimFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result=trimProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_emptyListExpressionOnlyStartSymbol_addNewStringToExpressionList(){
    //Arrange
    final String PATTERN="^abcgh";
    final List<String> EXPECTED= new ArrayList(){{add("abcgh");}};
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    TrimFilterProcessorDecorator trimProcessor = new TrimFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result=trimProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_emptyListExpressionOnlyEndSymbol_addNewStringToExpressionList(){
    //Arrange
    final String PATTERN="abcgh$";
    final List<String> EXPECTED= Arrays.asList("abcgh");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    TrimFilterProcessorDecorator trimProcessor = new TrimFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result=trimProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }
}
