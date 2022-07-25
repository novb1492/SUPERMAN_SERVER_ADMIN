package com.kimcompany.jangbogbackendver2;

import com.kimcompany.jangbogbackendver2.Aws.S3Service;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BasicController {

    private final S3Service s3Service;

    @RequestMapping(value = "/auth/file/upload",method = RequestMethod.POST)
    public void uploadImg(MultipartHttpServletRequest request){
        List<MultipartFile> multipartFiles=request.getFiles("upload");
        List<File> files = new ArrayList<>();
        for(MultipartFile m:multipartFiles){
            files.add(UtilService.convert(m));
        }
        for(File f:files){
            s3Service.uploadImage(f, "supermans3/images");
            f.delete();
        }
    }
}
