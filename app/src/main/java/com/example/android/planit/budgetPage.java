package com.example.android.planit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.planit.data.PlanitDbHelper;

import java.util.ArrayList;

public class budgetPage extends AppCompatActivity {
RecyclerView recyclerView;
TextView budgetNameInput,budgetAmountInput,totalRoll, budgetTotaltv;
   int budgetAmount;
   private PlanitDbHelper planitDbHelper;
   private  SharedPreferences sharedPreferences;
    budgetAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);

        planitDbHelper = new PlanitDbHelper(this);
        recyclerView=findViewById(R.id.budget_recycle);
        budgetNameInput=findViewById(R.id.budget_name_input);
        budgetAmountInput=findViewById(R.id.budget_amount_input);
        budgetTotaltv =findViewById(R.id.budget_total_input);
        totalRoll=findViewById(R.id.total_roll);
//        totalRoll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int ans ;
//                int ans2 =0;
//                //
//                for (int i = 0; i < budgetAdapter.budgetModelList.size(); i++){
//                    int num1 =Integer.parseInt( budgetAdapter.budgetModelList.get(i).getAmount());
//                    int num2 =Integer.parseInt( budgetAdapter.budgetModelList.get(i).getQuantity());
//                    ans= num1*num2;
//                    ans2+=ans;
//                }
//
//               if(ans2> budgetAmount) {
//                   budgetTotaltv.setTextColor(Color.RED);
//                   budgetTotaltv.setText("" + ans2);
//               }else if(ans2<budgetAmount){
//                   budgetTotaltv.setTextColor(Color.GREEN);
//                   budgetTotaltv.setText(""+ans2);
//               }else {
//                   budgetTotaltv.setTextColor(Color.BLACK);
//                   budgetTotaltv.setText("" + ans2);
//               }
//            }
//        });
        //
      String budgetName=getIntent().getStringExtra("keybudgetName");
       budgetAmount=getIntent().getIntExtra("keybudgetAmount",0);
        //
        budgetNameInput.setText(budgetName);
        budgetAmountInput.setText(String.valueOf(budgetAmount));
        //
        ArrayList<ItemModel> budgetModelArrayList= populate();
        //
        Log.v("budgetPage","budgetPage error adapter");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
       adapter=new budgetAdapter(this,budgetModelArrayList);
       recyclerView.setAdapter(adapter);
    }
//
    private ArrayList<ItemModel> populate() {
        ArrayList<ItemModel> list = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            ItemModel editModel = new ItemModel("",0,0,0);
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
        int budgetTotal = 546;
        sharedPreferences = getSharedPreferences(Constants.BUDGET_PREFERENCES, MODE_PRIVATE);
        int previousID = sharedPreferences.getInt(Constants.BUDGET_PREVIOUS_ID, 0);
        BudgetModel budgetModel = new BudgetModel(budgetNameString, previousID + 1, budgetTotal);
        planitDbHelper.insetBudgetData(budgetModel);
    }

    private void insertItemData(){
        ArrayList<ItemModel> data = adapter.getData();
        ItemModel itemModel = new ItemModel();
        for (int k = 0; k < data.size(); k++){
            itemModel.setName(data.get(k).getName());
            itemModel.setAmount(data.get(k).getAmount());
            itemModel.setQuantity(data.get(k).getQuantity());
            itemModel.setID(sharedPreferences.getInt(Constants.BUDGET_PREVIOUS_ID, 0) + 1 );
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}