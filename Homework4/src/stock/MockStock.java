package stock;

import java.util.List;

/**
 * Fake stock for testing.
 */
public class MockStock extends Stock {

  /**
   * Constructor.
   * @param ticker stock symbol
   * @param values stock data
   */
  public MockStock(String ticker, List<SingleStock> values) {
    super(ticker, values);
  }

  @Override
  public double findChange(Date startDay, Date endDay) throws IllegalArgumentException {
    System.out.println("Received: " + startDay.toString() + " to " + endDay.toString());
    return 0;
  }

  @Override
  public double xDayAverage(Date date, int x) throws IllegalArgumentException {
    System.out.println("Received: " + date.toString() + " for " + x + " days.");
    return 0;
  }

  @Override
  public boolean xDayCrossover(Date date, int x) {
    System.out.println("Received: " + date.toString() + " for " + x + " days.");
    return false;
  }

  @Override
  public String getDataOfDate(Date date) throws IllegalArgumentException {
    System.out.println("Received: " + date.toString());
    return "";
  }
}
