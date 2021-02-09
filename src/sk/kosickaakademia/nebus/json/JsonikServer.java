package sk.kosickaakademia.nebus.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
}
