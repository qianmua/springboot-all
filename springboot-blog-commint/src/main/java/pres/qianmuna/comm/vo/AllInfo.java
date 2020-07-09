package pres.qianmuna.comm.vo;

import lombok.Data;
import pres.qianmuna.comm.po.Commit1;
import pres.qianmuna.comm.po.Commit2;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  21:07
 * @description :
 */
@Data
public class AllInfo {

    private  Long cid;
    private  Long uid;
    private  String commValue;
    Commit1 commit1;
    private List<Commit2> commit2;
}
