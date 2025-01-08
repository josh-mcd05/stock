package stock.commands;

import java.io.File;

import model.Model;

/**
 * Facilitates the load function from controller to model.
 */
public class Load implements StockCommand {
  private final File parent;
  private boolean result;

  /**
   * Loads the given text file in as a portfolio.
   * @param parent the .txt file to be converted
   */
  public Load(File parent) {
    this.parent = parent;
  }

  @Override
  public void run(Model m) {
    result = m.loadFile(parent);
  }

  @Override
  public String message() {
    if (result) {
      return "Successfully loaded " + parent.getName();
    } else {
      return "Failed to load " + parent.getName();
    }
  }
}
