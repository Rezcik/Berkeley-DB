import DA.CatalogDA;
import DA.ProductDA;
import DA.ManufacturerDA;
import com.sleepycat.persist.EntityCursor;
import config.Database;
import entity.Catalog;
import entity.Product;
import entity.Manufacturer;

public class app {
    public static void Lab3() {
        Database.setup();
        ProductDA productDA = new ProductDA(Database.getStore());
        CatalogDA catalogDA = new CatalogDA(Database.getStore());
        ManufacturerDA manufacturerDA = new ManufacturerDA(Database.getStore());

        Product one = new Product(0, "1");

        productDA.save(one);

        one.setName("1");

        productDA.update(one);

        Manufacturer oneManufacture = new Manufacturer(0, "1", "1", one.getId());
        manufacturerDA.save(oneManufacture);

        System.out.println(manufacturerDA.getProducts());

        Catalog catalog = new Catalog(0, "1");
        catalogDA.save(catalog);
        System.out.println(catalogDA.getProductById(0));
    }

    public static void Lab4() {
        CatalogDA catalogDA = new CatalogDA(Database.getStore());
        try (EntityCursor<Catalog> entityCursor = catalogDA.cursor()) {
            for (Catalog catalog : entityCursor) {
                if (catalog.getId() == 1) {
                    System.out.println(catalog);
                }
            }
        }

        try (EntityCursor<Catalog> entityCursor = catalogDA.cursor()) {
            for (Catalog catalog : entityCursor) {
                if (catalog.getId() == 1) {
                    catalog.setName("111");
                    entityCursor.update(catalog);
                }
            }
        }
    }

    public static void main(String[] args) {
        Lab3();
        Lab4();
    }
}