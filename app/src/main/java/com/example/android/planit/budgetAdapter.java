package com.example.android.planit;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class budgetAdapter extends RecyclerView.Adapter<budgetAdapter.budget> {
    Context context;
    int total;
    public static ArrayList<budgetModel> budgetModelList;
    private LayoutInflater inflater;
    public final static String LOG_TAG=budgetAdapter.class.getSimpleName();
    public budgetAdapter(Context context, ArrayList<budgetModel> budgetModelList){

       this.context=context;
        this.budgetModelList = budgetModelList;
        Log.v("budgetAdapter","show error-"+context);
    }
    @NonNull
    @Override
    public budgetAdapter.budget onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     inflater=LayoutInflater.from(context);
    View view= inflater.inflate(R.layout.budget_item,parent,false);
        Log.v("budgetAdapter","show erreor");
    return new budget(view);
    }

    @Override
    public void onBindViewHolder(@NonNull budget holder, int position) {
        holder.nameView.setText(budgetModelList.get(position).getName());
        holder.quantityView.setText(budgetModelList.get(position).getQuantity());
        holder.amountView.setText(budgetModelList.get(position).getAmount());
        holder.totalView.setText(String.valueOf(budgetModelList.get(position).getTotal()));
        int num1 =Integer.parseInt( holder.quantityView.getText().toString());
        int num2 =Integer.parseInt(holder.amountView.getText().toString());
        total= num1*num2;
        holder.totalView.setText(String.valueOf(total));


        Log.v("budgetAdapter","show error-"+context);
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
            //
            nameView.addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    budgetModelList.get(getAdapterPosition()).setName(nameView.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            //
            quantityView .addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    totalView.setText(String.valueOf(total));
                    budgetModelList.get(getAdapterPosition()).setTotal(total);

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    budgetModelList.get(getAdapterPosition()).setQuantity(quantityView.getText().toString());
                }
            });
            amountView.addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   totalView.setText(String.valueOf(total));
                    budgetModelList.get(getAdapterPosition()).setTotal(total);

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    budgetModelList.get(getAdapterPosition()).setAmount(amountView.getText().toString());
                }
            });

        }
    }

}

