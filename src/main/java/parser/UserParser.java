package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserParser extends DefaultHandler {

    private final int limit;
    private final int from;
    private int count = 1;
    private final List<User> users = new ArrayList<>(7_620_000);

    public UserParser(int from, int limit) {
        this.limit = limit;
        this.from = from;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("row".equals(qName)) {
            int id = Integer.parseInt(attributes.getValue("Id"));
            int reputation = Integer.parseInt(attributes.getValue("Reputation"));
            String creationDate = parseOrDefault(attributes, "CreationDate", "");
            String displayName = parseOrDefault(attributes, "DisplayName", s -> s.replaceAll("[\\\\\r\n,'\"\\s&&[^ ]]+", ""), "");
            String lastAccessDate = parseOrDefault(attributes, "LastAccessDate", "");
            String websiteUrl = parseOrDefault(attributes, "WebsiteUrl", "");
            String location = parseOrDefault(attributes, "Location", s -> s.replaceAll("[\\\\\r\n,'\"\\s&&[^ ]]+", "").replace('\r', ' ').replace('\n', ' '), "");
            String aboutMe = parseOrDefault(attributes, "AboutMe", s -> s.replaceAll("[\\\\\r\n,'\"\\s&&[^ ]]+", "").replace('\r', ' ').replace('\n', ' '), "");
            int views = Integer.parseInt(attributes.getValue("Views"));
            int upVotes = Integer.parseInt(attributes.getValue("UpVotes"));
            int downVotes = Integer.parseInt(attributes.getValue("DownVotes"));
            int accountId = parseOrDefault(attributes, "AccountId", Integer::parseInt, Integer.MIN_VALUE);
            System.out.println(count++);
            if (count > from) {
                users.add(new User(id,
                                   reputation,
                                   creationDate,
                                   displayName,
                                   lastAccessDate,
                                   websiteUrl,
                                   location,
                                   aboutMe,
                                   views,
                                   upVotes,
                                   downVotes,
                                   accountId));
            }
            if (count > from + limit) {
                throw new SAXException("Limit");
            }
        }
    }


    private static String parseOrDefault(Attributes attributes, String name, String defaultValue) {
        String value = attributes.getValue(name);
        return value != null ? value : defaultValue;
    }

    private static <R> R parseOrDefault(Attributes attributes, String name, Function<String, R> mapper, R defaultValue) {
        String value = attributes.getValue(name);
        return value != null ? mapper.apply(value) : defaultValue;
    }

    public List<User> getUsers() {
        return users;
    }
}
