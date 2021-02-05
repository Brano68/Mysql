package sk.kosickaakademia.nebus.entity;

public class CapitalCity {
    private String nameCountry;
    private String nameCapitalCity;
    private int countOfCityzenships;

    public CapitalCity(String nameCountry, String nameCapitalCity, int countOfCityzenships) {
        this.nameCountry = nameCountry;
        this.nameCapitalCity = nameCapitalCity;
        this.countOfCityzenships = countOfCityzenships;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public String getNameCapitalCity() {
        return nameCapitalCity;
    }

    public int getCountOfCityzenships() {
        return countOfCityzenships;
    }
}
