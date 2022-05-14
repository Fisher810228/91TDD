package com.example.demo91;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BudgetServiceTest {

  @Test
  public void invalid_range() {

    FakeRepo fakeRepo = new FakeRepo();
    BudgetService budgetService = new BudgetService(fakeRepo);
    double expect = budgetService.query(LocalDate.of(2022, 06, 01), LocalDate.of(2022, 1, 1));
    assertEquals(expect, 0);

  }

  @Test
  public void no_overlap_budget() {

    FakeRepo fakeRepo = new FakeRepo();
    fakeRepo.setBudgets(List.of(new Budget("202205", 31)));
    BudgetService budgetService = new BudgetService(fakeRepo);

    double expect = budgetService.query(LocalDate.of(2022, 4, 1), LocalDate.of(2022, 4, 2));
    assertEquals(expect, 0);

  }

  @Test
  public void aMonthBuget() {

    FakeRepo fakeRepo = new FakeRepo();
    fakeRepo.setBudgets(List.of(new Budget("202203", 31)));
    BudgetService budgetService = new BudgetService(fakeRepo);

    double expect = budgetService.query(LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 31));
    assertEquals(expect, 31);

  }

  @Test
  public void aDayBuget() {
    FakeRepo fakeRepo = new FakeRepo();
    fakeRepo.setBudgets(List.of(new Budget("202206", 9000)));
    BudgetService budgetService = new BudgetService(fakeRepo);
    double expect = budgetService.query(LocalDate.of(2022, 6, 10), LocalDate.of(2022, 6, 10));
    assertEquals(expect, 300);
  }

  @Test
  public void crossMonthBudget() {
    FakeRepo fakeRepo = new FakeRepo();
    fakeRepo.setBudgets(List.of(
        new Budget("202201", 3100),
        new Budget("202202", 5600)
    ));
    BudgetService budgetService = new BudgetService(fakeRepo);
    double expect = budgetService.query(
        LocalDate.of(2022, 1, 1),
        LocalDate.of(2022, 2, 28));
    assertEquals(expect, 8700);
  }

  @Test
  public void crossThreeMonthBudget() {
    FakeRepo fakeRepo = new FakeRepo();
    fakeRepo.setBudgets(List.of(
        new Budget("202201", 3100),
        new Budget("202202", 5600),
            new Budget("202203", 31)

    ));
    BudgetService budgetService = new BudgetService(fakeRepo);
    double expect = budgetService.query(
        LocalDate.of(2022, 1, 1),
        LocalDate.of(2022, 3, 31));
    assertEquals(expect, 8731);
  }
}