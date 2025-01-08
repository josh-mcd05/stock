package stock.commands;

import model.Model;
import stock.Date;

/**
 * Facilitates the get data function from controller to model.
 */
public class GetData implements StockCommand {
  private final String s;
  private final Date d;
  private String result;

  /**
   * Gets all values of the given stock on the given date.
   * @param s the ticker of the stock
   * @param d the date to find the values on
   */
  public GetData(String s, Date d) {
    this.s = s;
    this.d = d;
  }

  @Override
  public void run(Model m) {
    result = m.getData(s, d);
  }

  @Override
  public String message() {
    return result;
  }
}
