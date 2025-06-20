package steam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "review_evaluations")
public class ReviewEvaluation {

    @Id
    private String id;

    @Field("reviewId")
    private String reviewId;

    @Field("userId")
    private String userId;

    @Field("evaluationType")
    private String evaluationType;

    @Field("comment")
    private String comment;

    @Field("createdAt")
    private String createdAt;

    public ReviewEvaluation() {}

    public ReviewEvaluation(String reviewId, String userId, String evaluationType, String comment, String createdAt) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.evaluationType = evaluationType;
        this.comment = comment;
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

    public String getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
