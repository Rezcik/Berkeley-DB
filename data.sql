CREATE TABLE manufacturer (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  product_id INTEGER NOT NULL
);

CREATE TABLE product (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE catalog (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE product_catalog (
  id INTEGER PRIMARY KEY,
  product_id INTEGER NOT NULL,
  catalog_id INTEGER NOT NULL,
  FOREIGN KEY (product_id) REFERENCES product (id),
  FOREIGN KEY (catalog_id) REFERENCES catalog (id)
);