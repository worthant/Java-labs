package collectionStorageManager;

import clientLogic.ClientHandler;
import clientLogic.PasswordHandler;
import main.ConnectionUtility;
import models.*;
import models.handlers.CityHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PostgreSQLManager implements DatabaseManager {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab7.PostgreSQLManager");

    @Override
    public ArrayList<City> getCollectionFromDatabase() {
        ArrayList<City> data = new ArrayList<>();
        Connection connection = ConnectionUtility.getConnection();

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT City.id, City.name as city_name, Coordinates.x, Coordinates.y, " +
                    "City.creation_date, City.area, City.population, City.meters_above_sea_level, " +
                    "City.climate, City.government, Human.name as governor_name, City.standard_of_living " +
                    "FROM City " +
                    "JOIN Coordinates ON City.coordinates_id = Coordinates.id " +
                    "JOIN Human ON City.governor_id = Human.id";


            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("city_name");
                Coordinates coordinates = new Coordinates(resultSet.getInt("x"), resultSet.getDouble("y"));
                Date creationDate = resultSet.getDate("creation_date");
                Integer area = resultSet.getInt("area");
                int population = resultSet.getInt("population");
                Double metersAboveSeaLevel = resultSet.getDouble("meters_above_sea_level");
                Climate climate = Climate.valueOf(resultSet.getString("climate"));
                Government government = Government.valueOf(resultSet.getString("government"));
                Human governor = new Human(resultSet.getString("governor_name"));
                StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(resultSet.getString("standard_of_living"));

                City city = new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
                data.add(city);
            }
        } catch (SQLException e) {
            logger.error("something went wrong during getting collection from db", e);
        }

        return data;
    }

    @Override
    public void writeCollectionToDatabase() {
        Connection connection = ConnectionUtility.getConnection();

        try {
            connection.setAutoCommit(false);

            // Retrieve all existing city IDs from the database
            Set<Long> existingCityIds = new HashSet<>();
            String getCityIdsQuery = "SELECT id FROM City";
            PreparedStatement getCityIdsStatement = connection.prepareStatement(getCityIdsQuery);
            ResultSet cityIdsResultSet = getCityIdsStatement.executeQuery();
            while (cityIdsResultSet.next()) {
                existingCityIds.add(cityIdsResultSet.getLong("id"));
            }

            for (City city : CityHandler.getInstance().getCollection()) {
                if (!existingCityIds.contains(city.getId())) {
                    city.setId(addElementToDatabase(city, connection));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error("something went wrong during writing collection to db", e);
        }
    }


    public long writeObjectToDatabase(City city) {
        Connection connection = ConnectionUtility.getConnection();
        long generatedId = -1;
        try {
            connection.setAutoCommit(false);

            generatedId = addElementToDatabase(city, connection);
            connection.commit();
        } catch (SQLException e) {
            logger.error("something went wrong during writing object to db", e);
        }
        return generatedId;
    }


    public long addElementToDatabase(City city, Connection connection) {
        long generatedId = -1;

        try {
            // Insert Coordinates
            String insertCoordinatesQuery = "INSERT INTO Coordinates (x, y) VALUES (?, ?) RETURNING id";
            PreparedStatement insertCoordinatesStatement = connection.prepareStatement(insertCoordinatesQuery);
            insertCoordinatesStatement.setInt(1, city.getCoordinates().getX());
            insertCoordinatesStatement.setDouble(2, city.getCoordinates().getY());
            ResultSet coordinatesResultSet = insertCoordinatesStatement.executeQuery();
            int coordinatesId = -1;
            if (coordinatesResultSet.next()) {
                coordinatesId = coordinatesResultSet.getInt(1);
            }

            // Insert Human (Governor)
            String insertHumanQuery = "INSERT INTO Human (name) VALUES (?) RETURNING id";
            PreparedStatement insertHumanStatement = connection.prepareStatement(insertHumanQuery);
            insertHumanStatement.setString(1, city.getGovernor().getName());
            ResultSet humanResultSet = insertHumanStatement.executeQuery();
            int governorId = -1;
            if (humanResultSet.next()) {
                governorId = humanResultSet.getInt(1);
            }

            // Insert City
            String insertCityQuery = "INSERT INTO City (name, coordinates_id, creation_date, area, population, meters_above_sea_level, climate, government, governor_id, standard_of_living) " +
                    "VALUES (?, ?, ?, ?, ?, ?, CAST(? AS climate_enum), CAST(? AS government_enum), ?, CAST(? AS standard_of_living_enum)) RETURNING id";
            PreparedStatement insertCityStatement = connection.prepareStatement(insertCityQuery);
            insertCityStatement.setString(1, city.getName());
            insertCityStatement.setInt(2, coordinatesId);
            insertCityStatement.setDate(3, new java.sql.Date(city.getCreationDate().getTime()));
            insertCityStatement.setInt(4, city.getArea());
            insertCityStatement.setInt(5, city.getPopulation());
            if (city.getMetersAboveSeaLevel() != null)
                insertCityStatement.setDouble(6, city.getMetersAboveSeaLevel());
            else insertCityStatement.setNull(6, Types.DOUBLE);
            insertCityStatement.setString(7, city.getClimate().toString());
            insertCityStatement.setString(8, city.getGovernment().toString());
            insertCityStatement.setInt(9, governorId);
            insertCityStatement.setString(10, city.getStandardOfLiving().toString());
            ResultSet cityResultSet = insertCityStatement.executeQuery();
            if (cityResultSet.next()) {
                generatedId = cityResultSet.getLong(1);
            }

            // Insert relationship between the City and the User (Creator)
            String upsertCreatorQuery = "INSERT INTO Creator (city_id, user_id) VALUES (?, ?) ON CONFLICT (city_id) DO NOTHING";
            PreparedStatement upsertCreatorStatement = connection.prepareStatement(upsertCreatorQuery);
            upsertCreatorStatement.setLong(1, generatedId);
            upsertCreatorStatement.setLong(2, ClientHandler.getUserId());
            upsertCreatorStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error adding element to database", e);
        }
        return generatedId;
    }


    public boolean removeCityById(long cityId) {
        Connection connection = ConnectionUtility.getConnection();
        try {
            String deleteCityQuery = "DELETE FROM City WHERE id = ? AND id IN (SELECT city_id FROM Creator WHERE user_id = ?)";
            PreparedStatement deleteCityStatement = connection.prepareStatement(deleteCityQuery);
            deleteCityStatement.setLong(1, cityId);
            deleteCityStatement.setLong(2, ClientHandler.getUserId());
            int rowsAffected = deleteCityStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long authUser(String name, char[] passwd) {
        Connection connection = ConnectionUtility.getConnection();
        try {
            String selectUserQuery = "SELECT id, passwd_hash, passwd_salt FROM \"User\" WHERE name = ?";
            PreparedStatement selectUserStatement = connection.prepareStatement(selectUserQuery);
            selectUserStatement.setString(1, name);
            ResultSet resultSet = selectUserStatement.executeQuery();

            if (resultSet.next()) {
                String passwdHash = resultSet.getString("passwd_hash");
                String passwdSalt = resultSet.getString("passwd_salt");
                String inputPasswdHash = PasswordHandler.hashPassword(passwd, passwdSalt);

                if (passwdHash.equals(inputPasswdHash)) {
                    return resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            logger.error("something went wrong during authentication process", e);
        }
        return -1;
    }

    public long regUser(String name, char[] passwd) {
        Connection connection = ConnectionUtility.getConnection();
        try {
            // Check if a user with the provided name already exists
            String selectUserQuery = "SELECT COUNT(*) FROM \"User\" WHERE name = ?";
            PreparedStatement selectUserStatement = connection.prepareStatement(selectUserQuery);
            selectUserStatement.setString(1, name);
            ResultSet resultSet = selectUserStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return -1; // A user with the provided name already exists
            }

            // Generate a unique salt for the new user
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[16];
            random.nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);

            // Hash the provided password with the generated salt
            String passwdHash = PasswordHandler.hashPassword(passwd, salt);

            // Insert the new user into the "User" table
            String insertUserQuery = "INSERT INTO \"User\" (name, passwd_hash, passwd_salt) VALUES (?, ?, ?)";
            PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, name);
            insertUserStatement.setString(2, passwdHash);
            insertUserStatement.setString(3, salt);

            int rowsAffected = insertUserStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertUserStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            logger.error("something went wrong during registration process", e);
        }
        return -1;
    }

    public List<Long> clearCitiesForUser() {
        Connection connection = ConnectionUtility.getConnection();
        long userId = ClientHandler.getUserId();
        List<Long> deletedCityIds = new ArrayList<>();
        try {
            // Get city IDs that belong to the current user
            String selectCityIdsQuery = "SELECT city_id FROM Creator WHERE user_id = ?";
            PreparedStatement selectCityIdsStatement = connection.prepareStatement(selectCityIdsQuery);
            selectCityIdsStatement.setLong(1, userId);
            ResultSet cityIdsResultSet = selectCityIdsStatement.executeQuery();
            while (cityIdsResultSet.next()) {
                deletedCityIds.add(cityIdsResultSet.getLong("city_id"));
            }

            // Delete cities that belong to the current user
            String deleteCitiesQuery = "DELETE FROM City WHERE id IN (SELECT city_id FROM Creator WHERE user_id = ?)";
            PreparedStatement deleteCitiesStatement = connection.prepareStatement(deleteCitiesQuery);
            deleteCitiesStatement.setLong(1, userId);
            deleteCitiesStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("something went wrong during clearing collections for user", e);
        }
        return deletedCityIds;
    }

    public boolean isCityOwnedByUser(long cityId) {
        Connection connection = ConnectionUtility.getConnection();
        try {
            String checkOwnershipQuery = "SELECT COUNT(*) FROM Creator WHERE city_id = ? AND user_id = ?";
            PreparedStatement checkOwnershipStatement = connection.prepareStatement(checkOwnershipQuery);
            checkOwnershipStatement.setLong(1, cityId);
            checkOwnershipStatement.setLong(2, ClientHandler.getUserId());
            ResultSet resultSet = checkOwnershipStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count <= 0;
            }
        } catch (SQLException e) {
            logger.error("something went wrong during checking object ownership", e);
        }
        return true;
    }

    public boolean updateCity(City obj) {
        Connection connection = ConnectionUtility.getConnection();
        try {
            connection.setAutoCommit(false);

            // Update the Human table
            String updateHumanQuery = "UPDATE Human SET name = ? FROM City WHERE City.governor_id = Human.id AND City.id = ?";
            PreparedStatement updateHumanStatement = connection.prepareStatement(updateHumanQuery);
            updateHumanStatement.setString(1, obj.getGovernor().getName());
            updateHumanStatement.setLong(2, obj.getId());
            updateHumanStatement.executeUpdate();

            // Update the Coordinates table
            String updateCoordinatesQuery = "UPDATE Coordinates SET x = ?, y = ? FROM City WHERE City.coordinates_id = Coordinates.id AND City.id = ?";
            PreparedStatement updateCoordinatesStatement = connection.prepareStatement(updateCoordinatesQuery);
            updateCoordinatesStatement.setInt(1, obj.getCoordinates().getX());
            updateCoordinatesStatement.setDouble(2, obj.getCoordinates().getY());
            updateCoordinatesStatement.setLong(3, obj.getId());
            updateCoordinatesStatement.executeUpdate();

            // Update the City table
            String updateCityQuery = "UPDATE City SET name = ?, creation_date = ?, area = ?, population = ?, meters_above_sea_level = ?, climate = CAST(? AS climate_enum), government = CAST(? AS government_enum), standard_of_living = CAST(? AS standard_of_living_enum) WHERE id = ?";
            PreparedStatement updateCityStatement = connection.prepareStatement(updateCityQuery);
            updateCityStatement.setString(1, obj.getName());
            updateCityStatement.setDate(2, new java.sql.Date(obj.getCreationDate().getTime()));
            updateCityStatement.setInt(3, obj.getArea());
            updateCityStatement.setInt(4, obj.getPopulation());
            if (obj.getMetersAboveSeaLevel() != null) {
                updateCityStatement.setDouble(5, obj.getMetersAboveSeaLevel());
            } else {
                updateCityStatement.setNull(5, Types.DOUBLE);
            }
            updateCityStatement.setString(6, obj.getClimate().toString());
            updateCityStatement.setString(7, obj.getGovernment().toString());
            updateCityStatement.setString(8, obj.getStandardOfLiving().toString());
            updateCityStatement.setLong(9, obj.getId());
            updateCityStatement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("something went wrong during updating city object", e);
        }
        return false;
    }

    @Override
    public Map<Long, String> getOwnerShipMap() {
        Map<Long, String> ownershipMap = new HashMap<>();
        Connection connection = ConnectionUtility.getConnection();

        try {
            String query = "SELECT c.id as city_id, u.name as client_name FROM City c " +
                    "JOIN Creator cr ON c.id = cr.city_id " +
                    "JOIN \"User\" u ON cr.user_id = u.id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long cityId = resultSet.getLong("city_id");
                String clientName = resultSet.getString("client_name");
                ownershipMap.put(cityId, clientName);
            }
        } catch (SQLException e) {
            logger.error("Error during getting ownership map", e);
        }

        return ownershipMap;
    }

}
