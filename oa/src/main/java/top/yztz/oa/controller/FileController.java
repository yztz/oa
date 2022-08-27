package top.yztz.oa.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.yztz.oa.utils.FileUtils;
import top.yztz.oa.utils.ResponseUtils;
import top.yztz.oa.utils.UUIDUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {


    @GetMapping(value = "/fetch/{path}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public BufferedImage getPicture(@PathVariable String path) {//获取图片
        File file;
        try {
            BufferedImage img;
            file = new File(FileUtils.pic.getAbsolutePath() + File.separator + path);
            if (StringUtils.isEmpty(path) || !file.exists()) {
                img = ImageIO.read(this.getClass().getResourceAsStream("/static/image/404.png"));
            } else {
                System.out.println("Fetch file " + path);
                img = ImageIO.read(file);
            }

            return img;
        } catch (Exception e) {
            System.out.println("err");
            return null;
        }
    }

    @PostMapping(value = "/upload")
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file) throws Exception {//文件上传

        String fileName = file.getOriginalFilename();
        String name = UUIDUtils.getUUID() + fileName.substring(fileName.lastIndexOf("."));

        File serverFile = new File(FileUtils.pic.getAbsolutePath() + "/" + name);
        file.transferTo(serverFile);

        return ResponseUtils.success("上传成功", name);
    }

}
