package steam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @Field("reviewId")
    private String reviewId;

    @Field("userId")
    private String userId;

    @Field("content")
    private String content;

    @Field("createdAt")
    private String createdAt;

    public Comment() {}

    public Comment(String reviewId, String userId, String content, String createdAt) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
