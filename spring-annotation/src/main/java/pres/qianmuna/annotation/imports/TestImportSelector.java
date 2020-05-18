package pres.qianmuna.annotation.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:56
 */

/*
* 返回需要导入的组件
* */
public class TestImportSelector implements ImportSelector {


    /**
     * 导入到容器中的全类名 返回值
     * AnnotationMetadata 标注import 注解的所有类信息
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 不要返回null
        return new String[]{"pres.qianmuna.annotation.bean2.Green","pres.qianmuna.annotation.bean2.Blue"};
    }
}
