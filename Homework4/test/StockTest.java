import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import stock.Date;
import stock.Portfolio;
import stock.SingleStock;
import stock.Stock;

import static org.junit.Assert.assertEquals;

/**
 * This tests a stock and makes sure the data is correctly formatted and can be extracted.
 */
public class StockTest {
  Portfolio portfolio = new Portfolio();
  Date start;
  Date end;
  Date date;

  @Before
  public void testPortfolio() {

    try {
      start = new Date(1, 5, 2021);
      end = new Date(1, 6, 2025);
      portfolio.addStock("GOOG");
      Stock google = portfolio.getStock("GOOG");
      google.findChange(start, end);
    } catch (Exception e) {
      //do nothing and continue
    }

    try {
      start = new Date(1, 5, 1900);
      end = new Date(1, 6, 2023);
      portfolio.addStock("GOOG");
      Stock google = portfolio.getStock("GOOG");
      google.findChange(start, end);
    } catch (Exception e) {
      //do nothing and continue
    }

    try {
      date = new Date(1, 5, 1900);
      portfolio.addStock("GOOG");
      Stock google = portfolio.getStock("GOOG");
      google.xDayAverage(date, 3);
    } catch (Exception e) {
      //do nothing and continue
    }
  }

  @Test
  public void testDiff() {
    start = new Date(11, 6, 2021);
    end = new Date(1, 5, 2023);
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals("-2406.22", google.findChange(start, end));
  }

  @Test
  public void testxDayAverage() {
    date = new Date(6, 1, 2023);
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals(91.805, google.xDayAverage(date, 4), 1);
  }

  @Test
  public void testxDayCrossoverFalse() {
    date = new Date(7, 4, 2021);
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals(false, google.xDayCrossover(date, 4));
  }

  @Test
  public void testxDayCrossoverTrue() {
    date = new Date(30, 1, 2024);
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals(true, google.xDayCrossover(date, 5));
  }

  @Test
  public void testPortfolioValue() {
    date = new Date(28, 1, 2024);
    portfolio.addStock("GOOG");
    portfolio.addStock("NVDA");
    portfolio.addStock("AMZN");
    assertEquals(939.79, portfolio.valueOf(date), 1);
  }

  @Test
  public void testInvalidIndex() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    date = new Date(30, 1, 2024);
    assertEquals(-1, google.indexOfDate(date));
  }

  @Test
  public void testValidIndex() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    date = new Date(30, 1, 2024);
    assertEquals(44, google.indexOfDate(date));
  }

  @Test
  public void testgetData() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    date = new Date(30, 1, 2024);
    assertEquals("Stock.Date: 2024-01-30\n"
            + "Open: 154.01\n"
            + "High: 155.04\n"
            + "Low: 152.775\n"
            + "Close: 153.05\n"
            + "Volume: 26578934", google.getDataOfDate(date));
  }

  @Test
  public void testgetValue() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    date = new Date(30, 1, 2024);
    assertEquals(153.05, google.getValue(date).getClose());
  }

  @Test
  public void getTicker() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals("GOOG", google.getTicker());
  }

  @Test
  public void testGetVals() {
    Date date = new Date(1,5,2024);
    Date date2 = new Date(3,5,2024);
    SingleStock s =  new SingleStock(date, 494.0, 495.0, 491.3, 493.8, 83);
    SingleStock t = new SingleStock(date2, 400.0, 450.0, 410.0, 437.3, 72);
    List<SingleStock> l = new ArrayList<SingleStock>();
    l.add(s);
    l.add(t);
    Stock google = new Stock("GOOG", l);
    google.buy(83, date);
    google.buy(72, date2);
    assertEquals("GOOG,2024-05-01,83.0,2024-05-03,155.0", google.getVals());
  }

  @Test
  public void testBuy() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    google.buy(10, new Date(4, 6, 2024));
    google.buy(50, new Date(6, 6, 2024));
    assertEquals(60, google.totalShares(), 0.1);

    try {
      google.buy(20, new Date(4, 6, 2024));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      google.buy(20, new Date(4, 6, 2023));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testSell() {
    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    google.buy(50, new Date(4, 6, 2024));
    google.sell(10, new Date(6, 6, 2024));
    assertEquals(40, google.totalShares(), 0.1);

    try {
      google.sell(20, new Date(4, 6, 2024));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      google.sell(60, new Date(6, 7, 2023));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @Test
  public void testMostRecentValue() {
    Date date = new Date(30,5,2024);
    Date date2 = new Date(31, 5, 2024);
    Date date3 = new Date(1,6,2024);
    Date date4 = new Date(7, 6, 2024);

    portfolio.addStock("GOOG");
    Stock google = portfolio.getStock("GOOG");
    assertEquals("Stock.Date: 2024-05-30\n" +
            "Open: 176.81\n" +
            "High: 178.23\n" +
            "Low: 176.26\n" +
            "Close: 177.4\n" +
            "Volume: 15023847\n", google.getDataOfDate(date));
    assertEquals("Stock.Date: 2024-05-31\n" +
            "Open: 173.4\n" +
            "High: 174.42\n" +
            "Low: 170.97\n" +
            "Close: 173.96\n" +
            "Volume: 28085151\n", google.getDataOfDate(date2));
    assertEquals("Stock.Date: 2024-06-02\n" +
            "Open: 173.4\n" +
            "High: 174.42\n" +
            "Low: 170.97\n" +
            "Close: 173.96\n" +
            "Volume: 28085151\n", google.getDataOfDate(date3));
    assertEquals("Stock.Date: 2024-06-07\n" +
            "Open: 177.43\n" +
            "High: 178.71\n" +
            "Low: 177.21\n" +
            "Close: 178.35\n" +
            "Volume: 14255818\n", google.getDataOfDate(date4));
  }

}