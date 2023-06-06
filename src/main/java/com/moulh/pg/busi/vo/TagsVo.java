package com.moulh.pg.busi.vo;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.PutObjectResult;
import com.moulh.pg.core.util.DateUtils;
import com.moulh.pg.core.util.MD5Util;
import com.moulh.pg.core.util.OssUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @ClassName : TagsVo
 * @Author : moulh
 * @Date : 2021-07-12 14:24
 * @Version : V1.0
 * @Description : 标签展示参数
 */
@Getter
@Setter
@ToString
@ApiModel(value = "标签展示参数")
public class TagsVo {
    @ApiModelProperty(value = "逻辑主键")
    private Integer id;
    @ApiModelProperty(value = "标签内容，10个字以内")
    private String tagsContent;
    @ApiModelProperty(value = "标签累计数量")
    private Integer tagsCount;

    public static void main(String[] args) {
        //上传文件
        String flilePathName = "C:\\Users\\moulh\\Desktop\\MOU\\待定图片.png";
        File file = new File(flilePathName);
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String keydex = fileName + DateUtils.getCurrentDateString();
        OSSClient ossClient = OssUtils.getOSSClient("oss-cn-beijing.aliyuncs.com","LTAI5tErWMRhQyUSSHRfw1FB","jmw5X4Pq0zmjonRNyQOKIaHUjlTIWB");
        try {
            PutObjectResult result = ossClient.putObject("fs-app-storage", MD5Util.getMD5String(keydex.getBytes()) + fileExtension, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("上传后的文件MD5数字唯一签名:" + MD5Util.getMD5String(keydex.getBytes()) + fileExtension);
    }
}
