package pres.qm.avtiviti.config;

import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/26  11:48
 */
@Configuration
public class ActivitiConfig {



    public final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public ActivitiConfig(DataSource dataSource , PlatformTransactionManager platformTransactionManager){
        this.dataSource = dataSource;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(){

        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(platformTransactionManager);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        Resource[] resources = null;

        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:process/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration.setDeploymentResources(resources);

        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(){
        ProcessEngineFactoryBean ef = new ProcessEngineFactoryBean();
        ef.setProcessEngineConfiguration(springProcessEngineConfiguration());

        return ef;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngineFactoryBean().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngineFactoryBean().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngineFactoryBean().getObject().getTaskService();
    }


    @Bean
    public HistoryService historyService() throws Exception {
        return processEngineFactoryBean().getObject().getHistoryService();
    }


}
