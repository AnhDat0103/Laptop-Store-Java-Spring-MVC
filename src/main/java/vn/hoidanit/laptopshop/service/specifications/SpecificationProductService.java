package vn.hoidanit.laptopshop.service.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.dto.ProductFilterRequestDTO;

public class SpecificationProductService {

    private static Specification<Product> filterWhereFactories(List<String> factories) {
        return (root, query, criteriaBuilder) -> root.get(Product_.FACTORY).in(factories);
    }

    private static Specification<Product> filterWhereTarget(List<String> target) {
        return (root, query, criteriaBuilder) -> root.get(Product_.TARGET).in(target);
    }

    private static Specification<Product> filterPriceBetween(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), min, max);
    }

    public static Specification<Product> getSpecForProductPage(ProductFilterRequestDTO productFilterRequestDTO) {
        Specification<Product> specification = null;
        Specification<Product> tmp = null;

        if (productFilterRequestDTO.getFactory() != null && productFilterRequestDTO.getFactory().isPresent()
                && !productFilterRequestDTO.getFactory().isEmpty()) {
            specification = filterWhereFactories(productFilterRequestDTO.getFactory().get());
            tmp = specification != null ? Specification.where(specification).and(tmp) : tmp;
        }

        if (productFilterRequestDTO.getTarget() != null && productFilterRequestDTO.getTarget().isPresent()
                && !productFilterRequestDTO.getTarget().isEmpty()) {
            List<String> target = productFilterRequestDTO.getTarget() != null
                    ? productFilterRequestDTO.getTarget().get()
                    : null;
            specification = filterWhereTarget(target);
            tmp = specification != null ? Specification.where(specification).and(tmp) : tmp;
        }

        if (productFilterRequestDTO.getPrice() != null && productFilterRequestDTO.getPrice().isPresent()
                && !productFilterRequestDTO.getPrice().isEmpty()) {
            specification = buildPriceSpecification(productFilterRequestDTO.getPrice().get());
            tmp = specification != null ? Specification.where(specification).and(tmp) : tmp;

        }
        return tmp;

    }

    private static Specification<Product> buildPriceSpecification(List<String> price) {
        Specification<Product> combinedSpec = Specification.where(null); // disconjunction
        for (String p : price) {
            double min = 0;
            double max = 0;

            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "duoi-10-trieu":
                    min = 1;
                    max = 10000000;
                    break;
                case "10-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    break;
                case "15-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    break;
                case "tren-20-trieu":
                    min = 20000000;
                    max = 200000000;
                    break;
            }

            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = filterPriceBetween(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        return combinedSpec;
    }

}
