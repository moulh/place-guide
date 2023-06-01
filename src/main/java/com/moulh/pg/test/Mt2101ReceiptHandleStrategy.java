package com.moulh.pg.test;

/**
 * @ClassName : Mt2101ReceiptHandleStrategy
 * @Author : moulh@paxsz.com
 * @Date : 2021-10-25 11:04
 * @Version : V1.0
 * @Description :
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT2101:" + receipt.getMessage());
    }
}
