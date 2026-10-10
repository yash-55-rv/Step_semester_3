import java.util.Arrays;

public class Playlist {
    // Requirements: Private internal tracking
    private final String[] songs;
    private int songCount;

    // Constructor to set fixed maximum size
    public Playlist(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Playlist size must be greater than 0.");
        }
        this.songs = new String[maxSize];
        this.songCount = 0;
    }

    // Requirement: Add a song to the internal array
    public void addSong(String songTitle) {
        if (songCount >= songs.length) {
            System.out.println("Playlist is full! Cannot add: " + songTitle);
            return;
        }
        this.songs[songCount] = songTitle;
        this.songCount++;
    }

    // Requirement: Return a safe copy of the songs added so far (Hint 1 & 2)
    public String[] getSongs() {
        // Arrays.copyOf creates a brand new array and copies only the songs added so far
        return Arrays.copyOf(this.songs, this.songCount);
    }

    // Requirement: Read-only count
    public int getSongCount() {
        return this.songCount;
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create playlist with space for 10 songs
        Playlist p = new Playlist(10);
        
        // Add two songs
        p.addSong("Song A");
        p.addSong("Song B");
        
        System.out.println("Song count: " + p.getSongCount()); // Output: 2

        // Get the safe copy
        String[] copy = p.getSongs();
        System.out.println("Original copy[0]: " + copy[0]); // Output: Song A

        // Attempt to "hack" or modify the array from the outside
        copy[0] = "Hacked";
        System.out.println("Modified copy[0]: " + copy[0]); // Output: Hacked

        // Verify the original playlist remains safe and untouched
        String[] verifiedSongs = p.getSongs();
        System.out.println("Playlist song[0] after hack attempt: " + verifiedSongs[0]); // Output: Song A
    }
}
