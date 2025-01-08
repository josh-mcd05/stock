package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the buy function from controller to model.
 */
public class Buy implements StockCommand {
  private final String s;
  private final Date d;
  private final double shares;

  /**
   * Buys the given stock on the given date for the given number of shares.
   * @param s the ticker of the stock to be bought
   * @param d the date to buy
   * @param shares the number of shares to buy
   */
  public Buy(String s, Date d, double shares) {
    this.s = s;
    this.d = d;
    this.shares = shares;
  }

  @Override
  public void run(Model m) {
    m.buy(s, d, shares);
  }

  @Override
  public String message() {
    return "Successfully bought " + shares + " shares of " + s + " on " + d.toString();
  }
}
