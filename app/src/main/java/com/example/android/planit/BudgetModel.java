package com.example.android.planit;

public class BudgetModel {
    String budgetName;
    int budgetId;
    int budgetAmount;

    public BudgetModel(String mbudgetName, int mbudgetId, int mtotalPrice){
        budgetName =mbudgetName;
        budgetId= mbudgetId;
        budgetAmount =mtotalPrice;
    }

    public BudgetModel(){

    }
    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(int budgetAmount) {
        this.budgetAmount = budgetAmount;
    }
}
