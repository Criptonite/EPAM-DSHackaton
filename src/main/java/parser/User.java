package parser;

public class User {
    private final int id;
    private final int reputation;
    private final String creationDate;
    private final String displayName;
    private final String lastAccessDate;
    private final String websiteUrl;
    private final String location;
    private final String aboutMe;
    private final int views;
    private final int upVotes;
    private final int downVotes;
    private final int accountId;

    public User(int id, int reputation, String creationDate, String displayName, String lastAccessDate, String websiteUrl, String location, String aboutMe, int views, int upVotes, int downVotes, int accountId) {
        this.id = id;
        this.reputation = reputation;
        this.creationDate = creationDate;
        this.displayName = displayName;
        this.lastAccessDate = lastAccessDate;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.aboutMe = aboutMe;
        this.views = views;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return id + "," +
                reputation + "," +
                "\"" + creationDate + "\"," +
                "\"" + displayName + "\"," +
                "\"" + lastAccessDate + "\"," +
                "\"" + websiteUrl + "\"," +
                "\"" + location + "\"," +
                "\"" + aboutMe + "\"," +
                views + "," +
                upVotes + "," +
                downVotes + "," +
                accountId;
    }


}
