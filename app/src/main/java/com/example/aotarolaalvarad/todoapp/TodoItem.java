package com.example.aotarolaalvarad.todoapp;

import android.nfc.Tag;

import com.orm.SugarRecord;

public class TodoItem extends SugarRecord<TodoItem>{

    //attributes
    private String title;

    //Constructors

    public TodoItem(){
    }

    public TodoItem(String title){
        this.title = title;
    }

    //Overrides

    @Override
    public String toString(){
        return this.title;
    }

    //Properties

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}