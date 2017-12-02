package domain;

public class Post {

    private int id;
    private PostType postTypeId;
    private int parentId;
    private AcceptedTypeId acceptedState;
    private String creationDate;
    private int score;
    private int viewCount;
    private String body;
    private int ownerUserId;
    private String title;
    private String[] tags;
    private int answerCount;
    private int commentCount;
    private int favoriteCount;

    public Post() {
    }

    public Post(int id, PostType postTypeId, int parentId, AcceptedTypeId acceptedState, String creationDate, int score, int viewCount,
        String body, int ownerUserId, String title, String[] tags, int answerCount, int commentCount, int favoriteCount) {
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
        this.tags = tags;
        this.answerCount = answerCount;
        this.commentCount = commentCount;
        this.favoriteCount = favoriteCount;
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

    public AcceptedTypeId getAcceptedState() {
        return acceptedState;
    }

    public void setAcceptedState(AcceptedTypeId acceptedState) {
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
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
}
