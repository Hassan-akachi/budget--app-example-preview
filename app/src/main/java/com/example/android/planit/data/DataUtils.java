package com.example.android.planit.data;

import android.content.Context;

import com.example.android.planit.BudgetModel;
import com.example.android.planit.ItemModel;
import com.example.android.planit.MainActivity;

import java.util.ArrayList;

public class DataUtils {
    Context context;
    private static DataUtils instance;
    private static final Object LOCK = new Object();
    private static PlanitDbHelper planitDbHelper;

    private DataUtils(Context context) {
        this.context = context;
        planitDbHelper = new PlanitDbHelper(context);
    }

    public static DataUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new DataUtils(context);
            }
        }
        return instance;

    }

    public void deleteAllDAta(MainActivity mainActivity) {
        planitDbHelper.deleteAllData();
    }


    public ArrayList<BudgetModel> getBudgetData() {
        ArrayList<BudgetModel> list = planitDbHelper.getBudgetData();
        return list;
    }

    public void insertItemData(ArrayList<ItemModel> data, int id) {
        ItemModel itemModel = new ItemModel();
        for (int k = 0; k < data.size(); k++) {
            itemModel.setName(data.get(k).getName());
            itemModel.setAmount(data.get(k).getAmount());
            itemModel.setQuantity(data.get(k).getQuantity());
            itemModel.setTotal(data.get(k).getTotal());
            itemModel.setID(id);
            planitDbHelper.insertItemData(itemModel);
            itemModel = new ItemModel();
        }
    }

    public void insertBudgetData(String budgetName, ArrayList<ItemModel> data, String budgetAmount, int id) {
        int BudgetTotal = 0;
        for (ItemModel itemModels : data) {
            BudgetTotal = BudgetTotal + itemModels.getTotal();
        }
        BudgetModel budgetModel = new BudgetModel(budgetName, id, Integer.valueOf(budgetAmount), BudgetTotal);
        planitDbHelper.insetBudgetData(budgetModel);
    }

    public ArrayList<ItemModel> getItemData(int budget_id) {
        return planitDbHelper.getItemData(budget_id);
    }

    public void updateBudgetData(String budgetName, ArrayList<ItemModel> data, String budgetAmount, int id) {
        int BudgetTotal = 0;
        for (ItemModel itemModels : data) {
            BudgetTotal = BudgetTotal + itemModels.getTotal();
        }
        BudgetModel budgetModel = new BudgetModel(budgetName, id, Integer.valueOf(budgetAmount), BudgetTotal);
        planitDbHelper.updateBudgetData(budgetModel);
    }

    public void updateItemData(ArrayList<ItemModel> data, int id) {
        ItemModel itemModel = new ItemModel();
        for (int k = 0; k < data.size(); k++) {
            itemModel.setName(data.get(k).getName());
            itemModel.setAmount(data.get(k).getAmount());
            itemModel.setQuantity(data.get(k).getQuantity());
            itemModel.setTotal(data.get(k).getTotal());
            itemModel.setID(id);
            planitDbHelper.updateItemData(itemModel);
            itemModel = new ItemModel();
        }
    }
}
