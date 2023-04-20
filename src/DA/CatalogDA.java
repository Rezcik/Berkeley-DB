package DA;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.*;
import entity.Catalog;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class CatalogDA {
    PrimaryIndex<Integer, Catalog> id;
    SecondaryIndex<Integer, Integer, Product> productId;

    public CatalogDA(EntityStore store) throws DatabaseException {
        id = store.getPrimaryIndex(Integer.class, Catalog.class);
        productId = store.getSecondaryIndex(id, Integer.class, "product");
    }

    public void save(Catalog catalog) {
        Integer id = this.id.sortedMap().lastKey();
        id = id == null ? 0 : id + 1;
        catalog.setId(id);
        this.id.put(catalog);
    }

    public List<Catalog> getProductById(Integer productId) {
        EntityJoin<Integer, Product> join = new EntityJoin<>(this.productId);
        join.addCondition(this.productId, productId);
        try (ForwardCursor<Catalog> entities = join.entities()) {
            return StreamSupport.stream(entities.spliterator(), false).toList();
        } catch (DatabaseException exc) {
            return new ArrayList<>();
        }
    }

    public EntityCursor<Catalog> cursor(){
        return this.id.entities();
    }

    public List<Catalog> get() {
        return this.id.map().values().stream().toList();
    }

    public Catalog update(Catalog catalog) {
        return this.id.put(catalog);
    }

    public boolean delete(Integer id) {
        return this.id.delete(id);
    }
}