package com.me;

/**
 * Created by heifrank on 16/8/23.
 */
public class XiaoZhao2 {
    public static int cal(String a, String b) {
        int la = a.length(), lb = b.length();
        int[][] f = new int[la + 1][lb + 1];
        for(int i = 1; i <= la; i++) {
            for(int j = 1; j <= lb; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[la][lb];
    }

    public static void main(String[] args) {
        System.out.println(cal("yidianzixun", "liyueaoyunhui"));
    }
}


