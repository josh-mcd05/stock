package stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This represents a stock that the user has in the portfolio but in general,
 * not on a specific date. The user can find the change in stocks
 * between two dates, find the x-day average for a given date, check if
 * a given date is an x-day crossover within the given range.
 */
public class Stock implements IStock{
  private final String ticker;
  private final List<SingleStock> values;
  private double totalShares;
  private Map<Date, Double> sharesByDate;

  /**
   * Constructor.
   * @param ticker stock ticker
   * @param values each stock per date
   */
  public Stock(String ticker, List<SingleStock> values) {
    this.ticker = ticker;
    this.values = values;
    this.totalShares = 0.0;
    this.sharesByDate = new HashMap<Date, Double>();
  }

  /**
   * This gets the values of the shares.
   * @return the value of the shares
   */
  public String getVals() {
    String finalResult = "";
    finalResult += ticker + ",";
    Collection<Date> dates = sharesByDate.keySet();
    Collection<Double> shares = sharesByDate.values();
    List<Date> dateList = new ArrayList<Date>(dates);
    List<Double> doubleList = new ArrayList<Double>(shares);
    for (int i = 0; i < dateList.size(); i++) {
      finalResult += dateList.get(i) + "," + doubleList.get(i) + ",";
    }


    return finalResult.substring(0, finalResult.length() - 1);

  }



  /**
   * Gets ticker of stock.
   *
   * @return ticker
   */
  public String getTicker() {
    return this.ticker;
  }

  /**
   * Gets the current total shares of this stock.
   *
   * @return current total shares
   */
  public double totalShares() {
    return this.totalShares;
  }

  /**
   * Finds the gain or loss over a specified period.
   *
   * @param startDay Starting date
   * @param endDay   Ending date
   * @return the gain or loss of this stock
   * @throws IllegalArgumentException when either date is not found
   */
  public double findChange(Date startDay, Date endDay) throws IllegalArgumentException {
    SingleStock start = null;
    SingleStock end = null;

    for (SingleStock s : values) {
      if (s.getDate().toString().equals(startDay.toString())) {
        start = s;
      }
      if (s.getDate().toString().equals(endDay.toString())) {
        end = s;
      }
    }

    if (start == null || end == null) {
      throw new IllegalArgumentException("Date not within range of known data");
    }

    return end.getClose() - start.getClose();
  }

  /**
   * Finds the x-day average starting at the given date for x days.
   *
   * @param date The starting date
   * @param x    days back to find the average
   * @return the average over x-days
   * @throws IllegalArgumentException if given date is not found
   */
  public double xDayAverage(Date date, int x) throws IllegalArgumentException {
    int indexOfDate = this.indexOfDate(mostRecentValue(date));

    double total = 0.0;
    for (int i = indexOfDate + x; i > indexOfDate; i--) {
      total += values.get(i).getClose();
    }

    return total / x;
  }

  /**
   * Finds if the given date is an x-day crossover within the given range.
   *
   * @param date the date to find
   * @param x    the range of values
   * @return true if the given date in the x range is an x-day crossover
   */
  public boolean xDayCrossover(Date date, int x) {
    int indexOfDate = this.indexOfDate(mostRecentValue(date));

    return this.values.get(indexOfDate).getClose() > this.xDayAverage(date, x);
  }

  /**
   * Finds the index of the given date within this.values.
   *
   * @param date the date to find
   * @return the index of the Stock.Stock.SingleStock with the corresponding date
   * @throws IllegalArgumentException if the date is not found
   */
  public int indexOfDate(Date date) throws IllegalArgumentException {
    for (int i = 0; i < values.size(); i++) {
      if (values.get(i).getDate().toString().equals(date.toString())) {
        return i;
      }
    }
    throw new IllegalArgumentException("Date: " + date.toString()
            + " not within range of known data");
  }

  /**
   * This returns all the data of a stock as a string for that day.
   *
   * @param date date of the stock
   * @return string with all the data
   * @throws IllegalArgumentException if date is not found
   */
  public String getDataOfDate(Date date) throws IllegalArgumentException {
    int indexOfDate = this.indexOfDate(this.mostRecentValue(date));

    return "Date: " + date.toString() + System.lineSeparator() +
            "Open: " + values.get(indexOfDate).getOpen() + System.lineSeparator() +
            "High: " + values.get(indexOfDate).getHigh() + System.lineSeparator() +
            "Low: " + values.get(indexOfDate).getLow() + System.lineSeparator() +
            "Close: " + values.get(indexOfDate).getClose() + System.lineSeparator() +
            "Volume: " + values.get(indexOfDate).getVolume() + System.lineSeparator();
  }

  /**
   * This gets the stock on a certain day.
   *
   * @param date certain day
   * @return the "single" stock
   * @throws IllegalArgumentException if date is invalid
   */
  public SingleStock getValue(Date date) throws IllegalArgumentException {
    int indexOfDate = this.indexOfDate(this.mostRecentValue(date));
    return this.values.get(indexOfDate);
  }

  /**
   * This accounts for dates not in the data.
   * @param date the invalid date
   * @return the most recent date that is valid
   * @throws IllegalArgumentException if date is invalid (parameter wise)
   */
  private Date mostRecentValue(Date date) throws IllegalArgumentException {
    Date mostRecent = null;
    for (SingleStock s : values) {
      if (s.getDate().toString().equals(date.toString())) {
        mostRecent = s.getDate();
        break;
      }
      if (s.getDate().isBefore(date)) {
        mostRecent = s.getDate();
        break;
      }
    }
    return mostRecent;

  }

  /**
   * This buys stocks at a certain date.
   * @param shares number of shares to buy
   * @param date date of buying
   * @throws IllegalArgumentException if date is not in chronological order
   */
  public void buy(double shares, Date date) throws IllegalArgumentException {
    if (this.isChronological(date)) {
      throw new IllegalArgumentException("Date must be after most recent activity");
    }
    this.totalShares += shares;
    this.sharesByDate.put(date, totalShares);
  }

  protected void set(Map<Date, Double> m) {
    Collection<Date> dates = m.keySet();
    Date mostRecent = null;
    for (Date date : dates) {
      if (mostRecent == null) {
        mostRecent = date;
      }
      else {
        if (!mostRecent.isBefore(date)) {
          mostRecent = date;
        }
      }
    }
    this.totalShares = m.get(mostRecent);
    this.sharesByDate = m;
  }

  /**
   * This sells a number of shares of a stock on a date.
   * @param shares shares of stock to sell
   * @param date date of selling
   * @throws IllegalArgumentException if the number of shares is over
   *            number of shares owned or if date isn't in chronological order
   */
  public void sell(double shares, Date date) throws IllegalArgumentException {
    if (this.isChronological(date)) {
      throw new IllegalArgumentException("Date must be after most recent activity");
    }
    if (shares > this.totalShares) {
      throw new IllegalArgumentException("Shares exceeds total shares");
    }
    this.totalShares = totalShares - shares;
    //System.out.println(date.toString() + ", " + totalShares);
    this.sharesByDate.put(date, totalShares);
  }

  public double getSharesOfDate(Date date) {
    Date d = this.mostRecentDate(date);
    return this.sharesByDate.get(d);
  }


  private boolean isChronological(Date date) {
    if (this.sharesByDate.isEmpty()) {
      return false;
    }
    Collection<Date> dates = this.sharesByDate.keySet();
    List<Date> listOfDates = new ArrayList<Date>(dates);
    Date d = listOfDates.get(listOfDates.size() - 1);
    if (date.isBefore(d)) {
      return !date.toString().equals(d.toString());
    }
    return false;
  }



  private Date mostRecentDate(Date date) throws IllegalArgumentException {
    Collection<Date> dates = this.sharesByDate.keySet();
    Date mostRecent = null;
    for (Date d : dates) {
      if (d.isBefore(date)) {
        if (mostRecent == null) {
          mostRecent = d;
        }
        else if (mostRecent.isBefore(d)) {
          mostRecent = d;
        }
      }
    }
    if (mostRecent == null) {
      throw new IllegalArgumentException("Date is before any activity");
    }
    return mostRecent;
  }

}


