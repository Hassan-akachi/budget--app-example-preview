package com.example.android.planit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class budgetPage extends AppCompatActivity {
RecyclerView recyclerView;
TextView budgetNameInput,budgetAmountInput,totalRoll,budgetTotal;
   int budgetAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        recyclerView=findViewById(R.id.budget_recycle);
        budgetNameInput=findViewById(R.id.budget_name_input);
        budgetAmountInput=findViewById(R.id.budget_amount_input);
        budgetTotal=findViewById(R.id.budget_total_input);
        totalRoll=findViewById(R.id.total_roll);
        totalRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ans ;
                int ans2 =0;
                //
                for (int i = 0; i < budgetAdapter.budgetModelList.size(); i++){
                    int num1 =Integer.parseInt( budgetAdapter.budgetModelList.get(i).getAmount());
                    int num2 =Integer.parseInt( budgetAdapter.budgetModelList.get(i).getQuantity());
                    ans= num1*num2;
                    ans2+=ans;
                }

               if(ans2> budgetAmount) {
                   budgetTotal.setTextColor(Color.RED);
                   budgetTotal.setText("" + ans2);
               }else if(ans2<budgetAmount){
                   budgetTotal.setTextColor(Color.GREEN);
                   budgetTotal.setText(""+ans2);
               }else {
                   budgetTotal.setTextColor(Color.BLACK);
                   budgetTotal.setText("" + ans2);
               }
            }
        });
        //
      String budgetName=getIntent().getStringExtra("keybudgetName");
       budgetAmount=getIntent().getIntExtra("keybudgetAmount",0);
        //
        budgetNameInput.setText(budgetName);
        budgetAmountInput.setText(String.valueOf(budgetAmount));
        //
        ArrayList<budgetModel> budgetModelArrayList= populate();
        //
        Log.v("budgetPage","budgetPage error adapter");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
       budgetAdapter adapter=new budgetAdapter(this,budgetModelArrayList);
       recyclerView.setAdapter(adapter);
    }
//
    private ArrayList<budgetModel> populate() {
        ArrayList<budgetModel> list = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            budgetModel editModel = new budgetModel("","","",0);
            editModel.setAmount(String.valueOf(0));
            editModel.setQuantity(String.valueOf(0));
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

    public void insertData() {
        String budgetNameString = budgetNameInput.getText().toString().trim();
        String budgetAmountString = budgetAmountInput.getText().toString().trim();
        String budgetTotalString = budgetTotal.getText().toString().trim();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Toast.makeText(this, "budget saved", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}