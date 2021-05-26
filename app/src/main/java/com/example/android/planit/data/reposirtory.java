package com.example.android.planit.data;

import android.content.Context;

import com.example.android.planit.BudgetModel;
import com.example.android.planit.ItemModel;

import java.util.ArrayList;

public final class reposirtory {
    static PlanitDbHelper mDbHelper;
    public  static ArrayList<BudgetModel> getbudgetDatabaseInfo(Context context) {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
       mDbHelper = new PlanitDbHelper(context);

        ArrayList<BudgetModel> budgetModelArrayList = new ArrayList<>();
        budgetModelArrayList = mDbHelper.getBudgetData();
        return budgetModelArrayList;
    }
    public static ArrayList<ItemModel> getItemData(Context context, int ID){
        mDbHelper = new PlanitDbHelper(context);
        ArrayList<ItemModel> arrayItemData =new ArrayList<>();
        arrayItemData =mDbHelper.getItemData(ID);
        return arrayItemData;

    }

}
