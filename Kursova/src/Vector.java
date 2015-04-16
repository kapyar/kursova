public class Vector {

	private int sizeState;
	double[][] matrix;

	public Vector(int sizeState) {
		this.sizeState = sizeState;
		matrix = new double[sizeState][];
	}

	public void setStrSizeFoState(int state, int size) {
		matrix[state] = new double[size];
	}

	public void setValue(int s, int strategy, double value) {

		matrix[s][strategy] = value;
	}

	public double getValue(int s, int strategy) {
		return matrix[s][strategy];
	}

	double[] getStrMasForState(int state) {
		return matrix[state];
	}

	public int getSizeState() {
		return sizeState;
	}

	public void setSizeState(int sizeState) {
		this.sizeState = sizeState;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

}
