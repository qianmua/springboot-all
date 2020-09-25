package pres.qm.ssm4.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/25  14:50
 * @description :
 */
@Configuration
@PropertySource("classpath:properties/jdbc.properties")
public class DatabaseConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${name}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 全局配置
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 默认为自增
        dbConfig.setIdType(IdType.AUTO);
        // 全局的表前缀策略配置
        dbConfig.setTablePrefix("t_");
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

    /**
     * mbp
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DruidDataSource dataSource, GlobalConfig globalConfig) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //加载数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //加载 mybatis-plus 全局属性
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        //指定 pojo 目录
        sqlSessionFactoryBean.setTypeAliasesPackage("pres.qm.ssm4.entity");
        return sqlSessionFactoryBean;
    }

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("pres.qm.ssm4.mapper");
        // 设置为上面的 factory name
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

    /**
     * 事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
