package cn.wanfeng.algorithm;

/**
 * 动态规划 菌子加工.
 * Date: 2026-08-10 00:02
 * Author: lzh
 */
public class DP_001_JunziJiagong {

    /**
     * 题目：
     * 一家云南菌子加工厂，需要加工count个菌子（count范围是1-15），总共有total小时的加工时间（单位小时，范围5-75，菌子的总加工时长 不能超过total）
     * - 第i个菌子的初始价值为values[i]
     * - 菌子会随时间流失新鲜度降低价值，第i个菌子的流失价值速度为decays[i]，单位为 元/小时
     * - 每个菌子的加工时长固定为5小时，加工菌子时不会流失新鲜度
     * - 当菌子的价值为0或负数时，菌子无价值，不会被加工
     * 求解total小时后可以加工出菌子的最大价值数。
     * <p>
     * 求解思路：
     * 菌子最多15个，我们用二进制掩码(mask)表示所有菌子的加工情况（掩码第i位是1表示第i个菌子已加工）
     * 动态规划 dp[mask] = 对于某一种菌子加工情况mask时，可加工出的最大价值数
     * <p>
     * 动态规划和贪心算法的区别：
     * 动态规划dp变量需要穷举题目给出的所有可能的完整状态（所有菌子的是否加工情况）
     * 贪心可能只反映走到某个时间点的不完整状态（部分菌子的加工情况），未被记录的状态如果不一样，就会导致最优解不一样
     *
     * @param count  菌子个数
     * @param total  总加工时间
     * @param values 菌子对应的初始价值
     * @param decays 菌子每小时流失的价值
     * @return 结果
     */
    public static long mashroom_process(int count, int total, int[] values, int[] decays) {
        int maxCnt = total / 5;     // 最大加工个数
        int mask_size = 1 << count;  // 掩码的总个数（例如count=4时，掩码范围为 0000 ~ 1111，总共有10000（15）种情况
        long[] dp = new long[mask_size];
        long ans = 0;                   // 最优解，至少为0

        // 遍历所有菌子加工情况
        for (int mask = 0; mask < mask_size; mask++) {
            // 从当前掩码中获取已加工菌子个数（二进制中1的个数）
            int cnt = Integer.bitCount(mask);
            // 如果这种情况的菌子加工数超过最大次数，直接跳过
            if (cnt > maxCnt) continue;
            // 更新最优解
            ans = Math.max(ans, dp[mask]);

            // 当前已加工的小时数
            int usedTime = cnt * 5;
            // 遍历所有菌子
            for (int i = 0; i < count; i++) {
                // 菌子i已经加工过，跳过
                if ((mask & (1 << i)) != 0) continue;
                // 如果加工菌子i后会超时，跳过
                if (cnt + 1 > maxCnt) continue;

                // 根据已加工小时数，计算菌子当前的价值
                long curValue = (long) values[i] - (long) decays[i] * usedTime;
                // 如果菌子i无价值，不能加工
                if (curValue <= 0) continue;

                // 将菌子i加入到mask中获取新的掩码
                int newMask = mask | (1 << i);
                // 更新加入菌子i后的状态的最优解
                dp[newMask] = Math.max(dp[newMask], dp[mask] + curValue);
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int count = 4;
        int total = 20;
        int[] values = new int[]{100, 200, 300, 97};
        int[] decays = new int[]{1, 3, 10, 5};

        long result = mashroom_process(count, total, values, decays);
        System.out.println(result);
    }
}
