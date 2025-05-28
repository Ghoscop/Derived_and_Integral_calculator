// Pacote da interface
package Interface;

// Importações necessárias
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

// Classe principal da aplicação, herda de Application (JavaFX)
public class MainInterface extends Application {

    // Método principal chamado automaticamente ao iniciar o JavaFX
    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o arquivo FXML da interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/heitor_e_matheus/calculadora/MainInterface.fxml"));
        Scene scene = new Scene(loader.load()); // Cria a cena a partir do FXML

        // Caminho da música inicial (modo escuro)
        String path = getClass().getResource("/Songs/lol.mp3").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media); // Cria o player de mídia
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Reproduz em loop infinito
        mediaPlayer.play(); // Começa a tocar a música

        // Define a transparência da cena (útil para janelas sem borda)
        scene.setFill(Color.TRANSPARENT);

        // Configurações da janela principal
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT); // Estilo sem barra de título
        stage.setResizable(false); // Impede redimensionamento
        mediaPlayer.setVolume(0.4); // Volume inicial

        // Define o título e o ícone da janela
        stage.setTitle("McLovin X Asta Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/iconAslovinPNG.png")));

        // Obtém o controlador da interface FXML e passa o stage e mediaPlayer para ele
        MainController controller = loader.getController();
        controller.inicio(stage, mediaPlayer); // Inicializa o controlador

        // Exibe a janela
        stage.show();
    }
}