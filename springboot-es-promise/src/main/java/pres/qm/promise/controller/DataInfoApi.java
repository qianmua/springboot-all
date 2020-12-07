package pres.qm.promise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/12/7  10:36
 * @description :
 */
@RestController
@RequestMapping("/api/data")
public class DataInfoApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/list")
    public List<Map<String, Object>> list(){
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from user");
        return mapList;
    }

}
