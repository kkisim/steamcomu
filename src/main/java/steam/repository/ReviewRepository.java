package steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import steam.model.Review;
import org.bson.types.ObjectId;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByGameId(ObjectId gameId);
    List<Review> findByUserId(String userId);
}
