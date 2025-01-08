package stock.commands;

import model.Model;

/**
 * Facilitates setting the active portfolio from controller to model.
 */
public class SetPortfolio implements StockCommand {
  int index;

  /**
   * Sets the active portfolio.
   * @param index the index of portfolio to be set
   */
  public SetPortfolio(int index) {
    this.index = index;
  }

  @Override
  public void run(Model m) {
    m.setPortfolio(index - 1);
  }

  @Override
  public String message() {
    return "Portfolio " + index + " active";
  }
}
