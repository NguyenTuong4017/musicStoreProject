package musicstore.musicselling.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import musicstore.musicselling.Entity.*;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.CartItemRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;

@Controller
public class CartController {
    @Autowired
    SongRepository songRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Cart getOrCreateCart() {
        if (cartRepository.findByCartId(1L) == null) {
            Cart cart = new Cart();
            return cartRepository.save(cart);
        }
        return cartRepository.findByCartId(1L);

    }

    // add the song to the cart
    @GetMapping("/add-song-to-cart")
    public String addSongToCart(@RequestParam Long songId, HttpServletRequest request) {
        Cart cart = getOrCreateCart();
        Song song = songRepository.findBySongId(songId);
        CartItem item = new CartItem();
        item.setSong(song);
        cart.addCartItems(item);
        cartRepository.save(cart);
        System.out.println("Add success");

        String pageDomain = request.getHeader("Referer");
        String map = pageDomain.substring(pageDomain.lastIndexOf('/') + 1);

        return "redirect:/" + map;
    }

    // push the data of cart list to the cart page
    @GetMapping("/cart")
    public String songCart(Model model) {
        Cart cart = getOrCreateCart();
        List<Genre> genreList = genreRepository.findAll();
        double price = 0;
        List<Song> songItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getSong() != null) {
                songItems.add(cartItem.getSong());
                price = price + cartItem.getSong().getSongPrice();
            }
        }

        String totalPrice = String.format("%.2f", price);
        model.addAttribute("genreList", genreList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("songItems", songItems);

        return "cart";
    }

    // remove the song from cart
    @GetMapping("remove-song-from-cart")
    public String removeSongFromCart(@RequestParam Long songId) {
        Cart cart = getOrCreateCart();
        Set<CartItem> cartList = cart.getCartItems();

        CartItem itemToRemove = null;

        for (CartItem item : cartList) {
            if (item.getSong().getSongId().equals(songId)) {
                itemToRemove = item;

            }
        }
        if (itemToRemove != null) {
            cart.removeCartItems(itemToRemove);
            cartRepository.save(cart);

        }

        return "redirect:/cart";

    }

}
