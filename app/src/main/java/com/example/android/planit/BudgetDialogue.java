package com.example.android.planit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class BudgetDialogue extends AppCompatDialogFragment {
    EditText EditBudgetName, EditBudgetAmount;
    private View view;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // building the dailog interface
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // enable to find view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.budget_info, null);
        //dailoe layout
        builder.setView(view);
        builder.setTitle("Budget Info");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //action taking when clicking in the ok button
                String budgetName = EditBudgetName.getText().toString().trim();
                int budgetAmount = Integer.parseInt(EditBudgetAmount.getText().toString());

                Intent intent = new Intent(getActivity(), budgetPage.class);
                intent.putExtra("keybudgetName", budgetName);
                intent.putExtra("keybudgetAmount", budgetAmount);

                startActivity(intent);

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        EditBudgetName = view.findViewById(R.id.edit_budget_name);
        EditBudgetAmount = view.findViewById(R.id.edit_budget_amount);
        return builder.create();
    }
    // enable edited text to return to return to the view

}