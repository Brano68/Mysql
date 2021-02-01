package sk.kosickaakademia.nebus.output;

import sk.kosickaakademia.nebus.entity.City;
import sk.kosickaakademia.nebus.entity.Country;

import java.util.List;

public class Output {

    public void printCities(List<City> list){
        for(City city : list){
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    public void printCountryInfo(Country country){
        if(country == null){
            System.out.println("Country does not exist");
        }else{
            System.out.println(country.getName() + " " + country.getCode3() +
                    " " + country.getCapitalCity() + " " + country.getArea());
        }

    }
}
