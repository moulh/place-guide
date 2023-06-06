package com.moulh.pg.busi.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moulh.pg.busi.entity.Images;
import com.moulh.pg.busi.entity.SaveImageResult;
import com.moulh.pg.busi.entity.User;
import com.moulh.pg.busi.input.UserUpdatePwdInput;
import com.moulh.pg.busi.mapper.ImagesMapper;
import com.moulh.pg.busi.mapper.UserMapper;
import com.moulh.pg.busi.service.CommonService;
import com.moulh.pg.core.constant.BasicConstant;
import com.moulh.pg.core.dto.UserTokenDTO;
import com.moulh.pg.core.exception.BusinessException;
import com.moulh.pg.core.service.RedisService;
import com.moulh.pg.core.util.DateUtils;
import com.moulh.pg.core.util.JWTUtil;
import com.moulh.pg.core.util.MD5Util;
import com.moulh.pg.core.util.OssUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName : CommonServiceImpl
 * @Author : moulh
 * @Date : 2021-07-15 17:35
 * @Version : V1.0
 * @Description : 公共接口类
 */
@Service
@Slf4j
@SuppressWarnings("ALL")
public class CommonServiceImpl implements CommonService {
    @Value("${oss.access-id}")
    private String accessId;
    @Value("${oss.access-key}")
    private String accessKey;
    @Value("${oss.bucket-name}")
    private String bucketName;
    @Value("${oss.out-endpoint}")
    private String outEndpoint;
    @Value("${oss.download-url}")
    private String downloadUrl;
    @Autowired
    private ImagesMapper imagesMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public SaveImageResult saveImages(HttpServletRequest request) {
        List<MultipartFile> multipartFiles = OssUtils.getFiles(request);
        assert multipartFiles != null;
        OSSClient ossClient = OssUtils.getOSSClient(outEndpoint,accessId,accessKey);
        for(MultipartFile multipartFile : multipartFiles) {
            //1.新增图片信息
            try {
                InputStream in = multipartFile.getInputStream();
                //获取文件名称
                String fileName = Optional.ofNullable(multipartFile.getOriginalFilename()).
                        orElseThrow(() -> new BusinessException("文件名称异常, 请重试"));
                //获取文件后缀名
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                //md5FileName为oss存储时的文件名称
                String md5FileName = MD5Util.getMD5String((multipartFile.getOriginalFilename() + DateUtils.getCurrentDateString()).getBytes()) + fileExtension;
                //上传文件
                PutObjectResult result = ossClient.putObject(bucketName, md5FileName, in);
                Images images = new Images();
                images.setImageName(fileName);
                images.setUrl(downloadUrl + md5FileName);
                imagesMapper.insert(images);
                SaveImageResult saveImageResult = new SaveImageResult();
                saveImageResult.setImageId(images.getId());
                saveImageResult.setImageUrl(images.getUrl());
                return saveImageResult;
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("上传失败");
            } finally {
                ossClient.shutdown();
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateUserPsw(UserUpdatePwdInput userUpdatePwdInput) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("user_name", userUpdatePwdInput.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            throw new BusinessException("用户名称错误");
        }
        if(!StringUtils.equals(user.getPwd(), userUpdatePwdInput.getOldPassword())){
            throw new BusinessException("用户密码错误");
        }
        user.setPwd(userUpdatePwdInput.getNewPassword());
        userMapper.updateById(user);

        //2.生成新的token
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setId(user.getId());
        userTokenDTO.setUserName(user.getUserName());
        userTokenDTO.setGmtCreate(System.currentTimeMillis());
        String token = JWTUtil.generateToken(userTokenDTO);
        //3.更新token
        redisService.set(BasicConstant.TOKEN_KEY + user.getId(), token);
        return token;
    }
}
