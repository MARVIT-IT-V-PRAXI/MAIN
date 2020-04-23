package com.example.maervit.json;

import android.content.Context;

import org.json.*;
import java.io.*;
import java.util.Iterator;

public class JsonDB {

    public static JSONObject dbGetRootElement(String FileName) {

        Context context = null;
        JSONObject object = null;

        File file = new File(context.getFilesDir(), FileName);
        byte[] bytes = new byte[(int) file.length()];

        try {
            FileInputStream in = new FileInputStream(file);

            try {
                in.read(bytes);
            } finally {
                in.close();
            }
        }
        catch(Exception e){
            //C
        }
        String contents = new String(bytes);

        try{

            object = new JSONObject(contents);
        }
        catch(JSONException err){}
        return object;
    }


    public static int dbGetInt(String key, JSONObject parent){
        int output = 0;
        try{
            output = parent.getInt(key);
        }catch(Exception e){}
        return output;
    }
    public static long dbGetLong(String key, JSONObject parent){
        long output = 0;
        try{
            output = parent.getLong(key);
        }catch(Exception e){}
        return output;
    }
    public static String dbGetString(String key, JSONObject parent){
        String output = null;
        try{
            output = parent.getString(key);
        }catch(Exception e){}
        return output;
    }
    public static JSONObject dbGetJSONObject(String key, JSONObject parent){
        JSONObject output = null;
        try{
            output = parent.getJSONObject(key);
        }catch(Exception e){}
        return output;
    }
    public static JSONObject dbGetJSONObject(int index, JSONArray parent){
        JSONObject output = null;
        try{
            output = parent.getJSONObject(index);
        }catch(Exception e){}
        return output;
    }
    public static JSONArray dbGetJSONArray(String key, JSONObject parent){
        JSONArray output = null;
        try{
            output = parent.getJSONArray(key);
        }catch(Exception e){}
        return output;
    }
    public static JSONObject dbFindObjectWithValue(JSONArray parent, String key, String value){
        JSONObject object = null;
        try {
            for (int i = 0; i < parent.length(); i++) {
                Iterator<String> keys = parent.getJSONObject(i).keys();

                while (keys.hasNext())
                    if (keys.next() == key) {
                        object = parent.getJSONObject(i);
                        break;
                    }

                if (object != null && object.getString(key) == value) return object;
            }
        }
        catch (Exception e){
            errorStr = e.getMessage();
            return null;
        }
        return null;
    }
    public static JSONObject dbFindObjectWithValue(JSONArray parent, String key, int value){
        JSONObject object = null;
        try {
            for (int i = 0; i < parent.length(); i++) {
                Iterator<String> keys = parent.getJSONObject(i).keys();

                while (keys.hasNext())
                    if (keys.next() == key) {
                        object = parent.getJSONObject(i);
                        break;
                    }

                if (object != null && object.getInt(key) == value) return object;
            }
        }
        catch (Exception e){
            errorStr = e.getMessage();
            return null;
        }
        return null;
    }
    public static JSONObject dbFindObjectWithValue(JSONArray parent, String key, long value){
        JSONObject object = null;
        try {
            for (int i = 0; i < parent.length(); i++) {
                Iterator<String> keys = parent.getJSONObject(i).keys();

                while (keys.hasNext())
                    if (keys.next() == key) {
                        object = parent.getJSONObject(i);
                        break;
                    }

                if (object != null && object.getLong(key) == value) return object;
            }
        }
        catch (Exception e){
            errorStr = e.getMessage();
            return null;
        }
        return null;
    }

    public static void dbAppendJSONObject(String FileName, JSONObject object, JSONArray parent){

        Context context = null;
        File file = new File(context.getFilesDir(), FileName);
        byte[] bytes = new byte[(int) file.length()];


        try {
            FileInputStream in = new FileInputStream(file);

            try {
                in.read(bytes);
            } finally {
                in.close();
            }
        }
        catch(Exception e){ }


        String JSONObjString = object.toString(), JSONArrString = parent.toString(), content = new String(bytes);

        int lastElementPos = JSONArrString.lastIndexOf('}');
        JSONArrString = (JSONArrString.substring(0, lastElementPos)) + ",\n" + JSONObjString + JSONArrString.substring(lastElementPos); //Append the object
        content.replace(parent.toString(), JSONArrString); //Replace the old with appended version


        try {
            FileOutputStream in = new FileOutputStream(file);

            try {
                in.write(content.getBytes());
            } finally {
                in.close();
            }
        }
        catch(Exception e){ }


    }
    public static void dbRemoveJSONObject(String FileName, JSONObject object, JSONArray parent){
        Context context = null;
        File file = new File(context.getFilesDir(), FileName);
        byte[] bytes = new byte[(int) file.length()];


        try {
            FileInputStream in = new FileInputStream(file);

            try {
                in.read(bytes);
            } finally {
                in.close();
            }
        }
        catch(Exception e){ }

        try {
            String JSONObjString = object.toString(), JSONArrString = parent.toString(), content = new String(bytes);


            if (parent.getJSONObject(parent.length() - 1) != object)JSONObjString += ',';
            JSONArrString .replace(JSONObjString, "");
            try {
                FileOutputStream in = new FileOutputStream(file);

                try {
                    in.write(content.getBytes());
                } finally {
                    in.close();
                }
            }
            catch(Exception e){ }



        }
        catch(Exception e){}
    }


    public static String dbGetError(){
        return errorStr;
    }




    static private String errorStr = "";

}

