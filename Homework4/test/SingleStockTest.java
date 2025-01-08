import org.junit.Test;

import stock.Date;
import stock.SingleStock;

import static org.junit.Assert.assertEquals;

/**\
 * This tests a single stock and checks the attributes.
 */
public class SingleStockTest {
  Date date = new Date(1,6,2024);
  SingleStock s = new SingleStock(date, 494.0, 495.0, 491.3, 493.8, 83);

  @Test
  public void testgetDate() {
    assertEquals(date, s.getDate());
  }

  @Test
  public void testgetHigh() {
    assertEquals(495.0, s.getHigh(), 0.1);
  }

  @Test
  public void testgetLow() {
    assertEquals(491.3, s.getLow(), 0.1);
  }

  @Test
  public void testgetOpen() {
    assertEquals(494.0, s.getOpen(), 0.1);
  }

  @Test
  public void testgetClose() {
    assertEquals(493.8, s.getClose(), 0.1);
  }

  @Test
  public void testgetVolume() {
    assertEquals(83, s.getVolume(), 0.1);
  }

}