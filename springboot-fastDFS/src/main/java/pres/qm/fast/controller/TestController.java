package pres.qm.fast.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/28  17:37
 * @description :
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    @GetMapping
    public String upload() throws FileNotFoundException {
        File file = new File("G:\\Java_Test\\img\\img1\\a2be1fb33d8e4520982b2812491f451c.jpg");
        //上传并保存图片，参数：1-上传的文件流  2-文件的大小 3-文件的后缀 4-null；
        StorePath storePath = this.fastFileStorageClient.uploadFile(new FileInputStream(file),file.length(),"jpg",null);
        //带分组的路径
        System.out.println(storePath.getFullPath());
        //不带分组的路径
        System.out.println(storePath.getPath());

        return storePath.getFullPath();
    }
}
