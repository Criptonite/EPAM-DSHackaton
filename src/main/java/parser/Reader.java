package parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Reader {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        readFile("C:\\Hackaton\\data\\Users.csv");
    }

    private static void readFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 7870; ++i) {
                System.out.println(i + "\t" + reader.readLine());
            }
        }
    }
}
