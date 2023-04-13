package com.techelevator.dao;

import com.techelevator.model.Playlist;

import java.util.List;

public interface PlaylistDao {

    public List <Playlist> getSongsInPlaylist(int userId);

    public void savePlaylist(Playlist playlist, int userId);


}
