package com.example.aotarolaalvarad.todoapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by aotarolaalvarad on 9/26/15.
 */
public class TodoItemView extends RelativeLayout {
    private TextView tvTitle;
    private TextView tvPriority;

    public TodoItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.todo_item_children, this, true);
        setupChildren();
    }

    public TodoItemView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public void setItem(TodoItem item) {
        tvTitle.setText(item.getTitle());
        tvPriority.setText(item.getPriority().toString());
    }

    private void setupChildren() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPriority = (TextView) findViewById(R.id.tvPriority);
    }

    public static TodoItemView inflate(ViewGroup parent){
        TodoItemView todoItemView = (TodoItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return todoItemView;

    }
}
