package com.me;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by heifrank on 16/4/18.
 */
public class MyWife {
    static Random random = new Random(System.currentTimeMillis());

    public static int[] shuffle(int n){
        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = i;

        Random random = new Random(10086);
        for(int i = 0; i < n; i++){
            Integer idx = random.nextInt(i + 1);
            int temp = res[i];
            res[i] = res[idx];
            res[idx] = temp;
        }
        return res;
    }

    public static void put(int[] res, int st, int end, List<Integer> numbers){
        int[] shuff = shuffle(end - st + 1);
        assert(numbers.size() <= end - st + 1);
        for(int i = 0; i < numbers.size(); i++){
            res[st + shuff[i]] = numbers.get(i);
        }
    }

    public static int[] work(int[] pos, int number){
        int loops = pos.length;
        int[] res = new int[pos[loops - 1] + 1];
        int[] cnt = new int[number + 1];
        int[][] upperCnt = new int[loops][number + 1];
        for(int i = 0; i < loops; i++)for(int j = 1; j <= number; j++)upperCnt[i][j] = 10086;

        for(int loop = loops - 1; loop >= 0; loop--){
            int x = random.nextInt(number) + 1;
            res[ pos[loop] ] = x;
            for(int i = 0; i <= loop; i++)
                upperCnt[i][x] = loop;
        }

//        for(int i = 0; i < loops; i++){
//            for(int j = 1; j <= number; j++)
//                System.out.printf("%d\t\t", upperCnt[i][j]);
//            System.out.println();
//        }

        for(int loop = 0; loop < loops; loop++){
            int st = loop == 0 ? 0 : pos[loop - 1] + 1;
            int ed = pos[loop];
            cnt[res[ pos[loop] ]]++;

            List<Integer> numbers = new ArrayList<>();
            for(int j = 1; j <= number; j++){
                if(cnt[j] < loop + 1){
                    numbers.add(j);
                    cnt[j]++;
                }
            }

            put(res, st, ed - 1, numbers);
            for(int i = st; i < ed; i++){
                int tmp = 0;
                if(res[i] == 0){
                    tmp = random.nextInt(number) + 1;
                    while(cnt[tmp] + 1 > upperCnt[loop][tmp]){
                        tmp = random.nextInt(number) + 1;
                    }
                    res[i] = tmp;
                }
                cnt[tmp]++;
            }
        }
        return res;
    }

    public static void print(int[] res, int[] pos){
        for (int i = 0; i < res.length; i++) {
            boolean flag = false;
            for (int j = 0; j < pos.length; j++) {
                if (pos[j] == i) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                System.out.printf("%d ", res[i]);
            else
                System.out.printf("(%d) ", res[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        int wants = 50;
        int[] starts = {8, 20, 38};
        int[] ends = {9, 25, 50};
        for(int i = 0; i < wants; i++){
            int[] pos = new int[starts.length];
            for(int j = 0; j < starts.length; j++){
                pos[j] = starts[j] + random.nextInt(ends[j] - starts[j] + 1);
            }
            int[] res = work(pos, 8);
            print(res, pos);

        }

    }
}
