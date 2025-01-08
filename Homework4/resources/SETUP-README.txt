Run the jar file by navigating to the file location in a terminal and running 
"java -jar Homework4.jar"

Once in the program, create a new portfolio by running "create-portfolio". You can then buy any stock supported by the API using the command "buy stock-ticker date number-of-shares". Do this will three different stocks to have three different stocks in this portfolio. You can start buying on any date, but cannot buy or sell on a date that is before a day that you have already bought or sold on for that stock.

For example, you can buy 30 stocks of google on May 31 2024 by running the command
 "buy GOOG 2024-05-31 30"
You now cannot buy or sell any stocks of google before May 31 2024. However you can then buy 10 stocks of Nvidia on May 22 2024 by running:
"buy NVDA 2024-05-22 10"

You can query the value of the portfolio on any date, however an error will be produced if the given date is before any trading has happened. For example, continue with the portfolio from above, running the command:
"get-value 2024-05-30"
Will produce the value with just the stocks of Nvidia, since you have not bought any shares of google yet. 

You can find the composition of shares by running "get-composition date" and get the distribution of values of each stock by running "get-distribution date" 


Any stocks and dates supported by the API are supported by our program.


