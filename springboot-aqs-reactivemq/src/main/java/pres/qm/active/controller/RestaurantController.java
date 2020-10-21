package pres.qm.active.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.active.service.GoodsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/21  16:34
 * @description :
 */
@RestController
public class RestaurantController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/byhttp")
    public List<Object> get(){

        System.out.println("http -");
        return goodsService.get("A");
    }
}
