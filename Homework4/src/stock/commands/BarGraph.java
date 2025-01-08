package stock.commands;

import model.Model;

/**
 * Facilitates creating a bar graph from controller to model.
 */
public class BarGraph implements StockCommand {
  private final String start;
  private final String end;
  private String result;

  /**
   * Creates a bar graph that contains values from start date to end date.
   * @param start the start date
   * @param end the end date
   */
  public BarGraph(String start, String end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public void run(Model m) {
    result = m.barGraph(start, end);
  }

  @Override
  public String message() {
    return result;
  }
}
