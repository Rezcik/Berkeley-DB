package DA;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import entity.Manufacturer;
import entity.Product;

import java.util.List;

public class ManufacturerDA {
    PrimaryIndex<Integer, Manufacturer> id;
    SecondaryIndex<Integer, Integer, Product> productId;

    public ManufacturerDA(EntityStore store) throws DatabaseException {
        id = store.getPrimaryIndex(Integer.class, Manufacturer.class);
        productId = store.getSecondaryIndex(id, Integer.class, "product");
    }

    public List<Product> getProducts() {
        return this.productId.map().values().stream().toList();
    }
}
