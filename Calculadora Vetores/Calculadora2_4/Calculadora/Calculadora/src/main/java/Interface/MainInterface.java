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
        // Carrega o arquivo FXML que define a interface gráfica
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/heitor_e_matheus/calculadora/MainInterface.fxml"));
        Scene scene = new Scene(loader.load());  // Cria a cena com o FXML carregado


        // Configura a mídia (música de fundo)
        String path = getClass().getResource("/Songs/sa2.mp3").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5); // Ajuste o volume se necessário
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproduz indefinidamente
        mediaPlayer.play();

        // Configura a cena e o palco (janela)
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Sonic Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/IconSonic.png")));

        // Obtém o controlador e passa o palco e mediaPlayer
        MainController controller = loader.getController();
        controller.inicio(stage, mediaPlayer);


        // Exibe a janela
        stage.show();
    }
}