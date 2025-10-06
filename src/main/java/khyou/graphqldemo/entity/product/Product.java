package khyou.graphqldemo.entity.product;

import khyou.graphqldemo.entity.SearchResult;

public interface Product extends SearchResult {
    String getId();
    String getName();
    double getPrice();
    ProductType getProductType();
}