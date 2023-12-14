package bt6.repository;

import bt6.entity.CartItem;
import bt6.entity.Order;
import bt6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Page<CartItem> findAllByStatusIsTrue(Pageable pageable);

    List<CartItem> findAllByOrder(Order order);

    List<CartItem> findAllByStatusIsTrue();
    CartItem findByProduct(Product product);
}
