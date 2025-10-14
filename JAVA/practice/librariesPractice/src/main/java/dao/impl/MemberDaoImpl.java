package dao.impl;

import config.DbConfig;
import dao.interfaces.MemberDao;
import domain.Member;
import exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDaoImpl implements MemberDao {

    @Override
    public Member create(Member member) throws DataAccessException {
        String sql = "INSERT INTO member (full_name, email, phone, active) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone());
            preparedStatement.setBoolean(4, member.isActive());

            if (preparedStatement.executeUpdate() == 0) {
                throw new DataAccessException("Member creation failed");
            }

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    member.setIdMember(keys.getInt(1));
                }
            }
            return member;
        } catch (SQLException e) {
            throw new DataAccessException("Error creating member", e);
        }
    }

    @Override
    public Optional<Member> findById(Integer id) throws DataAccessException {
        String sql = "SELECT * FROM member WHERE id_member = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet objRest = preparedStatement.executeQuery()) {
                if (objRest.next()) {
                    return Optional.of(mapResultSetToMember(objRest));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding member by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() throws DataAccessException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member ORDER BY full_name";
        try (Connection connection = DbConfig.getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet objRest = preparedStatement.executeQuery()) {

            while (objRest.next()) {
                members.add(mapResultSetToMember(objRest));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding all members", e);
        }
        return members;
    }

    @Override
    public void update(Member member) throws DataAccessException {
        String sql = "UPDATE member SET full_name = ?, email = ?, phone = ?, active = ? WHERE id_member = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, member.getFullName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone());
            preparedStatement.setBoolean(4, member.isActive());
            preparedStatement.setInt(5, member.getIdMember());

            if (preparedStatement.executeUpdate() == 0) {
                throw new DataAccessException("Member update failed");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error updating member", e);
        }
    }

    @Override
    public void delete(Integer id) throws DataAccessException {
        String sql = "DELETE FROM member WHERE id_member = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DataAccessException("Member deletion failed");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting member", e);
        }
    }

    // Helper method to avoid code repetition
    private Member mapResultSetToMember(ResultSet objRest) throws SQLException {
        Member member = new Member();
        member.setIdMember(objRest.getInt("id_member"));
        member.setFullName(objRest.getString("full_name"));
        member.setEmail(objRest.getString("email"));
        member.setPhone(objRest.getString("phone"));
        member.setActive(objRest.getBoolean("active"));
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) throws DataAccessException {
        String sql = "SELECT * FROM member WHERE email = ?";
        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            try (ResultSet objRest = preparedStatement.executeQuery()) {
                if (objRest.next()) {
                    // Reutilizamos el helper que ya tienes, ¡perfecto!
                    return Optional.of(mapResultSetToMember(objRest));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding member by email", e);
        }
        return Optional.empty();
    }
}