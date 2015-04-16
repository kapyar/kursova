public class Main {

    public static void main(String[] args) {

        Solver sys = new Solver(10, 12, 3);
        int[] d = new IterationMethod().execute(sys.setProbMatrix(), sys.setIncomeMatrix(150, 12));
        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i] + "    ");
        }

    }

}
