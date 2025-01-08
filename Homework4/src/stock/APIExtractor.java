package stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This takes in stock data from the given API by
 * reading each line of data one by one using the key
 * and the stock ticker. It then separates the data using commas
 * and places it inside an array so that the data is
 * easy to access/manipulate.
 */
public class APIExtractor implements API{
  private URL url;
  private final String APIKey = "J6DIGO0PAQSMIQ5O";
  private String stockTicker;

  /**
   * Constructor.
   *
   * @param stockTicker symbol of stock
   */
  public APIExtractor(String stockTicker) {
    this.stockTicker = stockTicker;
    try {
      this.url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockTicker + "&apikey=" + APIKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
  }

  /**
   * This displays the data in an array function.
   *
   * @return data as array
   */
  public List<String> displayData() {
    InputStream in = null;
    String data = "";
    ArrayList<String> finalData = new ArrayList<String>();
    String[] newData;

    try {
      in = url.openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      while (reader.readLine() != null) {
        data += reader.readLine() + ",";
      }

      newData = data.split(",");
      finalData.addAll(Arrays.asList(newData));
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockTicker);
    }



    return finalData.subList(0,finalData.size()-1);

  }
}
