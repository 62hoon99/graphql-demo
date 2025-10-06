package khyou.graphqldemo.entity.product;

import lombok.Getter;

@Getter
public class Electronics implements Product {
    private final String id;
    private final String name;
    private final double price;
    private final String warrantyPeriod;
    private final ProductType productType = ProductType.ELECTRONICS;

    public Electronics(String id, String name, double price, String warrantyPeriod) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warrantyPeriod = warrantyPeriod;
    }
}
