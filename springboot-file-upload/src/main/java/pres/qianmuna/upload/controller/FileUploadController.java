package pres.qianmuna.upload.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 13:26
 */
@RestController
public class FileUploadController {

    private static final String FILE_PATH = "";

    /**
     * File Upload
     * @param file 文件流
     * @param name 名字 么用
     * @param request  请求
     * @return info
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("img")MultipartFile file ,
                         @RequestParam("name")String name ,
                         HttpServletRequest request) {

        //得到文件名
        String filename = file.getOriginalFilename();

        //文件后缀
        if (filename != null) {
            String substring = filename.substring(filename.lastIndexOf("."));
            //生成文件名
            String str = UUID.randomUUID().toString().replace("-" , "") + substring;
            File file1 = new File( str);

            try {
                //文件拷贝 ， netty那里也有提到过
                //操作效率比Stream很高的
                file.transferTo(file1);
                //ending....
            } catch (IOException e) {
                e.printStackTrace();
            }


            return "success";
        }
        return "fail";

    }
}
