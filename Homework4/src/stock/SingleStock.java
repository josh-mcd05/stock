package stock;

/**
 * Represents the values of a single stock on a single day.
 */
public class SingleStock {
  private final Date date;
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final int volume;

  /**
   * Constructor.
   * @param date date
   * @param open opening price
   * @param high highest price
   * @param low lowest price
   * @param close price at market close
   * @param volume number of stocks
   */
  public SingleStock(Date date, double open, double high, double low, double close, int volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  /*
  public String vals() {
    return this.date.toString() + "," + String.valueOf(this.open) + "," +
            String.valueOf(this.high) + "," + this.low + "," + this.close;
  }
   */

  /**
   * This gets the date of the single stock object.
   * @return date
   */
  public Date getDate() {
    return date;
  }

  /**
   * This gets the high of the single stock object.
   * @return high
   */
  public double getOpen() {
    return open;
  }

  /**
   * This gets the close of the single stock object.
   * @return close
   */
  public double getClose() {
    return close;
  }

  /**
   * This gets the high of the single stock object.
   * @return high
   */
  public double getHigh() {
    return high;
  }

  /**
   * This gets the low of the single stock object.
   * @return low
   */
  public double getLow() {
    return low;
  }

  /**
   * This gets the volume of the single stock object.
   * @return volume
   */
  public int getVolume() {
    return volume;
  }
}
