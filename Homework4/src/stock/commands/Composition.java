package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the composition function from controller to model.
 */
public class Composition implements StockCommand {
  private final Date d;
  private String result;

  /**
   * Produces the composition of shares for the active portfolio on the given date.
   * @param d the date
   */
  public Composition(Date d) {
    this.d = d;
  }


  @Override
  public void run(Model m) {
    result = m.composition(d);
  }

  @Override
  public String message() {
    return result;
  }
}
