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

    // this interface is to update total amount in the budget page activity as the user enters new data in the
    interface updateTotalInterface{
        void updateTotal(int total);

    }
    private updateTotalInterface updateTotalInterface;
    Context context;
    int total;
    public  ArrayList<ItemModel> budgetModelList;
    private ArrayList<ItemModel> tempData = new ArrayList<>();
    private LayoutInflater inflater;
    public final static String LOG_TAG= budgetAdapter.class.getSimpleName();
    private Boolean finishedLoadingLayout = false;

    public budgetAdapter(Context context, ArrayList<ItemModel> budgetModelList){

       this.context=context;
        this.budgetModelList = budgetModelList;
        tempData.addAll(budgetModelList);
        Log.v("budgetAdapter","show error-"+context);
    }

    @NonNull
    @Override
    public budget onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     inflater=LayoutInflater.from(context);
    View view= inflater.inflate(R.layout.budget_item,parent,false);
        Log.v("budgetAdapter","show error");
    return new budget(view);
    }

    @Override
    public void onBindViewHolder(@NonNull budget holder, int position) {
        setFinishedLoadingLayout(false);

        ItemModel model= new ItemModel();

        holder.nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (finishedLoadingLayout) {
                    model.setName(editable.toString());
                    tempData.set(position, model);
                }

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
                if (finishedLoadingLayout) {
                    int quantity = Integer.parseInt(editable.toString());
                    int amount = Integer.parseInt(holder.amountView.getText().toString());
                    model.setQuantity(quantity);
                    int total = quantity * amount;
                    model.setTotal(total);
                    holder.totalView.setText(String.valueOf(total));
                    tempData.set(position, model);
                    // update the total amount in budget page activity
                    updateTotalInterface.updateTotal(calculateTotal());
                }
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
                if (finishedLoadingLayout) {
                    int amount = Integer.parseInt(editable.toString());
                    int quantity = Integer.parseInt(holder.quantityView.getText().toString());
                    int total = quantity * amount;
                    model.setTotal(total);
                    holder.totalView.setText(String.valueOf(total));
                    model.setAmount(Integer.parseInt(editable.toString()));
                    tempData.set(position, model);
                    // update the total amount in budget page activity
                    updateTotalInterface.updateTotal(calculateTotal());
                }
            }
        });
        holder.nameView.setText(tempData.get(position).getName());
        holder.quantityView.setText(String.valueOf(tempData.get(position).getQuantity()));
        holder.amountView.setText(String.valueOf(tempData.get(position).getAmount()));
        holder.totalView.setText(String.valueOf(tempData.get(position).getTotal()));

    }

    private int calculateTotal() {
        int total = 0;
        for (ItemModel itemModel : tempData){
            total = total + itemModel.getTotal();
        }
        return total;
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

    public void setNumberOfItems(int numberOfItems){
        ItemModel itemModel = new ItemModel("", 0, 0, 0, 0);
        for (int i = 0; i < numberOfItems; i++){
            budgetModelList.add(itemModel);
            tempData.add(itemModel);
        }
        notifyDataSetChanged();
        setFinishedLoadingLayout(false);

    }

    public  ArrayList<ItemModel> getData(){
        return tempData;
    }

    public void setFinishedLoadingLayout(Boolean finishedLoadingLayout) {
        this.finishedLoadingLayout = finishedLoadingLayout;
    }

    public void setUpdateTotalInterface(budgetAdapter.updateTotalInterface updateTotalInterface) {
        this.updateTotalInterface = updateTotalInterface;
    }
}

