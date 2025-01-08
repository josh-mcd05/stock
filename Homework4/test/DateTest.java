import org.junit.Test;

import stock.Date;

import static org.junit.Assert.assertEquals;

/**
 * Tests for date class.
 */
public class DateTest {

  @Test
  public void testBefore() {
    Date date1 = new Date(1, 6, 2024);
    Date date2 = new Date(3, 6, 2024);
    Date date3 = new Date(2, 6, 2024);
    Date date4 = new Date(1, 6, 2023);
    Date date5 = new Date(1, 1, 2021);
    Date date6 = new Date(31, 5, 2024);

    assertEquals(true, date1.isBefore(date2));
    assertEquals(false, date2.isBefore(date1));
    assertEquals(true, date3.isBefore(date2));
    assertEquals(false, date3.isBefore(date1));
    assertEquals(true, date4.isBefore(date2));
    assertEquals(true, date4.isBefore(date1));
    assertEquals(true, date5.isBefore(date6));
    assertEquals(true, date6.isBefore(date1));
    assertEquals(false, date1.isBefore(date6));
  }

}