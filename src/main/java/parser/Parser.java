package parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;

public class Parser {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(false);
        SAXParser sp = parserFactory.newSAXParser();

        try (BufferedWriter csvFile = new BufferedWriter(new FileWriter(new File("C:\\Hackaton\\data\\Users.csv")))) {
            csvFile.write("id,reputation,creationDate,displayName,lastAccessDate,websiteUrl,location,aboutMe,views,upVotes,downVotes,accountId\r\n");
            UserParser parser = new UserParser(0, Integer.MAX_VALUE);
            try {
                sp.parse(new File("C:\\Hackaton\\data\\Users.xml"), parser);
            } catch (SAXException ex) {

            }
            List<User> users = parser.getUsers();
            users.forEach(user -> {
                try {
                    csvFile.write(user + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
