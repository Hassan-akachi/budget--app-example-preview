package com.example.android.planit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BudgetDataAdapter extends RecyclerView.Adapter<BudgetDataAdapter.viewHolder> {
    ArrayList<BudgetModel> budgetModels;
    private LayoutInflater inflater;
    Context context;


    public BudgetDataAdapter(Context context, ArrayList<BudgetModel> budgetModelList){

        this.context=context;
        this.budgetModels = budgetModelList;
        Log.v("budgetAdapter","show error-"+context);
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater=LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.main_list_item,parent,false);
        Log.v("budgetAdapter","show error");
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.BudgetName.setText(budgetModels.get(position).getBudgetName());
    holder.BudgetAmount.setText(String.valueOf(budgetModels.get(position).getBudgetPrice()));
    }

    @Override
    public int getItemCount() {
        return budgetModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView BudgetName,BudgetAmount,TotalPrice;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
          BudgetName=itemView.findViewById(R.id.budget_item_name);
          BudgetAmount=itemView.findViewById(R.id.budget_item_amount);
          TotalPrice=itemView.findViewById(R.id.total_item_amount);
        }
    }
}
