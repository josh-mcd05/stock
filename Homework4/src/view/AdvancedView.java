package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;


import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Creates a more user-friendly GUI with buttons and designated user inputs.
 * Uses java swing to create a GUI.
 */
public class AdvancedView extends AView implements ActionListener {
  private JTextArea textOutput;
  Consumer<String> commandCallback;
  String command;
  JPanel mainPanel;
  JButton enter;
  JTextField tickerText;
  JTextField dateText;
  JTextField shareText;
  JPanel parameterPanel;

  /**
   * Constructs all parts of the GUI.
   */
  public AdvancedView() {
    super();
    JPanel createPanel;
    JButton create;
    this.setTitle("Portfolio Manager");
    this.setSize(500, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);


    createPanel = new JPanel();
    createPanel.setLayout(new FlowLayout());
    this.add(createPanel, BorderLayout.NORTH);

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(2, 1));
    this.add(mainPanel, BorderLayout.CENTER);

    create = new JButton("Create");
    create.setActionCommand("create");
    create.addActionListener(this);
    createPanel.add(create);

    JButton fileOpenButton = new JButton("Load a portfolio");
    fileOpenButton.setActionCommand("Load");
    fileOpenButton.addActionListener(this);
    createPanel.add(fileOpenButton);

    textOutput = new JTextArea(40, 40);
    mainPanel.add(textOutput);

    parameterPanel = new JPanel();

    tickerText = new JTextField(4);
    tickerText.setBorder(BorderFactory.createTitledBorder("Ticker"));
    parameterPanel.add(tickerText);
    tickerText.setVisible(false);

    dateText = new JTextField(12);
    dateText.setBorder(BorderFactory.createTitledBorder("YYYY-MM-DD"));
    parameterPanel.add(dateText);
    dateText.setVisible(false);

    shareText = new JTextField(4);
    shareText.setBorder(BorderFactory.createTitledBorder("Shares"));
    parameterPanel.add(shareText);
    shareText.setVisible(false);

    enter = new JButton("Enter");
    enter.setActionCommand("Enter");
    enter.addActionListener(this);
    parameterPanel.add(enter);
    enter.setVisible(false);

    mainPanel.add(parameterPanel);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    clear();
    switch (e.getActionCommand()) {
      case "create":
        commandCallback.accept("create-portfolio");
        this.addOptions();
        break;
      case "Load":
        command = "load ";
        final JFileChooser fchooserLoad = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files", "txt");
        fchooserLoad.setFileFilter(filter);
        int retvalueLoad = fchooserLoad.showOpenDialog(this);
        if (retvalueLoad == JFileChooser.APPROVE_OPTION) {
          File f = fchooserLoad.getSelectedFile();
          command += f.getAbsolutePath();
          commandCallback.accept(command);
          this.addOptions();
        } else {
          appendOutput("Please select a valid file");
        }
        break;
      case "save":
        command = "save ";
        final JFileChooser fchooserSave = new JFileChooser(".");
        int retvalueSave = fchooserSave.showSaveDialog(this);
        if (retvalueSave == JFileChooser.APPROVE_OPTION) {
          File f = fchooserSave.getSelectedFile();
          System.out.println(f.getAbsolutePath().replaceAll(f.getName(), ""));
          command += f.getName() + " "
                  + f.getAbsolutePath().replaceAll(f.getName(), "");
          System.out.println(command);
          commandCallback.accept(command);
        } else {
          appendOutput("Please select a valid file");
        }
        break;
      case "buy":
        command = "buy ";
        enter.setVisible(false);
        tickerText.setVisible(true);
        dateText.setVisible(true);
        shareText.setVisible(true);
        enter.setVisible(true);
        break;
      case "sell":
        command = "sell ";
        enter.setVisible(false);
        tickerText.setVisible(true);
        dateText.setVisible(true);
        shareText.setVisible(true);
        enter.setVisible(true);
        break;
      case "value":
        command = "get-value ";
        tickerText.setVisible(false);
        dateText.setVisible(true);
        shareText.setVisible(false);
        enter.setVisible(true);
        break;
      case "composition":
        command = "get-composition ";
        tickerText.setVisible(false);
        dateText.setVisible(true);
        shareText.setVisible(false);
        enter.setVisible(true);
        break;
      case "Enter":
        if (command.charAt(0) == 'b' || command.charAt(0) == 's') {
          command += tickerText.getText() + " " + dateText.getText() + " " + shareText.getText();
        } else {
          command += dateText.getText();
        }
        tickerText.setText("");
        tickerText.setVisible(false);
        dateText.setText("");
        dateText.setVisible(false);
        shareText.setText("");
        shareText.setVisible(false);
        enter.setVisible(false);
        commandCallback.accept(command);
        break;
      default:
        //nothing
    }
  }

  private void addOptions() {
    JPanel buttonPanel;
    JButton buy;
    JButton sell;
    JButton value;
    JButton composition;
    JButton save;

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    buy = new JButton("Buy");
    buy.setActionCommand("buy");
    buy.addActionListener(this);
    buttonPanel.add(buy);

    sell = new JButton("Sell");
    sell.setActionCommand("sell");
    sell.addActionListener(this);
    buttonPanel.add(sell);

    value = new JButton("Value");
    value.setActionCommand("value");
    value.addActionListener(this);
    buttonPanel.add(value);

    composition = new JButton("Composition");
    composition.setActionCommand("composition");
    composition.addActionListener(this);
    buttonPanel.add(composition);

    save = new JButton("Save");
    save.setActionCommand("save");
    save.addActionListener(this);
    buttonPanel.add(save);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    this.commandCallback = callback;
  }

  @Override
  public void appendOutput(String output) {
    this.textOutput.append(output);
  }

  @Override
  public void clear() {
    this.textOutput.setText("");
  }
}
