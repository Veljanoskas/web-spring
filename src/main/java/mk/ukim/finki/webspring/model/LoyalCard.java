package mk.ukim.finki.webspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class LoyalCard implements Serializable {

    @Id
    public String barcode;

    @DBRef
    public Vendor vendor;


    public LoyalCard() {
    }

    public LoyalCard(String barcode, Vendor vendor) {
        this.barcode = barcode;
        this.vendor = vendor;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
