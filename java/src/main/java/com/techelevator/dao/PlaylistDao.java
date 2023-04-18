package com.techelevator.dao;

import com.techelevator.model.Playlist;

import java.util.List;

public interface PlaylistDao {

    public List <Playlist> getSongsInPlaylist(int userId);

    public void savePlaylist(Playlist playlist, int userId);

    public void deletePlaylistById(String playlistName, int userId);

    public void updatePlaylistByName(String playlistName, int playlistId);

    public void updatePlaylistImage(String playlistImage, int playlistId);

    public void updatePlaylist(Playlist playlist);

}
