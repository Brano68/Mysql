package sk.kosickaakademia.nebus;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import sk.kosickaakademia.nebus.entity.CapitalCity;
import sk.kosickaakademia.nebus.entity.City;
import sk.kosickaakademia.nebus.entity.Country;
import sk.kosickaakademia.nebus.entity.Monument;
import sk.kosickaakademia.nebus.json.JsonikServer;
import sk.kosickaakademia.nebus.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Databazaa databazaa = new Databazaa();
        Output out = new Output();
        /*
        String name = "Italy";

        Country country = databazaa.getCountryInfo(name);
        out.printCountryInfo(country);




        Country country2 = databazaa.getCountryInfoWithLanguage(name);
        out.printCountryInfoo(country2);
         */



        /*
        List<City> list = databazaa.getCities(name);
        out.printCities(list);
         */


        //String code = databazaa.getCountryCode("Anglicko");
       //System.out.println(code);

        //City city = new City("Cerveny Klastor", 1500, "Stara Lubovna", "Slovakia");
        //databazaa.insertCity(city);

        //databazaa.updatePopulation("Slovakia", "Bratislava", 8);

        //List<CapitalCity> list = databazaa.getCapitalCities("Asia");
        //out.printCapitalCities(list);

        //databazaa.insertNewMonument("SVK", "Bratislava", "Bratislavsky Hrad");

        //List<Monument> list = databazaa.getMonuments();
        //System.out.println();
        //out.printMonuments(list);

        JsonikServer jsonikServer = new JsonikServer();
        System.out.println(jsonikServer.getMonuments());

        String json = makeJson().toJSONString();
        System.out.println(json);

        System.out.println(jsonikServer.insertNewMonument(makeJson()));

    }


    public static JSONObject makeJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code3","SVK");
        jsonObject.put("city", "Bratislava");
        jsonObject.put("name", "Bratislavsky UFO most");
        return jsonObject;
    }


}
