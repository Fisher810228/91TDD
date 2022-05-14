package com.example.demo91;

import java.util.ArrayList;
import java.util.List;

public class BudgetRepo {

  public void setData(List<Budget> data) {
    this.data = data;
  }

  List<Budget> data =new ArrayList<>();
  List<Budget> getAll(){
   return data;
  }
}
