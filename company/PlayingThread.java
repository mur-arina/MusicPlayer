package company;

import javax.sound.sampled.Clip;

/**
 * Class for controlling playing
 */
public class PlayingThread extends Thread {

    private int trackId;   // actual track id (in current playlist) which is playing
    boolean isStopped;     // pause
    boolean isKilled;      // stop playing and finish thread

    /**
     *  https://docs.oracle.com/javase/8/docs/api/index.html?javax/sound/sampled/Clip.html
     *  */
    Clip clip;

    /**
     * @return current clip instance
     */
    public Clip getClip() {
        return clip;
    }

    /**
     *
     * @param clip Clip instance
     * @param trackId TrackID for playing
     */
    public PlayingThread(Clip clip, int trackId) {
        super();
        this.clip = clip;
        isStopped = false;
        this.trackId = trackId;
    }

    /**
     * Run Thread method
     */
    public void run() {
        try {
            clip.setFramePosition(0);

            PlayPanel.setState(true);
            while (!isKilled && clip.getFramePosition() < clip.getFrameLength()) {
                Thread.sleep(10);
                if (!isStopped) {
                    clip.start();  // play audio
                    while (!isKilled && !isStopped && clip.getFramePosition() < clip.getFrameLength()) {
                        Thread.sleep(10);
                        /* Set moment at slider */
                        float now = (float) clip.getFramePosition() / (float)clip.getFrameLength();
                        AudioPlayer.getMomentSlider().setValue((int)(100 * now));
                        /* Set time progress for user */
                        AudioPlayer.getTimeViewer().setText(AudioPlayer.getTimeString());
                    }
                    clip.stop(); // stop playing
                }
            }
            clip.close();
            /* play next audio */
            if (!isKilled) {
                Playlist playlist = AudioPlayer.getPlaylist();
                int idx = (trackId + 1) % playlist.size();
                AudioPlayer.play(idx);
            }

        } catch (Exception ign) {
            ign.printStackTrace();
        }
    }

    /** Change playing moment
     * @param moment Frame position */
    void setMoment(int moment) {
        clip.setFramePosition(moment);
        AudioPlayer.getTimeViewer().setText(AudioPlayer.getTimeString());
    }

    /**
     * Pause
     */
    public void stopCurrent() {
        PlayPanel.setState(false);
        isStopped = true;
    }

    /**
     * Pause
     */
    public void continueCurrent() {
        PlayPanel.setState(true);
        isStopped = false;
    }


    /**
     * Stop playing
     */
    public void killCurrent() {
        isKilled = true;
    }
}
