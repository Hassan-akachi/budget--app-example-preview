package com.example.android.planit;

public class BudgetModel {
    //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
        String budgetName;
        int budgetId;
        int budgetPrice;

        public BudgetModel(String mbudgetName, int mbudgetId, int mtotalPrice) {
            this.budgetName = mbudgetName;
            this.budgetId = mbudgetId;
            this.budgetPrice = mtotalPrice;
        }

        public BudgetModel() {
        }

        public String getBudgetName() {
            return this.budgetName;
        }

        public void setBudgetName(String budgetName) {
            this.budgetName = budgetName;
        }

        public int getBudgetId() {
            return this.budgetId;
        }

        public void setBudgetId(int budgetId) {
            this.budgetId = budgetId;
        }

        public int getBudgetPrice() {
            return this.budgetPrice;
        }

        public void setBudgetPrice(int budgetPrice) {
            this.budgetPrice = budgetPrice;
        }
    }


