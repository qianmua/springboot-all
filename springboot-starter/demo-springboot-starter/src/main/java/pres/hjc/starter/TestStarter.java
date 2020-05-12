package pres.hjc.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/12
 * @time 14:12
 */
@ConfigurationProperties( prefix = "demo.hello")
public class TestStarter {

    /**
     * 可以使用接口配置这个
     */
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
