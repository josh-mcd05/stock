package stock.commands;

import model.Model;

/**
 * Facilitates the add function from controller to model.
 */
public class Add implements StockCommand {
  private final String s;

  /**
   * Create a add function with the given parameter.
   * @param s the ticker of the stock to be added
   */
  public Add(String s) {
    this.s = s;
  }

  @Override
  public void run(Model m) {
    m.addStock(s);
  }

  @Override
  public String message() {
    return "Stock " + s + " added to portfolio";
  }
}
