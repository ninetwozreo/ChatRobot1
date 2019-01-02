package com.chatRobot.model;
import java.math.BigInteger;


public class Pi {
    static BigInteger val=new BigInteger("1");

    public static void main(String[] args) {
        System.out.println(去他么的阶乘(new BigInteger("2018")));

    }
    private static BigInteger 去他么的阶乘(BigInteger condition){
        BigInteger a=new BigInteger("1");
        if(condition.compareTo(val)==1){
            a=condition.multiply(去他么的阶乘(condition.subtract(val)));
        }
        return a;
    }
}
