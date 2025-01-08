package stock.commands;

import java.io.File;

import model.Model;

/**
 * Facilitates the save function from controller to model.
 */
public class Save implements StockCommand {
  private final String fileName;
  private final File parent;
  private boolean result;

  /**
   * Saves the active portfolio to the file given.
   * @param fileName the name of the file
   * @param parent the path to this file
   */
  public Save(String fileName, File parent) {
    this.fileName = fileName;
    this.parent = parent;
  }

  @Override
  public void run(Model m) {
    result = m.saveFile(fileName, parent);
  }

  @Override
  public String message() {
    if (result) {
      return "Successfully saved to " + fileName;
    } else {
      return "Failed to save to " + fileName;
    }
  }
}
