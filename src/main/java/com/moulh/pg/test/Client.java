package com.moulh.pg.test;

import java.util.List;

/**
 * @ClassName : Client
 * @Author : moulh
 * @Date : 2021-10-25 11:06
 * @Version : V1.0
 * @Description :
 */
public class Client {
    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        //策略上下文
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        for (Receipt receipt : receiptList) {
            //获取并设置策略
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);
            //执行策略
            receiptStrategyContext.handleReceipt(receipt);
        }
    }
}
