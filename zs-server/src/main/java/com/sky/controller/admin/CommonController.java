package com.sky.controller.admin;

import com.sky.properties.PathYml;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author cyan
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags="通用接口")
public class CommonController {

    @Autowired
    private PathYml pY;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file) throws IOException {
        log.info("文件上传 {}",file);

        String path = pY.getPath(); ;

        String s =file.getOriginalFilename();
        int i =s.lastIndexOf(".");
        file.transferTo(new File(path+s));

        return Result.success(path+s);
    }

}
