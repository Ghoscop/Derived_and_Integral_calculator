package Controller;

import Model.Calculadora;
import Model.CalculadoraPolinomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private Calculadora calculadora;
    private MediaPlayer mediaPlayer;

    @FXML
    private Pane paneTitulo, paneBtnMode;
    @FXML
    private Label labelResultado;
    @FXML
    private TextField tfNumero;
    @FXML
    private ChoiceBox<String> escolha;
    @FXML
    private ImageView btnFechar, btnMinimizar,btnAslovin;
    @FXML
    private ImageView imgMode;
    @FXML
    private Pane fundoPane;

    private double x, y;
    private boolean isLightMode = true;

    private final List<Double> valor = new ArrayList<>();
    private int fase = 0;

    public MainController() {
        this.calculadora = new Calculadora();
    }

    @FXML
    public void inicio(Stage stage, MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        setDarkMode();
        changeMode();

        System.out.println("Iniciando o controlador");
        stage.setTitle("Sonic Calculator");
        stage.setResizable(false);

        paneTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        paneTitulo.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnAslovin.setOnMouseClicked(mouseEvent -> {

        });


        btnFechar.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimizar.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        escolha.getItems().addAll("Integral", "Derivada");
        escolha.setValue(null);
    }

    @FXML
    public void changeMode() {
        paneBtnMode.setOnMouseClicked(mouseEvent -> {
            isLightMode = !isLightMode;
            String path;
            Media media;

            if (isLightMode) {
                path = getClass().getResource("/Songs/McLovinsong.mp3").toExternalForm();
                mediaPlayer.setVolume(100);
                setLightMode();
            } else {
                path = getClass().getResource("/Songs/lol.mp3").toExternalForm();
                mediaPlayer.setVolume(0.4);
                setDarkMode();
            }

            media = new Media(path);

            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        });

    }

    @FXML
    private void setLightMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/DarkMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/LightMode.css").toExternalForm());

        Image image = new Image(getClass().getResource("/Imagens/dark.png").toExternalForm());
        imgMode.setImage(image);

        Image fundoImage = new Image(getClass().getResource("/Imagens/Mclovin.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    @FXML
    private void setDarkMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/LightMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/DarkMode.css").toExternalForm());

        Image image = new Image(getClass().getResource("/Imagens/light.png").toExternalForm());
        imgMode.setImage(image);

        Image fundoImage = new Image(getClass().getResource("/Imagens/Asta.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    @FXML
    private void noSinal(MouseEvent event) {
        String simbolo = ((Pane) event.getSource()).getId().replace("btn", "");

        if (simbolo.equals("Igual")) {
            if (!tfNumero.getText().isEmpty()) {
                String expressao = tfNumero.getText().trim();

                calculadora.setFuncao(expressao);
                System.out.println("Iniciando cálculo: opção selecionada - " + escolha.getValue());
                realizarCalculo();
            } else {
                System.out.println("Erro:  está incompleta");
                labelResultado.setText("Complete a função!");
            }
        }
    }

    private void realizarCalculo() {
        String opcao = escolha.getValue();
        String resultado = "";

        switch (opcao) {
            case "Derivada" -> resultado = calculadora.calcularDerivada();
            case "Integral" -> resultado = calculadora.calcularIntegral();
            default -> {
                System.out.println("Erro: Nenhuma operação selecionada");
                labelResultado.setText("Selecione uma operação");
                return;
            }
        }

        labelResultado.setText(resultado);
        System.out.println(resultado);

    }
}
