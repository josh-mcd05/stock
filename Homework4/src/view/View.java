package view;

import java.util.function.Consumer;

/**
 * Text interface for the user to work with.
 */
public interface View {

  /**
   * Makes this view visible.
   */
  public void makeVisible();

  /**
   * Sets the commandCallback for this view.
   * @param callback the callback that this view should reference
   */
  public void setCommandCallback(Consumer<String> callback);

  /**
   * Updates the view to show must recent inputs and outputs.
   */
  public void refresh();

  /**
   * Produces an error message if one occurs.
   * @param error the error to be produced
   */
  public void showErrorMessage(String error);

  /**
   * Produces the given string onto the text area at the north end of this view.
   * @param output the string to be shown
   */
  public void appendOutput(String output);

  public void clear();

}
