package pres.qianmuna.jpa3.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  23:03
 * @description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "table_demo_jpa_m1po")
public class M1Po implements Serializable {
    private static final long serialVersionUID = 4215542847537724412L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long m1id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column( name = "m2id")
    private Long m2id;
}
