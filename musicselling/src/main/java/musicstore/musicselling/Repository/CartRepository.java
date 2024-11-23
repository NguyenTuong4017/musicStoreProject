package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCartId(Long id);

}
