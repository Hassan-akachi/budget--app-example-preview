package com.example.android.planit;

public class ItemModel {
    private String name;
    private int quantity, amount, ID, total;

    public ItemModel(String mName, int mID, int mQuantity, int mAmount, int total) {
        name = mName;
        quantity = mQuantity;
        amount = mAmount;
        ID = mID;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ItemModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

