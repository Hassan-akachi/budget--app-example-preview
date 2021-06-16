package com.example.android.planit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class BudgetDialogue extends AppCompatDialogFragment {
    private final boolean new_project;
    private final int budget_id;
    EditText EditBudgetName, EditBudgetAmount, number_of_items;
    private View view;

    public BudgetDialogue(boolean new_project, int budget_id) {
        this.new_project = new_project;
        this.budget_id = budget_id;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // building the dailog interface
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // enable to find view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.budget_info, null);

        //dailogue layout
        builder.setView(view);
        builder.setTitle("Budget Info");
        EditBudgetName = view.findViewById(R.id.edit_budget_name);
        EditBudgetAmount = view.findViewById(R.id.edit_budget_amount);
        number_of_items = view.findViewById(R.id.number_of_items);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //action taking when clicking in the ok button
                String budgetName = EditBudgetName.getText().toString().trim();
                int budgetAmount = Integer.parseInt(EditBudgetAmount.getText().toString());
                int numberOfItems = Integer.parseInt(number_of_items.getText().toString());

                Intent intent = new Intent(getActivity(), budgetPage.class);
                intent.putExtra("keybudgetName", budgetName);
                intent.putExtra("keybudgetAmount", budgetAmount);
                intent.putExtra("number_of_items", numberOfItems);
                intent.putExtra("ID", budget_id);
                intent.putExtra("NEW PROJECT", new_project);

                startActivity(intent);

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
    // enable edited text to return to return to the view

}