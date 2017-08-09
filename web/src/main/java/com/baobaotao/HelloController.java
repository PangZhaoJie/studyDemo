package com.baobaotao;

import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by pangzhaojie on 17-8-8.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/index")
    public String hello() {
        return "index";
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest request, String fileName) throws Exception {
        fileName = "applicationContext.pdf";
        File file = ResourceUtils.getFile("classpath:P020151204412209505592.pdf");
        HttpHeaders httpHeaders = new HttpHeaders();
        String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtil.readAsByteArray(file), httpHeaders, HttpStatus.CREATED);
    }
}
