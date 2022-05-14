package com.example.demo91;

import java.util.List;

public class FakeRepo extends BudgetRepo {
  private List<Budget> budgets;

  @Override
  List<Budget> getAll() {
    return budgets;
  }

  public void setBudgets(List<Budget> budgets) {
    this.budgets = budgets;
  }
}
