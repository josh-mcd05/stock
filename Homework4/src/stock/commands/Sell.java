package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the sell function from controller to model.
 */
public class Sell implements StockCommand {
  private final String s;
  private final Date d;
  private final double shares;

  /**
   * Sells the given number of shares of the given stock on the given date.
   * @param s the stock
   * @param d the date
   * @param shares the number of shares
   */
  public Sell(String s, Date d, double shares) {
    this.s = s;
    this.d = d;
    this.shares = shares;
  }

  @Override
  public void run(Model m) {
    m.sell(s, d, shares);
  }

  @Override
  public String message() {
    return "Successfully sold " + shares + " shares of " + s + " on " + d.toString();
  }
}
