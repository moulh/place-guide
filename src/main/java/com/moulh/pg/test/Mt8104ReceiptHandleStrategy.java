package com.moulh.pg.test;

/**
 * @ClassName : Mt8104ReceiptHandleStrategy
 * @Author : moulh
 * @Date : 2021-10-25 11:05
 * @Version : V1.0
 * @Description :
 */
public class Mt8104ReceiptHandleStrategy implements IReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT8104:" + receipt.getMessage());
    }
}
