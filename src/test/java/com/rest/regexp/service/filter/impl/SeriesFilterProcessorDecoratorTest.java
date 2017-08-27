package com.rest.regexp.service.filter.impl;

import static org.junit.Assert.assertEquals;

import com.rest.regexp.service.filter.FilterProcessor;
import com.rest.regexp.service.filter.FilterProcessorBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SeriesFilterProcessorDecoratorTest {

  @Test
  public void process_addSeriesNotEmptyListExpression_removeFirstElementFormList() {
    //Arrange
    final String PATTERN = "abc[df]gh";
    final List<String> EXPECTED = Arrays.asList("abcdgh", "abcfgh");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    filterProcessor.getExpressions().add("first");
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addInnerSeriesWithLetters_returnEmptyList() {
    //Arrange
    final String PATTERN = "[abc[df]gh]";
    final List<String> EXPECTED = new ArrayList<>();
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addInnerSeriesWithNoLetters_returnEmptyList() {
    //Arrange
    final String PATTERN = "[abc[d-f]gh]";
    final List<String> EXPECTED = new ArrayList<>();
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addTwoSeries_processOnlyFirstOne() {
    //Arrange
    final String PATTERN = "[ac]middle[de]";
    final List<String> EXPECTED = Arrays.asList("amiddle[de]", "cmiddle[de]");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addSeriesWithNoLetters_returnEmptyList() {
    //Arrange
    final String PATTERN = "[a-c]end";
    final List<String> EXPECTED = new ArrayList<>();
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addSeriesFirstWithNoLettersSecondWithLetters_returnEmptyList() {
    //Arrange
    final String PATTERN = "[a-c]middle[de]";
    final List<String> EXPECTED = new ArrayList<>();
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithFewLettersInBeginPattern_returnListWithFewElements() {
    //Arrange
    final String PATTERN = "[abc]end";
    final List<String> EXPECTED = Arrays.asList("aend", "bend", "cend");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithOneLetterInBeginPattern_returnListWithOneElement() {
    //Arrange
    final String PATTERN = "[a]end";
    final List<String> EXPECTED = Arrays.asList("aend");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithFewLettersInMiddlePattern_returnListWithFewElements() {
    //Arrange
    final String PATTERN = ".*[abc]end";
    final List<String> EXPECTED = Arrays.asList(".*aend", ".*bend", ".*cend");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithOneLetterInMiddlePattern_returnListWithOneElement() {
    //Arrange
    final String PATTERN = ".*[a]end";
    final List<String> EXPECTED = Arrays.asList(".*aend");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithFewLettersInEndPattern_returnListWithFewElements() {
    //Arrange
    final String PATTERN = ".*[abc]";
    final List<String> EXPECTED = Arrays.asList(".*a", ".*b", ".*c");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }

  @Test
  public void process_addOneSeriesWithOneLetterInEndPattern_returnListWithOneElement() {
    //Arrange
    final String PATTERN = ".*[a]";
    final List<String> EXPECTED = Arrays.asList(".*a");
    FilterProcessor filterProcessor = new FilterProcessorBase(PATTERN);
    SeriesFilterProcessorDecorator seriesProcessor = new SeriesFilterProcessorDecorator(
        filterProcessor);

    //Act
    List<String> result = seriesProcessor.process();

    //Assert
    assertEquals(EXPECTED, result);
  }
}
