import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface SongIterator {
    boolean hasNext();
    Song next();
}

interface Playlist {
    SongIterator createIterator();
    void addSong(Song song);
}

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

class PlaylistImpl implements Playlist {
    private List<Song> songs;

    public PlaylistImpl() {
        this.songs = new ArrayList<>();
    }

    @Override
    public SongIterator createIterator() {
        return new PlaylistIterator();
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    private class PlaylistIterator implements SongIterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < songs.size();
        }

        @Override
        public Song next() {
            if (hasNext()) {
                Song song = songs.get(index);
                index++;
                return song;
            } else {
                return null;
            }
        }
    }
}

public class Ex2 {
    public static void main(String[] args) {
        Playlist playlist = new PlaylistImpl();

        playlist.addSong(new Song("Song 1", "Artist 1"));
        playlist.addSong(new Song("Song 2", "Artist 2"));
        playlist.addSong(new Song("Song 3", "Artist 3"));

        SongIterator iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }
}
