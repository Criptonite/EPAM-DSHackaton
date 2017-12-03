package dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PairsParser {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < 6; ++i) {
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Hackaton\\out\\pairs" + i + ".csv"))) {
                for (String curr = reader.readLine(); curr != null; curr = reader.readLine()) {
                    String[] parts = curr.split(",");
                    frequency.merge(parts[0].substring(1, parts[0].length() - 1), Integer.parseInt(parts[1]), (oldValue, newValue) -> oldValue + newValue);
                }
            }
        }
        frequency.entrySet()
                 .stream()
                 .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
                 .filter(entry -> !"class".equals(entry.getKey()))
                 .filter(entry -> !"list".equals(entry.getKey()))
                 .filter(entry -> !"array".equals(entry.getKey()))
                 .filter(entry -> !"true".equals(entry.getKey()))
                 .filter(entry -> !"false".equals(entry.getKey()))
                 .filter(entry -> !"arrays".equals(entry.getKey()))
                 .filter(entry -> !"username".equals(entry.getKey()))
                 .filter(entry -> !"false".equals(entry.getKey()))
                 .filter(entry -> !"google".equals(entry.getKey()))
                 .filter(entry -> !"redirect".equals(entry.getKey()))
                 .filter(entry -> !"localhost".equals(entry.getKey()))
                 .filter(entry -> !"stackoverflow".equals(entry.getKey()))
                 .filter(entry -> !"screenshot".equals(entry.getKey()))
                 .filter(entry -> !"workaround".equals(entry.getKey()))
                 .filter(entry -> !"newbie".equals(entry.getKey()))
                 .filter(entry -> !"everytime".equals(entry.getKey()))
                 .filter(entry -> !"blog".equals(entry.getKey()))
                 .filter(entry -> !"codethis".equals(entry.getKey()))
                 .filter(entry -> !"googled".equals(entry.getKey()))
                 .filter(entry -> !"abstract".equals(entry.getKey()))
                 .filter(entry -> !"appreciatedthanks".equals(entry.getKey()))
                 .filter(entry -> !"googling".equals(entry.getKey()))
                 .filter(entry -> !"codeand".equals(entry.getKey()))
                 .filter(entry -> !"codebut".equals(entry.getKey()))
                 .filter(entry -> !"foreach".equals(entry.getKey()))
                 .filter(entry -> !"�".equals(entry.getKey()))
//                 .filter(entry -> entry.getValue() > 1400)
                 .limit(100)

                 .forEachOrdered(entry -> System.out.println("\"" + entry.getKey() + "\","));

    }

}
