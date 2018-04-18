package cmpe273.fandango.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfig {
  @Bean
  public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
    HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
    factory.setEntityManagerFactory(emf);
    return factory;
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);
    return txManager;
  }
}
