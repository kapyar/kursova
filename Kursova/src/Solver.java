public class Solver {
    private int k;
    private int intense;
    private int time;

    public Solver(int k, int intense, int time) {
        super();
        this.k = k;
        this.intense = intense;
        this.time = time;
    }

    public Matrix setProbMatrix() {
        int[] sizeStr = new int[k + 1];
        for (int i = 0; i < sizeStr.length; i++) {
            sizeStr[i] = 2;
        }
        Matrix mat = new Matrix(sizeStr, k + 1);
        for (int i = 0; i < k + 1; i++) {
            mat.setValue(i, 0, Math.abs(i - 1), 1);
        }
        double sum = 0;
        for (int i = 0; i < k + 1; i++) {
            sum = 0;
            for (int j = 0; j < k; j++) {
                if (i <= j + 1) {
                    mat.setValue(i, 1, j, (Math.pow(intense * time, j) * Math.pow(Math.E, -intense * time)) / Utility.factorial(j));
                }
                sum += mat.getValue(i, 1, j);
            }
            mat.setValue(i, 1, k, 1 - sum);
        }

        return mat;
    }

    public Matrix setIncomeMatrix(int d, int r) {
        int[] sizeStr = new int[k + 1];
        for (int i = 0; i < sizeStr.length; i++) {
            sizeStr[i] = 2;
        }
        Matrix mat = new Matrix(sizeStr, k + 1);

        for (int i = 0; i < k + 1; i++) {

            for (int j = 0; j < k; j++) {

                mat.setValue(i, 1, j, d - r * (i - 1) * time);
            }
        }

        return mat;
    }

}
