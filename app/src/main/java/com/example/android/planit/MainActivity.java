package com.example.android.planit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.planit.data.PlanitDbHelper;
import com.example.android.planit.data.planitContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {
    TextView budgetNameInput,budgetAmountInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PlanitDbHelper mDbHelper = new PlanitDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + planitContract.planitEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
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