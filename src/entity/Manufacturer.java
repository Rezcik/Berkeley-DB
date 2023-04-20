package entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Manufacturer {
    @PrimaryKey(sequence = "manufacturer_pk")
    private int id;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = Product.class, name = "product_id_for_manufacturer")
    private int productId;
    private String name;
    private String address;

    public Manufacturer(int id, String name, String address, int productId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}
