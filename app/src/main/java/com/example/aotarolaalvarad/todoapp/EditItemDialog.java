package com.example.aotarolaalvarad.todoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;
import android.widget.Spinner;
import android.view.View.OnClickListener;


/**
 * Created by aotarolaalvarad on 9/27/15.
 */
public class EditItemDialog extends DialogFragment implements OnClickListener {

    private Button btnCancel;
    private Button btnSave;

    private EditText etTitle;
    private EditText etDueDate;
    private Spinner spPriority;

    ArrayAdapter<TodoItem.Priority> priorityAdapter;

    public interface EditItemDialogListener {
        void onFinishEditDialog(String title, String dueDate, TodoItem.Priority priority, boolean isDone, int position);
    }

    public EditItemDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditItemDialog newInstance(String title, String dueDate, TodoItem.Priority priority, boolean isDone, int position) {
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("dueDate", dueDate);
        args.putSerializable("priority", priority);
        args.putBoolean("isDone", isDone);
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view

        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSave = (Button) view.findViewById(R.id.btnUpdateItem);

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        etTitle = (EditText) view.findViewById(R.id.etEditItemText);
        etDueDate = (EditText) view.findViewById(R.id.etEditDueDateText);
        spPriority = (Spinner) view.findViewById(R.id.spinner);


        priorityAdapter = new ArrayAdapter<TodoItem.Priority>(getActivity(),
                android.R.layout.simple_list_item_1, TodoItem.Priority.values());

        spPriority.setAdapter(priorityAdapter);

        etTitle.setText(getArguments().getString("title"));
        etDueDate.setText(getArguments().getString("dueDate"));
        spPriority.setSelection(priorityAdapter.getPosition((TodoItem.Priority) getArguments().getSerializable("priority")));

        getDialog().setTitle(R.string.edit_item_below);
        // Show soft keyboard automatically and request focus to field
        etTitle.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                // your stuff here
                this.dismiss();
                break;
            case R.id.btnUpdateItem:
                EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                listener.onFinishEditDialog(
                        etTitle.getText().toString(),
                        etDueDate.getText().toString(),
                        ((TodoItem.Priority)spPriority.getSelectedItem()),
                        true,
                        getArguments().getInt("position")
                );

                this.dismiss();
                break;
            default:
                break;
        }
    }
}