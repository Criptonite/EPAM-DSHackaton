package dataset;

public class Dataset {

    private int id;
    private int[] vector;

    public Dataset() {
    }

    public Dataset(int id, int[] vector) {
        this.id = id;
        this.vector = new int[vector.length];
        System.arraycopy(vector, 0, this.vector, 0, vector.length);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = new int[vector.length];
        System.arraycopy(vector, 0, this.vector, 0, vector.length);
    }
}
