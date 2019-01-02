package com.chatRobot.utils;

/**
 * Created by shark on 2018-5-8.
 */


public class BigNumber {
    final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    final String stringValue;
    final int[] value;//存储值

    public static void main(String[] args) {
        int[] a = {10, 10, 10, 10, 10};
//
        BigNumber o = new BigNumber("84548484847864849848949848949849");
//                                                         465465874684485??
//                                    564328596489326584687050302375401328
        BigNumber q = new BigNumber("1564465");

        System.out.println(o.add(q));
    }


    //获取到int值的位数
    static int sizeOfInt(int a) {
        return a < 10 ? 1 : (a < 100 ? 2 : (a < 1000 ? 3 : a < 10000 ? 4 : (a < 100000 ? 5 : (a < 1000000 ? 6 : (a < 10000000 ? 7 : (a < 100000000 ? 8 : 9))))));

//        0564328596489326584687050302375401328
//        32859648932658468705030237541328
    }

    //获取到int数组的值
    public int[] getValue() {
        return value;
    }

    //数组转字符串
    private String backToString(int[] res) {
        StringBuffer a = new StringBuffer("");
        for (int i = 0; i < res.length; i++) {
            if (sizeOfInt(res[i]) < 4) {
                for (int j = 0; j < 4 - sizeOfInt(res[i]); j++) {
                    a.append("0");
                }
            }
            a.append(res[i]);
        }
        return a.toString();
    }

    //构造函数，根据String类型创建
    public BigNumber(String number) {

        int thisLength = number.length() % 4 == 0 ? number.length() / 4 : number.length() / 4 + 1;
        String[] num = new String[thisLength];
        int[] big1 = new int[thisLength];
        value = big1;
        stringValue = number;
        for (int i = thisLength - 1; i >= 0; i--) {
            if (i != 0) {
                int be = -4 * thisLength + 4 * (i + 1) + number.length() - 4;
                int en = -4 * thisLength + 4 * (i + 1) + number.length();
                num[i] = number.substring(be, en);
            } else {
                num[i] = number.substring(0, 4 * (1 - thisLength) + number.length());
            }
        }
        for (int i = 0; i < num.length; i++) {
//            if(num[i]==""||num[i]==null){
//                big[i]=0;
//            }else {

            value[i] = Integer.parseInt(num[i]);
//            }

//            System.out.println(big[i]);
        }

    }

    //整理
    public int[] addArrage(int[] res) {
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] > 9999) {
                res[i - 1] = res[i - 1] + res[i] / 10000;
                res[i] = res[i] % 10000;
            }
        }

        return res;
    }

    /***加法*/
    public String add(BigNumber number2) {
        int[] num1 = getValue();
        int[] num2 = number2.getValue();
        int[] min = (Integer.min(num1.length, num2.length) == num1.length) ? num1 : num2;//较大值
        int[] max = Integer.max(num1.length, num2.length) == num2.length ? num2 : num1;//较小值
        int[] res = new int[max.length + 1];
        for (int i = res.length - 1; i >= 0; i--) {
//            res[res.length-i]=10;
            if (i >= res.length - min.length) {
//                int ma=num2[i-num1.length+num2.length-1];
                res[i] = max[i - res.length + max.length] + min[i - res.length + min.length];
            } else if (i >= res.length - max.length) {
                res[i] = max[i - res.length + max.length];
            }
        }
        res = addArrage(res);
        return backToString(res);
    }
}
