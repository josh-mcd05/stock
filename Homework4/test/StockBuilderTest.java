import org.junit.Test;

import java.util.ArrayList;

import stock.MockStock;
import stock.SingleStock;
import stock.Stock;
import stock.StockBuilder;
import stock.Date;

import static org.junit.Assert.assertEquals;

/**
 * This tests the stock builder to see if it actually builds the data correctly.
 */
public class StockBuilderTest {

  @Test
  public void testStockBuilder() {
    StockBuilder sb = new StockBuilder("GOOG");

    Stock stock = new MockStock("String", new ArrayList<SingleStock>());

    Stock google = sb.build();
    Date date1 = new Date(7, 6, 2024);
    Date date2 = new Date(11, 6, 2021);

    assertEquals("Stock.Date: 2024-06-04" + System.lineSeparator()
            + "Open: 174.45" + System.lineSeparator()
            + "High: 175.19" + System.lineSeparator()
            + "Low: 173.22" + System.lineSeparator()
            + "Close: 175.13" + System.lineSeparator()
            + "Volume: 14066602" + System.lineSeparator(), google.getDataOfDate(date1));
    assertEquals("Stock.Date: 2021-06-11" + System.lineSeparator()
            + "Open: 2524.92" + System.lineSeparator()
            + "High: 2526.99" + System.lineSeparator()
            + "Low: 2498.29" + System.lineSeparator()
            + "Close: 2513.93" + System.lineSeparator()
            + "Volume: 1262309" + System.lineSeparator(), google.getDataOfDate(date2));

  }

}