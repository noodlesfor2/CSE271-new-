
public class Song {
    // **** INSTANCE PROPERTIES

    private String title;
    private String artist;
    private String albumName;
    private String duration;
    private int streams;
    private boolean favorite;

    // **** GETTERS & SETTERS

    /**
     * Returns the title of the song
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the song
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = capital(title);
    }

    /**
     * Returns the artist of the song
     * 
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song
     * 
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = capital(artist);
    }

    /**
     * Returns the name of the album
     * 
     * @return the albumName
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Sets the name of the album
     * 
     * @param albumName the albumName to set
     */
    public void setAlbumName(String albumName) {
        this.albumName = capital(albumName);
    }

    /**
     * Returns the duration of the song
     * 
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the song
     * 
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Returns the number of streams
     * 
     * @return the streams
     */
    public int getStreams() {
        return streams;
    }

    /**
     * Internally sets the number of times the song has been streamed.
     * 
     * @param streams the streams to set
     */
    private void setStreams(int streams) {
        this.streams = streams;
    }

    /**
     * Returns whether or not the song is a favorite.
     * 
     * @return the favorite
     */
    public boolean isFavorite() {
        return favorite;
    }

    /**
     * Sets whether or not the song is a favorite.
     * 
     * @param favorite the favorite to set
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    // **** CONSTRUCTORS

    // Workhorse
    public Song(String title, String artist, String albumName, String duration, int streams, boolean favorite) {

        this.setTitle(title);
        this.setArtist(artist);
        this.setAlbumName(albumName);
        this.setDuration(duration);
        this.setStreams(streams);
        this.setFavorite(favorite);
    }

    // Partial (everything but album name)

    public Song(String title, String artist, String duration, int streams, boolean favorite) {
        this(title, artist, "", duration, streams, favorite);
    }

    public Song(String[] arr) {
        this(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), "FAVORITE".equals(arr[5]));
    }

    // **** METHODS

    /**
     * Properly capitalizes the first letter of each word in a sentence.
     * 
     * @param str The input sentence
     * @return The newly capitalized sentence
     */
    private String capital(String str) {

        String newStr = "";
        String[] words = str.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (!(words[i].equals(""))) {
                newStr += words[i].substring(0, 1).toUpperCase();
                newStr += words[i].substring(1).toLowerCase();
                if (i + 1 != words.length) {
                    newStr += " ";
                }
            }
        }
        return newStr;
    }

    @Override
    public String toString() {
        if (this.isFavorite()) {
            String favStr = "favorited by you";
            return String.format("%s, %s (%s)%n%s%n%d plays, %s", this.getTitle(), this.getArtist(), this.getDuration(),
                    this.getAlbumName(), this.getStreams(), favStr);
        } else {
            return String.format("%s, %s (%s)%n%s%n%d plays", this.getTitle(), this.getArtist(), this.getDuration(),
                    this.getAlbumName(), this.getStreams());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song) {
            Song s = (Song) obj;

            return this.getArtist().equals(s.getArtist()) && this.getTitle().equals(s.getTitle())
                    && this.getDuration().equals(s.getDuration());
        }

        return false;
    }

    public void play() {
        this.setStreams(this.getStreams() + 1);
        System.out.printf("%s by %s is now playing.", this.getTitle(), this.getArtist());
    }
}
