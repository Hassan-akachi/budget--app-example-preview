package com.example.android.planit;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.planit.data.DataUtils;
import com.example.android.planit.data.PlanitDbHelper;

import java.util.ArrayList;

public class budgetPage extends AppCompatActivity implements budgetAdapter.updateTotalInterface {
    RecyclerView recyclerView;
    TextView budgetNameInput, budgetAmountInput, totalRoll, budgetTotaltv;
    int budgetAmount, budgetTotalAmount;
    private PlanitDbHelper planitDbHelper;
    /**
     * The new_budget boolean below helps to know whether a new budget
     * is being created or a former one is being updated.
     * This helps to know whether the database is updated or
     * a new one is inserted
     */
    private boolean new_budget = false;

    budgetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        planitDbHelper = new PlanitDbHelper(this);
        recyclerView = findViewById(R.id.budget_recycle);
        budgetNameInput = findViewById(R.id.budget_name_input);
        budgetAmountInput = findViewById(R.id.budget_amount_input);
        budgetTotaltv = findViewById(R.id.budget_total_input);
        totalRoll = findViewById(R.id.total_roll);

        new_budget = getIntent().getBooleanExtra("NEW_BUDGET", true);
        String budgetName = getIntent().getStringExtra("keybudgetName");
        budgetAmount = getIntent().getIntExtra("keybudgetAmount", 0);
        budgetTotalAmount = getIntent().getIntExtra("totalAmount", 0);

        if (new_budget) {
            populateAdapter(getIntent().getIntExtra("number_of_items", 10));
        } else {
            displaydatabase(getIntent().getIntExtra("ID", 1));
        }

        budgetNameInput.setText(budgetName);
        budgetAmountInput.setText(String.valueOf(budgetAmount));
        updateTotalTextView(budgetTotalAmount);


        Log.v("budgetPage", "budgetPage error adapter");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutCompleted(RecyclerView.State state) {
                adapter.setFinishedLoadingLayout(true);
                super.onLayoutCompleted(state);

            }
        });
    }

    private void displaydatabase(int budget_id) {
        ArrayList<ItemModel> list = DataUtils.getInstance(this).getItemData(budget_id);
        adapter = new budgetAdapter(this, list);
        adapter.setUpdateTotalInterface(budgetPage.this);
        recyclerView.setAdapter(adapter);
    }

    //
    private void populateAdapter(int number_of_items) {
        ArrayList<ItemModel> list = new ArrayList<>();
        for (int i = 0; i < number_of_items; i++) {
            ItemModel editModel = new ItemModel("", 0, 0, 0, 0);
            list.add(editModel);
        }
        adapter = new budgetAdapter(this, list);
        adapter.setUpdateTotalInterface(budgetPage.this);
        recyclerView.setAdapter(adapter);
    }


    // using to display the menu icon **IMPORTANT**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // name of menu.xml is inputed at R.menu.nameOfMenu.xmlFile
        inflater.inflate(R.menu.budget_menu, menu);
        return true;
    }

    public void insertDataIntoDatabase() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.BUDGET_PREFERENCES, MODE_PRIVATE);
         int previousID = sharedPreferences.getInt(Constants.BUDGET_PREVIOUS_ID, 0);
         // Check to update or insert new
        if (new_budget) {
            DataUtils.getInstance(this).insertBudgetData(budgetNameInput.getText().toString(), adapter.getData(), budgetAmountInput.getText().toString(), previousID + 1);
            DataUtils.getInstance(this).insertItemData(adapter.getData(), previousID + 1);
        }else {
            DataUtils.getInstance(this).updateBudgetData(budgetNameInput.getText().toString(), adapter.getData(), budgetAmountInput.getText().toString(), previousID + 1);
            DataUtils.getInstance(this).updateItemData(adapter.getData(), previousID + 1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.save) {
            insertDataIntoDatabase();
            finish();
        } else if (itemId == R.id.add_more_items) {
            addMoreItems();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        insertDataIntoDatabase();
        finish();
        super.onBackPressed();
    }

    private void addMoreItems() {
        EditText editText = new EditText(this);
        editText.setHint("How many more items?");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Number of items")
                .setView(editText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int more_items = Integer.parseInt(editText.getText().toString());
                        adapter.setNumberOfItems(more_items);
                    }
                })
                .setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void updateTotal(int total) {
        updateTotalTextView(total);
    }

    private void updateTotalTextView(int total) {
        budgetTotaltv.setText(String.valueOf(total));
        if (budgetAmount < total) {
            //User total cost is greater than budget
            budgetTotaltv.setTextColor(getResources().getColor(R.color.red));
        } else if (budgetAmount > total) {
            // User budget is greater than total amount of items
            budgetTotaltv.setTextColor(getResources().getColor(R.color.green));
        } else {
            //User budget is equal to total amount of items
            budgetTotaltv.setTextColor(getResources().getColor(R.color.black));
        }
    }
}