package domain;

public class User {

    private int id;
    private int reputation;
    private String creationDate;
    private String displayName;
    private String emailHash;
    private String lastAccessDate;
    private String websiteUrl;
    private String location;
    private int age;
    private String aboutMe;
    private int views;
    private int upVotes;
    private int downVotes;
    private int[] responsibility;

    public User() {
    }

    public User(int id, int reputation, String creationDate, String displayName, String emailHash, String lastAccessDate, String websiteUrl,
        String location, int age, String aboutMe, int views, int upVotes, int downVotes, int[] responsibility) {
        this.id = id;
        this.reputation = reputation;
        this.creationDate = creationDate;
        this.displayName = displayName;
        this.emailHash = emailHash;
        this.lastAccessDate = lastAccessDate;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.age = age;
        this.aboutMe = aboutMe;
        this.views = views;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.responsibility = responsibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReputation() {
        return reputation;
    }

    public int[] getResponsibility() {
        return responsibility;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public void setResponsibility(int[] responsibility)
    {
        this.responsibility = new int[responsibility.length];
        System.arraycopy(responsibility, 0, this.responsibility, 0, responsibility.length);
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

}
