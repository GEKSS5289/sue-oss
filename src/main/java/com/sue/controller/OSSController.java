package com.sue.controller;

import com.sue.pojo.Users;
import com.sue.pojo.vo.UsersVO;
import com.sue.resource.FileResource;
import com.sue.service.OSSService;
import com.sue.service.usercenterservice.UserCenterService;
import com.sue.utils.CookieUtils;
import com.sue.utils.IMOOCJSONResult;
import com.sue.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sue
 * @date 2020/8/14 9:50
 */

@RestController
@RequestMapping("fdfs")
public class OSSController extends BaseController {

    @Autowired
    private FileResource fileResource;

    @Autowired
    private UserCenterService userCenterService;

    @Autowired
    private OSSService ossService;

    @PostMapping("uploadFace")
    public IMOOCJSONResult uploadFace(
            String userId,
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        String path = ossService.upload(userId, file);

        if (StringUtils.isNotBlank(path)) {

            String finalUserFaceUrl = fileResource.getOssHost() + path;
            Users userResult = userCenterService.updateUserFace(userId, finalUserFaceUrl);
            UsersVO usersVO = conventUsersVO(userResult);
            CookieUtils.setCookie(request, response, "user",
                    JsonUtils.objectToJson(usersVO), true);

        } else {
            return IMOOCJSONResult.errorMsg("上传头像失败");
        }


        return IMOOCJSONResult.ok();
    }
}