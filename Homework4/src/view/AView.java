package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Abstract view class.
 * Defines methods that are the same for both views, and will be the same for future views.
 */
public abstract class AView extends JFrame implements View {

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(
            this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

}
