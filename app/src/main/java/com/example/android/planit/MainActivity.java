package com.example.android.planit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.planit.data.DataUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainRecyclerAdapter.onCLickListener  {
    TextView budgetNameInput,budgetAmountInput, noDataTextView;
    RecyclerView recyclerView;
    MainRecyclerAdapter mainRecycleAdapter;
    ArrayList<BudgetModel> budgetModelArrayList;




    @Override
    protected void onResume() {
        displayDatabaseInfo();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclyelist_view);
        budgetNameInput = findViewById(R.id.budget_name_input);
        budgetAmountInput = findViewById(R.id.budget_amount_input);
        noDataTextView= findViewById(R.id.no_data_text);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogue(true, -1);
            }
        });
    }



    private void displayDatabaseInfo() {
        budgetModelArrayList = DataUtils.getInstance(this).getBudgetData();
        mainRecycleAdapter = new MainRecyclerAdapter(this, budgetModelArrayList);
        mainRecycleAdapter.setOnCLickListener(MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainRecycleAdapter);
        if (budgetModelArrayList.size() == 0){
            noDataTextView.setVisibility(View.VISIBLE);
        }else {
            noDataTextView.setVisibility(View.INVISIBLE);
        }


    }



    // used to access the dialogue class (dilogueScreen)
    public void openDialogue(boolean new_project,  int budget_ID) {
        BudgetDialogue DialogueScreen = new BudgetDialogue(new_project, budget_ID );
        // important in other to get the fragment for the dailogue
        DialogueScreen.show(getSupportFragmentManager(),"BudgetDialogue");
    }
    // using to display the menu icon **IMPORTANT**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // name of menu.xml is inputed at R.menu.nameOfMenu.xmlFile
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //disable the delete menu if there is no data yet in the database
        if (budgetModelArrayList.size() == 0){
            menu.getItem(0).setEnabled(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            deleteAllDAata();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllDAata() {
        // Create a dialog and ensure user actually wants to delete all data
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle("Delete all data")
                .setMessage("Do you want to delete all your budgets?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataUtils.getInstance(MainActivity.this).deleteAllDAta(MainActivity.this);
                        //refresh adapter
                        mainRecycleAdapter.emptyAdapter();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(BudgetModel budgetModel) {
        Intent intent = new Intent(MainActivity.this, budgetPage.class);
        intent.putExtra("NEW_PROJECT", false);
        intent.putExtra("ID", budgetModel.getBudgetId());
        intent.putExtra("keybudgetName", budgetModel.getBudgetName());
        intent.putExtra("keybudgetAmount", budgetModel.getBudgetAmount());
        intent.putExtra("totalAmount", budgetModel.getTotalAmount());
        startActivity(intent);
    }
}