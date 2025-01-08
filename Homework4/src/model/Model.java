package model;

import java.io.File;
import java.util.List;
import java.util.Map;

import stock.Date;
import stock.Investments;
import stock.Portfolio;

/**
 * This represents the functions that the user can implement, which include
 * creating a portfolio, getting a portfolio, adding a stock, removing a stock,
 * finding the change between two dates, finding the average of a stock over
 * a certain period of time, finding the crossovers for a certain time period and stock
 * , the value of a portfolio at a date, buying/selling stock, composition of a portfolio
 * at a certain date, distribution of portfolio at certain date, etc.
 */
public interface Model {

  /**
   * This gets the portfolio at a certain index.
   * @param id index
   * @return portfolio at that index
   */
  Portfolio getPortfolio(int id);

  /**
   * This creates the portfolio.
   */
  public void createPortfolio();

  /**
   * Adds a specific stock to a portfolio.
   * @param ticker stock ticker symbol
   */
  public void addStock(String ticker);

  /**
   * Removes a specific stock from portfolio.
   * @param ticker stock ticker symbol
   */
  public void removeStock(String ticker);

  /**
   * This finds the change in a specific stock between two dates.
   * @param ticker stock ticker symbol
   * @param start start date
   * @param end end date
   * @return the change
   */
  public double findChange(String ticker, Date start, Date end);

  /**
   * This finds the average value over x-days of a specific stock.
   * @param ticker stock ticker symbol
   * @param start start date
   * @param x number of days
   * @return the average
   */
  public double findAverage(String ticker, Date start, int x);

  /**
   * This finds the crossovers of a specific stock over.
   * a period of time
   * @param ticker stock symbol
   * @param start start date
   * @param end end date
   * @param x days
   * @return a list of the crossovers
   */
  public List<String> findCrossovers(String ticker, Date start, Date end, int x);

  /**
   * This finds the value of a portfolio.
   * @param date date
   * @return value of portfolio
   */
  public double valueOf(Date date);

  /**
   * This gets the data of a stock in the portfolio.
   * @param ticker stock ticker symbol
   * @param date date
   * @return the data of a stock at a certain date
   */
  public String getData(String ticker, Date date);

  /**
   * This sets the portfolio number since you can have multiple.
   * @param index index of portfolio
   */
  public void setPortfolio(int index);

  /**
   * This buys shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param date date
   * @param shares shares of stock
   */
  public void buy(String ticker, Date date, double shares);

  /**
   * This sells shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param date date
   * @param shares shares of stock
   */
  public void sell(String ticker, Date date, double shares);

  /**
   * This returns the stock composition of the portfolio at a certain date.
   * @param date date
   * @return the composition
   */
  public String composition(Date date);

  /**
   * This returns the stock distribution of the portfolio at a certain date.
   * @param date date
   * @return the distribution
   */
  public String distribution(Date date);

  /**
   * This gets all the stocks in the portfolio.
   * @return all stocks
   */
  public List<String> getStocks();

  /**
   * This redistributes the stocks based on the user's desired weights.
   * @param date date
   * @param percents percentages/weights
   */
  public void redistribute(Date date, Map<String, Double> percents);

  /**
   * This saves the portfolio to a text file.
   * @param fileName name of file user wants to write to
   * @param parent directory of file
   * @return if it saved correctly
   */
  public boolean saveFile(String fileName, File parent);

  /**
   * This loads text file data as a portfolio.
   * @param parent file
   * @return if loaded correctly
   */
  public boolean loadFile(File parent);

  /**
   * This returns a bar graph of the portfolio over time.
   * @param start start date
   * @param end end date
   * @return a multi line string representing the graph
   */
  public String barGraph(String start, String end);



}
