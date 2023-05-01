package collectionStorageManager;

import models.*;
import models.handlers.CityHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class PostgreSQLManager implements DatabaseManager {

    @Override
    public ArrayList<City> readFromDatabase() {
        ArrayList<City> data = new ArrayList<>();
        HashMap<Integer, Coordinates> coordinatesMap = new HashMap<>();
        HashMap<Integer, Human> humansMap = new HashMap<>();

        try {
            Properties info = new Properties();
            info.load(this.getClass().getResourceAsStream("/db.cfg"));
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
            Statement statement = connection.createStatement();

            ResultSet resultCoordinatesSet = statement.executeQuery("SELECT * FROM Coordinates");
            while (resultCoordinatesSet.next()) {
                int id = resultCoordinatesSet.getInt("id");
                int x = resultCoordinatesSet.getInt("x");
                double y = resultCoordinatesSet.getDouble("y");
                coordinatesMap.put(id, new Coordinates(x, y));
            }

            ResultSet resultHumanSet = statement.executeQuery("SELECT * FROM Human");
            while (resultHumanSet.next()) {
                int id = resultHumanSet.getInt("id");
                String name = resultHumanSet.getString("name");
                humansMap.put(id, new Human(name));
            }

            ResultSet resultSet = statement.executeQuery("SELECT * FROM City");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Coordinates coordinates = coordinatesMap.get(resultSet.getInt("coordinates_id"));
                Date creationDate = resultSet.getDate("creation_date");
                Integer area = resultSet.getInt("area");
                int population = resultSet.getInt("population");
                Double metersAboveSeaLevel = resultSet.getDouble("meters_above_sea_level");
                if (resultSet.wasNull()) {
                    metersAboveSeaLevel = null;
                }
                Climate climate = Climate.valueOf(resultSet.getString("climate"));
                Government government = Government.valueOf(resultSet.getString("government"));
                int governorId = resultSet.getInt("governor_id");
                Human governor = humansMap.get(governorId);
                StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(resultSet.getString("standard_of_living"));

                City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
                data.add(city);
            }
            return data;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void writeToDatabase() {
        try {
            Properties info = new Properties();
            info.load(this.getClass().getResourceAsStream("/db.cfg"));
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
            connection.setAutoCommit(false);

            // Запись в таблицу Coordinates
            String insertCoordinatesQuery = "INSERT INTO Coordinates (x, y) VALUES (?, ?) RETURNING id";
            PreparedStatement insertCoordinatesStatement = connection.prepareStatement(insertCoordinatesQuery);

            // Запись в таблицу Human
            String insertHumanQuery = "INSERT INTO Human (name) VALUES (?) RETURNING id";
            PreparedStatement insertHumanStatement = connection.prepareStatement(insertHumanQuery);

            // Запись в таблицу City
            String insertCityQuery = "INSERT INTO City (name, coordinates_id, creation_date, area, population, metersAboveSeaLevel, climate, government, governor_id, standardOfLiving) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertCityStatement = connection.prepareStatement(insertCityQuery);

            for (City city : CityHandler.getInstance().getCollection()) {
                // Запись в таблицу Coordinates и получение сгенерированного id
                insertCoordinatesStatement.setInt(1, city.getCoordinates().getX());
                insertCoordinatesStatement.setDouble(2, city.getCoordinates().getY());
                ResultSet coordinatesIdResultSet = insertCoordinatesStatement.executeQuery();
                coordinatesIdResultSet.next();
                int coordinatesId = coordinatesIdResultSet.getInt("id");

                // Запись в таблицу Human и получение сгенерированного id
                insertHumanStatement.setString(1, city.getGovernor().getName());
                ResultSet humanIdResultSet = insertHumanStatement.executeQuery();
                humanIdResultSet.next();
                int humanId = humanIdResultSet.getInt("id");

                // Запись в таблицу City
                insertCityStatement.setString(1, city.getName());
                insertCityStatement.setInt(2, coordinatesId);
                insertCityStatement.setDate(3, new java.sql.Date(city.getCreationDate().getTime()));
                insertCityStatement.setInt(4, city.getArea());
                insertCityStatement.setInt(5, city.getPopulation());
                if (city.getMetersAboveSeaLevel() != null) {
                    insertCityStatement.setDouble(6, city.getMetersAboveSeaLevel());
                } else {
                    insertCityStatement.setNull(6, Types.DOUBLE);
                }
                insertCityStatement.setString(7, city.getClimate().toString());
                insertCityStatement.setString(8, city.getGovernment().toString());
                insertCityStatement.setInt(9, humanId);
                insertCityStatement.setString(10, city.getStandardOfLiving().toString());

                insertCityStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
