package dao.impl;

import config.DbConfig;
import dao.interfaces.StatusDao;
import domain.Status;
import exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDaoImpl implements StatusDao {
    @Override
    public Optional<Status> findById(int id) throws DataAccessException {
        String sql = "SELECT * FROM loan_status WHERE id_status = ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet objRest = preparedStatement.executeQuery();

            if (objRest.next()) {
                Status status = new Status();
                status.setIdStatus(objRest.getInt("id_status"));
                status.setName(objRest.getString("name"));
                return Optional.of(status);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error searching status by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Status> findAll() throws DataAccessException {
        List<Status> statusLoan = new ArrayList<>();
        String sql = "SELECT * FROM loan_status ORDER BY name";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet objRest = preparedStatement.executeQuery()) {

            while (objRest.next()) {
                Status status = new Status();
                status.setIdStatus(objRest.getInt("id_status"));
                status.setName(objRest.getString("name"));
                statusLoan.add(status);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding all status", e);
        }
        return statusLoan;
    }
}