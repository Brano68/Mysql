package sk.kosickaakademia.nebus.entity;

public class City {
    private String name;
    private int population;
    private String district;
    private String code3;
    private String country;
    private String city;

    public City(String name, int population){
        this.name = name;
        this.population = population;
    }

    public City(String name, int population, String district, String country) {
        this.name = name;
        this.population = population;
        this.district = district;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }
}
