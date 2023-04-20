package entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class ProductCatalog {
    @PrimaryKey(sequence = "manufacturer_pk")
    private int id;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = Product.class, name = "product")
    private int productId;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = Catalog.class, name = "catalog")
    private int catalogId;

    public ProductCatalog(int id, int productId, int catalogId) {
        this.id = id;
        this.productId = productId;
        this.catalogId = catalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
}
