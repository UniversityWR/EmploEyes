package com.br.emploeyes.listener;
 
import com.br.emploeyes.dao.JPAUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    JPAUtil.init();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    JPAUtil.destroy();
  }
}
