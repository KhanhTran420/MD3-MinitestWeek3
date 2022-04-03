package dao;

import model.Classes;

import java.sql.SQLException;
import java.util.List;

public interface IClassesDAO {
    void insertClasses(Classes classes) throws SQLException, SQLException;

    Classes selectClasses(int id);

    List<Classes> selectAllClasses();

    boolean deleteClasses(int id) throws SQLException;
//
    boolean updateClasses(Classes classes) throws SQLException;
}
