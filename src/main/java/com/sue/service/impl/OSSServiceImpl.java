package com.sue.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sue.resource.FileResource;
import com.sue.service.OSSService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author sue
 * @date 2020/8/14 9:46
 */

@Service
public class OSSServiceImpl implements OSSService {

    @Autowired
    private FileResource fileResource;

    @Override
    public String upload(String userId, MultipartFile file) throws Exception {

        OSS ossClient = new OSSClientBuilder()
                .build(
                        fileResource.getEndpoint(),
                        fileResource.getAccessKeyId(),
                        fileResource.getAccessKeySecret()
                );

        InputStream inputStream = file.getInputStream();

        String myObjectName = fileResource.getObjectName() + "/" + userId + "/" + userId + "." + getSuffix(file.getOriginalFilename());

        ossClient.putObject(fileResource.getBucketName(), myObjectName, inputStream);
        ossClient.shutdown();

        return myObjectName;
    }


    private String getSuffix(String fileName){
        if(StringUtils.isNotBlank(fileName)){
            String fileNameArr[] = fileName.split("\\.");
            // 获取文件的后缀名
            String suffix = fileNameArr[fileNameArr.length - 1];
            return suffix;
        }else{
            throw new RuntimeException("无效文件名");
        }
    }
}
