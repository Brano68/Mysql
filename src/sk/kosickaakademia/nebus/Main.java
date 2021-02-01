package sk.kosickaakademia.nebus;

import sk.kosickaakademia.nebus.entity.City;
import sk.kosickaakademia.nebus.entity.Country;
import sk.kosickaakademia.nebus.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Databazaa databazaa = new Databazaa();
        Output out = new Output();
        String name = "Italy";

        Country country = databazaa.getCountryInfo(name);
        out.printCountryInfo(country);
        /*
        List<City> list = databazaa.getCities(name);

        out.printCities(list);

         */
    }
}
