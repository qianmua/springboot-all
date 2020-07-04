package pres.qianmuna.jpa3.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@Builder
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

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH} , optional = false)
    @JoinColumn(name = "o1id", referencedColumnName = "o1id")
    @Getter
    @Setter
    @JsonIgnore
    private O1Po o1id;

    @Override
    public String toString() {
        return "O2Po{" +
                "o2id=" + o2id +
                ", name='" + name + '\'' +
                '}';
    }
}
