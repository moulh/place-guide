package com.moulh.pg.test;

/**
 * @ClassName : ReceiptStrategyContext
 * @Author : moulh
 * @Date : 2021-10-25 11:02
 * @Version : V1.0
 * @Description :
 */
public class ReceiptStrategyContext {
    private IReceiptHandleStrategy receiptHandleStrategy;

    /**
     * 设置策略接口
     * @param receiptHandleStrategy
     */
    public void setReceiptHandleStrategy(IReceiptHandleStrategy receiptHandleStrategy) {
        this.receiptHandleStrategy = receiptHandleStrategy;
    }

    public void handleReceipt(Receipt receipt){
        if (receiptHandleStrategy != null) {
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }
}
