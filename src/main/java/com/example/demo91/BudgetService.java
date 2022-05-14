package com.example.demo91;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BudgetService {

  public BudgetService(BudgetRepo budgetRepo) {
    this.budgetRepo = budgetRepo;
  }

  BudgetRepo budgetRepo;

  double query(LocalDate start, LocalDate end) {
    int amount = 0;
    if (end.isBefore(start)) {
      return 0;
    }

      List<Budget> all = budgetRepo.getAll();
      for (Budget budget : all) {
        String yearMonth = budget.getYearMonth();
        DateTimeFormatter yyyyMM = DateTimeFormatter.ofPattern("yyyyMM");
        YearMonth yearMonth1 = YearMonth.parse(yearMonth, yyyyMM);

        if(yearMonth1.isBefore(YearMonth.from(start))|| yearMonth1.isAfter(YearMonth.from(end)))
          continue;
        if(end.getYear() == start.getYear() &&
            end.getMonthValue() == start.getMonthValue()
        && yearMonth1.getYear() == start.getYear() &&
            yearMonth1.getMonthValue() == start.getMonthValue()){
          Period between = Period.between(start, end);
          amount = amount + budget.getAmount() / yearMonth1.lengthOfMonth() * (between.getDays()+1) ;
        } else if (yearMonth1.getYear() == start.getYear() &&
            yearMonth1.getMonthValue() == start.getMonthValue()) {
          Period between = Period.between(start, yearMonth1.atEndOfMonth());
          amount = amount + budget.getAmount() / yearMonth1.lengthOfMonth() * (between.getDays()+1) ;

        } else if(yearMonth1.getYear() == end.getYear() &&
            yearMonth1.getMonthValue() == end.getMonthValue()){
          Period between = Period.between(yearMonth1.atDay(1), end);
          amount = amount + budget.getAmount() / yearMonth1.lengthOfMonth() * (between.getDays()+1) ;
        } else {

          amount = amount + budget.getAmount();
        }

      }
    return amount;

  }
}
