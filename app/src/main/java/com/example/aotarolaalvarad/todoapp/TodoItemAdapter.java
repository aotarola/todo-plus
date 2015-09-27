package com.example.aotarolaalvarad.todoapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aotarolaalvarad on 9/26/15.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemAdapter(Context context, List<TodoItem> todoItems){
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TodoItemView todoItemView = (TodoItemView) convertView;

        if(null == todoItemView)
            todoItemView = todoItemView.inflate(parent);

        todoItemView.setItem(getItem(position));

        return todoItemView;

    }
}
