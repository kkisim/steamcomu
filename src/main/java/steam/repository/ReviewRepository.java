package steam.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import steam.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByGameId(ObjectId gameId);
    List<Review> findByUserId(String userId);

    List<Review> findByGameIdOrderByCreatedAtDesc(ObjectId gameId);
    List<Review> findByGameIdOrderByRatingDesc(ObjectId gameId);
    List<Review> findByGameIdOrderByRatingAsc(ObjectId gameId);
    List<Review> findByGameIdOrderByUserIdAsc(ObjectId gameId); // 🔹 추가됨: 이름순 정렬
}
