public class IterationMethod {

    public int[] execute(Matrix probabilityMatrix, Matrix IncomeMatrix) {

        Vector q = prepareProbabilityVector(probabilityMatrix, IncomeMatrix);

        int[] d = new int[probabilityMatrix.getSizeState()];
        d = findMaxStrategyForEveryState(q);

        double[][] metrix = new double[probabilityMatrix.getSizeState()][probabilityMatrix.getSizeState()];
        double[] incomeVector = new double[probabilityMatrix.getSizeState()];

        int[] prevSol = new int[probabilityMatrix.getSizeState()];
        double[] vesu = new double[probabilityMatrix.getSizeState()];
        double income = 0;

        do {

            for (int i = 0; i < probabilityMatrix.getSizeState(); i++) {
                metrix[i] = probabilityMatrix.getVectorForStateStrategy(i, d[i]);
                incomeVector[i] = q.getValue(i, d[i]);
            }
            prevSol = d;
            vesu = findVesu(metrix, incomeVector);
            income = vesu[incomeVector.length - 1];
            vesu[incomeVector.length - 1] = 0;
            d = betterCriterium(probabilityMatrix, q, vesu);
        } while (!Utility.isTwoVectorsEquals(d, prevSol));
        return d;
    }

    private Vector prepareProbabilityVector(Matrix probabilityMatrix, Matrix IncomeMatrix) {
        Vector q = new Vector(probabilityMatrix.getSizeState());

        for (int i = 0; i < q.getSizeState(); i++) {
            q.setStrSizeFoState(i, probabilityMatrix.getStrSizeForState(i));
        }

        for (int i = 0; i < probabilityMatrix.getSizeState(); i++) {
            for (int k = 0; k < probabilityMatrix.getStrSizeForState(i); k++) {
                for (int j = 0; j < probabilityMatrix.getSizeState(); j++) {
                    q.setValue(i, k, q.getValue(i, k) + probabilityMatrix.getValue(i, k, j) * IncomeMatrix.getValue(i, k, j));
                }
            }
        }
        return q;
    }

    private double[] findVesu(double[][] metrix, double[] income) {
        double[][] metrixForSystem = new double[income.length][income.length];

        for (int i = 0; i < income.length; i++) {

            for (int j = 0; j < income.length - 1; j++) {
                if (i == j)
                    metrixForSystem[i][j] = 1 - metrix[i][j];
                else
                    metrixForSystem[i][j] = -metrix[i][j];
            }
            metrixForSystem[i][income.length - 1] = 1;

        }
        double[] b = income;
        return PoissonElimination.lsolve(metrixForSystem, b);
    }

    public int[] betterCriterium(Matrix P, Vector q, double[] v) {
        int[] res = new int[P.getSizeState()];
        for (int i = 0; i < res.length; i++) {
            res[i] = maxStr(i, P, q, v);
        }
        return res;
    }

    public int maxStr(int state, Matrix P, Vector q, double[] v) {
        double[] kryteriy = new double[P.getStrSizeForState(state)];
        for (int i = 0; i < kryteriy.length; i++) {
            kryteriy[i] = getCriterium(state, i, P, q, v);
        }
        int strategy = 0;
        double max = kryteriy[0];
        for (int i = 1; i < kryteriy.length; i++) {
            if (kryteriy[i] > max) {
                max = kryteriy[i];
                strategy = i;
            }

        }
        return strategy;
    }

    public double getCriterium(int state, int stratagy, Matrix P, Vector q, double[] v) {
        double sum = 0;
        for (int j = 0; j < P.getSizeState(); j++) {
            sum += P.getValue(state, stratagy, j) * v[j];
        }
        return q.getValue(state, stratagy) + sum;
    }

    public int[] findMaxStrategyForEveryState(Vector q) {
        int[] d = new int[q.getSizeState()];
        for (int i = 0; i < q.getSizeState(); i++) {
            d[i] = Utility.findMax(q.getStrMasForState(i));
        }
        return d;
    }
}
