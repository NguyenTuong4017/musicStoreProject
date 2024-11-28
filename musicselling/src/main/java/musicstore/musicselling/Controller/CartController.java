package musicstore.musicselling.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import musicstore.musicselling.Repository.UserRepository;
import musicstore.musicselling.Service.CustomUserDetail;

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
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Create cart for user
    private Cart getOrCreateCart() {
        // Get the current user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        // Get the current user id
        Long userId = userDetail.getId();

        Cart cart = cartRepository.findCartByUserUserId(userId);

        // Check if cart is not exist then create a new one
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findByUserId(userId));
            cartRepository.save(cart);
        }

        return cart;
    }

    // add the song to the cart
    @GetMapping("/add-song-to-cart")
    public String addSongToCart(@RequestParam Long songId, HttpServletRequest request) {
        // create cart
        Cart cart = getOrCreateCart();
        // get the song by its id
        Song song = songRepository.findBySongId(songId);
        // create new cart item for holding the song
        CartItem item = new CartItem();
        item.setSong(song);

        // add item to cart then save
        cart.addCartItems(item);
        cartRepository.save(cart);
        System.out.println("Add success");

        // get the page domain to keep at the same page when click add to cart button
        String pageDomain = request.getHeader("Referer");
        String map = pageDomain.substring(pageDomain.lastIndexOf('/') + 1);

        return "redirect:/" + map;
    }

    // add album to cart
    @GetMapping("/add-album-to-cart")
    public String addAlbumToCart(@RequestParam Long albumId, HttpServletRequest request) {
        // create cart
        Cart cart = getOrCreateCart();

        // find album by its id
        Album album = albumRepository.findByAlbumId(albumId);

        // create new cart item for holding the album
        CartItem item = new CartItem();

        // set the item as the album
        item.setAlbum(album);

        // add the item to cart then save it
        cart.addCartItems(item);
        cartRepository.save(cart);

        // get the page domain to keep at the same page when click add to cart button
        String pageDomain = request.getHeader("Referer");
        String map = pageDomain.substring(pageDomain.lastIndexOf('/') + 1);

        return "redirect:/" + map;

    }

    // push the data of cart list to the cart page
    @GetMapping("/cart")
    public String songCart(Model model) {
        // create cart
        Cart cart = getOrCreateCart();

        // get the genre list for the navbat
        List<Genre> genreList = genreRepository.findAll();

        // create an item list to show all the items in the cart page
        Set<CartItem> itemList = cart.getCartItems();

        // create an item id list for checking if the item is in the cart
        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;

        double price = 0;

        // caculate the total price in the cart
        for (CartItem item : cart.getCartItems()) {
            price = price + item.getCartItemPrice();
        }

        // Add the user information to navbar
        if (cart != null) {
            isLoggedIn = true;
            UserEntity user = userRepository.findByUserId(cart.getUser().getUserId());
            model.addAttribute("user", user);

            // Get the item id to check if it's in the cart
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getSong() != null) {
                    itemId.add(cartItem.getSong().getSongId());
                }

            }
            model.addAttribute("itemId", itemId);
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        // format the price to 0.00 form
        String totalPrice = String.format("%.2f", price);
        model.addAttribute("itemList", itemList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    // remove items from cart by item id
    @GetMapping("remove-item-from-cart")
    public String removeItemFromCart(@RequestParam Long itemId) {

        // create cart
        Cart cart = getOrCreateCart();

        // get all the items in the cart
        Set<CartItem> cartList = cart.getCartItems();

        CartItem itemToRemove = null;

        // find the item to remove by comparing the id
        for (CartItem item : cartList) {
            if (item.getCartItemId().equals(itemId)) {
                itemToRemove = item;
                break;
            }
        }

        // if the item to remove is found then remove it from the cart database
        if (itemToRemove != null) {
            cart.removeCartItems(itemToRemove);
            cartRepository.save(cart);

        }

        return "redirect:/cart";

    }

}
