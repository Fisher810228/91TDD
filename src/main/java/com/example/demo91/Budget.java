package com.example.demo91;

public class Budget {
  String yearMonth;

  public Budget(String yearMonth, int amount) {

    this.yearMonth = yearMonth;
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  int amount;

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }
}
