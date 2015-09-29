package com.example.aotarolaalvarad.todoapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.text.ParseException;
import java.util.Date;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aotarolaalvarad.todoapp.EditItemDialog.EditItemDialogListener;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EditItemDialogListener {

    private final int REQUEST_CODE = 200;

    List<TodoItem> todoItems;
    TodoItemAdapter aToDoAdapter;
    ListView lvItems;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position).markAsDone();
                aToDoAdapter.notifyDataSetChanged();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem selectedTodoItem = todoItems.get(position);

                showEditDialog(selectedTodoItem, position);
            }
        });
    }

    private void showEditDialog(TodoItem selectedTodoItem, int position) {
        FragmentManager fm = getSupportFragmentManager();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        EditItemDialog editItemDialog = EditItemDialog.newInstance(
                selectedTodoItem.getTitle(),
                format.format(selectedTodoItem.getDueDate()),
                selectedTodoItem.getPriority(),
                position
        );
        editItemDialog.show(fm, "dialog_edit_item");
    }

    private void populateArrayItems() {
        todoItems = Select.from(TodoItem.class)
                    .where(Condition.prop("is_done").eq("false"))
                    .list();
        aToDoAdapter = new TodoItemAdapter(this, todoItems);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onAddItem(View view) {
        TodoItem newTodoITem = new TodoItem(etEditText.getText().toString());
        aToDoAdapter.add(newTodoITem);
        newTodoITem.save();
        etEditText.setText("");
    }

    @Override
    public void onFinishEditDialog(String title, String dueDate, TodoItem.Priority priority, int position) {
        TodoItem selectedItem = todoItems.get(position);

        Date myDate = null;
        try {
            myDate = new SimpleDateFormat("MM/dd/yyyy").parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        selectedItem.setTitle(title);
        selectedItem.setDueDate(myDate);
        selectedItem.setPriority(priority);
        selectedItem.save();
        aToDoAdapter.notifyDataSetChanged();
    }
}
