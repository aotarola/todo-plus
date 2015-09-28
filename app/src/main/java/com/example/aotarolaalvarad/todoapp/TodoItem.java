package com.example.aotarolaalvarad.todoapp;
import com.orm.SugarRecord;
import java.util.Date;

public class TodoItem extends SugarRecord{

    //attributes
    private String title;
    private Date dueDate;

    private Priority priority;

    public static enum Priority {
        LOW, MEDIUM, HIGH;

        @Override
        public String toString() {
            return super.toString().toUpperCase();
        }
    }

    //Constructors

    public TodoItem(){
    }

    public TodoItem(String title){
        this.title = title;
        this.priority = Priority.MEDIUM;
        this.dueDate = new Date();
    }

    //Overrides

    @Override
    public String toString(){
        return this.title;
    }

    //Properties

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}