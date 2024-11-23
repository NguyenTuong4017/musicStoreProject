package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
