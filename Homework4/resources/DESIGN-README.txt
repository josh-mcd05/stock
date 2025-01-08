The design of our program follows the model, view, controller design recipe.

The Model is represented by the StockModel class, adn is designed to interact entirely with the Portfolio class.
A Portfolio is a collection of stocks, each individually represented by a Stock class.
A Stock class is constructed using the StockBuilder class. A Stock is made up
of SingleStock's, each of which contain all data from the AlphaVantage API for a single day.
One SingleStock contains the date, the open, high, low, and close price, and the volume.
The data is extracted from the API using the APIExtractor class, and organized into an array of Strings, which are then sorted
by date by the StockBuilder class, constructing all the SingleStocks to be assigned to the Stock, which is placed into
the portfolio.
All calculations are done in either the Portfolio class or the Stock class. The Model exists as a transport for
the parameters, and a middle man for the Controller and the Portfolio.

The controller is the StockController class, and exists as a filter for user input to be organized and sent to the model
correctly for the Portfolio to be able to calculate. It will take in user command sent to it by the view and handle
creating the strings and values needed to calculate information about the Portfolio.

The view is the StockView class, and it handles gathering information from the user, and output information.
It creates a window that has a text box that can take user command in a very specific way, that is outlined as
a menu in the output box.



In Assignment 6, we created a new view, the AdvancedView class, that creates a more user-friendly and less
error prone than the original view. It utilizes buttons as command inputs and then very designated text-boxes
with clear instructions for the user to input different parameters for the command. It is a much more user-inclined
GUI that the original.
