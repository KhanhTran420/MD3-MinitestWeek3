package dao;

import model.Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements IClassesDAO {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/QuanLyLopHoc";
    public static final String JDBC_ACCOUNT = "root";
    public static final String JDBC_PASSWORD = "khanh123";

    public static final String INSERT_INTO_CLASSES_SQL = "INSERT INTO classes (id,name,description) values (?,?,?);";
    public static final String SELECT_FROM_CLASSES_BY_ID = "select class_Name,class_Description from classes where id=?;";
    private static final String DELETE_CLASS_SQL = "delete from classes where id = ?;";
    public static final String SELECT_ALL_FROM_CLASSES = "select * from classes";
    private static final String UPDATE_CLASS_SQL = "update classes set name = ?,description= ? where id = ?;";

    public ClassesDAO() {
    }
    protected Connection getconnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL,JDBC_ACCOUNT,JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertClasses(Classes classes) throws SQLException {
        System.out.println(INSERT_INTO_CLASSES_SQL);
        try(Connection connection = getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CLASSES_SQL)){
            preparedStatement.setInt(1,classes.getClassesID());
            preparedStatement.setString(2,classes.getClassName());
            preparedStatement.setString(3,classes.getClassDescription());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    public Classes selectClasses(int id) {
        Classes classes = null;
        try(Connection connection = getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CLASSES_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()){
                String className = rs.getString("class_Name");
                String classDescription = rs.getString("class_Description");
                classes = new Classes(id,className,classDescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Classes> selectAllClasses() {
        List<Classes> classes = new ArrayList<>();
        try(Connection connection = getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CLASSES)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String className = rs.getString("class_Name");
                String classDescription = rs.getString("class_Description");
                classes.add(new Classes(id,className,classDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public boolean deleteClasses(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getconnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CLASS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateClasses(Classes classes) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getconnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CLASS_SQL);) {
            statement.setString(1, classes.getClassName());
            statement.setString(2, classes.getClassDescription());
            statement.setInt(3, classes.getClassesID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
