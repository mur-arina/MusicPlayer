package company;

import java.io.*;
import java.util.*;

/** Singleton class implements Audio database */
public class AudioBase {

    /** All audio files array */
    ArrayList<String> paths;
    /** Folders with audios */
    ArrayList<String> folders;
    /**  Hash Map with playlists - key is playlist name */
    HashMap<String, Playlist> playlists;

    static AudioBase instance;

    private AudioBase() {
        paths = new ArrayList<>();
        folders = new ArrayList<>();
        playlists = new HashMap<>();
        createPlaylist("All");
        String maybeMusic = System.getProperty("user.home") + File.separator + "Music";
        File file = new File(maybeMusic);
        if (file.exists()) {
            addFolder(maybeMusic, true);
        }
    }

    /** Adds new folder in base
     * @param folder path to the folder */
    public void addFolder(String folder) {
        addFolder(folder, false);
    }

    /**
     * Add all audios from folder in base
     * @param folder Folder
     * @param recursion Recursive search flag (true - search recursively)
     */
    public void addFolder(String folder, boolean recursion) {
        if (folders.contains(folder)) {
           return;
        }
        folders.add(folder);
        findAudios(folder, recursion);
    }

    /**
     * find audio in folder with recursive search or without (recursion flag)
     * @param path Folder path
     * @param recursion Recursion flag
     */
    public void findAudios(String path, boolean recursion) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        if (arrFiles != null) {
            for (File file : arrFiles) {
                String name = file.getAbsolutePath();
                if (file.isFile() && name.contains(".wav")) {
                    if (!paths.contains(name)) {
                        paths.add(name);
                        if (!playlists.get("All").contains(name)) {
                            playlists.get("All").add(name);
                        }
                    }
                } else if (recursion && file.isDirectory()) {
                    findAudios(file.getAbsolutePath(), recursion);
                }
            }
        }
    }

    public ArrayList<String> getAudioList() {
        return paths;
    }

    /**
     * Create playlist
     * @param name Playlist name
     */
    public void createPlaylist(String name) {
        playlists.put(name, new Playlist(name));
    }

    /**
     * Delete playlist
     * @param name Playlist name to be deleted
     */
    public void deletePlaylist(String name) {
        playlists.remove(name);
    }

    /**
     * Add audio in playlist by playlist name
     * @param name Playlist name
     * @param path Audio path
     */
    public void addInPlaylist(String name, String path) {
        if (playlists.get(name).contains(path)) {
            return;
        }
        playlists.get(name).add(path);
    }

    /**
     * Get playlists from base.
     * @return Playlists dict (Playlist name is key)
     */
    HashMap<String, Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Get singleton of Base
     * @return instance
     */
    public static AudioBase getAudioBase() {
        if (instance == null) {
            instance = new AudioBase();
        }
        return instance;
    }

    /**
     * Save all data in file
     * @param writer
     */
    public void saveData(BufferedWriter writer) {

        try {
            writer.write(paths.size() + "\n");
            for (var path : paths) {
                writer.write(path.trim() + "\n");
            }
            writer.write(folders.size() + "\n");
            for (var folder : folders) {
                writer.write(folder.trim() + "\n");
            }
            writer.write(playlists.size() + "\n");
            for (var playlist : playlists.entrySet()) {
                writer.write(playlist.getKey().trim() + "\n");
                writer.write(playlist.getValue().size() + "\n");
                for (var track : playlist.getValue()) {
                    writer.write(track + "\n");
                }
            }
            writer.close();
        } catch (Exception ignored) {

        }


    }

    /**
     * restore data from file
     * @param reader
     */
    public void restoreData(BufferedReader reader) {
        try {
            int pathCount = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < pathCount; ++i) {
                String path = reader.readLine().trim();
                File file = new File(path);
                if (file.exists() && !paths.contains(path)) {
                    paths.add(path);
                }
            }
            int foldersCnt = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < foldersCnt; ++i) {
                String path = reader.readLine().trim();
                File file = new File(path);
                if (file.exists() && !folders.contains(path)) {
                    folders.add(path);
                }
            }

            int playlistsCnt = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < playlistsCnt; ++i) {
                String name = reader.readLine().trim();
                boolean created = false;
                if (!AudioBase.getAudioBase().getPlaylists().containsKey(name)) {
                    AudioBase.getAudioBase().createPlaylist(name);
                    created = true;
                }
                int cnt = Integer.parseInt(reader.readLine().trim());
                for (int j = 0; j < cnt; ++j) {
                    String path = reader.readLine().trim();
                    File file = new File(path);
                    if (file.exists()) {
                        AudioBase.getAudioBase().addInPlaylist(name, path);
                    }
                }
            }
            reader.close();

        } catch (Exception ignored) {
        }
    }
}
