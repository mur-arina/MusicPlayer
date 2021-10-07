package company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.File;

/** Class for controlling audio playing with some static methods */
public class AudioPlayer {

    private static PlayingThread audioThread;
    private static FloatControl volumeControl;
    private static MomentSlider momentSlider;

    private static JLabel timeViewer;
    private static JLabel nameViewer;

    private static Playlist playlist;

    private static int trackIdNow = -2;

    /**
     * set playlist for playing.
     * @param list Playlist for playing
     */
    public static void setPlaylist(Playlist list) {
        if (playlist == list) {
            return;
        }
        stop();
        playlist = list;
    }

    /**
     * Get instance of current playlist.
     * @return playlist active playlist instance
     */
    public static Playlist getPlaylist() {
        return playlist;
    }

    /**
     * Play audio from playlist.
     * @param  trackId  Track id in current playlist for playing.
     */
    public static void play(int trackId) {
        if (audioThread != null) {
            Clip clip = audioThread.getClip();
            clip.setFramePosition((int)(clip.getMicrosecondLength() / 1000));
            audioThread.killCurrent();
        }
        String track = AudioPlayer.getPlaylist().get(trackId);
        File wawFile = new File(track);
        nameViewer.setText(track);
        trackIdNow = trackId;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(wawFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            audioThread = new PlayingThread(clip, trackId);

            audioThread.start();

        } catch (Exception e) {}

    }

    /**
     * Stop playing current audio forever.
     */
    public static void stop() {
        if (audioThread == null) {
            return;
        }
        audioThread.killCurrent();
        try {
            audioThread.join();
        } catch (Exception ignored) {}

        audioThread = null;
    }

    /**
     * Stop playing current audio forever.
     */
    public static void pause() {
        if (audioThread == null) {
            return;
        }
        audioThread.stopCurrent();
    }

    /**
     * Remove pause.
     */
    public static void continuePlaying() {
        audioThread.continueCurrent();
    }

    /**
     * Set volume level.
     * @param  volume  Number in percents (0 to 100)
     */
    public static void setVolume(int volume) {
        if (volumeControl == null) {
            return;
        }
        float v = (float) volume;
        v /= 100;
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        volumeControl.setValue(v * (max - min) + min);
    }

    /**
     * Set moment for playing
     * @param  moment  moment in percents (from 0 to 100)
     */
    public static void setMoment(int moment) {
        if (audioThread == null) {
            return;
        }
        float k = (float) moment;
        k /= 100;
        Clip clip = audioThread.getClip();
        if (clip == null) {
            return;
        }
        float max = clip.getFrameLength();
        audioThread.setMoment((int)(max * k));
    }

    /**
     * Set slider for audio progress.
     * @param  slider  an instance of slider
     */
    public static void setMomentSlider(MomentSlider slider) {
        momentSlider = slider;
    }

    /**
     * Get progress slider
     * @return progress slider
     */
    public static MomentSlider getMomentSlider() {
        return momentSlider;
    }

    /**
     * setter for time label component
     * @param label JLabel to view time
     */
    public static void setTimeViewer(JLabel label) {
        timeViewer = label;
    }

    /**
     *
     * @return JLabel instance of view time.
     */
    public static JLabel getTimeViewer() {
        return timeViewer;
    }

    /**
     *
     * @param viewer JLabel to show active track name.
     */
    public static void setNameViewer(JLabel viewer) {
        nameViewer = viewer;
    }

    /**
     *  play next track, if now is last, play first */
    public static void playNext() {
        if (trackIdNow != -2) {
            audioThread.killCurrent();
            play((trackIdNow + 1) % playlist.size());
        }
    }

    /** play previous track, if now is first, play last */
    public static void playPrev() {
        if (trackIdNow != -2) {
            --trackIdNow;
            if (trackIdNow < 0) {
                trackIdNow = playlist.size() - 1;
            }
            audioThread.killCurrent();
            play(trackIdNow);
        }
    }

    /**
     * Returns audio progress in minutes and seconds.
     * @return Current progress of audio (format: 00:00/10:00 )
     */
    public static String getTimeString() {
        if (audioThread == null) {
            return "00:00/00:00";
        }
        Clip clip = audioThread.getClip();
        if (clip == null) {
            return "00:00/00:00";
        }
        /* current progress */
        long time = clip.getMicrosecondPosition() / 1000000;
        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);
        /* full time of track */
        long allTime = clip.getMicrosecondLength() / 1000000;
        int allMinutes = (int) (allTime / 60);
        int allSeconds = (int) (allTime % 60);

        return minutes + ":" + seconds + "/" + allMinutes + ":" + allSeconds;
    }

}
