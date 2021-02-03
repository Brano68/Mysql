package sk.kosickaakademia.nebus;

import sk.kosickaakademia.nebus.entity.City;
import sk.kosickaakademia.nebus.entity.Country;
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

        databazaa.updatePopulation("Slovakia", "Bratislava", 8);


    }
}
