package com.moulh.pg.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName : ReceiptHandleStrategyFactory
 * @Author : moulh@paxsz.com
 * @Date : 2021-10-25 11:02
 * @Version : V1.0
 * @Description :
 */
public class ReceiptHandleStrategyFactory {
    private ReceiptHandleStrategyFactory(){}

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        IReceiptHandleStrategy receiptHandleStrategy = null;
        if (StringUtils.equals("MT2101",receiptType)) {
            receiptHandleStrategy = new Mt2101ReceiptHandleStrategy();
        } else if (StringUtils.equals("MT8104",receiptType)) {
            receiptHandleStrategy = new Mt8104ReceiptHandleStrategy();
        }
        return receiptHandleStrategy;
    }
}
