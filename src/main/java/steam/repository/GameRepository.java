package steam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import steam.model.Game;

import java.util.List;

public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByTitleContainingIgnoreCase(String keyword); // 🔍 제목 검색용
}
