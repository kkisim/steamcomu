package steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import steam.model.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    // 추가적으로 필요한 메서드가 있다면 여기에 정의 가능
}
