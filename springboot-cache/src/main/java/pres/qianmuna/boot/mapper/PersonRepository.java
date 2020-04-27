package pres.qianmuna.boot.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.qianmuna.boot.pojo.Person;

import java.util.List;

/**
 * Created by yangyibo on 17/1/13.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
