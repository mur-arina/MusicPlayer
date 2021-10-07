package company;

import java.util.ArrayList;

/**
 * Playlist - is ArrayList with additional information
 */
public class Playlist extends ArrayList<String> {
    String name;
    public Playlist(String name) {
        super();
        this.name = name;
    }

    /**
     * Get playlist name
     * @return Playlist name
     */
    String getName() {
        return name;
    }

}
