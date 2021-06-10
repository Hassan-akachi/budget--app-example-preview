package com.example.android.planit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.planit.data.PlanitDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainRecycleAdapter.onCLickListener  {
    TextView budgetNameInput,budgetAmountInput;
    RecyclerView recyclerView;
    ArrayList<String> tempArray;
    MainRecycleAdapter mainRecycleAdapter;
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


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogue();
            }
        });

    }



    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PlanitDbHelper mDbHelper = new PlanitDbHelper(this);

        budgetModelArrayList = new ArrayList<>();
        budgetModelArrayList = mDbHelper.getBudgetData();
        tempArray = new ArrayList<>();
        for (int i=0; i < budgetModelArrayList.size(); i++){
            tempArray.add(budgetModelArrayList.get(i).getBudgetName());
            
        }
        mainRecycleAdapter = new MainRecycleAdapter(this, budgetModelArrayList);
        mainRecycleAdapter.setOnCLickListener(MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainRecycleAdapter);


    }



    // used to access the dailogue class (dailogueScreen)
    public void openDialogue() {
        BudgetDialogue DialogueScreen = new BudgetDialogue();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                Toast.makeText(this, "all budget is deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(BudgetModel budgetModel) {
        Intent intent = new Intent(MainActivity.this, budgetPage.class);
        intent.putExtra("ID", budgetModel.getBudgetId());
        intent.putExtra("keybudgetName", budgetModel.getBudgetName());
        intent.putExtra("keybudgetAmount", budgetModel.getBudgetAmount());
        intent.putExtra("totalAmount", budgetModel.getTotalAmount());
        startActivity(intent);
    }
}