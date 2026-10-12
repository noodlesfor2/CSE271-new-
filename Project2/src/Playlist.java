import java.util.ArrayList;

public class Playlist {

    // **** INSTANCE PROPERTIES

    private static int uniqueNum = 100;

    private String uniqueTag;
    private String name;
    private boolean priv;
    private ArrayList<Song> songs;

    // **** GETTERS & SETTERS

    /**
     * @return the uniqueTag
     */
    public String getUniqueTag() {
        return uniqueTag;
    }

    /**
     * @param uniqueTag the uniqueTag to set
     */
    private void setUniqueTag(String name, int uniqueNum) {
        this.uniqueTag = createTag(this.getName(), uniqueNum);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the priv
     */
    public boolean isPriv() {
        return priv;
    }

    /**
     * @param priv the priv to set
     */
    public void setPriv(boolean priv) {
        this.priv = priv;
    }

    /**
     * @return the pList
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * @param pList the pList to set
     */
    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    // **** CONSTRUCTORS

    // Workhorse
    public Playlist(String name, boolean priv, ArrayList<Song> songs) {
        this.setName(name);
        this.setUniqueTag(this.getName(), uniqueNum);
        uniqueNum++;
        this.setPriv(priv);
        this.setSongs(songs);
    }

    // Partial (no given songs ArrayList)
    public Playlist(String name, boolean priv) {
        this(name, priv, new ArrayList<Song>());
    }

    // **** METHODS

    /**
     * Creates the uniqueTag for a Playlist using the name, and the uniqueNum.
     * 
     * @param str The name of the Playlist
     * @param num The unique number
     * @return The uniqueTag
     */
    private String createTag(String str, int num) {

        String newTag = "";
        String[] parts = str.split(" ");

        // Generating the unique tag based on name and unique number
        for (int i = 0; i < parts.length; i++) {
            if (!(parts[i].equals(""))) {
                newTag += parts[i].substring(0, 1).toUpperCase();
            }
        }
        newTag += Integer.toString(num);
        return newTag;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Playlist) {
            Playlist p = (Playlist) obj;

            return this.getName().equals(p.getName()) && this.getSongs().equals(p.getSongs());
        }
        return false;
    }

    @Override
    public String toString() {
        String songStr = "";

        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            if (s.isFavorite()) {
                songStr += String.format("%d. %s, %s (%s)%s%n", i + 1, s.getTitle(), s.getArtist(), s.getDuration(),
                        "<3");
            } else {
                songStr += String.format("%d. %s, %s (%s)%n", i + 1, s.getTitle(), s.getArtist(), s.getDuration());
            }
        }

        if (this.isPriv()) {
            return String.format("Private: %s (%d songs) #%s%n%s", this.getName(), this.getSongs().size(),
                    this.getUniqueTag(), songStr);
        } else {
            return String.format("Public: %s (%d songs) #%s%n%s", this.getName(), this.getSongs().size(),
                    this.getUniqueTag(), songStr);
        }
    }

    /**
     * Adds a song to the Playlist
     * 
     * @param s The song
     * @return True if the song was added, False if not
     */
    public boolean addSong(Song s) {
        if (this.getSongs().contains(s)) {
            return false;
        }

        this.getSongs().add(s);
        return true;
    }

    /**
     * Removes a song from the Playlist
     * 
     * @param s The song
     * @return True if the song was removed, False if not
     */
    public boolean removeSong(Song s) {
        if (this.getSongs().contains(s)) {
            this.getSongs().remove(s);
            return true;
        }
        return false;
    }
}
