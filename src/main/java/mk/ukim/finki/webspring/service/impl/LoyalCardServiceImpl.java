package mk.ukim.finki.webspring.service.impl;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.Vendor;
import mk.ukim.finki.webspring.repository.LoyalCardRepository;
import mk.ukim.finki.webspring.service.LoyalCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoyalCardServiceImpl implements LoyalCardService {

    @Autowired
    private LoyalCardRepository repository;

    @Override
    public Flux<LoyalCard> getAllLoyalCards() {
        return repository.findAll();
    }

    @Override
    public Mono<LoyalCard> getLoyalCardById(String id) {
        return repository.findById(id);
    }

    @Override
    public void saveLoyalCard(LoyalCard loyalCard) {
        repository.save(loyalCard).subscribe();
    }

    @Override
    public Mono<Void> deleteLoyalCard(String id) {
        return repository.deleteById(id);
    }
}
