package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.StockModel;
import stock.commands.Add;
import stock.commands.Average;
import stock.commands.BarGraph;
import stock.commands.Buy;
import stock.commands.Composition;
import stock.commands.CreatePortfolio;
import stock.commands.Crossover;
import stock.commands.Distribution;
import stock.commands.FindChange;
import stock.commands.GetData;
import stock.commands.Load;
import stock.commands.Redistribute;
import stock.commands.Remove;
import stock.commands.Save;
import stock.commands.Sell;
import stock.commands.SetPortfolio;
import stock.commands.StockCommand;
import stock.commands.ValueOfPortfolio;
import view.AdvancedView;
import view.StockView;
import view.View;
import model.Model;
import stock.Date;

/**
 * Controller.Controller for stocks and portfolios.
 * Will handle inputs from the user, and output results into a text-based interface.
 */
public class StockController implements Controller {
  private final View view;
  private final Model model;
  private Redistribute redistribute;

  /**
   * Create a controller to work with the view.
   * and an empty portfolio.
   */
  public StockController() {
    model = new StockModel();
    this.view = new StockView();
  }

  public StockController(View view) {
    model = new StockModel();
    this.view = view;
  }

  @Override
  public void run() {
    this.view.setCommandCallback(this::accept);
    this.view.makeVisible();
    this.view.appendOutput(writeMenu());
  }

  /**
   * This processes the user command for each case.
   *
   * @param userInstruction user command
   * @return menu of options
   */
  private String processCommand(String userInstruction) {
    StringBuilder sb = new StringBuilder();
    Scanner sc = new Scanner(userInstruction);
    StockCommand cmd = null;

    while (sc.hasNext()) {
      String command = sc.next();

      switch (command) {
        case "add":
          cmd = new Add(sc.next());
          break;
        case "remove":
          cmd = new Remove(sc.next());
          break;
        case "find-change":
          cmd = new FindChange(sc.next(), toDate(sc.next()), toDate(sc.next()));
          break;
        case "avg":
          cmd = new Average(sc.next(), toDate(sc.next()), sc.nextInt());
          break;
        case "crossover":
          cmd = new Crossover(sc.next(), toDate(sc.next()), toDate(sc.next()), sc.nextInt());
          break;
        case "get-value":
          cmd = new ValueOfPortfolio(toDate(sc.next()));
          break;
        case "get-data":
          cmd = new GetData(sc.next(), toDate(sc.next()));
          break;
        case "create-portfolio":
          cmd = new CreatePortfolio();
          break;
        case "set-portfolio":
          cmd = new SetPortfolio(sc.nextInt());
          break;
        case "buy":
          cmd = new Buy(sc.next(), toDate(sc.next()), sc.nextDouble());
          break;
        case "sell":
          cmd = new Sell(sc.next(), toDate(sc.next()), sc.nextDouble());
          break;
        case "get-composition":
          cmd = new Composition(toDate(sc.next()));
          break;
        case "get-distribution":
          cmd = new Distribution(toDate(sc.next()));
          break;
        case "redistribute":
          List<String> tickers = model.getStocks();
          if (sc.hasNext()) {
            Date d = toDate(sc.next());
            Map<String, Double> percents = new HashMap<String, Double>();
            for (String ticker : tickers) {
              percents.put(ticker, sc.nextDouble() / 100);
            }
            cmd = new Redistribute(d, percents);
          } else {
            StringBuilder output = new StringBuilder();
            sb.append("No percents found, please enter percents for stocks in the following order:"
                    + System.lineSeparator());
            for (String ticker : tickers) {
              sb.append(ticker + " ");
            }
            this.output(output.toString() + System.lineSeparator());
          }
          break;
        case "save":
          cmd = new Save(sc.next(), new File(sc.next()));
          break;
        case "load":
          cmd = new Load(new File(sc.next()));
          break;
        case "get-bar-graph":
          cmd = new BarGraph(sc.next(), sc.next());
          break;
        case "clear":
          view.clear();
          output(this.writeMenu());
          break;
        default:
          sb.append("Not valid instruction, please try again." + System.lineSeparator());
          break;
      }
      if (cmd != null) {
        cmd.run(model);
        sb.append(cmd.message() + System.lineSeparator());
      }
    }
    return sb.toString();
  }

  /**
   * This converts a string to a date.
   *
   * @param date date as a string
   * @return
   */
  private Date toDate(String date) {
    List<String> newData = Arrays.asList(date.split("-"));
    List<Integer> finalData = new ArrayList<>();
    for (String x : newData) {
      finalData.add(Integer.valueOf(x));
    }
    return new Date(finalData.get(2), finalData.get(1), finalData.get(0));
  }

  /**
   * This accepts the user input.
   *
   * @param command user input
   */
  private void accept(String command) {
    String status = "";

    try {
      status = processCommand(command);
    } catch (Exception ex) {
      error(ex);
    }
    this.output(status);
    view.refresh();
  }


  /**
   * This is the menu for the user to look at and select an option.
   *
   * @return menu
   */
  private String writeMenu() {
    StringBuilder sb = new StringBuilder();
    if (view.getClass() == StockView.class) {
      sb.append("Welcome to your stock portfolio!" + System.lineSeparator());
      sb.append("To start, create a portfolio." + System.lineSeparator());
      sb.append("All dates should be entered in YYYY-MM-DD format" + System.lineSeparator());
      sb.append("The possible commands are: " + System.lineSeparator());
      sb.append("create-portfolio (creates a new portfolio)" + System.lineSeparator());
      sb.append("set-portfolio num (sets the active portfolio to the given number"
              + System.lineSeparator());
      sb.append("buy stock-ticker date num-shares (buy this number of shares on the given date)"
              + System.lineSeparator());
      sb.append("sell stock-ticker date num-shares (sell this number of shares on the given date)"
              + System.lineSeparator());
      sb.append("find-change stock-ticker start-date end-date"
              + "(finds the gain or loss of the given stock from start"
              + " date to end date)" + System.lineSeparator());
      sb.append("get-data stock-ticker date "
              + "(produces the data of the given stock on the given date)"
              + System.lineSeparator());
      sb.append("avg stock-ticker date x-days"
              + " (finds the average of the active stock on the given date over"
              + " x-days)" + System.lineSeparator());
      sb.append("crossover stock-ticker start-date end-date x-days"
              + " (finds the x-day crossovers between start date and end date"
              + System.lineSeparator());
      sb.append("get-value date (finds the total value of your portfolio on the given date)"
              + System.lineSeparator());
      sb.append("get-composition date (Find the composition of your portfolio in number of "
              + "each shares of each stock you own)" + System.lineSeparator());
      sb.append("get-distribution date (Find the distribution of your portfolio in value "
              + "of each of the stocks you own)" + System.lineSeparator());
      sb.append("redistribute date percent1 percent2 ... (redistributes stock shares based on"
              + " given percents of the total value of this portfolio. To retrieve the order of"
              + " stocks, type 'redistribute' with no parameters)" + System.lineSeparator());
      sb.append("save file-name file-path (saves the current portfolio as a .txt file with the"
              + " given name to the given path" + System.lineSeparator());
      sb.append("load file-path (load a portfolio from a .txt file" + System.lineSeparator());
      sb.append("get-bar-graph start-date end-date (produces a bar graph showing the value"
              + " of this portfolio in the given range)" + System.lineSeparator());
      sb.append("clear (clears the output)" + System.lineSeparator());
    }
    else if (view.getClass() == AdvancedView.class) {
      sb.append("Welcome to portfolio manager!" + System.lineSeparator());
      sb.append("To start, create a portfolio, or load one in." + System.lineSeparator());
    }


    return sb.toString();
  }

  /**
   * This outputs a certain message.
   *
   * @param message message in menu
   */
  private void output(String message) {
    view.appendOutput(message);
  }

  /**
   * This returns an error if there is one.
   *
   * @param e error
   */
  private void error(Exception e) {
    view.showErrorMessage(e.getMessage());
  }
}
