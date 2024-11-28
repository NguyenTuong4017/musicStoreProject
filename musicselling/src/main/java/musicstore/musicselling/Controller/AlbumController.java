package musicstore.musicselling.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Cart;
import musicstore.musicselling.Entity.CartItem;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Entity.UserEntity;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.CartItemRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;
import musicstore.musicselling.Repository.UserRepository;
import musicstore.musicselling.Service.CustomUserDetail;

@Controller
public class AlbumController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

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

    // Return to the album page
    @GetMapping("/album")
    public String showAllAlbum(Model model) {
        // Create model
        List<Album> albumList = albumRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;

        // Create cart
        Cart cart = getOrCreateCart();

        // Add the user information to navbar
        if (cart != null) {
            isLoggedIn = true;
            UserEntity user = userRepository.findByUserId(cart.getUser().getUserId());
            model.addAttribute("user", user);

            // Get the item id to check if it's in the cart
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getAlbum() != null) {
                    itemId.add(cartItem.getAlbum().getAlbumId());
                }

            }
            for (Long id : itemId) {
                System.out.println("Album id: " + id);
            }
            model.addAttribute("itemId", itemId);
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("albumList", albumList);
        model.addAttribute("genreList", genreList);
        return "album";
    }

    // Show all songs of an album
    @GetMapping("/album/songs")
    public String showAllSongsOfAlbum(Model model, Long albumId) {
        Album album = albumRepository.findByAlbumId(albumId);
        List<Song> songList = album.getSongs();
        List<Genre> genreList = genreRepository.findAll();
        List<Long> itemId = new ArrayList<>();

        boolean isLoggedIn = false;
        // Create cart
        Cart cart = getOrCreateCart();
        // Add the user information to navbar
        if (cart != null) {
            isLoggedIn = true;
            UserEntity user = userRepository.findByUserId(cart.getUser().getUserId());
            model.addAttribute("user", user);
            // Get the item id to check if it's in the cart
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getAlbum() != null) {
                    itemId.add(cartItem.getAlbum().getAlbumId());
                }

            }
            model.addAttribute("itemId", itemId);
        }
        model.addAttribute("isLoggedIn", isLoggedIn);

        model.addAttribute("album", album);
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);

        return "song-of-album";
    }

}
