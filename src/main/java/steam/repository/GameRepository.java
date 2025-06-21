package steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import steam.model.Game;
import org.bson.types.ObjectId;

@Repository
public interface GameRepository extends MongoRepository<Game, ObjectId> {
}

