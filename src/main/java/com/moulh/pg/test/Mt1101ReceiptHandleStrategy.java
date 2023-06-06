package com.moulh.pg.test;

/**
 * @ClassName : Mt1101ReceiptHandleStrategy
 * @Author : moulh
 * @Date : 2021-10-25 11:04
 * @Version : V1.0
 * @Description :
 */
public class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101:" + receipt.getMessage());
    }
}
