package ru.geekbrains.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.market.model.Product;


public class ProductSpecifications {

    private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Product> titleLike(String title) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }

    private static Specification<Product> categoryIdIs(Long categoryId) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null); //такое создание говорит, что если нет ограничений она не работает
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.getFirst("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.getFirst("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }

        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            String title = params.getFirst("title");
            spec = spec.and(ProductSpecifications.titleLike(title));
        }

        if (params.containsKey("category_id") && !params.getFirst("category_id").isBlank()) {
            Long categoryId = Long.parseLong(params.getFirst("category_id"));
            spec = spec.and(ProductSpecifications.categoryIdIs(categoryId));
        }

        return spec;
    }
}
