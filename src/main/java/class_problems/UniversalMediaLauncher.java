package class_problems;
interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int counter = 1001;
    private final String fileId;

    public MediaFile() {
        this.fileId = "MF-" + counter++;
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {
    private final String title;

    public AudioFile(String title) {
        super();
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;
        return "Playing audio: " + title + " from " + String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public String pause() {
        return "Audio paused: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private final String showName;
    private final int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber + " of " + showName + " from second " + fromSecond;
    }

    @Override
    public String pause() {
        return "Podcast paused: " + showName;
    }
}

public class UniversalMediaLauncher {
    public static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        Podcast p = new Podcast("Tech Talk", 12);
        System.out.println(p.play());

        Playable ref = a; // Upcasting
        launchAll(new Playable[]{ ref, p });
    }
}