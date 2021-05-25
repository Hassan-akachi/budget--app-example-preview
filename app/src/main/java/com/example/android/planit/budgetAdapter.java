package com.example.android.planit;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class budgetAdapter extends RecyclerView.Adapter<budgetAdapter.budget> {
    Context context;
    int total;
    public  ArrayList<ItemModel> budgetModelList;
    private ArrayList<ItemModel> tempData = new ArrayList<>();
    private LayoutInflater inflater;
    public final static String LOG_TAG=budgetAdapter.class.getSimpleName();
    public budgetAdapter(Context context, ArrayList<ItemModel> budgetModelList){

       this.context=context;
        this.budgetModelList = budgetModelList;
        Log.v("budgetAdapter","show error-"+context);
    }
    @NonNull
    @Override
    public budgetAdapter.budget onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     inflater=LayoutInflater.from(context);
    View view= inflater.inflate(R.layout.budget_item,parent,false);
        Log.v("budgetAdapter","show error");
    return new budget(view);
    }

    @Override
    public void onBindViewHolder(@NonNull budget holder, int position) {
        ItemModel tempItemModel = new ItemModel();
        final int[] tempQuantity = {0};
        final int[] tempAmount = {0};
        holder.nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tempItemModel.setName(editable.toString());
                tempData.add(position, tempItemModel);


            }
        });
        holder.quantityView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tempQuantity[0] = Integer.parseInt(editable.toString());
                holder.totalView.setText(String.valueOf(tempQuantity[0] * tempAmount[0]));
                tempItemModel.setQuantity(Integer.parseInt(editable.toString()));
                tempData.add(position, tempItemModel);
            }
        });
        holder.amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tempAmount[0] = Integer.parseInt(editable.toString());
                holder.totalView.setText(String.valueOf(tempQuantity[0] * tempAmount[0]));
                tempItemModel.setAmount(Integer.parseInt(editable.toString()));
                tempData.add(position, tempItemModel);
            }
        });


        if (tempData.size() != 0) {
            holder.nameView.setText(tempData.get(position).getName());
            holder.quantityView.setText(String.valueOf(tempData.get(position).getQuantity()));
            holder.amountView.setText(String.valueOf(budgetModelList.get(position).getAmount()));
        } else {
            holder.nameView.setText(budgetModelList.get(position).getName());

            holder.quantityView.setText(String.valueOf(budgetModelList.get(position).getQuantity()));
            holder.amountView.setText(String.valueOf(budgetModelList.get(position).getAmount()));

            int num1 = Integer.parseInt(holder.quantityView.getText().toString());
            int num2 = Integer.parseInt(holder.amountView.getText().toString());
            total = num1 * num2;
            holder.totalView.setText(String.valueOf(total));


            Log.v("budgetAdapter", "show error-" + context);
        }
    }

    @Override
    public int getItemCount() {
        return budgetModelList.size();
    }

    //inner class
    public class budget extends RecyclerView.ViewHolder {
        EditText nameView, quantityView, amountView;
        TextView totalView;

        public budget(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_edit);
            quantityView = itemView.findViewById(R.id.quantity_edit);
            amountView = itemView.findViewById(R.id.amount_edit);
            totalView =itemView.findViewById(R.id.total_edit);

        }
    }

    public  ArrayList<ItemModel> getData(){
        return tempData;
    }

}

