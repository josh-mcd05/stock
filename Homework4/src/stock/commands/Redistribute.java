package stock.commands;

import java.util.Map;

import model.Model;
import stock.Date;

/**
 * Facilitates the redistribute method from controller to model.
 */
public class Redistribute implements StockCommand {
  private final Date date;
  private final Map<String, Double> percents;

  /**
   * Redistributes the shares of each stock in the portfolio to the given percents.
   * @param date the date to perform the redistribution
   * @param percents the percents
   */
  public Redistribute(Date date, Map<String, Double> percents) {
    this.date = date;
    this.percents = percents;
  }

  @Override
  public void run(Model m) {
    m.redistribute(date, percents);
  }

  @Override
  public String message() {
    return "Successfully redistributed portfolio";
  }
}
