package pres.qianmuna.jpa3.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  21:55
 * @description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "table_demo_jpa_o1po")
public class O1Po implements Serializable {


    private static final long serialVersionUID = 259780995455379036L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long o1id;

    @Getter
    @Setter
    private String name;
}
