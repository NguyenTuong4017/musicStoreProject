package musicstore.musicselling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Repository.AlbumRepository;
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

	@Autowired
	private AlbumRepository albumRepository;

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
			Artist wokeup = new Artist("WOKEUP", "WOKEUP is a music producer and DJ based in Ho Chi Minh city");

			// create songs

			// tlinh
			Song tinh_yeu_co_nghia_la_gi = new Song("tình yêu có nghĩa là gì?", 1195976,
					"/static/song-image/neu-luc-do.jpg", 2.99f);
			Song nguoi_dep_ngu = new Song("người đẹp ngủ", 4599404, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song nguoi_dien = new Song("người điên", 2998403, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song kho_bau_danh_roi = new Song("kho báu đánh rơi", 1958082, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song ai = new Song("ái", 2581533, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song lam_lanh_chua_tinh = new Song("làm lành chữa tình", 4942651, "/static/song-image/neu-luc-do.jpg",
					2.99f);
			Song the_gioi_than_tien = new Song("thế giới thần tiên", 2692935, "/static/song-image/neu-luc-do.jpg",
					2.99f);
			Song nhung_dom_sang = new Song("những đốm sáng", 4078994, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song nu_sieu_anh_hung = new Song("nữ siêu anh hùng", 2471825, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song ghe_iu_dau_cua_em_oi = new Song("ghế iu dấu của em ơi", 4165388, "/static/song-image/neu-luc-do.jpg",
					2.99f);
			Song neu_luc_do = new Song("nếu lúc đó", 2357101, "/static/song-image/neu-luc-do.jpg", 2.99f);
			Song ket_thuc_mo_dau = new Song("kết thúc = mở đầu", 1725881, "/static/song-image/neu-luc-do.jpg", 2.99f);

			// amee
			Song mong_yu = new Song("MỘNG YU", 89237592, "/static/song-image/mongmee.jpg", 2.99f);
			Song cuoc_goi_luc_nua_dem = new Song("Cuộc gọi lúc nửa đêm", 2735687, "/static/song-image/mongmee.jpg",
					2.99f);
			Song beautiful_nightmare = new Song("Beautiful nightmare (interlude)", 78235,
					"/static/song-image/mongmee.jpg", 2.99f);
			Song mien_mong_mi = new Song("Miền Mộng Mị", 89275982, "/static/song-image/mongmee.jpg", 2.99f);
			Song hai_nghin_cau_hoi_vi_sao = new Song("2000 câu hỏi vì sao", 29375935, "/static/song-image/mongmee.jpg",
					2.99f);

			// son tung mtp
			Song chay_ngay_di = new Song("Chạy Ngay Đi", 234879825,
					"/static/song-image/chay-ngay-di.jpg", 2.99f);
			Song hay_trao_cho_anh = new Song("Hãy Trao Cho Anh", 2387532,
					"/static/song-image/hay-trao-cho-anh.jpg", 2.99f);

			// mck
			Song chi_mot_dem_nua_thoi = new Song("Chỉ Một Đêm Nữa Thôi (feat. tlinh)", 782643,
					"/static/song-image/99-phan-tram.jpg", 2.99f);
			Song em_la_chau_bau = new Song("Em Là Châu Báu", 298374,
					"/static/song-image/em-la-chau-bau.jpg", 2.99f);

			// andree right hand
			Song nhac_anh = new Song("Nhạc Anh (feat. Wxrdie)", 89723985, "/static/song-image/nhac-anh.jpg", 2.99f);
			Song em_iu = new Song("Em iu (feat. Wxrdie, Bình Gold & 2pillz)", 9872985, "/static/song-image/em-iu.jpg",
					2.99f);

			// create genres
			Genre pop = new Genre("Pop");
			Genre rnb = new Genre("R&B");
			Genre hiphopRap = new Genre("Hip-Hop/Rap");

			// create albums
			Album mongMee = new Album("MỘNGMEE - EP", "/static/song-image/mongmee.jpg", 12.99f);
			Album ai_album = new Album("ái", "/static/song-image/neu-luc-do.jpg", 12.99f);

			// save genre
			genreRepository.save(pop);
			genreRepository.save(rnb);
			genreRepository.save(hiphopRap);

			// add genre to song
			tinh_yeu_co_nghia_la_gi.addGenres(pop);
			tinh_yeu_co_nghia_la_gi.addGenres(rnb);

			nguoi_dep_ngu.addGenres(pop);
			nguoi_dep_ngu.addGenres(rnb);

			nguoi_dien.addGenres(pop);
			nguoi_dien.addGenres(rnb);

			kho_bau_danh_roi.addGenres(pop);
			kho_bau_danh_roi.addGenres(rnb);

			ai.addGenres(pop);
			ai.addGenres(rnb);

			lam_lanh_chua_tinh.addGenres(pop);
			lam_lanh_chua_tinh.addGenres(rnb);

			the_gioi_than_tien.addGenres(pop);
			the_gioi_than_tien.addGenres(rnb);

			nhung_dom_sang.addGenres(pop);
			nhung_dom_sang.addGenres(rnb);

			nu_sieu_anh_hung.addGenres(pop);
			nu_sieu_anh_hung.addGenres(rnb);

			ghe_iu_dau_cua_em_oi.addGenres(pop);
			ghe_iu_dau_cua_em_oi.addGenres(rnb);

			neu_luc_do.addGenres(pop);
			neu_luc_do.addGenres(rnb);

			ket_thuc_mo_dau.addGenres(pop);
			ket_thuc_mo_dau.addGenres(rnb);

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

			cuoc_goi_luc_nua_dem.addGenres(pop);

			beautiful_nightmare.addGenres(pop);

			// save songs
			songRepository.save(tinh_yeu_co_nghia_la_gi);
			songRepository.save(nguoi_dep_ngu);
			songRepository.save(nguoi_dien);
			songRepository.save(kho_bau_danh_roi);
			songRepository.save(ai);
			songRepository.save(lam_lanh_chua_tinh);
			songRepository.save(the_gioi_than_tien);
			songRepository.save(nhung_dom_sang);
			songRepository.save(nu_sieu_anh_hung);
			songRepository.save(ghe_iu_dau_cua_em_oi);
			songRepository.save(neu_luc_do);
			songRepository.save(ket_thuc_mo_dau);

			songRepository.save(mong_yu);
			songRepository.save(cuoc_goi_luc_nua_dem);
			songRepository.save(beautiful_nightmare);
			songRepository.save(mien_mong_mi);
			songRepository.save(hai_nghin_cau_hoi_vi_sao);

			songRepository.save(chay_ngay_di);
			songRepository.save(hay_trao_cho_anh);

			songRepository.save(chi_mot_dem_nua_thoi);
			songRepository.save(em_la_chau_bau);

			songRepository.save(nhac_anh);
			songRepository.save(em_iu);

			// add songs to album
			mongMee.addSong(mong_yu);
			mongMee.addSong(cuoc_goi_luc_nua_dem);
			mongMee.addSong(beautiful_nightmare);
			mongMee.addSong(mien_mong_mi);
			mongMee.addSong(hai_nghin_cau_hoi_vi_sao);

			ai_album.addSong(tinh_yeu_co_nghia_la_gi);
			ai_album.addSong(nguoi_dep_ngu);
			ai_album.addSong(nguoi_dien);
			ai_album.addSong(kho_bau_danh_roi);
			ai_album.addSong(ai);
			ai_album.addSong(lam_lanh_chua_tinh);
			ai_album.addSong(the_gioi_than_tien);
			ai_album.addSong(nhung_dom_sang);
			ai_album.addSong(nu_sieu_anh_hung);
			ai_album.addSong(ghe_iu_dau_cua_em_oi);
			ai_album.addSong(neu_luc_do);
			ai_album.addSong(ket_thuc_mo_dau);

			// add song to artist
			tlinh.addSong(tinh_yeu_co_nghia_la_gi);
			tlinh.addSong(nguoi_dep_ngu);
			tlinh.addSong(nguoi_dien);
			tlinh.addSong(kho_bau_danh_roi);
			tlinh.addSong(ai);
			tlinh.addSong(lam_lanh_chua_tinh);
			tlinh.addSong(the_gioi_than_tien);
			tlinh.addSong(nhung_dom_sang);
			tlinh.addSong(nu_sieu_anh_hung);
			tlinh.addSong(ghe_iu_dau_cua_em_oi);
			tlinh.addSong(neu_luc_do);
			tlinh.addSong(ket_thuc_mo_dau);

			tlinh.addSong(em_la_chau_bau);

			rpt_mck.addSong(chi_mot_dem_nua_thoi);
			rpt_mck.addSong(em_la_chau_bau);
			rpt_mck.addSong(mong_yu);

			song_tung_mtp.addSong(chay_ngay_di);
			song_tung_mtp.addSong(hay_trao_cho_anh);

			andree_right_hand.addSong(nhac_anh);
			andree_right_hand.addSong(em_iu);

			amee.addSong(mien_mong_mi);
			amee.addSong(mong_yu);
			amee.addSong(hai_nghin_cau_hoi_vi_sao);
			amee.addSong(cuoc_goi_luc_nua_dem);
			amee.addSong(beautiful_nightmare);

			two_pillz.addSong(neu_luc_do);

			wokeup.addSong(ghe_iu_dau_cua_em_oi);

			// add feature artist to song
			chi_mot_dem_nua_thoi.addFeaturedArtist(tlinh);

			em_iu.addFeaturedArtist(wxrdie);
			em_iu.addFeaturedArtist(binh_gold);
			em_iu.addFeaturedArtist(two_pillz);
			// save artist
			artistRepository.save(song_tung_mtp);
			artistRepository.save(tlinh);
			artistRepository.save(rpt_mck);
			artistRepository.save(wxrdie);
			artistRepository.save(andree_right_hand);
			artistRepository.save(amee);
			artistRepository.save(binh_gold);
			artistRepository.save(two_pillz);

			// add albums to artist
			amee.addAlbum(mongMee);
			tlinh.addAlbum(ai_album);

			// save album
			albumRepository.save(mongMee);
			albumRepository.save(ai_album);

			// save songs again to link with album
			songRepository.save(tinh_yeu_co_nghia_la_gi);
			songRepository.save(nguoi_dep_ngu);
			songRepository.save(nguoi_dien);
			songRepository.save(kho_bau_danh_roi);
			songRepository.save(ai);
			songRepository.save(lam_lanh_chua_tinh);
			songRepository.save(the_gioi_than_tien);
			songRepository.save(nhung_dom_sang);
			songRepository.save(nu_sieu_anh_hung);
			songRepository.save(ghe_iu_dau_cua_em_oi);
			songRepository.save(neu_luc_do);
			songRepository.save(ket_thuc_mo_dau);

			songRepository.save(mong_yu);
			songRepository.save(cuoc_goi_luc_nua_dem);
			songRepository.save(beautiful_nightmare);
			songRepository.save(mien_mong_mi);
			songRepository.save(hai_nghin_cau_hoi_vi_sao);

			songRepository.save(chay_ngay_di);
			songRepository.save(hay_trao_cho_anh);

			songRepository.save(chi_mot_dem_nua_thoi);
			songRepository.save(em_la_chau_bau);

			songRepository.save(nhac_anh);
			songRepository.save(em_iu);

		};
	}
}
