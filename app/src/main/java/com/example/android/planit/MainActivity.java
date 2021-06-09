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

import com.example.android.planit.data.PlanitDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    TextView budgetNameInput,budgetAmountInput;
    ListView listView;
    ArrayList<String> tempArray;
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
        listView = findViewById(R.id.list_view);
        budgetNameInput=findViewById(R.id.budget_name_input);
        budgetAmountInput=findViewById(R.id.budget_amount_input);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openDialogue();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, budgetPage.class);
                intent.putExtra("ID", budgetModelArrayList.get(i).getBudgetId());
                intent.putExtra("keybudgetName", tempArray.get(i));
                intent.putExtra("keybudgetAmount", budgetModelArrayList.get(i).getBudgetAmount());
                startActivity(intent);
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
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempArray);
        listView.setAdapter(arrayAdapter);
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



}