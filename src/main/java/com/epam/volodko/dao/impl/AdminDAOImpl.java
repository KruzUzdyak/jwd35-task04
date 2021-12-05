package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDAOImpl extends AbstractUserDAO<Admin> {

    private static final String FIND_ADMIN_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ROLE_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_ID);
    private static final String FIND_ALL_ADMINS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s ai ON u.%s = ai.%s WHERE r.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ROLE_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.ROLES_ROLE_ID);
    private static final String SAVE_NEW_ADMIN_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID);
    private static final String SAVE_NEW_ADMIN_INFO_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES ((SELECT %s FROM %s WHERE %s = ?), ?, ?);",
            Table.ADMIN_INFO, Column.ADMIN_INFO_USER_ID, Column.ADMIN_INDO_WORKS_SINCE, Column.ADMIN_INFO_NOTE,
            Column.USERS_ID, Table.USERS, Column.USERS_LOGIN);

    @Override
    Admin findById(int userId) throws DAOException {
        Admin admin;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ADMIN_BY_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                admin = BuilderFactory.getAdminBuilder().build(resultSet);
            } else {
                admin = new Admin();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find admin by id.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find admin by id.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return admin;
    }

    @Override
    List<Admin> findAll() throws DAOException {
        List<Admin> admins = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
            statement.setInt(1, Role.ADMIN.getRoleId());
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                admins.add(BuilderFactory.getAdminBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find all admins.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find all admins.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return admins;
    }

    @Override
    public void saveNewUser(Admin admin) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SAVE_NEW_ADMIN_QUERY);
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getName());
            statement.setString(4, admin.getPhone());
            statement.setInt(5, admin.getRole().getRoleId());
            statement.executeUpdate();
            statement = connection.prepareStatement(SAVE_NEW_ADMIN_INFO_QUERY);
            statement.setString(1, admin.getLogin());
            statement.setLong(2, admin.getWorksSince().getTime());
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when saving new admin.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when saving new admin.", e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    public static void main(String[] args) {

    }
}
