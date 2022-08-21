package com.roy.utils;

import java.util.Random;

/**
 * 随机生成验证码工具类
 */
public class ValidateCodeUtils {
    /**
     * 随机生成验证码
     * @param length 长度为4位或者6位
     * @return
     */
    public static Integer generateValidateCode(int length){
        Integer code =null;
        if(length == 4){
            code = new Random().nextInt(9999);//生成随机数，最大为9999
            if(code < 1000){
                code = code + 1000;//保证随机数为4位数字
            }
        }else if(length == 6){
            code = new Random().nextInt(999999);//生成随机数，最大为999999
            if(code < 100000){
                code = code + 100000;//保证随机数为6位数字
            }
        }else{
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code;
    }

    /**
     * 随机生成验证码
     * @param obj 传入参数
     * @param length 长度为4位或者6位
     * @return
     */
    public static String generateValidateCode(String obj,int length){

        String[] fix = {"00000","0000","000","00","0",""};

        int hash = obj.hashCode();
        hash = hash<0?-hash:hash;
        int encryption = 20206666;
        long result = hash^encryption;
        long nowTime = System.currentTimeMillis();
        result = result^nowTime;
        long code = result % 1000000;

        String s = code+"";
        s = fix[s.length()-1] + code;
        if(0<length&&length<6){
            s = s.substring(0,length);
        }
        return s;
    }


    /**
     * 随机生成指定长度字符串验证码
     * @param length 长度
     * @return
     */
    public static String generateValidateCode4String(int length){
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        String capstr = hash1.substring(0, length);
        return capstr;
    }



}
