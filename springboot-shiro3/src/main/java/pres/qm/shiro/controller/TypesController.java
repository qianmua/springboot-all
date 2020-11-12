package pres.qm.shiro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pres.qm.shiro.entity.Types;
import pres.qm.shiro.service.TypesService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qianmuna
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/shiro/types")
public class TypesController {

    @Autowired private TypesService typesService;

    @GetMapping("/get") public List<Types> queryAll(){
        return typesService.list();
    }


}

