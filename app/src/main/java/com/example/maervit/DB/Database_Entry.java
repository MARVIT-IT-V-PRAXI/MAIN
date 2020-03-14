package com.example.maervit.DB;

public class Database_Entry {

    Database_Entry(){
        next_in_chain = null;
    }

    public int ExpDate;
    public int Country;
    public String Name;
    public long BarCode;

    public Database_Entry next_in_chain;
}
