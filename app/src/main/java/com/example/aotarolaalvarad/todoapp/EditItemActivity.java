package com.example.aotarolaalvarad.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditItemText;
    EditText etEditDueDateText;
    Integer oldItemPosition;
    ArrayAdapter<TodoItem.Priority> priorityAdapter;
    Spinner spPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        setTextBoxes();
    }

    protected void setTextBoxes(){
        etEditItemText =  (EditText) findViewById(R.id.etEditItemText);
        etEditDueDateText =  (EditText) findViewById(R.id.etEditDueDateText);
        spPriority = (Spinner) findViewById(R.id.spinner);

        priorityAdapter = new ArrayAdapter<TodoItem.Priority>(this,
                android.R.layout.simple_list_item_1, TodoItem.Priority.values());

        spPriority.setAdapter(priorityAdapter);

        String oldItemText = getIntent().getStringExtra("oldItemText");
        String oldDueDateText = getIntent().getStringExtra("oldDueDateText");
        TodoItem.Priority oldPriority = (TodoItem.Priority)getIntent().getSerializableExtra("oldPriorityText");

        spPriority.setSelection(priorityAdapter.getPosition(oldPriority));

        oldItemPosition = getIntent().getIntExtra("oldItemPosition",0);
        etEditItemText.setText(oldItemText);
        etEditDueDateText.setText(oldDueDateText);
        etEditItemText.setSelection(oldItemText.length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSubmit(View v) {
        Intent data = new Intent();

        data.putExtra("updatedItemText", etEditItemText.getText().toString());
        data.putExtra("updatedDueDateText", etEditDueDateText.getText().toString());
        data.putExtra("updatedPriorityText", ((TodoItem.Priority)spPriority.getSelectedItem()));
        data.putExtra("itemPosition",oldItemPosition);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish();
    }
}
