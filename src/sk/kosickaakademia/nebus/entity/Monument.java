package sk.kosickaakademia.nebus.entity;

import java.util.List;

public class Monument {

    private String countryName;
    private String cityName;
    private String monumentName;
    private int idMonument;

    public Monument(String countryName, String cityName, String monumentName, int idMonument) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.monumentName = monumentName;
        this.idMonument = idMonument;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMonumentName() {
        return monumentName;
    }

    public int getIdMonument() {
        return idMonument;
    }

}
