package stock;

/**
 * Represents a date within a calendar year.
 */
public class Date {
  private int day;
  private int month;
  private int year;

  /**
   * Represents a date in a calendar year.
   *
   * @param day   the day of the month
   * @param month the month of the year
   * @param year  the year after 0
   */
  public Date(int day, int month, int year) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid month");
    } else if (year < 0) {
      throw new IllegalArgumentException("Invalid year");
    } else if (month <= 7 && month % 2 == 1 || month > 7 && (month % 2) == 0) {
      if (day < 1 || day > 31) {
        throw new IllegalArgumentException("Invalid day");
      }
    } else if (day < 1 || day > 30) {
      throw new IllegalArgumentException("Invalid day");
    } else if (month == 2) {
      if (day > 29) {
        throw new IllegalArgumentException("Invalid days for February");
      } else if (!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day == 29) {
        throw new IllegalArgumentException("Invalid day for February");
      }
    }
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Constructor.
   * @param month month
   * @param year year
   */
  public Date(int month, int year) {
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid month");
    } else if (year < 0) {
      throw new IllegalArgumentException("Invalid year");
    }
    this.month = month;
    this.year = year;
    if (month <= 7 && month % 2 == 1 || month > 7 && (month % 2) == 0) {
      this.day = 31;
    }
    else if (month == 2) {
      if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
        this.day = 29;
      }
      else {
        this.day = 28;
      }
    }
    else {
      this.day = 30;
    }
  }

  /**
   * Constructor.
   * @param year year
   */
  public Date(int year) {
    this.day = 31;
    this.month = 12;
    this.year = year;
  }


  /**
   * Advances the date by the given number of days.
   *
   * @param days number of days to be moved forward
   */
  public void advance(int days) {
    days = this.day + days;
    this.day = 0;
    if (days <= 0) {
      month--;
      while (days < 0) {
        if (month < 1) {
          month = 12 + month;
          year--;
        }
        if (this.has31Days()) {
          if (days <= -31) {
            this.month--;
            days = days + 31;
          } else {
            this.day = 31 + days;
            break;
          }
        } else if (month == 2) {
          if (this.isLeapYear()) {
            if (days <= -29) {
              this.month--;
              days = days + 29;
            } else {
              this.day = 29 + days;
              break;
            }
          } else {
            if (days <= -28) {
              this.month--;
              days = days + 28;
            } else {
              this.day = 28 + days;
              break;
            }
          }
        } else if (days <= -30) {
          this.month--;
          days = days + 30;
        } else {
          this.day = 30 + days;
          break;
        }
      }
      if (month == 0) {
        this.year--;
        this.month = 12;
      }
    }
    while (days > 0) {
      if (month > 12) {
        month = month % 12;
        year++;
      }
      if (this.has31Days()) {
        if (days > 31) {
          this.month++;
          days = days - 31;
        } else {
          this.day = days;
          break;
        }
      } else if (month == 2) {
        if (this.isLeapYear()) {
          if (days > 29) {
            this.month++;
            days = days - 29;
          } else {
            this.day = days;
            break;
          }
        } else {
          if (days > 28) {
            this.month++;
            days = days - 28;
          } else {
            this.day = days;
            break;
          }
        }
      } else if (days > 30) {
        this.month++;
        days = days - 30;
      } else {
        this.day = days;
        break;
      }
    }

    if (this.day == 0) {
      if (this.has31Days()) {
        this.day = 31;
      } else if (this.month == 2) {
        if (this.isLeapYear()) {
          this.day = 29;
        } else {
          this.day = 28;
        }
      } else {
        this.day = 30;
      }
    }
  }

  /**
   * Determines if this date is a leap year.
   *
   * @return true if this date is within a leap year
   */
  private boolean isLeapYear() {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
  }

  /**
   * Determines if this date has 31 days.
   *
   * @return true if this month can have 31 days
   */
  private boolean has31Days() {
    return month <= 7 && month % 2 == 1 || month > 7 && (month % 2) == 0;
  }


  /**
   * Converts this date to a string in the format of YYYY-MM-DD.
   *
   * @return A String in the format of YYYY-MM-DD
   */
  public String toString() {
    return String.format("%04d-%02d-%02d", this.year, this.month, this.day);
  }

  /**
   * This checks if a date is before another.
   * @param date other date
   * @return true if yes, false if no
   */
  public boolean isBefore(Date date) {
    if (this.year < date.year) {
      return true;
    }
    else if (this.year == date.year) {
      if (this.month < date.month) {
        return true;
      }
      else if (this.month == date.month) {
        return this.day <= date.day;
      }
    }
    return false;
  }

}



