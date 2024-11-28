package musicstore.musicselling;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import musicstore.musicselling.Config.WebSecurityConfig;
import musicstore.musicselling.Controller.UserController;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.CartItemRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;
import musicstore.musicselling.Repository.UserRepository;
import musicstore.musicselling.Service.UserDetailServiceImpl;

@WebMvcTest(UserController.class)
@Import(WebSecurityConfig.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailServiceImpl userDetailsService;

    @MockBean
    private SongRepository songRepository;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private AlbumRepository albumRepository;

    @Test
    @WithMockUser
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testSignUpPage() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testCreateNewUser() throws Exception {

        mockMvc.perform(post("/create-new-user")
                .param("username", "testuser")
                .param("password", "password123")
                .param("confirmPassword", "password123"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(model().attribute("message", "Sign up success"));
    }

}
