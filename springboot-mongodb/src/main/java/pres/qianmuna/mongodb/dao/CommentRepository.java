package pres.qianmuna.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pres.qianmuna.mongodb.entity.CommentPO;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 18:01
 */
/**
 * 评论持久层
 * */
public interface CommentRepository extends MongoRepository<CommentPO , String> {
}
