package com.moulh.pg.test;

import lombok.Data;

/**
 * @ClassName : Receipt
 * @Author : moulh
 * @Date : 2021-10-25 10:59
 * @Version : V1.0
 * @Description :
 */
@Data
public class Receipt {
    /**
     * 回执信息
     */
    String message;

    /**
     * 回执类型(`MT1101、MT2101、MT4101、MT8104、MT8105、MT9999`)
     */
    String type;

    public Receipt(String message, String type) {
        this.message = message;
        this.type = type;
    }
}
