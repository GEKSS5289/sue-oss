package com.sue.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author sue
 * @date 2020/8/14 9:45
 */

public interface OSSService {

    public String upload(String userId, MultipartFile file) throws Exception;
}
