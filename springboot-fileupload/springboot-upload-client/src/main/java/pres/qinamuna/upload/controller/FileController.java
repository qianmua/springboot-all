package pres.qinamuna.upload.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 23:17
 */
@RestController
public class FileController {


    @PostMapping("/upload")
    public String file(MultipartFile upload) throws IOException {

        System.out.println("上传文件");

        String path = "http://localhost:9090/";


        String filename = upload.getOriginalFilename();

        String replace = UUID.randomUUID().toString().replace("-", "");

        filename = replace + "_" + filename;

        Client client = Client.create();

        WebResource resource = client.resource(path + filename);

        resource.put(upload.getBytes());

        return "SUCCESS";

    }
}
