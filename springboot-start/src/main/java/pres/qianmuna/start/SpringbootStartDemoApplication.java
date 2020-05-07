package pres.qianmuna.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 17:11
 */
@SpringBootApplication
public class SpringbootStartDemoApplication {

    public static void main(String[] args) {

        /**
         * run(new Class<?>[] { primarySource }, args);
         * //先创建构造器 在run
         * new SpringApplication(primarySources).run(args);
         *
         * public SpringApplication(Class<?>... primarySources) {
         * 		this(null, primarySources);
         * }
         * //加载bean
         * public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
         * 		this.resourceLoader = resourceLoader;
         * 		Assert.notNull(primarySources, "PrimarySources must not be null");
         * 		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
         * 		this.webApplicationType = WebApplicationType.deduceFromClasspath();
         * 	    //去找spring.factories 下面的配置
         * 		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
         * 	    //去找listener
         * 		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
         * 	    //从配置类中寻找有main方法的主配置类
         * 		this.mainApplicationClass = deduceMainApplicationClass();
         * }
         *
         * //run
         * public ConfigurableApplicationContext run(String... args) {
         * 		StopWatch stopWatch = new StopWatch();
         * 		stopWatch.start();
         * 		ConfigurableApplicationContext context = null;
         * 		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
         * 		configureHeadlessProperty();
         *
         *
         * 	    //获得springRunListener
         * 	    //从类路径下spring.factories
         * 		SpringApplicationRunListeners listeners = getRunListeners(args);
         *
         *
         *
         * 	    //获得listener
         * 		listeners.starting();
         *
         * 		try {
         *
         * 	        //封装命令行参数
         * 			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
         *
         * 	        //准备IOC环境
         * 			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
         * 	        //完成后回调，表示准备完成
         *
         *
         * 			configureIgnoreBeanInfo(environment);
         * 			Banner printedBanner = printBanner(environment);
         *
         * 	        //决定创建IOC容器 普通？ web？
         * 			context = createApplicationContext();
         * 			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
         * 					new Class[] { ConfigurableApplicationContext.class }, context);
         *
         * 			//创建上下文 回调初始化
         * 		    运行完成后回调contextLoad
         * 			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
         *
         *
         *          //刷新容器  【初始化创建IOC容器完成 加锁啦~
         *          //扫描，创建，加载（配置类，组件，自动配置。。。。）
         * 			refreshContext(context);
         *
         *
         * 	        //从IOC容器中得到所有的ApplicationRunner&CommendLineRunner进行回调
         * 			afterRefresh(context, applicationArguments);
         * 			stopWatch.stop();
         * 			if (this.logStartupInfo) {
         * 				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
         *                        }
         * 			listeners.started(context);
         * 			callRunners(context, applicationArguments);* 		}
         * 		catch (Throwable ex) {
         * 			handleRunFailure(context, ex, exceptionReporters, listeners);
         * 			throw new IllegalStateException(ex);
         * 		}
         *
         * 		try {
         * 			listeners.running(context);
         * 		}
         * 		catch (Throwable ex) {
         * 			handleRunFailure(context, ex, exceptionReporters, null);
         * 			throw new IllegalStateException(ex);
         * 		}
         *
         * 	    //返回启动的IOC容器
         * 		return context;
         * 	}
         *
         *
         * */
        SpringApplication.run(SpringbootStartDemoApplication.class,args);
    }
}
