import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import stock.Date;
import stock.Portfolio;
import stock.Stock;

import static org.junit.Assert.assertEquals;

/**
 * This is a portfolio to test if the user can change it.
 */
public class PortfolioTest {
  Portfolio portfolio = new Portfolio();

  @Before
  public void testFile() throws IOException {
    Date date = new Date(1, 5, 2024);
    Date date2 = new Date(3, 5, 2024);
    portfolio.buy("GOOG", 83, date);
    portfolio.buy("GOOG", 72, date2);
    portfolio.buy("AMZN", 90, date);
    File parent = new File("C://Users//kz135//OneDrive//Documents");
    portfolio.saveToFile("Test.txt", parent);
    assertEquals("", "");
  }

  @Before
  public void testReadFile() throws IOException {
    File file = new File("/Users/josh/Desktop/portfolio.txt");
    portfolio.loadFromFile(file);
    File parent = new File("C://Users//kz135//OneDrive//Documents");
    portfolio.saveToFile("Stupid.txt",parent);
    assertEquals("", "");
  }

  @Test
  public void testBarGraph() {
    Date date1 = new Date(6,11,2023);
    Date date2 = new Date(11,12,2023);
    portfolio.buy("GOOG", 31, date1);
    portfolio.buy("GOOG", 72, date2);
    assertEquals("", portfolio.valueOverTime(
            "2023-11-08", "2023-12-15", 1));
  }

  @Test
  public void testBarGraph2() {
    Date date1 = new Date(6,11,2023);
    Date date2 = new Date(11,12,2023);
    portfolio.buy("AMZN", 31, date1);
    portfolio.buy("GOOG", 72, date2);
    assertEquals("", portfolio.valueOverTime(
            "2023-12-08", "2023-12-12", 1));
  }

  @Test
  public void testAddStock() {
    portfolio.addStock("GOOG");
    assertEquals("GOOG", portfolio.getStock("GOOG").getTicker());
  }

  @Test
  public void testRemoveStock() {
    portfolio.addStock("GOOG");
    portfolio.removeStock("GOOG");
    assertEquals(null, portfolio.getStock("GOOG"));
  }

  @Test
  public void testSize() {
    portfolio.addStock("GOOG");
    portfolio.addStock("NVDA");
    portfolio.addStock("AMZN");
    assertEquals(3, portfolio.size());
  }

  @Test
  public void testBuy() {
    portfolio.addStock("GOOG");
    portfolio.buy("GOOG", 10, new Date(4, 6, 2024));
    portfolio.buy("GOOG", 35, new Date(6, 6, 2024));
    assertEquals(45, portfolio.getStock("GOOG").totalShares(), 0.1);

    portfolio.buy("NVDA", 50, new Date(31, 5, 2024));
    assertEquals(50, portfolio.getStock("NVDA").totalShares(), 0.1);
  }

  @Test
  public void testSell() {
    portfolio.buy("GOOG", 10, new Date(4, 6, 2024));
    portfolio.buy("NVDA", 35, new Date(31, 5, 2024));

    portfolio.sell("GOOG", 10, new Date(6, 6, 2024));
    assertEquals(0, portfolio.getStock("GOOG").totalShares(), 0.1);

    portfolio.sell("NVDA", 20, new Date(4, 6, 2024));
    assertEquals(15, portfolio.getStock("NVDA").totalShares(), 0.1);

    try {
      portfolio.sell("AMZN", 10, new Date(6, 6, 2024));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testComposition() {
    portfolio.buy("GOOG", 10, new Date(30, 5, 2024));
    portfolio.buy("NVDA", 50, new Date(30, 5, 2024));
    portfolio.buy("AMZN", 100, new Date(30, 5, 2024));

    portfolio.sell("GOOG", 5, new Date(5, 6, 2024));
    portfolio.sell("NVDA", 20, new Date(5, 6, 2024));
    portfolio.sell("AMZN", 10, new Date(5, 6, 2024));

    StringBuilder resultJuneOne = new StringBuilder();
    StringBuilder resultJuneEight = new StringBuilder();

    resultJuneOne.append("Shares owned of each stock on 2024-06-01: " + System.lineSeparator());
    resultJuneOne.append("GOOG: 10.0" + System.lineSeparator());
    resultJuneOne.append("NVDA: 50.0" + System.lineSeparator());
    resultJuneOne.append("AMZN: 100.0" + System.lineSeparator());
    assertEquals(resultJuneOne.toString(), portfolio.findComposition(
            new Date(1, 6, 2024)));

    resultJuneEight.append("Shares owned of each stock on 2024-06-08: " + System.lineSeparator());
    resultJuneEight.append("GOOG: 5.0" + System.lineSeparator());
    resultJuneEight.append("NVDA: 30.0" + System.lineSeparator());
    resultJuneEight.append("AMZN: 90.0" + System.lineSeparator());
  }

  @Test
  public void testValue() {
    Date date = new Date(3, 6, 2024);
    portfolio.buy("GOOG", 12, date);
    Stock google = portfolio.getStock("GOOG");
    double value = 12 * google.getValue(date).getClose();
    assertEquals(value, portfolio.valueOf(date), 0.1);

    Date date2 = new Date(5, 6, 2024);
    portfolio.buy("NVDA", 50, date2);
    portfolio.sell("GOOG", 10, date2);
    Stock nvidia = portfolio.getStock("NVDA");
    double value2 = (2 * google.getValue(date2).getClose())
            + (nvidia.getValue(date2).getClose() * 50);
    assertEquals(value2, portfolio.valueOf(date2), 0.1);

    Date date3 = new Date(7, 6, 2024);
    portfolio.buy("AMZN", 5, date3);
    portfolio.sell("NVDA", 30, date3);
    Stock amazon = portfolio.getStock("AMZN");
    double value3 = (google.getValue(date3).getClose() * google.getSharesOfDate(date3))
            + (amazon.getValue(date3).getClose() * amazon.getSharesOfDate(date3))
            + (nvidia.getValue(date3).getClose() * nvidia.getSharesOfDate(date3));

    assertEquals(value3, portfolio.valueOf(date3), 0.1);
    assertEquals(value2, portfolio.valueOf(date2), 0.1);
    assertEquals(value, portfolio.valueOf(date), 0.1);
  }

  @Test
  public void testValueDistribution() {
    Date date = new Date(30, 5, 2024);
    portfolio.buy("GOOG", 100, date);
    portfolio.buy("NVDA", 50, date);
    Stock google = portfolio.getStock("GOOG");
    Stock nvidia = portfolio.getStock("NVDA");
    String result = "The total value of your portfolio, " + portfolio.valueOf(date)
            + ", is made up by: " + System.lineSeparator();
    result += "GOOG: " + google.getValue(date).getClose() * google.getSharesOfDate(date)
            + System.lineSeparator();
    result += "NVDA: " + nvidia.getValue(date).getClose() * nvidia.getSharesOfDate(date)
            + System.lineSeparator();
    assertEquals(result, portfolio.valueDistibution(date));

    Date date2 = new Date(5, 6, 2024);
    portfolio.buy("AMZN", 25, date2);
    portfolio.sell("GOOG", 30, date2);
    portfolio.buy("NVDA", 25, date2);
    Stock amazon = portfolio.getStock("AMZN");
    String result2 = "The total value of your portfolio, " + portfolio.valueOf(date2)
            + ", is made up by: " + System.lineSeparator();
    result2 += "GOOG: " + google.getValue(date2).getClose() * google.getSharesOfDate(date2)
            + System.lineSeparator();
    result2 += "NVDA: " + nvidia.getValue(date2).getClose() * nvidia.getSharesOfDate(date2)
            + System.lineSeparator();
    result2 += "AMZN: " + amazon.getValue(date2).getClose() * amazon.getSharesOfDate(date2)
            + System.lineSeparator();
    assertEquals(result2, portfolio.valueDistibution(date2));
    assertEquals(result, portfolio.valueDistibution(date));

    Date date3 = new Date(7, 6, 2024);
    portfolio.buy("AMZN", 50, date3);
    portfolio.sell("GOOG", 70, date3);
    portfolio.buy("NVDA", 100, date3);
    String result3 = "The total value of your portfolio, " + portfolio.valueOf(date3)
            + ", is made up by: " + System.lineSeparator();
    result3 += "NVDA: " + nvidia.getValue(date3).getClose() * nvidia.getSharesOfDate(date3)
            + System.lineSeparator();
    result3 += "AMZN: " + amazon.getValue(date3).getClose() * amazon.getSharesOfDate(date3)
            + System.lineSeparator();
    assertEquals(result3, portfolio.valueDistibution(date3));
    assertEquals(result2, portfolio.valueDistibution(date2));
    assertEquals(result, portfolio.valueDistibution(date));



  }

}