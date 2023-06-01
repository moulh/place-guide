package com.moulh.pg.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : ReceiptBuilder
 * @Author : moulh@paxsz.com
 * @Date : 2021-10-25 11:08
 * @Version : V1.0
 * @Description :
 */
public class ReceiptBuilder {
    public static List<Receipt> generateReceiptList(){
        //直接模拟一堆回执对象
        List<Receipt> receiptList = new ArrayList<>();
        receiptList.add(new Receipt("我是MT2101回执喔","MT2101"));
        receiptList.add(new Receipt("我是MT1101回执喔","MT1101"));
        receiptList.add(new Receipt("我是MT8104回执喔","MT8104"));
        receiptList.add(new Receipt("我是MT9999回执喔","MT9999"));
        //......
        return receiptList;
    }
}
