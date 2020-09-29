package pres.qm.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import pres.qm.excel.read.DemoDate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/29  13:58
 * @description :
 */
public class DemoDataListener extends AnalysisEventListener<DemoDate> {

    /**
     * max 3000
     */
    private static final int BATCH_COUNT = 100;
    List<DemoDate> list = new ArrayList<>();


    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
//    private DemoDAO demoDAO;
    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//        demoDAO = new DemoDAO();
    }
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
//    public DemoDataListener(DemoDAO demoDAO) {
//        this.demoDAO = demoDAO;
//    }


    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(DemoDate data, AnalysisContext context) {
        System.out.println(JSON.toJSONString(data));
        list.add(data);
        if (list.size() > BATCH_COUNT) {
//            save();
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        save();
        System.out.println("over!");

    }
}
