package mk.ukim.finki.webspring.service.impl;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.Vendor;
import mk.ukim.finki.webspring.repository.LoyalCardRepository;
import mk.ukim.finki.webspring.repository.UserRepository;
import mk.ukim.finki.webspring.repository.VendorRepository;
import mk.ukim.finki.webspring.service.UserService;
import mk.ukim.finki.webspring.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository repo;

    @Override
    public Flux<Vendor> getAllVendors() {
        return repo.findAll();
    }

    @Override
    public Mono<Vendor> getVendorById(String id) {
        return repo.findById(id);
    }

    @Override
    public Mono<Vendor> saveVendor(Vendor vendor) {
         return repo.save(vendor);
    }

    @Override
    public Mono<Void> deleteVendor(String id) {
        return repo.deleteById(id);
    }
}
