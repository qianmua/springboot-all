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

    /**
     * @SpringBootApplication
     *
     * @SpringBootConfiguration = @Configuration
     *
     * @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
     *                @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
     *                 主次容器扫描
     *
     *
     * @EnableAutoConfiguration 实现自动配置核心！
     * @AutoConfigurationPackage 配置自动扫描包 ， 扫描当前类 下面 所有包
     * @Import(AutoConfigurationImportSelector.class)
     * AutoConfigurationImportSelector.class 真正实现自动配置 和 ImportSelector （spring的扩展类） 继承关系
     *
     * selectImports() 方法 返回一个String[] 内容时 返回类名形式的String字符串 然后通过反射加载到spring ioc
     *
     * 自动配置 就是 把所有javaConfig 提前帮你写好 然后 加载到spring ioc
     * javaConfig 在autoConfigure这个模块里面
     *
     * 他回去扫描 mata-inf spring.factories
     *
     * selectImports() 会去 扫描 这个 spring.factories文件
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        /**
         * run(new Class<?>[] { primarySource }, args);
         * //先创建构造器 在run
         * new SpringApplication(primarySources).run(args);
         *
         * publics SpringApplication(Class<?>... primarySources) {
         * 		this(null, primarySources);
         * }
         * //加载bean
         * publics SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
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
         * publics ConfigurableApplicationContext run(String... args) {
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
