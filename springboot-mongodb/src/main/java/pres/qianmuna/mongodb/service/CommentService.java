package pres.qianmuna.mongodb.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 18:02
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.qianmuna.mongodb.dao.CommentRepository;
import pres.qianmuna.mongodb.entity.CommentPO;

import java.util.List;

/**
 * 评论业务层
 * */
@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    /**
     * 添加
     * @param commentPO
     */
    public void saveComment(CommentPO commentPO){
        repository.save(commentPO);
    }

    /**
     * 更新
     * @param commentPO
     */
    public void updateComment(CommentPO commentPO){
        repository.save(commentPO);
    }


    /**
     * 根据id删除
     * @param id
     */
    public void deleteCommentById(String id){
        repository.deleteById(id);
    }

    /**
     * 查询全部
     * @return
     */
    public List<CommentPO> queryAll(){
        return repository.findAll();
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public CommentPO findCommentbyId(String id){
        return repository.findById(id).get();
    }



}
