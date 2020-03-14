package com.example.maervit.DB;

public class Database_Options {

    Database_Options(){ //Init to defaults
        setLen = -1;
        maxLen = Integer.MAX_VALUE;
        allowOverwriting = true;
        staticDatabase = false;
        shrinkBehaviour = ShrinkBehaviour.DB_SHRINK;
        allowChaining = false;
    }

    public enum ShrinkBehaviour{

        DB_SHRINK,
        DB_LEAVE_HOLE,

        //TODO: maybe add more shrink options

    }

    public int setLen;
    public int maxLen;
    public boolean allowOverwriting;
    public boolean staticDatabase;
    public boolean allowChaining;
    public ShrinkBehaviour shrinkBehaviour;



}


