package pres.qm.ssm4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/18  16:41
 * @description :
 */
@Configuration
@ComponentScan( basePackages = {"pres.qm.ssm4"})
@EnableTransactionManagement
@Import(DatabaseConfig.class)
public class RootConfig {
}
