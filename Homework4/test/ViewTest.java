import controller.Controller;
import controller.StockController;

/**
 * This tests the view by running it.
 */
public class ViewTest {
  /**
   * This runs our program.
   * @param args default in main
   */
  public static void main(String[] args) {
    Controller controller = new StockController();
    controller.run();
  }

}