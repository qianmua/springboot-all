package pres.qm.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/26  10:56
 */
public class ActivitiConfigTest {

    public void testCreateDefaultDbTable(){
        // 使用 activiti 默认方式创建表
        ProcessEngine processEngines = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngines);


        // 服务API
        // 工作流引擎创建
        RepositoryService repositoryService = processEngines.getRepositoryService();
    }

    public void testCreateCustomDbTable(){
        // 自定义
        // 自定义配置文件名
        // 自定义配置BeanName
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("xml" , "beanName");



    }

}
