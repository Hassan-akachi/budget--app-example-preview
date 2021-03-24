package com.example.android.planit;

import android.content.Context;

public class budgetModel{
        private String name,quantity,amount;
        private int total;
        public  budgetModel( String mName, String mQuantity, String mAmount, int mTotal) {
        name =mName;
        quantity=mQuantity;
        amount=mAmount;
        total=mTotal;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
