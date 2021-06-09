package com.example.android.planit;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.example.android.planit.data.PlanitDbHelper;

import java.util.ArrayList;

public class budgetPage extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView budgetNameInput, budgetAmountInput, totalRoll, budgetTotaltv;
    int budgetAmount, number_of_items, budget_ID;
    private PlanitDbHelper planitDbHelper;
    private SharedPreferences sharedPreferences;
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

        budget_ID = getIntent().getIntExtra("ID", -1);
        String budgetName = getIntent().getStringExtra("keybudgetName");
        budgetAmount = getIntent().getIntExtra("keybudgetAmount", 0);
        if (budget_ID == -1) {
            number_of_items = getIntent().getIntExtra("number_of_items", 0);
            ArrayList<ItemModel> budgetModelArrayList = populate();
            adapter = new budgetAdapter(this, budgetModelArrayList);
            recyclerView.setAdapter(adapter);
        }else {
            displaydatabase(budget_ID);
        }

        budgetNameInput.setText(budgetName);
        budgetAmountInput.setText(String.valueOf(budgetAmount));



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
        PlanitDbHelper planitDbHelper = new PlanitDbHelper(budgetPage.this);
        ArrayList<ItemModel> list= planitDbHelper.getItemData(budget_id);
        adapter = new budgetAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    //
    private ArrayList<ItemModel> populate() {
        ArrayList<ItemModel> list = new ArrayList<>();

        for (int i = 0; i < number_of_items; i++) {
            ItemModel editModel = new ItemModel("", 0, 0, 0, 0 );
            list.add(editModel);
        }
        return list;
    }


    // using to display the menu icon **IMPORTANT**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // name of menu.xml is inputed at R.menu.nameOfMenu.xmlFile
        inflater.inflate(R.menu.budget_menu, menu);
        return true;
    }

    public void insertBudgetData() {
        String budgetNameString = budgetNameInput.getText().toString();
        sharedPreferences = getSharedPreferences(Constants.BUDGET_PREFERENCES, MODE_PRIVATE);
        int previousID = sharedPreferences.getInt(Constants.BUDGET_PREVIOUS_ID, 0);
        BudgetModel budgetModel = new BudgetModel(budgetNameString, previousID + 1, budgetAmount);
        planitDbHelper.insetBudgetData(budgetModel);
    }

    private void insertItemData() {
        ArrayList<ItemModel> data = adapter.getData();
        ItemModel itemModel = new ItemModel();
        for (int k = 0; k < data.size(); k++) {
            itemModel.setName(data.get(k).getName());
            itemModel.setAmount(data.get(k).getAmount());
            itemModel.setQuantity(data.get(k).getQuantity());
            itemModel.setID(sharedPreferences.getInt(Constants.BUDGET_PREVIOUS_ID, 0) + 1);
            planitDbHelper.insertItemData(itemModel);
            itemModel = new ItemModel();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                insertBudgetData();
                insertItemData();
                finish();
                break;
            case R.id.add_more_items:
                addMoreItems();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void addMoreItems() {
        EditText editText =new EditText(this);
        editText.setHint("How many more items?");
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
}