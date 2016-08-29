package com.me;

/**
 * Created by heifrank on 16/8/23.
 */
public class XiaoZhao {
    final int XX = 4, YY = 3, ZZ = 9;
    int dx = 1, dy = 1, dz = 1;
    public int cal(int x, int y, int z, int dep) {
        if ( z == ZZ )
            return 0;
        switch (dep % 3) {
            case 0:
                if (x + dx < 0 || x + dx > XX)
                    dx = -dx;
                x += dx;
                break;
            case 1:
                if (y + dy < 0 || y + dy > YY)
                    dy = -dy;
                y += dy;
                break;
            case 2:
                if (z + dz < 0 || z + dz > ZZ)
                    dz = -dz;
                z += dz;
                break;
        }
        return 1 + cal(x, y, z, dep + 1);
    }

    public static void main(String[] args) {
        XiaoZhao xiaoZhao = new XiaoZhao();
        System.out.println(xiaoZhao.cal(0, 0, 0, 0));
    }
}
