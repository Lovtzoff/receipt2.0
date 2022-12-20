package ru.clevertec.controller.listener;

import ru.clevertec.util.database.DBConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet слушатель.
 *
 * @author Ловцов Алексей
 */
@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionPool.INSTANCE.destroyPool();
    }
}
