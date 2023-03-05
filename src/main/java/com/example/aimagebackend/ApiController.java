package com.example.aimagebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ImageRepository imageRepository;
    public class MyRunnable implements Runnable {
        private String filename;

        public MyRunnable(String filename) {
            this.filename = filename;
        }

        public void run() {
            // 从数据库根据filename取图片
            Image image = imageRepository.findByFileNameEquals(this.filename);
            // 发送给算法端图片并等待接收处理后的图片

            // 发送到邮箱
        }
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file, @RequestParam("email") String email) throws IOException {
        try {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();
            Image image = new Image(filename,"1", email, data);
            imageRepository.save(image);
            MyRunnable myRunnable = new MyRunnable(filename);
            Thread thread = new Thread(myRunnable);
            thread.start();
            return ResponseEntity.ok("上传成功");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("上传失败：" + e.getMessage());
        }
    }
}
