package mk.ukim.finki.webspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LoyalCard {

    @Id
    private String barcode;
    private String vendor;

}
