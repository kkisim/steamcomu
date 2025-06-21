package steam.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    private String userId;
    private ObjectId gameId; // 🔄 수정됨
    private String comment;
    private double rating;
    private String createdAt;

    public Review() {}

    public Review(String userId, String gameId, String comment, double rating, String createdAt) {
        this.userId = userId;
        this.gameId = new ObjectId(gameId); // 문자열 → ObjectId 변환
        this.comment = comment;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    // Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId != null ? gameId.toHexString() : null;
    }

    public void setGameId(String gameIdStr) {
        this.gameId = new ObjectId(gameIdStr);
    }

    public ObjectId getGameIdObject() {
        return gameId;
    }

    public void setGameId(ObjectId gameId) {
        this.gameId = gameId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
