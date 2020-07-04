package pres.qianmuna.jpa3.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  18:33
 * @description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "table_demo_jpa_one")
public class TableOne implements Serializable {

    private static final long serialVersionUID = 2737182236918393214L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long oid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Long tid;

}
