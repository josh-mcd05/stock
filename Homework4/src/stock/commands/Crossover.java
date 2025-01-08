package stock.commands;

import java.util.List;

import model.Model;
import stock.Date;

/**
 * Facilitates the crossover function from controller to model.
 */
public class Crossover implements StockCommand {
  private final String s;
  private final Date start;
  private final Date end;
  private final int x;
  private List<String> result;

  /**
   * Finds all crossovers between the two given dates for the given stock with the given x-day.
   * average.
   * @param s the ticker of the stock
   * @param start the start date
   * @param end the end date
   * @param x the x in the x-day average used to calculate
   */
  public Crossover(String s, Date start, Date end, int x) {
    this.s = s;
    this.start = start;
    this.end = end;
    this.x = x;
  }

  @Override
  public void run(Model m) {
    result = m.findCrossovers(s, start, end, x);
  }

  @Override
  public String message() {
    StringBuilder sb = new StringBuilder();
    sb.append("The following dates are an " + x + "-day crossover for " + s + ": "
            + System.lineSeparator());
    for (String s : result) {
      sb.append(s + System.lineSeparator());
    }

    return sb.toString();
  }
}
