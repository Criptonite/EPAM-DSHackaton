package domain;

import dataset.UserVectorsProvider;

import java.util.Arrays;
import java.util.Objects;

public class Post {

    private int id;
    private PostType postTypeId;
    private Integer parentId;
    private AcceptedTypeId acceptedState;
    private String creationDate;
    private int score;
    private int viewCount;
    private String body;
    private Integer ownerUserId;
    private String title;
    private int answerCount;
    private int commentCount;
    private int favoriteCount;
    private int[] sphere;

    public Post(int id, PostType postTypeId, Integer parentId, Integer ownerUserId, int score, int viewCount) {
        this.id = id;
        this.postTypeId = postTypeId;
        this.parentId = parentId;
        this.ownerUserId = ownerUserId;
        this.score = score;
        this.viewCount = viewCount;
        sphere = new int[UserVectorsProvider.SECTIONS.size()];
    }

    public Post(int id, PostType postTypeId, int parentId, AcceptedTypeId acceptedState, String creationDate, int score, int viewCount,
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

    public void merge(int[] vector) {
        for (int i = 0; i < vector.length; ++i) {
            this.sphere[i] += vector[i];
        }
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

    public Integer getParentId() {
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

    public Integer getOwnerUserId() {
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
        this.sphere = Arrays.copyOf(sphere, sphere.length);
    }

    public void incSphere(int index, int value) {
        sphere[index] += value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post)o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTypeId=" + postTypeId +
                ", parentId=" + parentId +
                ", score=" + score +
                ", viewCount=" + viewCount +
                ", ownerUserId=" + ownerUserId +
                ", sphere=" + Arrays.toString(sphere) +
                '}';
    }
}
