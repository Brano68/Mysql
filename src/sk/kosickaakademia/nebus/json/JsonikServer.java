package sk.kosickaakademia.nebus.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.nebus.Databazaa;
import sk.kosickaakademia.nebus.entity.Monument;

import java.util.List;

public class JsonikServer {

    public String getMonuments(){
        String resultJSON = "";
        List<Monument> list = new Databazaa().getMonuments();
        if(list.isEmpty()){
            return "{}";
        }

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(Monument i : list){
            JSONObject newItem = new JSONObject();
            newItem.put("ID", i.getIdMonument());
            newItem.put("Country", i.getCountryName());
            newItem.put("City", i.getCityName());
            newItem.put("Monument", i.getMonumentName());
            jsonArray.add(newItem);
        }
        jsonObject.put("Count", list.size());
        jsonObject.put("monuments", jsonArray);

        resultJSON = jsonObject.toJSONString();

        return resultJSON;

    }


    public JSONObject getMonumentss(){
        String resultJSON = "";
        List<Monument> list = new Databazaa().getMonuments();
        if(list.isEmpty()){
            return null;
        }

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(Monument i : list){
            JSONObject newItem = new JSONObject();
            newItem.put("ID", i.getIdMonument());
            newItem.put("Country", i.getCountryName());
            newItem.put("City", i.getCityName());
            newItem.put("Monument", i.getMonumentName());
            jsonArray.add(newItem);
        }
        jsonObject.put("Count", list.size());
        jsonObject.put("monuments", jsonArray);
        return jsonObject;

    }


    public boolean insertNewMonument(JSONObject json){
        if(json.isEmpty()){
            return false;
        }
        System.out.println(json);

        String code3 = (String) json.get("code3");
        String country = (String) json.get("city");
        String name = (String) json.get("name");
        if(new Databazaa().insertNewMonument(code3, country, name)){
            return true;
        }
        return false;
    }


    public boolean insertNewMonumet(String json) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        System.out.println(jsonObject);
        String code3 = (String) jsonObject.get("code3");
        String city = (String) jsonObject.get("city");
        String name = (String) jsonObject.get("name");
        if(new Databazaa().insertNewMonument(code3, city, name)){
            return true;
        }
        return false;
    }
}
