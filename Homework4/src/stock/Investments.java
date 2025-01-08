package stock;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is a general representation of a user's investments, where they can do the following:
 * The user can buy or sell stocks, find the composition of stocks
 * within the portfolio at a date, determine the value of portfolio
 * on a date, the distribution of the portfolio, and save the portfolio
 * to a text file or load data from a text file into a portfolio. The portfolio
 * can also do more complicated operations such as redistributing the stocks
 * to be more balanced and also display a bar graph of portfolio value over
 * a certain length of time.
 */
public interface Investments {
  /**
   * Adds a stock to portfolio.
   *
   * @param ticker stock symbol
   */
  void addStock(String ticker);

  /**
   * Removes a stock from portfolio.
   *
   * @param ticker stock symbol
   */
  void removeStock(String ticker);

  /**
   * Gets a stock of a certain symbol.
   *
   * @param ticker stock symbol
   * @return the stock of that symbol
   */
  Stock getStock(String ticker);

  /**
   * This gets the value of a portfolio.
   *
   * @param date date user chooses
   * @return value of portfolio as a decimal number
   */
  double valueOf(Date date);

  /**
   * This is the size of a portfolio and how many stocks it has.
   *
   * @return how big it is
   */
  int size();

  /**
   * This saves the portfolio to a file.
   * @param filename name of file to save to
   * @param parent directory for file to be in
   * @throws IOException if parent file isn't valid
   */
  void saveToFile(String filename, File parent) throws IOException;

  /**
   * This loads text file data into a portfolio.
   * @param file file to load from
   * @return portfolio
   * @throws IOException if file is not a valid file
   */
  Portfolio loadFromFile(File file) throws IOException;

  /**
   * This buys shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param shares shares of stock
   * @param date date
   */
  void buy(String ticker, double shares, Date date);

  /**
   * This sells shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param shares shares of stock
   * @param date date
   */
  void sell(String ticker, double shares, Date date);

  /**
   * This returns the stock composition of the portfolio at a certain date.
   * @param date date
   * @return the composition
   */
  String findComposition(Date date);

  /**
   * This returns the stock distribution of the portfolio at a certain date.
   * @param date date
   * @return the distribution
   */
  String valueDistibution(Date date);

  /**
   * This redistributes the stocks based on the user's desired weights.
   * @param date date
   * @param percents percentages/weights
   */
  public void redistribute(Date date, Map<String, Double> percents) throws IllegalArgumentException;

  /**
   * This gets all the stocks in the portfolio.
   * @return a list of stocks
   */
  List<String> getAllStocks();

  /**
   * This calculates the value of a portfolio over time
   * through asterisks representing a certain value.
   * The program accounts for user input by
   * creating three different cases where there can be default
   * values for year or month. Then it calculates the
   * difference between the number of days using a
   * temporary variable that has the same parameters as the start date.
   * The difference is used to calculate the most
   * optimal divider (to advance the start date each time)
   * that makes it between 5 and 30 lines of asterisks when it reaches te end date.
   * Then, another temp variable with the same parameters
   * as the start date is used to find all
   * the portfolio values for each and find the max
   * and use that to make sure there aren't more than
   * 40 asterisks in a line and use that as the scale
   * for the value of each asterisk.
   * @param start start date
   * @param end  end date
   * @return a multi line string that displays the value
   *        of portfolio over time at certain date stops
   */
  String valueOverTime(String start, String end, int num);
}
