package stock;

/**
 * This represents a general share in a company. A user can find the change in stocks
 * between two dates, find the x-day average for a given date, check if
 * a given date is an x-day crossover within the given range.
 */
public interface IStock {
  /**
   * This gets the values of the shares.
   * @return the value of the shares
   */
  String getVals();

  /**
   * Gets ticker of stock.
   *
   * @return ticker
   */
  String getTicker();

  /**
   * Gets the current total shares of this stock.
   *
   * @return current total shares
   */
  double totalShares();

  /**
   * Finds the gain or loss over a specified period.
   *
   * @param startDay Starting date
   * @param endDay   Ending date
   * @return the gain or loss of this stock
   * @throws IllegalArgumentException when either date is not found
   */
  public double findChange(Date startDay, Date endDay) throws IllegalArgumentException;

  /**
   * Finds the x-day average starting at the given date for x days.
   *
   * @param date The starting date
   * @param x    days back to find the average
   * @return the average over x-days
   * @throws IllegalArgumentException if given date is not found
   */
  double xDayAverage(Date date, int x) throws IllegalArgumentException;

  /**
   * Finds if the given date is an x-day crossover within the given range.
   *
   * @param date the date to find
   * @param x    the range of values
   * @return true if the given date in the x range is an x-day crossover
   */
  boolean xDayCrossover(Date date, int x);

  /**
   * Finds the index of the given date within this.values.
   *
   * @param date the date to find
   * @return the index of the Stock.Stock.SingleStock with the corresponding date
   * @throws IllegalArgumentException if the date is not found
   */
  int indexOfDate(Date date) throws IllegalArgumentException;

  /**
   * This returns all the data of a stock as a string for that day.
   *
   * @param date date of the stock
   * @return string with all the data
   * @throws IllegalArgumentException if date is not found
   */
  String getDataOfDate(Date date) throws IllegalArgumentException;

  /**
   * This gets the stock on a certain day.
   *
   * @param date certain day
   * @return the "single" stock
   * @throws IllegalArgumentException if date is invalid
   */
  SingleStock getValue(Date date) throws IllegalArgumentException;

  /**
   * This buys stocks at a certain date.
   * @param shares number of shares to buy
   * @param date date of buying
   * @throws IllegalArgumentException if date is not in chronological order
   */
  void buy(double shares, Date date) throws IllegalArgumentException;

  /**
   * This sells a number of shares of a stock on a date.
   * @param shares shares of stock to sell
   * @param date date of selling
   * @throws IllegalArgumentException if the number of shares is over
   *            number of shares owned or if date isn't in chronological order
   */
  void sell(double shares, Date date) throws IllegalArgumentException;

}
