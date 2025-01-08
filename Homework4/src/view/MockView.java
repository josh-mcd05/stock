package view;

import java.util.function.Consumer;

/**
 * Sample view for testing.
 */
public class MockView implements View {


  @Override
  public void makeVisible() {
    //only for testing
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    //only for testing
  }

  @Override
  public void refresh() {
    //only for testing
  }

  @Override
  public void showErrorMessage(String error) {
    //only for testing
  }

  @Override
  public void appendOutput(String output) {
    System.out.println(output);
  }

  @Override
  public void clear() {
    //only for testing
  }
}
