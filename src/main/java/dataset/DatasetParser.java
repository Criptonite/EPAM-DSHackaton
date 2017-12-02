package dataset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetParser {

    private static List<Dataset> dataset = new ArrayList<>(800_000);

    public static void main(String[] args) throws IOException {
        readDatasets("Dataset.csv");
    }

    private static void readDatasets(String s) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0]);
                int[] vector = new int[parts.length-1];
                for (int i = 1; i < parts.length; i++) {
                    vector[i-1] = Integer.parseInt(parts[i]);
                }
                dataset.add(new Dataset(id, vector));
            }

        }
    }


}
