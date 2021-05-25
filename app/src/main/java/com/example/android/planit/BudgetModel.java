package com.example.android.planit;

public class BudgetModel {
    String budgetName;
    int budgetId;
    int totalPrice;

    public BudgetModel(String mbudgetName, int mbudgetId, int mtotalPrice){
        budgetName =mbudgetName;
        budgetId= mbudgetId;
        totalPrice =mtotalPrice;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
