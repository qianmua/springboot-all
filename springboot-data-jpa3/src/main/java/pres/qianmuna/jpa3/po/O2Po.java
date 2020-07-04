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
@Table(name = "table_demo_jpa_o2po")
public class O2Po implements Serializable {

    private static final long serialVersionUID = -4551989418828735301L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long o2id;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "o1id")
    private Long o1id;
}
