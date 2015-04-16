public class Matrix {
	private int sizeState;
	double[][][] matrix;

	public Matrix(int[] sizeSTR, int sizeState) {
		this.sizeState = sizeState;
		matrix = new double[sizeState][][];
		for (int i = 0; i < sizeState; i++) {
			matrix[i] = new double[sizeSTR[i]][sizeState];
		}
	}

	public int getStrSizeForState(int state) {
		return matrix[state].length;
	}

	public int getSizeState() {
		return sizeState;
	}

	public void setSizeState(int sizeState) {
		this.sizeState = sizeState;
	}

	public double[][][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][][] matrix) {
		this.matrix = matrix;
	}

	public void setValue(int s1, int strategy, int s2, double value) {
		matrix[s1][strategy][s2] = value;
	}

	public double getValue(int s1, int strategy, int s2) {
		return matrix[s1][strategy][s2];
	}

	public double[] getVectorForStateStrategy(int state, int strategy) {
		return matrix[state][strategy];
	}

	@Override
	public String toString() {
		String forReturn = "";
		for (int i = 0; i < sizeState; i++) {
			forReturn += i + "\t";
			for (int k = 0; k < getStrSizeForState(i); k++) {
				for (int j = 0; j < sizeState; j++) {
					forReturn += matrix[i][k][j] + " ";
				}

				forReturn += "\n";
			}
		}
		return forReturn;
	}

}
