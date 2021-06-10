package com.example.android.planit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android.planit.BudgetModel;
import com.example.android.planit.Constants;
import com.example.android.planit.ItemModel;

import java.util.ArrayList;

public class PlanitDbHelper extends SQLiteOpenHelper {
    /** Name of the database file */

    public final static String DATABASE_NAME ="planit.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    public final static int DATABASE_VERSION =1;

    public PlanitDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constants.BUDGET_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.BUDGET_NAME + " TEXT," +
                Constants.BUDGET_ID + " INTEGER," +
                Constants.BUDGET_TOTAL + " INTEGER )");
        db.execSQL("CREATE TABLE " + Constants.ITEMS_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.ITEM_NAME + " TEXT," +
                Constants.ITEM_PRICE + " INTEGER," +
                Constants.ITEM_QUANTITY + " INTEGER," +
                Constants.ITEM_ID + " INTEGER)");

    }

    public void insetBudgetData(BudgetModel budgetModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.BUDGET_NAME, budgetModel.getBudgetName());
        contentValues.put(Constants.BUDGET_ID, budgetModel.getBudgetId());
        contentValues.put(Constants.BUDGET_TOTAL, budgetModel.getBudgetAmount());
        SQLiteDatabase database = getWritableDatabase();
        database.insert(Constants.BUDGET_TABLE, null, contentValues);
        database.close();
    }

    public void insertItemData(ItemModel itemModel){
        ContentValues  contentValues = new ContentValues();
        contentValues.put(Constants.ITEM_NAME, itemModel.getName());
        contentValues.put(Constants.ITEM_PRICE, itemModel.getAmount());
        contentValues.put(Constants.ITEM_QUANTITY, itemModel.getQuantity());
        contentValues.put(Constants.ITEM_ID, itemModel.getID());
        SQLiteDatabase database = getWritableDatabase();
        database.insert(Constants.ITEMS_TABLE, null, contentValues);
    }

    public ArrayList<BudgetModel> getBudgetData(){
        ArrayList<BudgetModel> budgetModelArrayList =new ArrayList<>();
        SQLiteDatabase liteDatabase=getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM " + Constants.BUDGET_TABLE , null);
        int i = 0;
        while (i < cursor.getCount()){
            BudgetModel budgetModel = new BudgetModel();
            if (i == 0) {
                cursor.moveToFirst();
            }
            budgetModel.setBudgetName(cursor.getString(1));
            budgetModel.setBudgetId(cursor.getInt(2));
            budgetModel.setBudgetAmount(cursor.getInt(3));
            budgetModelArrayList.add(budgetModel);
            cursor.moveToNext();
            i++;
        }
        cursor.close();
        return budgetModelArrayList;
    }

    public ArrayList<ItemModel> getItemData(int position){
        ArrayList<ItemModel> list = new ArrayList<>();
        ItemModel itemModel = new ItemModel();
        SQLiteDatabase database =getReadableDatabase();
        Cursor cursor = database.query(Constants.ITEMS_TABLE, null,  Constants.ITEM_ID + " = ?", new String[]{String.valueOf(position)}, null,null, null);
        while (cursor.moveToNext()){
            itemModel.setName(cursor.getString(1));
            itemModel.setAmount(cursor.getInt(2));
            itemModel.setQuantity(cursor.getInt(3));
            itemModel.setID(cursor.getInt(4));
            list.add(itemModel);
            itemModel= new ItemModel();

        }
        return list;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
