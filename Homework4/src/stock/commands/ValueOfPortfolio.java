package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the get-value function from controller to model.
 */
public class ValueOfPortfolio implements StockCommand {
  Date date;
  double result;

  /**
   * Finds the total value of the active portfolio on the given date.
   * @param date the date
   */
  public ValueOfPortfolio(Date date) {
    this.date = date;
  }

  @Override
  public void run(Model m) {
    result = m.valueOf(date);
  }

  @Override
  public String message() {
    return "The value of your active portfolio is $" + String.format("%f.2", result);
  }
}
