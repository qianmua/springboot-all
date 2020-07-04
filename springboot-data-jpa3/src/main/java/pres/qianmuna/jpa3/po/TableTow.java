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
@Table(name = "table_demo_jpa_two")
public class TableTow implements Serializable {

    private static final long serialVersionUID = -2643664335416520017L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long tid;

    @Getter
    @Setter
    private String name;
}
