package mk.ukim.finki.webspring.repository;

import mk.ukim.finki.webspring.model.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor,String> {
}
