package Interface;

import Controller.MainController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainInterface extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/heitor_e_matheus/calculadora/MainInterface.fxml"));
        Scene scene = new Scene(loader.load());

        String path = getClass().getResource("/Songs/lol.mp3").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        mediaPlayer.setVolume(0.4);
        stage.setTitle("McLovin X Asta Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/iconAslovinPNG.png")));

        MainController controller = loader.getController();
        controller.inicio(stage, mediaPlayer);

        stage.show();
    }
}