import java.lang.Math.*;

public class Main {
    public static void main(String args[]) {
        int maxInt = 18, minInt = 4, lenD = (maxInt - minInt) / 2 + 1, lenX = 19;
        int gSizeI = 8, gSizeJ = 19;

        int[] d;
        d = new int[lenD];
        float[] x;
        x = new float[lenX];

        for (int i1 = 0; i1 < lenD; i1++) {
            d[i1] = minInt + i1 * 2;
        }
        for (int i2 = 0; i2 < x.length; i2++) {
            x[i2] = (float) (Math.random() * 18 - 14);
        }

        double[][] g = new double[gSizeI][gSizeJ];
        for (int i = 0; i < gSizeI; i++) {
            for (int j = 0; j < gSizeJ; j++) {
                if (d[i] == 10)
                    g[i][j] = Math.pow(2 / Math.pow(Math.pow(2 * x[j], 2), (1 - x[j] / 0.25) / 2), 2);
                else if (d[i] == 4 || d[i] == 12 || d[i] == 14 || d[i] == 16)
                    g[i][j] = 2 * Math.pow(x[j], (x[j] + 1) / 0.25) / 1 / 2;
                else
                    g[i][j] = Math.log(Math.pow(Math.E, Math.pow(Math.pow(Math.E, Math.pow(x[j], (1 - x[j]) / x[j])), 2 * Math.atan(Math.pow(Math.E, -Math.abs(x[j]))))));
            }
        }

        for (double[] i: g) {
            for (double j : i) {
                System.out.printf(" %.4f ", j);
            }
            System.out.println();
        }
    }
}