package com.nghianv.dialog;

/**
 * Created by nguyennghia on 28/03/2016.
 */
public class Author {
    private int id;
    private String name;

    public Author(){

    }

    public Author(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
