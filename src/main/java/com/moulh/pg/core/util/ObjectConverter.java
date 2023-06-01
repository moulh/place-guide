package com.moulh.pg.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

/**
 * @ClassName : ObjectConverter
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 18:15
 * @Version : V1.0
 * @Description :
 */
public class ObjectConverter<T, S> {
    private ObjectConverter() {
    }

    private final static Objenesis OBJENESIS = new ObjenesisStd(true);

    /**
     * 对象属性拷贝
     *
     * @param sourceBean
     * @param targetClass
     * @return
     */
    public static <T, S> S convert(T sourceBean, Class<S> targetClass) {
        S targetBean = OBJENESIS.newInstance(targetClass);
        BeanUtils.copyProperties(sourceBean, targetBean);
        return targetBean;
    }
}
