package dao.impl;

import config.DbConfig;
import dao.interfaces.RoleDao;
import domain.Role;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao{
    @Override
    public Optional<Role> findById(int id) throws DataAccessException {
        String sql = "SELECT * FROM Role WHERE idRole = ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet objRest = preparedStatement.executeQuery();

            if (objRest.next()) {
                Role role = new Role();
                role.setIdRole(objRest.getInt("idRole"));
                role.setTypeRol(objRest.getString("typeRol"));
                return Optional.of(role);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error searching role by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Role> findAll() throws DataAccessException {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM Role ORDER BY typeRol";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet objRest = preparedStatement.executeQuery()) {

            while (objRest.next()) {
                Role role = new Role();
                role.setIdRole(objRest.getInt("idRole"));
                role.setTypeRol(objRest.getString("typeRol"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding all roles", e);
        }
        return roles;
    }
}
