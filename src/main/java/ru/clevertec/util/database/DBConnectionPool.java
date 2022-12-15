package ru.clevertec.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 * Пул соединений с базой данных.
 */
public enum DBConnectionPool {

    /**
     * Пул соединений экземпляра базы данных.
     */
    INSTANCE;

    private final BlockingQueue<Connection> freeConnection;
    private final BlockingQueue<Connection> activeConnection;

    private final int DEFAULT_POOL_SIZE = Integer.parseInt(DBPropertiesManager.getPoolSize());
    private final String DATABASE_DRIVER = DBPropertiesManager.getDriver();

    DBConnectionPool() {
        activeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        registerDriver();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            freeConnection.offer(DBConnectionCreator.createConnection());
        }
    }

    /**
     * Получить соединение.
     *
     * @return the connection
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = freeConnection.take();
            activeConnection.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Освободить соединение.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection){
        if (activeConnection.remove(connection)) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            freeConnection.offer(connection);
        }
    }

    /**
     * Уничтожить пул.
     */
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        deregisterDriver();
    }

    private void registerDriver() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deregisterDriver() {
        forEachRemaining(DriverManager.getDrivers(), driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static <T> void forEachRemaining(Enumeration<T> e, Consumer<? super T> c) {
        while (e.hasMoreElements()) {
            c.accept(e.nextElement());
        }
    }
}
