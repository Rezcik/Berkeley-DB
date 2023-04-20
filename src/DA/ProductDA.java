package DA;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.*;
import entity.Catalog;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ProductDA {
    PrimaryIndex<Object, Product> id;
    SecondaryIndex<Integer, Object, Product> productId;

    public ProductDA(EntityStore store) throws DatabaseException {
        id = store.getPrimaryIndex(Object.class, Product.class);
        productId = store.getSecondaryIndex(id, Integer.class, "product");
    }

    public void save(Product catalog) {
        Object id = this.id.sortedMap().lastKey();
        id = id == null ? 0 : id + 1;
        catalog.setId(id);
        this.id.put(catalog);
    }

    public List<Product> get() {
        return this.id.map().values().stream().toList();
    }

    public Product update(Product product) {
        return this.id.put(product);
    }
}
