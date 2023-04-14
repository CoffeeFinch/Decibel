package com.techelevator.dao;
import com.techelevator.model.Playlist;
import com.techelevator.model.Song;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcPlaylistDao implements PlaylistDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcPlaylistDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }
    @Override
    public List <Playlist> getSongsInPlaylist(int userId) {
       List <Playlist> playlistOfSongs = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        Playlist playlist = new Playlist();

        String sql = " SELECT playlist.playlist_id, playlist_name " +
                " FROM playlist_users " +
                " JOIN playlist ON playlist_users.playlist_id = playlist.playlist_id " +
                " WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {

            playlist.setPlaylistId(results.getInt("playlist_id"));
            playlist.setPlaylistName(results.getString("playlist_name"));


            String sql2 = "SELECT song.song_id, title, artist, genre.genre_id, genre_name, mood_name, mood.mood_id, song_playlist.playlist_id " +
                    "FROM song " +
                    "JOIN song_genre ON song_genre.song_id = song.song_id " +
                    "JOIN genre ON song_genre.genre_id = genre.genre_id " +
                    "JOIN song_mood ON song_mood.song_id =song.song_id " +
                    "JOIN mood ON song_mood.mood_id = mood.mood_id " +
                    "JOIN song_playlist ON song_playlist.song_id = song.song_id " +
                    "WHERE playlist_id = ?";
            SqlRowSet results2 = jdbcTemplate.queryForRowSet(sql2, playlist.getPlaylistId());
            while (results2.next()){
                songs.add(mapRowToSong(results2));
            }
            playlist.setSongs(songs);
        }
        playlistOfSongs.add(playlist);

        return playlistOfSongs;
    }
@Override
public void savePlaylist(Playlist playlist, int userId){

}

    private Playlist mapRowToPlaylist(SqlRowSet row) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistName(row.getString("playlist_name"));
        playlist.setPlaylistId(row.getInt("playlist_id"));


        return playlist;

    }
    private Song mapRowToSong(SqlRowSet row) {
        Song song = new Song();

        song.setArtist( row.getString("artist"));
        song.setGenre(row.getString("genre_name"));
        song.setMood(row.getString("mood_name"));
        song.setTitle(row.getString("title"));
        song.setMoodId(row.getInt("mood_id"));
        song.setSongId(row.getInt("song_id"));


        return song;
    }
}
