package khyou.graphqldemo.entity.cart;

import khyou.graphqldemo.entity.product.Product;

public record CartItem(String id, int quantity, Product product, Cart cart) {
}
