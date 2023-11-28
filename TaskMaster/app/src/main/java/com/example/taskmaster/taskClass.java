package com.example.taskmaster;


public class taskClass {
    String tid,title,task,username;

    public taskClass(String tid, String title, String task, String username) {
        this.tid = tid;
        this.title = title;
        this.task = task;
        this.username = username;
    }



    public taskClass() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
