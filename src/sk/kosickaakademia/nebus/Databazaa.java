package sk.kosickaakademia.nebus;

import sk.kosickaakademia.nebus.entity.City;
import sk.kosickaakademia.nebus.entity.Country;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Databazaa {
    private String url = "jdbc:mysql://itsovy.sk:3306/world_x";
    private String username = "mysqluser";
    private String password = "Kosice2021!";

    public List<City> getCities(String country){
        /*
        String query = "SELECT Name, CountryCode " +
                " FROM city " +
                " WHERE CountryCode LIKE '"+countryCode+"'";

         */
        /*
        String query = "SELECT Name, CountryCode " +
                " FROM city " +
                " WHERE CountryCode LIKE ?";

         */
        /*
        String query = "SELECT city.Name, city.CountryCode " +
                "FROM city " +
                "INNER JOIN country ON country.code = city.CountryCode " +
                "WHERE country.name LIKE ?";


        */
        String query = "SELECT city.Name, JSON_EXTRACT(Info,'$.Population') AS Population " +
                "FROM city " +
                "INNER JOIN country ON country.code = city.CountryCode " +
                "WHERE country.name LIKE ? ORDER BY Population DESC";
        List<City> list = new ArrayList<City>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            if(conn != null){

                System.out.println("Success");
                PreparedStatement ps = conn.prepareStatement(query);
                //osetrenie otaznikom
                ps.setString(1, country);

                System.out.println(ps);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("Name");
                    //String code = rs.getString("CountryCode");
                    //System.out.println(city +" "+ code);
                    int pop = rs.getInt("Population");
                    //System.out.println(name + "(" + pop + ")");
                    City city = new City(name, pop);
                    list.add(city);

                }
                conn.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public Country getCountryInfo(String country){
        String query = "SELECT country.name, country.code, city.name, " +
                " JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS Continent, " +
                " JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS Area" +
                " FROM country " +
                " INNER JOIN city ON country.Capital = city.ID " +
                " INNER JOIN countryinfo ON country.code = countryinfo._id " +
                " WHERE country.name like ?";

        Country countryInfo = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String code3 = rs.getString("country.code");
                String capitalCity = rs.getString("city.name");
                String continent = rs.getString("Continent");
                int area = rs.getInt("Area");
                //System.out.println(code3 + " " + capitalCity + " " + continent + " " + area);
                countryInfo = new Country(country, code3, capitalCity, area, continent);
            }

            conn.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return countryInfo;
    }

    public Country getCountryInfoWithLanguage(String country){
        String query = "SELECT country.name, country.code, city.name, " +
                " JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS Continent, " +
                " JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS Area" +
                " FROM country " +
                " INNER JOIN city ON country.Capital = city.ID " +
                " INNER JOIN countryinfo ON country.code = countryinfo._id " +
                " WHERE country.name like ?";

        Country countryInfo = null;
        String code3 = "";
        String capitalCity = "";
        String continent = "";
        int area = 0;
        ArrayList<String> languages;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                code3 = rs.getString("country.code");
                capitalCity = rs.getString("city.name");
                continent = rs.getString("Continent");
                area = rs.getInt("Area");
                //String language = rs.getString("language");
                //System.out.println(code3 + " " + capitalCity + " " + continent + " " + area + " " + language);
                //languages = findOutLanguages(code3);
            }
            conn.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        languages = findOutLanguages(code3);
        countryInfo = new Country(country, code3, capitalCity, area, continent, languages);
        return countryInfo;
    }

    public ArrayList<String> findOutLanguages(String CountryCode){
        ArrayList<String> languages = new ArrayList<>();

        String query = "SELECT countrylanguage.language " +
                " FROM countrylanguage " +
                " WHERE CountryCode like ? AND IsOfficial like 'T'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, CountryCode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String language = rs.getString("Language");
                //System.out.println(language);
                languages.add(language);
            }
            conn.close();
            return languages;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String getCountryCode(String name){
        if(name == null || name.equalsIgnoreCase("")){
            return null;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "Select code from country where name like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String code = rs.getString("Code");
                return code;
            }

            conn.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public void insertCity(City city){
        String country = city.getCountry();
        String code3 = getCountryCode(country);
        if(code3==null){
            System.out.println("Warning. The country doesnt exist!!!");
        }else{
            city.setCode3(code3);
            String query = "INSERT INTO city (Name, CountryCode, District, Info) " +
                    " VALUES( ?,?,?,?) ";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, city.getName());
                ps.setString(2, city.getCode3());
                ps.setString(3, city.getDistrict());
                String json = "{\"Population\":"+city.getPopulation()+"}";
                ps.setString(4,json);
                int result = ps.executeUpdate();
                System.out.println("Result" + result);

                conn.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
