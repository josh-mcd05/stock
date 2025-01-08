package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the average function from controller to model.
 */
public class Average implements StockCommand {
  private final String s;
  private final Date d;
  private final int x;
  private double result;

  /**
   * Runs the average runction with the given parameters.
   * @param s the ticker of the stock to be averaged
   * @param d the date to be averaged from
   * @param x the number of days backwards that will be averaged
   */
  public Average(String s, Date d, int x) {
    this.s = s;
    this.d = d;
    this.x = x;
  }

  @Override
  public void run(Model m) {
    result = m.findAverage(s, d, x);
  }

  @Override
  public String message() {
    return "The " + x + "-day average from " + d.toString() + " is: $"
            + String.format("%.2f", result);
  }
}
