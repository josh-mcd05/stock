package stock;

import java.util.List;

/**
 * This is API data in general taken from any API
 * that the user can use for their own purposes.
 */
public interface API {
  /**
   * This displays the data in an array function.
   *
   * @return data as array
   */
  List<String> displayData();
}
