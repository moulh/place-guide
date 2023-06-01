package com.moulh.pg.busi.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName : ObjectImagesInput
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-13 17:12
 * @Version : V1.0
 * @Description : 景点/住宿/餐饮图片信息
 */
@Getter
@Setter
@ToString
@ApiModel(value = "景点/住宿/餐饮明细图片信息")
public class ObjectImagesInput {
    @ApiModelProperty(value = "图片上传时返回的id")
    private Integer imagesId;

    @ApiModelProperty(value = "图片标题 10字以内")
    private String imagesTitle;

    @ApiModelProperty(value = "图片描述 200字以内")
    private String imagesDesc;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ObjectImagesInput)){
            return false;
        }
        ObjectImagesInput objectImagesInput = (ObjectImagesInput)o;
        if(this == objectImagesInput){
            return true;
        }
        // 如果图片id/标题/描述相等，我们认为两个对象相等
        return objectImagesInput.imagesId.equals(this.imagesId)
                && ((StringUtils.isBlank(objectImagesInput.imagesTitle) && StringUtils.isBlank(this.imagesTitle)) || (((StringUtils.isNotBlank(objectImagesInput.imagesTitle) && StringUtils.isNotBlank(this.imagesTitle))) && (objectImagesInput.imagesTitle.equals(this.imagesTitle))))
                && ((StringUtils.isBlank(objectImagesInput.imagesDesc) && StringUtils.isBlank(this.imagesDesc)) || (((StringUtils.isNotBlank(objectImagesInput.imagesDesc) && StringUtils.isNotBlank(this.imagesDesc))) && (objectImagesInput.imagesDesc.equals(this.imagesDesc))));
    }

    @Override
    public int hashCode() {
        int result = getImagesId();
        result = 31 * result + (getImagesTitle() != null ? getImagesTitle().hashCode() : 0);
        result = 31 * result + (getImagesDesc() != null ? getImagesDesc().hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        ObjectImagesInput objectImagesInput1= new ObjectImagesInput();
        objectImagesInput1.setImagesId(123);
        //objectImagesInput1.setImagesTitle("123");
        //objectImagesInput1.setImagesDesc("");
        ObjectImagesInput objectImagesInput2 = new ObjectImagesInput();
        objectImagesInput2.setImagesId(123);
        objectImagesInput2.setImagesTitle("123");
        objectImagesInput2.setImagesDesc("1");
        System.out.println(objectImagesInput1.equals(objectImagesInput2));
    }
}
