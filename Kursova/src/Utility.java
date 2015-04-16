public class Utility {

    public static boolean isTwoVectorsEquals(int d[], int d2[]) {
        for (int i = 0; i < d.length; i++) {
            if (d[i] != d2[i])
                return false;
        }
        return true;
    }

    public static int findMax(double[] arr) {
        double max = arr[0];
        int indexOfMaxValue = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                indexOfMaxValue = i;
            }
        }
        return indexOfMaxValue;
    }

    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

}
