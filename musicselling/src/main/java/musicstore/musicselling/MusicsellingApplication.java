package musicstore.musicselling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;

@SpringBootApplication
public class MusicsellingApplication {
	@Autowired
	private SongRepository songRepository;

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private GenreRepository genreRepository;

	public static void main(String[] args) {
		SpringApplication.run(MusicsellingApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// create artists
			Artist song_tung_mtp = new Artist("Sơn Tùng M-TP", "Vietnamese singer-songwriter and actor.");
			Artist tlinh = new Artist("tlinh", "tlinh is a Vietnamese independent singer and rapper.");
			Artist rpt_mck = new Artist("RPT MCK", "RPT MCK is a Vietnamese singer and rapper based in Ha Noi.");
			Artist wxrdie = new Artist("Wxrdie", "Wxrdie is a Vietnamese rapper based in Ha Noi.");
			Artist andree_right_hand = new Artist("Andree Right Hand",
					"Andree Right Hand is a Vietnamese rapper and businessman based in Ho Chi Minh city.");
			Artist amee = new Artist("AMEE", "AMEE is a Vietnamese singer based in Ho Chi Minh city.");
			Artist binh_gold = new Artist("Bình Gold", "Bình Gold is a Vietnamese rapper based in Ha Noi.");
			Artist two_pillz = new Artist("2pillz", "2pillz is a music producer and DJ based in Ho Chi Minh city");

			// create songs
			Song neu_luc_do = new Song("nếu lúc đó", 3794587,
					"/static/song-image/neu-luc-do.jpg");
			Song chay_ngay_di = new Song("Chạy Ngay Đi", 234879825,
					"/static/song-image/chay-ngay-di.jpg");
			Song hay_trao_cho_anh = new Song("Hãy Trao Cho Anh", 2387532,
					"/static/song-image/hay-trao-cho-anh.jpg");
			Song chi_mot_dem_nua_thoi = new Song("Chỉ Một Đêm Nữa Thôi (feat. tlinh)", 782643,
					"/static/song-image/99-phan-tram.jpg");
			Song em_la_chau_bau = new Song("Em Là Châu Báu", 298374,
					"/static/song-image/em-la-chau-bau.jpg");
			Song hai_nghin_cau_hoi_vi_sao = new Song("2000 câu hỏi vì sao", 29375935, "/static/song-image/mongmee.jpg");
			Song mong_yu = new Song("MỘNG YU", 89237592, "/static/song-image/mongmee.jpg");
			Song mien_mong_mi = new Song("Miền Mộng Mị", 89275982, "/static/song-image/mongmee.jpg");
			Song nhac_anh = new Song("Nhạc Anh (feat. Wxrdie)", 89723985, "/static/song-image/nhac-anh.jpg");
			Song em_iu = new Song("Em iu (feat. Wxrdie, Bình Gold & 2pillz)", 9872985, "/static/song-image/em-iu.jpg");

			// create genres
			Genre pop = new Genre("Pop");
			Genre rnb = new Genre("R&B");
			Genre hiphopRap = new Genre("Hip-Hop/Rap");

			// save genre
			// genreRepository.save(pop);
			// genreRepository.save(rnb);
			// genreRepository.save(hiphopRap);

			// add genre to song
			neu_luc_do.addGenres(rnb);
			neu_luc_do.addGenres(pop);

			chay_ngay_di.addGenres(hiphopRap);
			chay_ngay_di.addGenres(rnb);

			hay_trao_cho_anh.addGenres(hiphopRap);
			hay_trao_cho_anh.addGenres(rnb);

			chi_mot_dem_nua_thoi.addGenres(hiphopRap);

			em_la_chau_bau.addGenres(hiphopRap);
			em_la_chau_bau.addGenres(pop);

			hai_nghin_cau_hoi_vi_sao.addGenres(pop);

			mong_yu.addGenres(rnb);
			mong_yu.addGenres(pop);

			mien_mong_mi.addGenres(pop);
			mien_mong_mi.addGenres(rnb);

			nhac_anh.addGenres(hiphopRap);

			em_iu.addGenres(hiphopRap);

			// save songs
			// songRepository.save(neu_luc_do);
			// songRepository.save(chay_ngay_di);
			// songRepository.save(hay_trao_cho_anh);
			// songRepository.save(chi_mot_dem_nua_thoi);
			// songRepository.save(em_la_chau_bau);
			// songRepository.save(hai_nghin_cau_hoi_vi_sao);
			// songRepository.save(mong_yu);
			// songRepository.save(mien_mong_mi);
			// songRepository.save(nhac_anh);
			// songRepository.save(em_iu);

			// add song to artist
			tlinh.addSong(neu_luc_do);
			tlinh.addSong(em_la_chau_bau);
			tlinh.addSong(chi_mot_dem_nua_thoi);

			rpt_mck.addSong(chi_mot_dem_nua_thoi);
			rpt_mck.addSong(chi_mot_dem_nua_thoi);
			rpt_mck.addSong(mong_yu);

			song_tung_mtp.addSong(chay_ngay_di);
			song_tung_mtp.addSong(hay_trao_cho_anh);

			wxrdie.addSong(em_iu);
			wxrdie.addSong(nhac_anh);

			andree_right_hand.addSong(nhac_anh);
			andree_right_hand.addSong(em_iu);

			amee.addSong(mien_mong_mi);
			amee.addSong(mong_yu);
			amee.addSong(hai_nghin_cau_hoi_vi_sao);

			binh_gold.addSong(em_iu);

			two_pillz.addSong(neu_luc_do);
			two_pillz.addSong(em_iu);

			// save artist
			// artistRepository.save(song_tung_mtp);
			// artistRepository.save(tlinh);
			// artistRepository.save(rpt_mck);
			// artistRepository.save(wxrdie);
			// artistRepository.save(andree_right_hand);
			// artistRepository.save(amee);
			// artistRepository.save(binh_gold);
			// artistRepository.save(two_pillz);
		};
	}
}
