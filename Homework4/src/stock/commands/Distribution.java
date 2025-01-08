package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the distribution function from controller to model.
 */
public class Distribution implements StockCommand {
  private final Date d;
  private String result;

  /**
   * Finds the distribution of values of the active portfolio on the given date.
   * @param d the date
   */
  public Distribution(Date d) {
    this.d = d;
  }

  @Override
  public void run(Model m) {
    result = m.distribution(d);
  }

  @Override
  public String message() {
    return result;
  }
}
