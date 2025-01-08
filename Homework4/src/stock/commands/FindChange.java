package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the find change function from controller to model.
 */
public class FindChange implements StockCommand {
  private final String s;
  private final Date start;
  private final Date end;
  private double result;

  /**
   * Finds the gain or loss of the given stock over the given time period.
   * @param s the ticker of the stock
   * @param start the start date
   * @param end the end date
   */
  public FindChange(String s, Date start, Date end) {
    this.s = s;
    this.start = start;
    this.end = end;
    result = 0.0;
  }

  @Override
  public void run(Model m) {
    result = m.findChange(s, start, end);
  }

  @Override
  public String message() {
    return "The change of " + s + " from " + start.toString() + " to " + end.toString() + " was: $"
            + String.format("%.2f", result);
  }
}
