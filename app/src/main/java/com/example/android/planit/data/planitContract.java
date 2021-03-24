package com.example.android.planit.data;

import android.provider.BaseColumns;

public final class planitContract {
    private planitContract(){}
    //
    public final  static class planitEntry implements BaseColumns {
        //table name
        public final static String TABLE_NAME ="planit";
        //Column title
        public final static String _ID =BaseColumns._ID;
        public final static String COLUMN_BUDGET_NAME ="budgetName";
        public final static String COLUMN_BUDGET_AMOUNT ="budgetAmount";
        public final static String COLUMN_TOTAL_AMOUNT="totalAmount";
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_QUANTITY="quantity";
        public final static String COLUMN_AMOUNT="amount";
        public final static String COLUMN_TOTAL="total";

    }
}
