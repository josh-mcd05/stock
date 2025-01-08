import controller.Controller;
import controller.StockController;
import view.AdvancedView;
import view.View;

/**
 * Main method for stock program.
 * Sets up portfolio with Google, nvidia, and amazon already contained.
 */
public class StockProgram {
  /**
   * Main function.
   * @param args arguments
   */
  public static void main(String[] args) {
    View view = new AdvancedView();
    Controller controller = new StockController(view);
    controller.run();
  }
}
