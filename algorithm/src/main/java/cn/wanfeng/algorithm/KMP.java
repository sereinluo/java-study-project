package cn.wanfeng.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * kmp算法.
 * Date: 2026-08-09 15:34
 * Author: lzh
 */
public class KMP {

    /**
     * 构建pi函数
     * index为子串中的一个位置
     * value为子串截取到index为止时，包含的相同前后缀的最长长度
     *
     * @param pattern 模式串、子串
     * @return pi函数
     */
    public static int[] buildPi(String pattern) {
        int[] pi = new int[pattern.length()];

        for (int left = 0, right = 1; right < pattern.length(); right++) {
            // 若此时左右匹配的字符不相同，left回退到更短的前缀（即前缀的前缀）的后一位，尝试再次匹配，直到匹配到相同字符或left==0
            while (left > 0 && pattern.charAt(right) != pattern.charAt(left)) {
                left = pi[left - 1];
            }

            if (pattern.charAt(right) == pattern.charAt(left)) {
                left++;
            }

            // 此时left为字符串在right位置时的最大相同前后缀长度

            // 设置pi函数值
            pi[right] = left;
        }
        return pi;
    }

    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();

        if (pattern == null || pattern.isEmpty()) {
            return result;
        }


        int textLen = text.length();
        int patternLen = pattern.length();
        int[] pi = buildPi(pattern);

        // i为遍历text的索引，j为遍历pattern的索引
        for (int i = 0, j = 0; i < textLen; i++) {
            // 不匹配时，将j移动到j前面的子串的最长前缀后一位，直到字符相同或j==0   m,.
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }


            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // 匹配成功
            if (j == patternLen) {
                result.add(i - patternLen + 1);
                j = pi[j - 1];
            }
        }

        return result;
    }


    public static void main(String[] args) {

    }
}
