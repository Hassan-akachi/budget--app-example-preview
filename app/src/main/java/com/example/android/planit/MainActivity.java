package com.example.android.planit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.planit.data.PlanitDbHelper;
import com.example.android.planit.data.reposirtory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    TextView budgetNameInput,budgetAmountInput;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list_view);
//        budgetNameInput=findViewById(R.id.budget_name_input);
//        budgetAmountInput=findViewById(R.id.budget_amount_input);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public
            @Override void onClick(View view) {
            openDialogue();
            }
        });
    }

    private void displayDatabaseInfo() {
    ArrayList<BudgetModel> budgetData = reposirtory.getbudgetDatabaseInfo(this);

        BudgetDataAdapter budgetDataAdapter= new BudgetDataAdapter(this,budgetData);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(budgetDataAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();

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