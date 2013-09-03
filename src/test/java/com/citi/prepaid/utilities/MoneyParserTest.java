package com.citi.prepaid.utilities;

import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MoneyParserTest extends TestCase
{
  /**
   * Create the test case
   * 
   * @param testName
   *          name of the test case
   */
  public MoneyParserTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(MoneyParserTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testFormatDefault()
  {

    MoneyParser utility = new MoneyParser();

    for (int i = -10000000; i < 10000000; i++)
    {
      Integer val = utility.parse(utility.format(Integer.valueOf(i)));
      assertEquals(val.intValue(), i);
    }

    Integer valIntMax1 = utility.parse(utility.format(Integer
        .valueOf(Integer.MAX_VALUE)));
    assertEquals(valIntMax1.intValue(), Integer.MAX_VALUE);

    Integer valIntMin1 = utility.parse(utility.format(Integer
        .valueOf(Integer.MIN_VALUE)));
    assertEquals(valIntMin1.intValue(), Integer.MIN_VALUE);

  }

  public void testFormatExplicitLocale()
  {

    MoneyParser utility2 = new MoneyParser(Locale.GERMANY);

    for (int i = -10000000; i < 10000000; i++)
    {

      Integer val = utility2.parse(utility2.format(Integer.valueOf(i)));
      assertEquals(val.intValue(), i);
    }

    Integer valIntMax2 = utility2.parse(utility2.format(Integer
        .valueOf(Integer.MAX_VALUE)));
    assertEquals(valIntMax2.intValue(), Integer.MAX_VALUE);

    Integer valIntMin2 = utility2.parse(utility2.format(Integer
        .valueOf(Integer.MIN_VALUE)));
    assertEquals(valIntMin2.intValue(), Integer.MIN_VALUE);

  }

  public void testFormatNoFractionalSeparator()
  {

    MoneyParser utility3 = new MoneyParser(Locale.JAPAN);

    for (int i = -10000000; i < 10000000; i++)
    {
      Integer val = utility3.parse(utility3.format(Integer.valueOf(i)));
      assertEquals(val.intValue(), i);
    }

    Integer valIntMax3 = utility3.parse(utility3.format(Integer
        .valueOf(Integer.MAX_VALUE)));
    assertEquals(valIntMax3.intValue(), Integer.MAX_VALUE);

    Integer valIntMin3 = utility3.parse(utility3.format(Integer
        .valueOf(Integer.MIN_VALUE)));
    assertEquals(valIntMin3.intValue(), Integer.MIN_VALUE);

  }

}