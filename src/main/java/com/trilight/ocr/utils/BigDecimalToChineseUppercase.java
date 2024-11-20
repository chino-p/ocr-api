package com.trilight.ocr.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalToChineseUppercase {

    private static final String[] CHINESE_DIGITS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] UNITS = {"", "拾", "佰", "仟"};
    private static final String[] BIG_UNITS = {"", "万", "亿"};
    private static final String[] DECIMAL_UNITS = {"角", "分"};

    public static String convert(BigDecimal number) {
        if (number.signum() < 0) {
            return "负" + convert(number.abs()); // 处理负数
        }

        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return "零元整"; // 特殊情况
        }

        StringBuilder result = new StringBuilder();

        // 分离整数部分和小数部分
        BigDecimal integerPart = number.setScale(0, RoundingMode.FLOOR); // 整数部分
        BigDecimal decimalPart = number.subtract(integerPart).setScale(2, RoundingMode.HALF_UP); // 小数部分精确到分

        // 处理整数部分
        result.append(convertIntegerPart(integerPart.toBigInteger().toString())).append("元");

        // 处理小数部分
        if (decimalPart.compareTo(BigDecimal.ZERO) > 0) {
            result.append(convertDecimalPart(decimalPart));
        } else {
            result.append("整"); // 没有小数部分时加“整”
        }

        return result.toString();
    }

    private static String convertIntegerPart(String numberStr) {
        if (numberStr.equals("0")) {
            return "零"; // 如果整数部分为 0，直接返回“零”
        }

        StringBuilder result = new StringBuilder();
        int length = numberStr.length();
        boolean hasZero = false; // 记录是否需要插入“零”

        for (int i = 0; i < length; i++) {
            int digit = numberStr.charAt(i) - '0';
            int position = length - i - 1;

            // 获取单位
            String unit = UNITS[position % 4];
            String bigUnit = BIG_UNITS[position / 4];

            if (digit == 0) {
                hasZero = true;
            } else {
                if (hasZero) {
                    result.append("零");
                    hasZero = false;
                }
                result.append(CHINESE_DIGITS[digit]).append(unit);
            }

            // 添加万、亿单位
            if (position % 4 == 0 && !result.isEmpty()) {
                result.append(bigUnit);
            }
        }

        // 删除末尾的多余“万”或“亿”
        if (result.lastIndexOf("万") == result.length() - 1 || result.lastIndexOf("亿") == result.length() - 1) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    private static String convertDecimalPart(BigDecimal decimalPart) {
        StringBuilder result = new StringBuilder();

        int jiao = decimalPart.multiply(BigDecimal.TEN).intValue(); // 角
        int fen = decimalPart.multiply(new BigDecimal("100")).remainder(BigDecimal.TEN).intValue(); // 分

        if (jiao > 0) {
            result.append(CHINESE_DIGITS[jiao]).append(DECIMAL_UNITS[0]);
        }

        if (fen > 0) {
            result.append(CHINESE_DIGITS[fen]).append(DECIMAL_UNITS[1]);
        }

        return result.toString();
    }
}
