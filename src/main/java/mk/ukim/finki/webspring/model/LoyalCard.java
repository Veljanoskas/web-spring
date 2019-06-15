package mk.ukim.finki.webspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class LoyalCard implements Serializable {

    @Id
    public String barcode;
    public String vendor;

    public LoyalCard() {
    }


}
