package com.thecodereveal.shopease.specification;

import com.thecodereveal.shopease.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ProductSpecification {

    public static Specification<Product> hasCategoryId(UUID categorId){
        return  (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"),categorId);
    }

    public static Specification<Product> hasCategoryTypeId(UUID typeId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryType").get("id"),typeId);
    }

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) ->
                name == null || name.isEmpty()
                        ? cb.conjunction()
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> hasCategoryType(String categoryTypeName) {
        return (root, query, cb) -> {
            if (categoryTypeName == null || categoryTypeName.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("categoryType").get("name")), categoryTypeName.toLowerCase());
        };
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return cb.conjunction();
            if (minPrice != null && maxPrice != null)
                return cb.between(root.get("price"), minPrice, maxPrice);
            if (minPrice != null)
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, cb) -> {
            if (color == null || color.isEmpty()) {
                return cb.conjunction();
            }
            query.distinct(true);

            // Join sang productVariants
            var variantJoin = root.join("productVariants");
            return cb.equal(cb.lower(variantJoin.get("color")), color.toLowerCase());
        };
    }

    public static Specification<Product> hasSize(String size) {
        return (root, query, cb) -> {
            if (size == null || size.isEmpty()) {
                return cb.conjunction();
            }
            query.distinct(true);

            var variantJoin = root.join("productVariants");
            return cb.equal(cb.lower(variantJoin.get("size")), size.toLowerCase());
        };
    }
}
