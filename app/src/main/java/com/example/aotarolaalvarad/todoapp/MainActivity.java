package com.example.aotarolaalvarad.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                todoItems.remove(position).delete();
                aToDoAdapter.notifyDataSetChanged();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, EditItemActivity.class);
                myIntent.putExtra("oldItemText", todoItems.get(position).toString() );
                myIntent.putExtra("oldItemPosition", position);
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        });
    }

    private void populateArrayItems() {
        todoItems = TodoItem.listAll(TodoItem.class);
        aToDoAdapter = new TodoItemAdapter(this, todoItems);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            int position = data.getIntExtra("itemPosition", 0);
            String updatedItemText = data.getStringExtra("updatedItemText");

            //Extract local Activity values
            String oldItemText = todoItems.get(position).toString();

            // Check if it's worth to write in data store
            if(updatedItemText != oldItemText) {
                TodoItem selectedITem = new TodoItem(updatedItemText);
                selectedITem.save();
                todoItems.set(position, selectedITem);
                aToDoAdapter.notifyDataSetChanged();
            }
        }
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
}
