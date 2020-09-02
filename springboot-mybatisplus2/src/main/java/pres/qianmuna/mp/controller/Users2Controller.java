package pres.qianmuna.mp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.mp.api.R;
import pres.qianmuna.mp.entity.Users2;
import pres.qianmuna.mp.service.Users2Service;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qianmuna
 * @since 2020-09-02
 */
@RestController
@RequestMapping("/mp/users2")
public class Users2Controller {

    @Autowired
    private Users2Service users2Service;

    @GetMapping("/de")
    public R findAll(){
        List<Users2> list = users2Service.list();
        return R.ok()
                .data("list" , list);
    }

}

