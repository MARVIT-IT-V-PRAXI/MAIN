//=========================================
//Database_Core.java
//
//(c) MaRViT 2020
//
// This class handles main functions of Hashing database
//=========================================
package com.example.maervit.DB;

public class Database_Core {

    public enum Status{

        DB_OK,
        DB_GENERAL_ERROR,
        DB_NOT_FOUND,
        DB_UNINITIALIZED,
        DB_OVERFLOW,
        DB_INDEX_OUT_OF_BOUNDS,
        DB_OVERWRITE,
        DB_ACCESS_DENIED,
        DB_ILLEGAL_OPERATION

        //TODO: expand error handling

    }


    public Database_Core(){
        options = new Database_Options();
        entryList = new Database_Entry[65536];
    }

    public Database_Core(int len){
        options = new Database_Options();
        options.setLen = len;
        entryList = new Database_Entry[len];
    }

    public Database_Core(Database_Options _options){

        options = _options;

        if(options.setLen > -1) entryList = new Database_Entry[options.setLen];
        else entryList = new Database_Entry[65536];

    }





    public Status CreateItem(int Index, Database_Entry entry) {

        if (options.setLen > -1 && Index > options.setLen)
            return Status.DB_INDEX_OUT_OF_BOUNDS;

        if (!options.allowChaining && !options.allowOverwriting && GetItem(Index) != null)
            return Status.DB_OVERWRITE;

        Database_Entry _TEMP = GetItem(Index);
        Database_Entry _RETURN_TEMP = GetItem(Index);

        if (!options.allowChaining || _TEMP == null) {
            if (options.setLen > -1)
                return __ALLOCATE_STATIC(Index, entry);
            return __ALLOCATE_DYNAMIC(Index, entry);
        }
        return Status.DB_ACCESS_DENIED;
    }

    public Database_Entry GetItem(int Index){
        if(entryList.length <= Index)
            return null;

        return entryList[Index];
    }

    public Status RemoveItem(int Index){

        if(options.staticDatabase)
            return Status.DB_ILLEGAL_OPERATION;

        if(entryList.length <= Index)
            return Status.DB_INDEX_OUT_OF_BOUNDS;

        if(options.shrinkBehaviour == Database_Options.ShrinkBehaviour.DB_LEAVE_HOLE) return __FREE_STATIC(Index);
        return __FREE_DYNAMIC(Index);
    }






    public Database_Options options;
    private Database_Entry[] entryList;



    private Status __ALLOCATE_STATIC(int _INDEX, Database_Entry __ENTRY){

        entryList[_INDEX] = __ENTRY;
        return Status.DB_OK;
    }

    private Status __ALLOCATE_DYNAMIC(int _INDEX, Database_Entry __ENTRY){


         if (entryList.length == options.maxLen)
             return Status.DB_OVERFLOW;

         Database_Entry[] ___TEMP;
         if(_INDEX >= entryList.length)___TEMP = new Database_Entry[entryList.length + (_INDEX - (entryList.length - 1))];
         else ___TEMP = new Database_Entry[entryList.length + 1];

         ___TEMP[_INDEX] = __ENTRY;

         for (int i = 0; i < entryList.length; i++){
             ___TEMP[i] = entryList[i];
         }

         entryList = ___TEMP;
         return Status.DB_OK;
    }


    private Status __FREE_STATIC(int _INDEX){
        entryList[_INDEX] = null;
        return Status.DB_OK;

    }
    private Status __FREE_DYNAMIC(int _INDEX){

        Database_Entry[] ___TEMP = new Database_Entry[entryList.length - 1];

        int INDEX_ADDITION = 0;
        for (int i = 0; i < ___TEMP.length; i++){

            if (i == _INDEX)INDEX_ADDITION ++;
            ___TEMP[i] = entryList[i + INDEX_ADDITION];
        }

        entryList = ___TEMP;
        return Status.DB_OK;


    }


}
