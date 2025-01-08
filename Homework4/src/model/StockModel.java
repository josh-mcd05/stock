package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import stock.Date;
import stock.Portfolio;

/**
 * This represents the functions that the user can implement, which include
 * creating a portfolio, getting a portfolio, adding a stock, removing a stock,
 * finding the change between two dates, finding the average of a stock over
 * a certain period of time, finding the crossovers for a certain time period and stock
 * , the value of a portfolio at a date, buying/selling stock, composition of a portfolio
 * at a certain date, distribution of portfolio at certain date, etc.
 */
public class StockModel implements Model {
  private List<Portfolio> portfolio;
  private Portfolio activePortfolio;

  /**
   * Constructor.
   */
  public StockModel() {
    portfolio = new ArrayList<Portfolio>();
    activePortfolio = null;
  }

  @Override
  public Portfolio getPortfolio(int id) {
    return portfolio.get(id);
  }

  @Override
  public void createPortfolio() {
    activePortfolio = new Portfolio();
    portfolio.add(activePortfolio);
  }

  @Override
  public void addStock(String ticker) {
    activePortfolio.addStock(ticker);
  }

  @Override
  public void removeStock(String ticker) {
    activePortfolio.removeStock(ticker);
  }

  @Override
  public double findChange(String ticker, Date start, Date end) {
    return activePortfolio.getStock(ticker).findChange(start, end);
  }

  @Override
  public double findAverage(String ticker, Date start, int x) {
    return activePortfolio.getStock(ticker).xDayAverage(start, x);
  }

  @Override
  public List<String> findCrossovers(String ticker, Date start, Date end, int x) {
    List<String> dates = new ArrayList<String>();
    while (!start.toString().equals(end.toString())) {
      try {
        if (activePortfolio.getStock(ticker).xDayCrossover(start, x)) {
          dates.add(start.toString());
        }
      } catch (Exception e) {
        //Date not found, continue to next.
      }
      start.advance(1);
    }
    return dates;
  }

  @Override
  public double valueOf(Date date) {
    return activePortfolio.valueOf(date);
  }

  @Override
  public String getData(String ticker, Date date) {
    return activePortfolio.getStock(ticker).getDataOfDate(date);
  }

  @Override
  public void setPortfolio(int index) {
    activePortfolio = portfolio.get(index);
  }

  @Override
  public void buy(String ticker, Date date, double shares) {
    activePortfolio.buy(ticker, shares, date);
  }

  @Override
  public void sell(String ticker, Date date, double shares) {
    activePortfolio.sell(ticker, shares, date);
  }

  @Override
  public String composition(Date date) {
    return activePortfolio.findComposition(date);
  }

  @Override
  public String distribution(Date date) {
    return activePortfolio.valueDistibution(date);
  }

  @Override
  public List<String> getStocks() {
    return activePortfolio.getAllStocks();
  }

  @Override
  public void redistribute(Date date, Map<String, Double> percents) {
    activePortfolio.redistribute(date, percents);
  }

  @Override
  public boolean saveFile(String fileName, File parent) {
    try {
      activePortfolio.saveToFile(fileName, parent);
      return true;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  @Override
  public boolean loadFile(File parent) {
    try {
      activePortfolio = new Portfolio().loadFromFile(parent);
      portfolio.add(activePortfolio);
      return true;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  @Override
  public String barGraph(String start, String end) {
    return activePortfolio.valueOverTime(
            start, end, (portfolio.indexOf(activePortfolio) + 1));
  }


}
