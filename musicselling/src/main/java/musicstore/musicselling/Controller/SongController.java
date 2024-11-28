package musicstore.musicselling.Controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Entity.UserEntity;
import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Cart;
import musicstore.musicselling.Entity.CartItem;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.CartItemRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;
import musicstore.musicselling.Repository.UserRepository;
import musicstore.musicselling.Service.CustomUserDetail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SongController {
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

    // Show all the songs in the song-list page
    @GetMapping("/song-list")
    public String showAllSong(Model model) {
        List<Song> songList = songRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        boolean isLoggedIn = false;

        List<Long> itemId = new ArrayList<>();
        Cart cart = getOrCreateCart();

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
        System.out.println("The user is " + isLoggedIn);
        model.addAttribute("isLoggedIn", isLoggedIn);

        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);
        return "song-list";

    }

    // Find the song by the search value contains the name of artist or the name of
    // the song
    @GetMapping("/find")
    public String searchResult(@RequestParam(value = "searchString", required = false) String searchString, Model model,
            HttpServletRequest request) {

        // Find the song by search the artist name
        List<Song> songList = songRepository.findByArtists_ArtistNameContainingIgnoreCase(searchString);

        // Get the genres to show on the navbar
        List<Genre> genreList = genreRepository.findAll();
        // Create cart
        Cart cart = getOrCreateCart();

        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;

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
        // if the song list found by artist name is not null then return to the
        // search-result page with that song list
        if (!songList.isEmpty()) {

            model.addAttribute("isLoggedIn", isLoggedIn);
            model.addAttribute("songList", songList);
            model.addAttribute("genreList", genreList);
            return "search-result";
        }
        // find the song by song name
        List<Song> songList2 = songRepository.findBySongNameContaining(searchString);
        // if the song list found by song name is not empty then return the search
        // result page with that song list
        if (!songList2.isEmpty()) {

            model.addAttribute("isLoggedIn", isLoggedIn);

            model.addAttribute("songList", songList2);
            model.addAttribute("genreList", genreList);
            return "search-result";
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("genreList", genreList);

        // return to the error page if the song list found by 2 methods above is empty
        model.addAttribute("message", "No result.");
        return "error";

    }

    // Sort the songs by their genres
    @GetMapping("/song-genre")
    public String songOfGenre(Model model, Long genreId) {
        // Get genres to add in the navbar
        List<Genre> genreList = genreRepository.findAll();

        // find the genre by the genre id
        Genre genre = genreRepository.findByGenreId(genreId);

        // get songs by genre
        Set<Song> songList = genre.getSongs();

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
                if (cartItem.getSong() != null) {
                    itemId.add(cartItem.getSong().getSongId());
                }

            }
            model.addAttribute("itemId", itemId);
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("itemId", itemId);
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);
        return "song-of-genre";
    }

    // Return user to the page of one song
    @GetMapping("/song-page")
    public String songPage(Model model, Long songId) {
        // Get genres to add in the navbar
        List<Genre> genreList = genreRepository.findAll();

        // Get the song by its id
        Song song = songRepository.findBySongId(songId);

        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;
        Cart cart = getOrCreateCart();

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

        model.addAttribute("song", song);
        model.addAttribute("genreList", genreList);

        return "song-page";
    }

}