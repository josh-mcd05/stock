package stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Organizes data from API for a stock and creates a Stock Object.
 */
public class StockBuilder {
  String ticker;
  private List<SingleStock> values;

  /**
   * Constructor.
   * @param ticker stock symbol
   */
  public StockBuilder(String ticker) {
    this.ticker = ticker;
    values = new ArrayList<SingleStock>();
  }

  /**
   * Builds the stock with user input.
   * @return non-null stock
   */
  public Stock build() {
    List<String> data = new APIExtractor(ticker).displayData();

    for (String entry : data) {
      if (entry == null) {
        int index = data.indexOf(entry);
        data.set(index, "0");
      }
    }

    for (int i = 0; i < data.size(); i += 6) {
      try {
        String dateString = data.get(i);
        Date date = new Date(Integer.parseInt(dateString.substring(8)),
                Integer.parseInt(dateString.substring(5, 7)),
                Integer.parseInt(dateString.substring(0, 4)));
        double open = Double.parseDouble(data.get(i + 1));
        double high = Double.parseDouble(data.get(i + 2));
        double low = Double.parseDouble(data.get(i + 3));
        double close = Double.parseDouble(data.get(i + 4));
        int volume = Integer.parseInt(data.get(i + 5));
        values.add(new SingleStock(date, open, high, low, close, volume));
      }
      catch (Exception e) {
        System.out.println("There was an error at index: " + i / 6);
        throw new RuntimeException(e.getMessage());
      }
    }

    return new Stock(ticker, values);
  }
}
