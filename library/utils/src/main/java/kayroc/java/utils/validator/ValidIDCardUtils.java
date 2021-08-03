package kayroc.java.utils.validator;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kayroc.android.utils.LogUtils;
import kayroc.java.utils.StringUtils;


/**
 * 居民身份证工具类
 *
 * @author kayroc
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "unused"})
public final class ValidIDCardUtils {

    private ValidIDCardUtils() {
    }

    /** 加权因子 */
    private static final int[] POWER = {
        7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };
    /** 身份证最少位数 */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    /** 身份证最大位数 */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    /** 省份编码 */
    private static final Map<String, String> CITY_CODE_MAPS = new HashMap<>();
    /** 台湾身份首字母对应数字 */
    private static final Map<String, Integer> TW_FIRST_CODE_MAPS = new HashMap<>();
    /** 香港身份首字母对应数字 */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> HK_FIRST_CODE_MAPS = new HashMap<>();

    static {
        CITY_CODE_MAPS.put("11", "北京");
        CITY_CODE_MAPS.put("12", "天津");
        CITY_CODE_MAPS.put("13", "河北");
        CITY_CODE_MAPS.put("14", "山西");
        CITY_CODE_MAPS.put("15", "内蒙古");
        CITY_CODE_MAPS.put("21", "辽宁");
        CITY_CODE_MAPS.put("22", "吉林");
        CITY_CODE_MAPS.put("23", "黑龙江");
        CITY_CODE_MAPS.put("31", "上海");
        CITY_CODE_MAPS.put("32", "江苏");
        CITY_CODE_MAPS.put("33", "浙江");
        CITY_CODE_MAPS.put("34", "安徽");
        CITY_CODE_MAPS.put("35", "福建");
        CITY_CODE_MAPS.put("36", "江西");
        CITY_CODE_MAPS.put("37", "山东");
        CITY_CODE_MAPS.put("41", "河南");
        CITY_CODE_MAPS.put("42", "湖北");
        CITY_CODE_MAPS.put("43", "湖南");
        CITY_CODE_MAPS.put("44", "广东");
        CITY_CODE_MAPS.put("45", "广西");
        CITY_CODE_MAPS.put("46", "海南");
        CITY_CODE_MAPS.put("50", "重庆");
        CITY_CODE_MAPS.put("51", "四川");
        CITY_CODE_MAPS.put("52", "贵州");
        CITY_CODE_MAPS.put("53", "云南");
        CITY_CODE_MAPS.put("54", "西藏");
        CITY_CODE_MAPS.put("61", "陕西");
        CITY_CODE_MAPS.put("62", "甘肃");
        CITY_CODE_MAPS.put("63", "青海");
        CITY_CODE_MAPS.put("64", "宁夏");
        CITY_CODE_MAPS.put("65", "新疆");
        CITY_CODE_MAPS.put("71", "台湾");
        CITY_CODE_MAPS.put("81", "香港");
        CITY_CODE_MAPS.put("82", "澳门");
        CITY_CODE_MAPS.put("83", "台湾");
        CITY_CODE_MAPS.put("91", "国外");
        TW_FIRST_CODE_MAPS.put("A", 10);
        TW_FIRST_CODE_MAPS.put("B", 11);
        TW_FIRST_CODE_MAPS.put("C", 12);
        TW_FIRST_CODE_MAPS.put("D", 13);
        TW_FIRST_CODE_MAPS.put("E", 14);
        TW_FIRST_CODE_MAPS.put("F", 15);
        TW_FIRST_CODE_MAPS.put("G", 16);
        TW_FIRST_CODE_MAPS.put("H", 17);
        TW_FIRST_CODE_MAPS.put("J", 18);
        TW_FIRST_CODE_MAPS.put("K", 19);
        TW_FIRST_CODE_MAPS.put("L", 20);
        TW_FIRST_CODE_MAPS.put("M", 21);
        TW_FIRST_CODE_MAPS.put("N", 22);
        TW_FIRST_CODE_MAPS.put("P", 23);
        TW_FIRST_CODE_MAPS.put("Q", 24);
        TW_FIRST_CODE_MAPS.put("R", 25);
        TW_FIRST_CODE_MAPS.put("S", 26);
        TW_FIRST_CODE_MAPS.put("T", 27);
        TW_FIRST_CODE_MAPS.put("U", 28);
        TW_FIRST_CODE_MAPS.put("V", 29);
        TW_FIRST_CODE_MAPS.put("X", 30);
        TW_FIRST_CODE_MAPS.put("Y", 31);
        TW_FIRST_CODE_MAPS.put("W", 32);
        TW_FIRST_CODE_MAPS.put("Z", 33);
        TW_FIRST_CODE_MAPS.put("I", 34);
        TW_FIRST_CODE_MAPS.put("O", 35);
        HK_FIRST_CODE_MAPS.put("A", 1);
        HK_FIRST_CODE_MAPS.put("B", 2);
        HK_FIRST_CODE_MAPS.put("C", 3);
        HK_FIRST_CODE_MAPS.put("R", 18);
        HK_FIRST_CODE_MAPS.put("U", 21);
        HK_FIRST_CODE_MAPS.put("Z", 26);
        HK_FIRST_CODE_MAPS.put("X", 24);
        HK_FIRST_CODE_MAPS.put("W", 23);
        HK_FIRST_CODE_MAPS.put("O", 15);
        HK_FIRST_CODE_MAPS.put("N", 14);
    }

    /**
     * 身份证校验规则, 验证 15 位身份编码是否合法
     *
     * @param idCard 待验证身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean validateIdCard15(final String idCard) {
        // 属于数字, 并且长度为 15 位数
        if (isNumber(idCard) && idCard.length() == CHINA_ID_MIN_LENGTH) {
            // 获取省份编码
            String provinceCode = idCard.substring(0, 2);
            if (CITY_CODE_MAPS.get(provinceCode) == null) {
                return false;
            }
            // 获取出生日期
            String birthCode = idCard.substring(6, 12);
            Date birthDate = null;
            try {
                birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
            } catch (ParseException e) {
                LogUtils.e(e.getMessage());
            }
            Calendar calendar = Calendar.getInstance();
            if (birthDate != null) {
                calendar.setTime(birthDate);
            }
            // 判断是否有效日期
            return validateDateSmallerThenNow(
                calendar.get(Calendar.YEAR),
                Integer.parseInt(birthCode.substring(2, 4)),
                Integer.parseInt(birthCode.substring(4, 6))
            );
        }
        return false;
    }

    /**
     * 身份证校验规则, 验证 18 位身份编码是否合法
     *
     * @param idCard 待验证身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean validateIdCard18(final String idCard) {
        if (idCard != null && idCard.length() == CHINA_ID_MAX_LENGTH) {
            // 前 17 位
            String code17 = idCard.substring(0, 17);
            // 第 18 位
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            // 判断前 17 位是否数字
            if (isNumber(code17)) {
                try {
                    int[] cardArrays = convertCharToInt(code17.toCharArray());
                    int sum17 = getPowerSum(cardArrays);
                    // 获取校验位
                    String str = getCheckCode18(sum17);
                    // 判断最后一位是否一样
                    if (str.length() > 0 && str.equalsIgnoreCase(code18)) {
                        return true;
                    }
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        return false;
    }

    /**
     * 将 15 位身份证号码转换为 18 位
     *
     * @param idCard 15 位身份编码
     *
     * @return 18 位身份编码
     */
    @SuppressLint("SimpleDateFormat")
    public static String convert15CardTo18(final String idCard) {
        // 属于数字, 并且长度为 15 位数
        if (isNumber(idCard) && idCard.length() == CHINA_ID_MIN_LENGTH) {
            String idCard18;
            Date birthDate = null;
            // 获取出生日期
            String birthday = idCard.substring(6, 12);
            try {
                birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                LogUtils.e(e.getMessage());
            }
            Calendar calendar = Calendar.getInstance();
            if (birthDate != null) {
                calendar.setTime(birthDate);
            }
            try {
                // 获取出生年 ( 完全表现形式, 如: 2010)
                String year = String.valueOf(calendar.get(Calendar.YEAR));
                // 保存省市区信息 + 年 + 月日 + 后续信息 ( 顺序位、性别等 )
                idCard18 = idCard.substring(0, 6) + year + idCard.substring(8);
                // 转换字符数组
                int[] cardArrays = convertCharToInt(idCard18.toCharArray());
                int sum17 = getPowerSum(cardArrays);
                // 获取校验位
                String str = getCheckCode18(sum17);
                // 判断长度, 拼接校验位
                return (str.length() > 0) ? (idCard18 + str) : null;
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 验证台湾身份证号码
     *
     * @param idCard 身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean validateTWCard(final String idCard) {
        // 台湾身份证 10 位
        if (idCard == null || idCard.length() != 10) {
            return false;
        }
        try {
            // 第一位英文 不同县市
            String start = idCard.substring(0, 1);
            String mid = idCard.substring(1, 9);
            String end = idCard.substring(9, 10);
            Integer iStart = TW_FIRST_CODE_MAPS.get(start);
            if (iStart == null) {
                iStart = 0;
            }
            int sum = iStart / 10 + (iStart % 10) * 9;
            char[] chars = mid.toCharArray();
            int iFlag = 8;
            for (char c : chars) {
                sum = sum + Integer.parseInt(String.valueOf(c)) * iFlag;
                iFlag--;
            }
            return (sum % 10 == 0 ? 0 : 10 - sum % 10) == Integer.parseInt(end);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 验证香港身份证号码 ( 部份特殊身份证无法检查 )
     * 身份证前 2 位为英文字符, 如果只出现一个英文字符则表示第一位是空格, 对应数字 58 前 2 位英文字符 A-Z 分别对应数字 10-35
     * 最后一位校验码为 0-9 的数字加上字符 "A", "A" 代表 10
     * 将身份证号码全部转换为数字, 分别对应乘 9-1 相加的总和, 整除 11 则证件号码有效
     *
     * @param idCard 身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    @SuppressWarnings("DuplicateExpressions")
    public static boolean validateHKCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return false;
        }
        try {
            String card = idCard.replaceAll("[(|)]", "");
            int sum;
            if (card.length() == 9) {
                sum = ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 9 + ((int) card.substring(1, 2).toUpperCase().toCharArray()[0] - 55) * 8;
                card = card.substring(1, 9);
            } else {
                sum = 522 + ((int) card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 8;
            }
            String mid = card.substring(1, 7);
            String end = card.substring(7, 8);
            char[] chars = mid.toCharArray();
            int flag = 7;
            for (char c : chars) {
                sum = sum + Integer.parseInt(String.valueOf(c)) * flag;
                flag--;
            }
            if ("A".equalsIgnoreCase(end)) {
                sum = sum + 10;
            } else {
                sum = sum + Integer.parseInt(end);
            }
            return (sum % 11 == 0);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return false;
    }

    /**
     * 判断 10 位数的身份证号, 是否合法
     *
     * @param idCard 身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    @SuppressWarnings("RegExpDuplicateCharacterInClass")
    public static String[] validateIdCard10(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return null;
        }
        String[] info = new String[3];
        info[0] = "N"; // 默认未知地区
        info[1] = "N"; // 默认未知性别
        info[2] = "false"; // 默认非法
        try {
            String card = idCard.replaceAll("[(|)]", "");
            // 获取身份证长度
            int cardLength = card.length();
            // 属于 8, 9, 10 长度范围内
            if (cardLength >= 8 && cardLength <= 10) {
                if (idCard.matches("^[a-zA-Z][0-9]{9}$")) {
                    // 台湾
                    info[0] = "台湾";
                    String char2 = idCard.substring(1, 2);
                    if ("1".equals(char2)) {
                        info[1] = "M";
                    } else if ("2".equals(char2)) {
                        info[1] = "F";
                    } else {
                        info[1] = "N";
                        info[2] = "false";
                        return info;
                    }
                    info[2] = validateTWCard(idCard) ? "true" : "false";
                } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) {
                    // 澳门
                    info[0] = "澳门";
                    info[1] = "N";
                    // TODO
                } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) {
                    // 香港
                    info[0] = "香港";
                    info[1] = "N";
                    info[2] = validateHKCard(idCard) ? "true" : "false";
                }
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return info;
    }

    /**
     * 验证身份证是否合法
     *
     * @param idCard 身份证号码
     *
     * @return {@code true} yes, {@code false} no
     */
    public static boolean validateCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return false;
        }
        String card = idCard.trim();
        if (validateIdCard18(card)) {
            return true;
        }
        if (validateIdCard15(card)) {
            return true;
        }
        String[] cardArrays = validateIdCard10(card);
        return (cardArrays != null && "true".equals(cardArrays[2]));
    }

    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     *
     * @return 年龄
     */
    public static int getAgeByIdCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return 0;
        }
        try {
            String idCardStr = idCard;
            // 属于 15 位身份证, 则转换为 18 位
            if (idCardStr.length() == CHINA_ID_MIN_LENGTH) {
                idCardStr = convert15CardTo18(idCard);
            }
            // 属于 18 位身份证才处理
            assert idCardStr != null;
            if (idCardStr.length() == CHINA_ID_MAX_LENGTH) {
                String year = idCardStr.substring(6, 10);
                // 获取当前年份
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                // 当前年份 ( 出生年份 )
                return currentYear - Integer.parseInt(year);
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return 0;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     *
     * @return 生日 (yyyyMMdd)
     */
    public static String getBirthByIdCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return null;
        }
        try {
            String idCardStr = idCard;
            // 属于 15 位身份证, 则转换为 18 位
            if (idCardStr.length() == CHINA_ID_MIN_LENGTH) {
                idCardStr = convert15CardTo18(idCard);
            }
            // 属于 18 位身份证才处理
            assert idCardStr != null;
            if (idCardStr.length() == CHINA_ID_MAX_LENGTH) {
                return idCardStr.substring(6, 14);
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     *
     * @return 生日 (yyyyMMdd)
     */
    public static String getBirthdayByIdCard(final String idCard) {
        // 获取生日
        String birth = getBirthByIdCard(idCard);
        // 进行处理
        if (birth != null) {
            try {
                return birth.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 根据身份编号获取生日 ( 年份 )
     *
     * @param idCard 身份编号
     *
     * @return 生日 (yyyy)
     */
    public static String getYearByIdCard(final String idCard) {
        // 获取生日
        String birth = getBirthByIdCard(idCard);
        // 进行处理
        if (birth != null) {
            try {
                return birth.substring(0, 4);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 根据身份编号获取生日 ( 月份 )
     *
     * @param idCard 身份编号
     *
     * @return 生日 (MM)
     */
    public static String getMonthByIdCard(final String idCard) {
        // 获取生日
        String birth = getBirthByIdCard(idCard);
        // 进行处理
        if (birth != null) {
            try {
                return birth.substring(4, 6);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 根据身份编号获取生日 ( 天数 )
     *
     * @param idCard 身份编号
     *
     * @return 生日 (dd)
     */
    public static String getDateByIdCard(final String idCard) {
        // 获取生日
        String birth = getBirthByIdCard(idCard);
        // 进行处理
        if (birth != null) {
            try {
                return birth.substring(6, 8);
            } catch (Exception e) {
                LogUtils.e(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     *
     * @return 性别 男 (M)、女 (F)、未知 (N)
     */
    public static String getGenderByIdCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return null;
        }
        try {
            String idCardStr = idCard;
            // 属于 15 位身份证, 则转换为 18 位
            if (idCardStr.length() == CHINA_ID_MIN_LENGTH) {
                idCardStr = convert15CardTo18(idCard);
            }
            // 属于 18 位身份证才处理
            assert idCardStr != null;
            if (idCardStr.length() == CHINA_ID_MAX_LENGTH) {
                // 获取第 17 位性别信息
                String cardNumber = idCardStr.substring(16, 17);
                // 奇数为男, 偶数为女
                return (Integer.parseInt(cardNumber) % 2 == 0) ? "F" : "M";
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        // 默认未知
        return "N";
    }

    /**
     * 根据身份编号获取户籍省份
     *
     * @param idCard 身份编码
     *
     * @return 省级编码
     */
    public static String getProvinceByIdCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return null;
        }
        try {
            // 身份证长度
            int idCardLength = idCard.length();
            // 属于 15 位身份证、或 18 位身份证
            if (idCardLength == CHINA_ID_MIN_LENGTH || idCardLength == CHINA_ID_MAX_LENGTH) {
                return CITY_CODE_MAPS.get(idCard.substring(0, 2));
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后, 再获取和值
     *
     * @param data byte[] 数据
     *
     * @return 身份证编码, 加权引子
     */
    public static int getPowerSum(final int[] data) {
        if (data == null) {
            return 0;
        }
        int len = data.length;
        if (len == 0) {
            return 0;
        }
        int powerLength = POWER.length;
        int sum = 0;
        if (powerLength == len) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < powerLength; j++) {
                    if (i == j) {
                        sum = sum + data[i] * POWER[j];
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 将 POWER 和值与 11 取模获取余数进行校验码判断
     *
     * @param sum {@link ValidIDCardUtils#getPowerSum}
     *
     * @return 校验位
     */
    public static String getCheckCode18(final int sum) {
        String code = "";
        if (sum % 11 == 10) {
            code = "2";
        } else if (sum % 11 == 9) {
            code = "3";
        } else if (sum % 11 == 8) {
            code = "4";
        } else if (sum % 11 == 7) {
            code = "5";
        } else if (sum % 11 == 6) {
            code = "6";
        } else if (sum % 11 == 5) {
            code = "7";
        } else if (sum % 11 == 4) {
            code = "8";
        } else if (sum % 11 == 3) {
            code = "9";
        } else if (sum % 11 == 2) {
            code = "x";
        } else if (sum % 11 == 1) {
            code = "0";
        } else if (sum % 11 == 0) {
            code = "1";
        }
        return code;
    }

    // ==========
    // = 私有方法 =
    // ==========

    /**
     * 将字符数组转换成数字数组
     *
     * @param data char[]
     *
     * @return int[]
     */
    private static int[] convertCharToInt(final char[] data) {
        if (data == null) {
            return null;
        }
        int len = data.length;
        if (len == 0) {
            return null;
        }
        try {
            int[] arrays = new int[len];
            for (int i = 0; i < len; i++) {
                arrays[i] = Integer.parseInt(String.valueOf(data[i]));
            }
            return arrays;
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
        return null;
    }

    /**
     * 验证小于当前日期 是否有效
     *
     * @param yearData  待校验的日期 ( 年 )
     * @param monthData 待校验的日期 ( 月 1-12)
     * @param dayData   待校验的日期 ( 日 )
     *
     * @return {@code true} yes, {@code false} no
     */
    @SuppressWarnings("ConstantConditions")
    private static boolean validateDateSmallerThenNow(
        final int yearData,
        final int monthData,
        final int dayData
    ) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int datePerMonth;
        int MIN = 1930;
        if (yearData < MIN || yearData >= year) {
            return false;
        }
        if (monthData < 1 || monthData > 12) {
            return false;
        }
        switch (monthData) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((yearData % 4 == 0 && yearData % 100 != 0) || (yearData % 400 == 0)) && (yearData > MIN && yearData < year);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (dayData >= 1) && (dayData <= datePerMonth);
    }

    /**
     * 检验数字
     *
     * @param str 待校验的字符串
     *
     * @return {@code true} yes, {@code false} no
     */
    private static boolean isNumber(final String str) {
        return !StringUtils.isEmpty(str) && str.matches("^[0-9]*$");
    }
}