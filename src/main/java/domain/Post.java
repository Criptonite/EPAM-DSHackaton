package domain;

public class Post {

    private int id;
    private PostType postTypeId;
    private int parentId;
    private int acceptedState;
    private String creationDate;
    private int score;
    private int viewCount;
    private String body;
    private int ownerUserId;
    private String title;
    private int answerCount;
    private int commentCount;
    private int favoriteCount;
    private int[] sphere;

    public Post() {
    }

    public Post(int id, PostType postTypeId, int parentId, int acceptedState, String creationDate, int score, int viewCount,
        String body, int ownerUserId, String title, int answerCount, int commentCount, int favoriteCount, int[] sphere) {
        this.id = id;
        this.postTypeId = postTypeId;
        this.parentId = parentId;
        this.acceptedState = acceptedState;
        this.creationDate = creationDate;
        this.score = score;
        this.viewCount = viewCount;
        this.body = body;
        this.ownerUserId = ownerUserId;
        this.title = title;
        this.answerCount = answerCount;
        this.commentCount = commentCount;
        this.favoriteCount = favoriteCount;
        this.sphere = new int[sphere.length];
        System.arraycopy(sphere, 0, this.sphere, 0, sphere.length);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostType getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(PostType postTypeId) {
        this.postTypeId = postTypeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAcceptedState() {
        return acceptedState;
    }

    public void setAcceptedState(int acceptedState) {
        this.acceptedState = acceptedState;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int[] getSphere() {
        return sphere;
    }

    public void setSphere(int[] sphere) {
        this.sphere = new int[sphere.length];
        System.arraycopy(sphere, 0, this.sphere, 0, sphere.length);
    }
}
