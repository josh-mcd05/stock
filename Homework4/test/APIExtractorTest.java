import org.junit.Test;

import stock.APIExtractor;

import static org.junit.Assert.assertEquals;

/**
 * This tests if the data is extracted correctly.
 */
public class APIExtractorTest {
  APIExtractor test = new APIExtractor("GOOG");

  @Test
  public void testDate() {
    System.out.print(test.displayData());
    assertEquals("2024-06-06", test.displayData().get(0));
  }

  @Test
  public void testOpen() {
    assertEquals(177.4100, test.displayData().get(2));
  }

  @Test
  public void testHigh() {
    assertEquals(177.4100, test.displayData().get(3));
  }

  @Test
  public void testLow() {
    assertEquals(177.4100, test.displayData().get(4));
  }

  @Test
  public void testClose() {
    assertEquals(177.4100, test.displayData().get(5));
  }
}