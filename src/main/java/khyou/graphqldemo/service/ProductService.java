package khyou.graphqldemo.service;

import khyou.graphqldemo.entity.product.Clothing;
import khyou.graphqldemo.entity.product.Electronics;
import khyou.graphqldemo.entity.product.Product;
import khyou.graphqldemo.input.AddProductInput;
import khyou.graphqldemo.repository.Database;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final Sinks.Many<Product> productSink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    public Product getProduct(String productId) throws BadRequestException {
        return Database.getInstance().products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Product not found"));
    }

    public List<Product> getProducts() {
        return Database.getInstance().products;
    }

    public Product addProduct(AddProductInput addProductInput) throws BadRequestException {
        Product product = switch (addProductInput.getProductType()) {
            case ELECTRONICS -> {
                if (addProductInput.getWarrantyPeriod() == null) {
                    throw new BadRequestException("Warranty period is required");
                }
                yield new Electronics(
                        UUID.randomUUID().toString().substring(0, 5),
                        addProductInput.getName(),
                        addProductInput.getPrice(),
                        addProductInput.getWarrantyPeriod()
                );
            }
            case CLOTHING -> {
                if (addProductInput.getSize() == null) {
                    throw new BadRequestException("Size is required");
                }
                yield new Clothing(
                        UUID.randomUUID().toString().substring(0, 5),
                        addProductInput.getName(),
                        addProductInput.getPrice(),
                        addProductInput.getSize()
                );
            }
        };
        Database.getInstance().products.add(product);
        productSink.tryEmitNext(product);
        return product;
    }

    public Flux<Product> messageFlux(String productName) {
        return productSink.asFlux().filter(product ->
                productName == null || product.getName().contains(productName)
        );
    }
}
