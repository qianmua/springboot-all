package pres.qianmuna.jpa3.vo;

import lombok.*;
import pres.qianmuna.jpa3.po.TableOne;
import pres.qianmuna.jpa3.po.TableTow;

import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  19:10
 * @description :
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ViewInfo implements Serializable {

    private static final long serialVersionUID = 8668686236037571415L;

    @Getter
    @Setter
    private TableOne tableOne;

    @Getter
    @Setter
    private TableTow tableTow;

}
