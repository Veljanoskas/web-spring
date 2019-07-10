package mk.ukim.finki.webspring.repository;

import mk.ukim.finki.webspring.model.LoyalCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LoyalCardRepository extends ReactiveMongoRepository<LoyalCard, String> {



}
