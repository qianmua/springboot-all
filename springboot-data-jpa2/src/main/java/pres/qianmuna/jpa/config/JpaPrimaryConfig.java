package pres.qianmuna.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 13:00
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "trans",
        basePackages = { "pres.qianmuna.jpa.entity"})
public class JpaPrimaryConfig {

    @Resource
    @Qualifier("primary")
    private DataSource dataSource;

    @Primary
    @Bean( name = "entityManagePrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactoryBean(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean( name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource)
                .properties(getVend())
                .packages("pres.qianmuna.jpa.entity")
                .persistenceUnit("primaryUntil")
                .build();
    }

    @Resource
    private JpaProperties jpaProperties;

    private Map getVend(){
        return jpaProperties.getProperties(/*new HibernateSettings()*/);
    }

    @Primary
    @Bean( name = "trans")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean(builder).getObject()));
    }

}
