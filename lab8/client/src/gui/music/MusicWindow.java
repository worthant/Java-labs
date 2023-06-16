package gui.music;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MusicWindow {

    private List<String> playlist = new ArrayList<>();

    private Stage stage;
    private MediaPlayer mediaPlayer;
    private int currentSongIndex = 0;

    public MusicWindow() {
        stage = new Stage();
        loadSongs();

        // UI Elements
        Label songLabel = new Label();
        Button playButton = new Button("Play");
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Prev");
        Button shuffleButton = new Button("Shuffle");
        Slider volumeSlider = new Slider(0, 1, 0.5);

        // Add event handlers
        playButton.setOnAction(event -> {
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                playButton.setText("Play");
            } else if (mediaPlayer != null) {
                mediaPlayer.play();
                playButton.setText("Pause");
            }
        });

        nextButton.setOnAction(event -> {
            currentSongIndex = (currentSongIndex + 1) % playlist.size();
            setSong(currentSongIndex, songLabel);
            playButton.setText("Pause");
        });

        prevButton.setOnAction(event -> {
            currentSongIndex = (currentSongIndex - 1 + playlist.size()) % playlist.size();
            setSong(currentSongIndex, songLabel);
            playButton.setText("Pause");
        });

        shuffleButton.setOnAction(event -> {
            Collections.shuffle(playlist);
            currentSongIndex = 0;
            setSong(currentSongIndex, songLabel);
            playButton.setText("Play");
        });

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.doubleValue());
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });

        VBox root = new VBox(10, songLabel, playButton, nextButton, prevButton, shuffleButton, volumeSlider);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Music Player");
    }

    private void loadSongs() {
        try {
            URI uri = getClass().getResource("/music").toURI();

            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                myPath = fileSystem.getPath("/music");
            } else {
                myPath = Paths.get(uri);
            }

            Stream<Path> walk = Files.walk(myPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext();){
                Path path = it.next();
                String fileName = path.getFileName().toString();
                if (fileName.endsWith(".mp3")) {
                    URL resource = getClass().getResource("/music/" + fileName);
                    if (resource != null) {
                        playlist.add(resource.toString());
                    }
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set playing song
     * @param index
     * @param songLabel
     */
    private void setSong(int index, Label songLabel) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String songPath = playlist.get(index);
        Media media = new Media(songPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        songLabel.setText("Playing: " + getFileName(playlist.get(index)));
    }

    /**
     * Parse absolute path of the file for Windows-like systems
     * @param fullPath absolute path
     * @return filename
     */
    private String getFileName(String fullPath) {
        try {
            URI uri = new URI(fullPath);
            String[] parts = uri.getPath().split("/");
            return parts[parts.length - 1];
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * show Music window
     */
    public void show() {
        stage.show();
    }
}

