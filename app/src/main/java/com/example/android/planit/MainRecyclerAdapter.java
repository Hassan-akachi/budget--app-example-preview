package com.example.android.planit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {


    public interface onCLickListener{
        void onClick(BudgetModel budgetModel);
    }

    private  onCLickListener onCLickListener;
    private Context context;



    ArrayList<BudgetModel> ModelArrayList;

    public MainRecyclerAdapter(Context context, ArrayList<BudgetModel>ModelArrayList){
        this.context=context;
        this.ModelArrayList = ModelArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from( context);
        CardView cardView = (CardView) inflater.inflate(R.layout.main_list_item,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView amountItemName,NameItem,TotalItem;
        CardView cardView = holder.cardView;
        amountItemName = cardView.findViewById(R.id.budget_item_amount);
        NameItem = cardView.findViewById(R.id.budget_item_name);
        TotalItem = cardView.findViewById(R.id.total_item_amount);
        int amount = ModelArrayList.get(position).getBudgetAmount();
        int total =  ModelArrayList.get(position).getTotalAmount();

        amountItemName.setText(String.valueOf(amount));
        NameItem.setText(ModelArrayList.get(position).getBudgetName());
        TotalItem.setText(String.valueOf(total));
        // set colour for total amount
        if (amount < total){
            //User total cost is greater than budget
            TotalItem.setTextColor(context.getResources().getColor(R.color.red));
        }else if(amount > total){
            // User budget is greater than total amount of items
            TotalItem.setTextColor(context.getResources().getColor(R.color.green));
        }else {
            //User budget is equal to total amount of items
            TotalItem.setTextColor(context.getResources().getColor(R.color.black));
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onCLickListener.onClick(ModelArrayList.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return ModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;

        }
    }


    public void setOnCLickListener(onCLickListener onCLickListener){
        this.onCLickListener = onCLickListener;
    }
    public void emptyAdapter() {
        ModelArrayList = new ArrayList<>();
        notifyDataSetChanged();
    }

}
