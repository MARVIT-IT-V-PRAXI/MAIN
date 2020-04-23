//=======================================
//
//  Fridge.java, (c) Team MaRViT 2020
//
//  This class is implementation of fridge
//  It's also sitting between JsonDB and fridge
//  Activity
//
//=======================================



//=======================================
//  IMPORTANT: TODO LIST:
//  Line 33: implement error handling
//  MainActivity: implement
//
//
//
//=======================================
package com.example.maervit.json;

import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Fridge {




    public Fridge(){

        dbUserRoot = JsonDB.dbGetRootElement("user.json");
        dbProductsRoot = JsonDB.dbGetRootElement("products.json");

        JSONObject userData = JsonDB.dbGetJSONObject("user",dbUserRoot);
        fridgeData = JsonDB.dbGetJSONArray("fridge", dbUserRoot);
        productData = JsonDB.dbGetJSONArray("content", dbProductsRoot);
        dateFormat = JsonDB.dbGetString("dateformat", userData);
    }


    public Item[] GetItemsInFridge() {

        Item[] items = new Item[fridgeData.length()];
        try {
            for (int i = 0; i < items.length; i++) {

                items[i].uObject = JsonDB.dbGetJSONObject(i, fridgeData);
                long productID = JsonDB.dbGetLong("id", items[i].uObject);
                JSONObject object = JsonDB.dbFindObjectWithValue(productData, "product_id", productID);

                items[i].dateAdded = new SimpleDateFormat(dateFormat).parse(JsonDB.dbGetString("adddate", items[i].uObject));
                items[i].dateExpiration = new SimpleDateFormat(dateFormat).parse(JsonDB.dbGetString("expdate", items[i].uObject));

                items[i].productName = JsonDB.dbGetString("product_name",object);
                items[i].countryOrigin = JsonDB.dbGetString("country_origin",object);
                items[i].description = JsonDB.dbGetString("desc",object);
                items[i].CO2Footprint = JsonDB.dbGetInt("co2footprint",object);

            }
        }
        catch(Exception e) {
            return null;
        }
        return items;
    }


    public void AddItem(Long id, String DateAdded, String EXPDate ) {
       String JSON = "";


       JSON += "{\n";
       JSON += "    \"id\": " + id.toString() + ",\n";
       JSON += "    \"adddate\": " + DateAdded  + ",\n";
       JSON += "    \"expdate\": " + EXPDate + ",\n";
       JSON += "}\n";

       try {
           JsonDB.dbAppendJSONObject("user.json", new JSONObject(JSON), fridgeData);
       }
       catch(Exception e){}

        dbUserRoot = JsonDB.dbGetRootElement("user.json"); //Reload user data

    }

    public void RemoveItem(Item item) {
        JsonDB.dbRemoveJSONObject("user.json", item.uObject,fridgeData);
        dbUserRoot = JsonDB.dbGetRootElement("user.json"); //Reload user data
    }

    private JSONObject dbUserRoot, dbProductsRoot;
    private String dateFormat;
    private JSONArray fridgeData, productData;









}


class Item{
    JSONObject uObject;
    Date dateAdded, dateExpiration;
    String productName, countryOrigin, description;
    int CO2Footprint;

}
