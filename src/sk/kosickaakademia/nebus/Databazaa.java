package sk.kosickaakademia.nebus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Databazaa {
    private String url = "jdbc:mysql://itsovy.sk:3306/world_x";
    private String username = "---";
    private String password = "---";

    public void showCities(String country){
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

        String query = "SELECT city.Name, city.CountryCode " +
                "FROM city " +
                "INNER JOIN country ON country.code = city.CountryCode " +
                "WHERE country.name LIKE ?";
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
                    String city = rs.getString("Name");
                    String code = rs.getString("CountryCode");
                    System.out.println(city +" "+ code);
                }
                conn.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
