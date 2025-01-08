package stock;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


/**
 * The portfolio is a collection of stocks that the user has.
 * The user can buy or sell stocks, find the composition of stocks
 * within the portfolio at a date, determine the value of portfolio
 * on a date, the distribution of the portfolio, and save the portfolio
 * to a text file or load data from a text file into a portfolio. The portfolio
 * can also do more complicated operations such as redistributing the stocks
 * to be more balanced and also display a bar graph of portfolio value over
 * a certain length of time.
 */
public class Portfolio implements Investments {
  private Map<String, Stock> stocks;
  //private Map<String, Double> shares;

  /**
   * Constructor.
   */
  public Portfolio() {
    stocks = new HashMap<String, Stock>();
    //shares = new HashMap<String, Double>();
  }

  /**
   * Adds a stock to portfolio.
   *
   * @param ticker stock symbol
   */
  public void addStock(String ticker) {
    StockBuilder stock = new StockBuilder(ticker);
    stocks.put(ticker, stock.build());
  }

  /**
   * Removes a stock from portfolio.
   *
   * @param ticker stock symbol
   */
  public void removeStock(String ticker) {
    stocks.remove(ticker);
  }

  /**
   * Gets a stock of a certain symbol.
   *
   * @param ticker stock symbol
   * @return the stock of that symbol
   */
  public Stock getStock(String ticker) {
    return stocks.get(ticker);
  }

  /**
   * This gets the value of a portfolio.
   *
   * @param date date user chooses
   * @return value of portfolio as a decimal number
   */
  public double valueOf(Date date) {
    double sum = 0.0;
    Collection<Stock> stock = stocks.values();
    List<Stock> newStock = new ArrayList<Stock>(stock);
    for (int i = 0; i < stocks.size(); i++) {
      Stock s = newStock.get(i);
      double shares;
      try {
        shares = s.getSharesOfDate(date);
      } catch (Exception e) {
        shares = 0;
      }
      SingleStock ss = s.getValue(date);
      sum += ss.getClose() * shares;
    }
    return sum;
  }

  /**
   * This is the size of a portfolio and how many stocks it has.
   *
   * @return how big it is
   */
  public int size() {
    return stocks.size();
  }

  /**
   * This saves the portfolio to a file.
   * @param filename name of file to save to
   * @param parent directory for file to be in
   * @throws IOException if parent file isn't valid
   */
  public void saveToFile(String filename, File parent) throws IOException {
    File file = new File(parent, filename);
    FileWriter f = new FileWriter(file);
    String s = "";
    for (Stock stock : this.stocks.values()) {
      s += stock.getVals() + "\n";
    }
    f.write(s);
    f.close();
  }

  /**
   * THis converts a string to a date.
   * @param date string of a date in YYYY-MM-DD format
   * @return a date
   */
  private Date toDate(String date) {
    List<String> newData = Arrays.asList(date.split("-"));
    List<Integer> finalData = new ArrayList<>();
    for (String x : newData) {
      finalData.add(Integer.valueOf(x));
    }
    return new Date(finalData.get(2), finalData.get(1), finalData.get(0));
  }

  /**
   * This loads text file data into a portfolio.
   * @param file file to load from
   * @return portfolio
   * @throws IOException if file is not a valid file
   */
  public Portfolio loadFromFile(File file) throws IOException {
    Path path = file.toPath();
    String s = "";
    List<String> fileLines = Files.readAllLines(path);
    for (int i = 0; i < fileLines.size(); i++) {
      s += fileLines.get(i) + "\n";
    }
    String[] allStocks = s.split("\n");
    for (String stock : allStocks) {
      String[] vals = stock.split(",");
      List<String> updatedVals = new ArrayList<>(Arrays.asList(vals));
      List<String> updatedVals2 = updatedVals.subList(1, updatedVals.size());

      this.addStock(updatedVals.get(0));
      Stock stock1 = this.stocks.get(updatedVals.get(0));
      Map<Date, Double> m = new HashMap<Date, Double>();
      for (int i = 0; i < updatedVals2.size(); i += 2) {
        m.put(this.toDate(updatedVals2.get(i)), Double.parseDouble(updatedVals2.get(i + 1)));
      }
      stock1.set(m);
      //System.out.println(m);
      //System.out.println(stock1.getTicker());
    }

    return this;
  }

  /**
   * This buys shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param shares shares of stock
   * @param date date
   */
  public void buy(String ticker, double shares, Date date) throws IllegalArgumentException {
    Stock s = stocks.get(ticker);
    if (s == null) {
      this.addStock(ticker);
      s = stocks.get(ticker);
    }
    s.buy(shares, date);
  }

  /**
   * This sells shares of a specific stock at a date.
   * @param ticker stock symbol ticker
   * @param shares shares of stock
   * @param date date
   */
  public void sell(String ticker, double shares, Date date) throws IllegalArgumentException {
    Stock s = stocks.get(ticker);
    if (s == null) {
      throw new IllegalArgumentException("Stock not in portfolio, buy first");
    }
    s.sell(shares, date);
  }

  /**
   * This returns the stock composition of the portfolio at a certain date.
   * @param date date
   * @return the composition
   */
  public String findComposition(Date date) {
    Collection<Stock> stock = stocks.values();
    StringBuilder sb = new StringBuilder();
    sb.append("Shares owned of each stock on " + date.toString() + ": " + System.lineSeparator());

    for (Stock s : stock) {
      sb.append(s.getTicker() + ": " + s.getSharesOfDate(date) + System.lineSeparator());
    }
    return sb.toString();
  }

  /**
   * This returns the stock distribution of the portfolio at a certain date.
   * @param date date
   * @return the distribution
   */
  public String valueDistibution(Date date) {
    Collection<Stock> stock = stocks.values();
    StringBuilder sb = new StringBuilder();
    sb.append("The total value of your portfolio, $" + String.format("%.2f", this.valueOf(date))
            + ", is made up by: " + System.lineSeparator());
    for (Stock s : stock) {
      double value;
      try {
        value = s.getSharesOfDate(date) * s.getValue(date).getClose();
        if (value != 0) {
          sb.append(s.getTicker() + ": $" + String.format("%.2f", value) + System.lineSeparator());
        }
      } catch (Exception e) {
        //skip this value, no data on this date.
      }
    }
    return sb.toString();
  }

  /**
   * This redistributes the stocks based on the user's desired weights.
   * @param date date
   * @param percents percentages/weights
   */
  public void redistribute(Date date, Map<String, Double> percents) throws IllegalArgumentException{
    double total = 0;
    for (Double percent : percents.values()) {
      total += percent;
    }

    if (total != 100) {
      throw new IllegalArgumentException("Percents do not add up!");
    }

    Collection<String> tickers = stocks.keySet();
    double totalValue = this.valueOf(date);
    for (String s : tickers) {
      Stock stock = stocks.get(s);
      this.sell(s, stock.getSharesOfDate(date), date);
      double close = stock.getValue(date).getClose();
      double percentage = percents.get(s);
      double expected = totalValue * percentage;
      double shares = expected / close;
      this.buy(s, shares, date);
    }
  }

  /**
   * This gets all the stocks in the portfolio.
   * @return a list of stocks
   */
  public List<String> getAllStocks() {
    Collection<String> stock = stocks.keySet();
    return new ArrayList<String>(stock);
  }

  /**
   * This checks if a number is prime.
   * @param n input value
   * @return true if prime, false if not
   */
  private boolean isPrime(int n) {
    int count = 2;
    while (count != n) {
      if (n % count == 0) {
        return false;
      }
      count += 1;
    }
    return true;
  }

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
  public String valueOverTime(String start, String end, int num) {
    String output = "\nPerformance of portfolio " + num + " from " + start + " to " + end + "\n";
    List<String> startDateVals = new ArrayList<String>(Arrays.asList(start.split("-")));
    List<String> endDateVals = new ArrayList<String>(Arrays.asList(end.split("-")));
    if (startDateVals.size() != endDateVals.size()) {
      throw new IllegalArgumentException("Start date and " +
              "end dates must have the same number of parameters"
              + "(e.g. day, month, year)");
    }

    Date startDate;
    Date endDate;
    Date tempStartDate;
    Date tempStartDate2;
    Date tempEndDate;
    int daysDifference = 0;
    int divider = 0;
    if (startDateVals.size() == 1) {
      startDate = new Date(Integer.parseInt(startDateVals.get(0)));
      endDate = new Date(Integer.parseInt(endDateVals.get(0)));
      tempStartDate = this.toDate(startDate.toString());
      tempStartDate2 = this.toDate(startDate.toString());
      tempEndDate = this.toDate(endDate.toString());
    } else if (startDateVals.size() == 2) {
      startDate = new Date(Integer.parseInt(startDateVals.get(1)),
              Integer.parseInt(startDateVals.get(0)));
      endDate = new Date(Integer.parseInt(endDateVals.get(1)),
              Integer.parseInt(endDateVals.get(0)));
      tempStartDate = this.toDate(startDate.toString());
      tempStartDate2 = this.toDate(startDate.toString());
      tempEndDate = this.toDate(endDate.toString());
    } else {
      startDate = new Date(Integer.parseInt(startDateVals.get(2)),
              Integer.parseInt(startDateVals.get(1)),
              Integer.parseInt(startDateVals.get(0)));
      endDate = new Date(Integer.parseInt(endDateVals.get(2)), Integer.parseInt(endDateVals.get(1)),
              Integer.parseInt(endDateVals.get(0)));
      tempStartDate = this.toDate(startDate.toString());
      tempStartDate2 = this.toDate(startDate.toString());
      tempEndDate = this.toDate(endDate.toString());
    }

    while (!(tempStartDate.toString().equals(endDate.toString()))) {
      tempStartDate.advance(1);
      daysDifference += 1;
    }

    if (this.isPrime(daysDifference)) {
      daysDifference = daysDifference - 1;
      tempEndDate.advance(-1);
    }

    if (daysDifference < 5) {
      divider = 1;
    } else {
      while (divider < daysDifference) {
        divider += 1;
        if (daysDifference % divider == 0) {
          if (daysDifference / divider >= 5 && daysDifference / divider <= 30) {
            break;
          }
        }
      }
    }

    List<Double> listOfValues = new ArrayList<>();
    while (!(tempStartDate2.toString().equals(tempEndDate.toString()))) {
      listOfValues.add(this.valueOf(tempStartDate2));
      tempStartDate2.advance(divider);
    }

    Double[] vals = listOfValues.toArray(new Double[listOfValues.size()]);
    Arrays.sort(vals);
    double max = vals[vals.length - 1];
    double asteriskValue = max / 40;
    //System.out.println(max);


    while (!(startDate.toString()).equals(tempEndDate.toString())) {
      output += this.getAstricks(startDate, asteriskValue);
      startDate.advance(divider);
    }
    output += this.getAstricks(endDate, asteriskValue);

    output += "\n" + "Scale: * = $" + String.format("%.2f", asteriskValue);

    return output;

  }

  private String getAstricks(Date startDate, double asteriskValue) {
    String output = "";
    int asterisks = (int) (this.valueOf(startDate) / asteriskValue);
    output += startDate.toString() + "   ";
    for (int i = 0; i < asterisks; i++) {
      output += "*";
    }
    output += "\n";
    return output;
  }

}
