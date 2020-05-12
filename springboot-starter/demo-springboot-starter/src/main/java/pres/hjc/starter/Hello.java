package pres.hjc.starter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/12
 * @time 14:12
 */
public class Hello {

    /**
     * action
     *
     */
    TestStarter testStarter;

    public TestStarter getTestStarter() {
        return testStarter;
    }

    public void setTestStarter(TestStarter testStarter) {
        this.testStarter = testStarter;
    }

    public String say(String name ){
        return testStarter.getPrefix() + "name" + testStarter.getSuffix();
    }
}
