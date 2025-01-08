package stock.commands;

import model.Model;

/**
 * Facilitates the remove function from controller to model.
 */
public class Remove implements StockCommand {
  private final String s;

  /**
   * Removes the given stock form the portfolio.
   * @param s the stock to be removed
   */
  public Remove(String s) {
    this.s = s;
  }

  @Override
  public void run(Model m) {
    m.removeStock(s);
  }

  @Override
  public String message() {
    return "Stock " + s + " removed";
  }
}
