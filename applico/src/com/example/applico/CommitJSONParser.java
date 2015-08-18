package com.example.applico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


 
/** A class to parse json data */
public class CommitJSONParser {
 
    // Receives a JSONObject and returns a list
    public List<HashMap<String,Object>> parse(JSONObject jObject){
 
        JSONArray jCommits = null;
        try {
            // Retrieves all the elements in the 'countries' array
            jCommits = jObject.getJSONArray("commits");
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        // Invoking getCountries with the array of json object
        // where each json object represent a country
        return getCommits(jCommits);
    }
 
    private List<HashMap<String, Object>> getCommits(JSONArray jCommits){
        int commitCount = jCommits.length();
        List<HashMap<String, Object>> commitList = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> commit = null;
 
        // Taking each country, parses and adds to list object
        for(int i=0; i<commitCount;i++){
            try {
                // Call getCountry with country JSON object to parse the country
                commit = getCountry((JSONObject)jCommits.get(i));
                commitList.add(commit);
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
 
        return commitList;
    }
 
    // Parsing the Country JSON object
    private HashMap<String, Object> getCountry(JSONObject jCommit){
 
        HashMap<String, Object> country = new HashMap<String, Object>();
        String commitName = "";
        String flag="";
        String language = "";
        String capital = "";
        String currencyCode = "";
        String currencyName = "";
 
        try {
            commitName = jCommit.getString("countryname");
            flag = jCommit.getString("flag");
            language = jCommit.getString("language");
            capital = jCommit.getString("capital");
            currencyCode = jCommit.getJSONObject("currency").getString("code");
            currencyName = jCommit.getJSONObject("currency").getString("currencyname");
 
            String details =        "Language : " + language + "\n" +
                                "Capital : " + capital + "\n" +
                                "Currency : " + currencyName + "(" + currencyCode + ")";
 
            country.put("country", commitName);
            country.put("flag", R.drawable.ic_launcher);
            country.put("flag_path", flag);
            country.put("details", details);
 
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return country;
    }
}
