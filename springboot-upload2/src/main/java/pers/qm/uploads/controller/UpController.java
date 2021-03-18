package pers.qm.uploads.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.qm.uploads.po.MsgVo;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/3/18  15:06
 */
@RestController
@RequestMapping("/u")
public class UpController {

    @PostMapping( value = "/upload" , produces = "application/json;charset=utf-8")
    public MsgVo<String> uploadMut(@RequestBody MsgVo<MultipartFile> msg){
        System.err.println(msg);
        MultipartFile data = msg.getData();
        System.err.println(data);
        return new MsgVo<String>().success();
    }

}
