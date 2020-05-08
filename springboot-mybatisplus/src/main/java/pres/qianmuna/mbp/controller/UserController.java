package pres.qianmuna.mbp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.mbp.entity.Users;
import pres.qianmuna.mbp.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 18:31
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * select all
     * @return
     */
    @GetMapping("/getall")
    public List<Users> queryAll(){
//        wrapper 条件过滤器
        List<Users> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        return users;
    }

    //开始事务 触发回滚异常 ， 隔离级别
    @Transactional( rollbackFor = { RuntimeException.class , Error.class} , isolation = Isolation.DEFAULT)
    @GetMapping("/insert")
    public void insert(){
        Users users = new Users().setAddrname("100").setAddress("200").setName("666");
        userMapper.insert(users);
    }

    @GetMapping("/update")
    public void undate(){
        //自动拼接动态sql
        Users users = new Users().setUtid(1006L).setAddrname("200").setName("hu");
        int i = userMapper.updateById(users);
    }

    @GetMapping("/select/{id}")
    public Users queryById(@PathVariable("id") Long id){
        return userMapper.selectById(id);
    }

    /**
     * 多个id查询
     * @param id
     * @return
     */
    @GetMapping("/select/search")
    public List<Users> querySearchById(@PathVariable("id") Long id){
        return userMapper.selectBatchIds(Arrays.asList(1,2,3));
    }

    /**
     * 条件查询
     */
    @GetMapping("/select/where")
    public void selectByWheres(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","hu");
        hashMap.put("id",1006L);

        List<Users> users = userMapper.selectByMap(hashMap);
        users.forEach(System.out::println);
    }

    /**
     * 配置了逻辑删哦
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userMapper.deleteById(id);
    }


    /**
     * 分页
     * @param pages
     * @param size
     */
    @GetMapping("/select/{pages}/{size}")
    public void sleectPage(@PathVariable("pages")Long pages , @PathVariable("size")Long size){
        Page<Users> page = new Page<>(pages, size);
        userMapper.selectPage(page,null);
    }



    //条件查询
    public void testWrapper(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age" , 10)
                .eq("name" , "hu")
                .between("age" , 20 , 30)
                ;

        userMapper.selectList(wrapper);
        userMapper.selectOne(wrapper);

        //模糊查询
        QueryWrapper<Users> wrapper1 = new QueryWrapper<>();
        wrapper1.notLike("name" , "e")
                .likeRight("email" ,"test")
                //sql
                .inSql("id" , "select id form users where id = 1001")
                //升序
                .orderByAsc("id");

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper1);
    }




}
