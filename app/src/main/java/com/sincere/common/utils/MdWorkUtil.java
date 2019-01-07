package com.sincere.common.utils;

import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 校验类
 */

public class MdWorkUtil {
    public static String getMD5(String val) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes());
        byte[] m = md5.digest();//加密
        return getString(m);
    }

    private static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    /**
     * 是否为身份证
     *
     * @param IDNumber
     * @return
     */
    public static boolean isId(String IDNumber) {
        if (TextUtils.isEmpty(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        Log.e("tag", "身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                                "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("tag", "异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }

    public static String getMD(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xffffff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 匹配手机号的规则：[3578]是手机号第二位可能出现的数字
     */
    public static final String REGEX_MOBILE = "^[1][35789][0-9]{9}$";

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }


    /**
     * 固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean isTelephone(String telephone) {
        String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, telephone);

    }

    /**
     * 社会统一代码
     */


    public static final String SOCIAL_UNIFIED_CODE = "[A-Z0-9]{18}";


    /**
     * 社会统一代码
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isSocial(String mobile) {
        return Pattern.matches(SOCIAL_UNIFIED_CODE, mobile);
    }

    /**
     * 邮编
     *
     * @param postcode
     * @return
     */
    public static boolean isPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 网址
     *
     * @param url
     */
    public static boolean isWebUrl(String url) {
        String regex = "(?i)\\b((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^]\\s`!()\\[{};:'\".,<>?«»“”‘’]))";
//        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    /**
     * 传真
     *
     * @param fax
     * @return
     */
    public static boolean isFax(String fax) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(fax).matches();
    }

    public static boolean checkBankCard(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return false;
        }
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }


    //    static String creditCode = "91350100M000100Y43";// 测试
    static String isCreditCode = "true";
    static String error_CreditCode = "社会信用代码不合法";
    static String error_CreditCode_min = "社会信用代码不足18位，请核对后再输！";
    static String error_CreditCode_max = "社会信用代码大于18位，请核对后再输！";
    static String error_CreditCode_empty = "社会信用代码不能为空！";
    private static Map<String, Integer> datas = null;
    private static char[] pre17s;
    static int[] power = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
    // 社会统一信用代码不含（I、O、S、V、Z） 等字母
    static char[] code = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y'};

    public static String checkCreditCode(String creditCode) {
        if (creditCode.length() != 18) {
            return error_CreditCode_min;
        }
        String temp = creditCode;
        System.out.println(temp);
        initDatas(code.length);
        pre17(temp);
        String creditCodes = isCreditCode(temp);
        return creditCodes;
    }

    /**
     * 判断是否是一个有效的社会信用代码
     *
     * @param creditCode
     * @return
     */
    private static String isCreditCode(String creditCode) {
        if ("".equals(creditCode) || " ".equals(creditCode)) {
            System.out.println(error_CreditCode_empty);
            return error_CreditCode_empty;
        } else if (creditCode.length() < 18) {
            System.out.println(error_CreditCode_min);
            return error_CreditCode_min;
        } else if (creditCode.length() > 18) {
            System.out.println(error_CreditCode_max);
            return error_CreditCode_max;
        } else {
            int sum = sum(pre17s);
            if (sum == -1) {
                return error_CreditCode;
            }
            int temp = sum % 31;
            temp = temp == 0 ? 31 : temp;//  谢谢 whhitli的帮助
            System.out.println(code[31 - temp] + " " + (creditCode.substring(17, 18).equals(code[31 - temp] + "") ? isCreditCode : error_CreditCode));
            return creditCode.substring(17, 18).equals(code[31 - temp] + "") ? isCreditCode : error_CreditCode;
        }
    }

    /**
     * @param chars
     * @return
     */
    private static int sum(char[] chars) {
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            String str = datas.get(chars[i] + "") + "";
            if (str.equals("null")) {
                return -1;
            }
            int code = datas.get(chars[i] + "");
            sum += power[i] * code;
        }
        return sum;

    }

    /**
     * 获取前17位字符
     *
     * @param creditCode
     */
    private static void pre17(String creditCode) {
        String pre17 = creditCode.substring(0, 17);
        pre17s = pre17.toCharArray();
    }

    /**
     * 初始化数据
     *
     * @param count
     */
    private static void initDatas(int count) {
        datas = new HashMap<>();
        for (int i = 0; i < code.length; i++) {
            datas.put(code[i] + "", i);
        }
        System.out.println();
    }

    private static final String TAG = "MD5Util";

    /***
     * MD5加码 生成32位md5码
     */
    public static String addMD5(String inStr) {
        Log.e(TAG, "string2MD5: -------------------------");
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {
        Log.e(TAG, "convertMD5: -------------------------");
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

}
