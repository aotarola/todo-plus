package com.example.aotarolaalvarad.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditItemText;
    Integer itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etEditItemText =  (EditText) findViewById(R.id.etEditItemText);
        String itemValue = getIntent().getStringExtra("itemValue");
        itemPosition = getIntent().getIntExtra("itemPosition",0);
        etEditItemText.setText(itemValue);
        etEditItemText.setSelection(itemValue.length());
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
        data.putExtra("itemPosition",itemPosition);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish();
    }
}
