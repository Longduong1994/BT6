package bt6.repository;

import bt6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% AND p.price BETWEEN :min AND :max")
    Page<Product> findAllByProductNameContainingAndPriceBetween(@Param("name") String name,
                                                                @Param("min") Double min,
                                                                @Param("max") Double max,
                                                                Pageable pageable);
    boolean existsByProductName(String name);
}
