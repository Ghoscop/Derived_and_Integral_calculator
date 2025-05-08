package Controller;

import Model.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private Calculadora calculadora;
    private MediaPlayer mediaPlayer;

    @FXML
    private Pane paneTitulo;
    @FXML
    private Label labelResultado;
    @FXML
    private TextField tfNumero;
    @FXML
    private ChoiceBox<String> escolha;
    @FXML
    private ImageView btnFechar, btnMinimizar;
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

        btnFechar.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimizar.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        escolha.getItems().addAll("Integral", "Derivada");
        escolha.setValue(null);
    }

    @FXML
    public void changeMode(ActionEvent event) {
        isLightMode = !isLightMode;
        if (isLightMode) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    @FXML
    private void setLightMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/DarkMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/LightMode.css").toExternalForm());

        Image image = new Image(getClass().getResource("/Imagens/dark.png").toExternalForm());
        imgMode.setImage(image);

        Image fundoImage = new Image(getClass().getResource("/Imagens/SonicPanel.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    @FXML
    private void setDarkMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/LightMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/DarkMode.css").toExternalForm());

        Image image = new Image(getClass().getResource("/Imagens/light.png").toExternalForm());
        imgMode.setImage(image);

        Image fundoImage = new Image(getClass().getResource("/Imagens/mygod.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    // Função para capturar coordenadas
    @FXML
    private void capturarnumero(MouseEvent event) {
        try {
            // Em vez de pegar o texto de labelResultado, capturamos diretamente os valores dos campos de texto
            if (fase == 0) {
                double numero = Double.parseDouble(tfNumero.getText().trim());  // Captura o valor
                valor.add(numero);
                fase++;
            }

            if (fase == 1) {
                labelResultado.setText("Escolha uma operação e clique em Calcular");
                System.out.println("Todas as coordenadas capturadas: " + valor);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: valor inválido");
            labelResultado.setText("Erro: Insira um número válido");
        }
    }

    @FXML
    private void noSinal(MouseEvent event) {
        String simbolo = ((Pane) event.getSource()).getId().replace("btn", "");

        if (simbolo.equals("Igual")) {
            // Verifica se todos os campos de coordenada estão preenchidos
            if (!tfNumero.getText().isEmpty()) {
                String expressao = tfNumero.getText().trim();  // Captura a expressão digitada pelo usuário

                // Passa a expressão simbólica para a calculadora
                calculadora.setFuncao(expressao);
                System.out.println("Iniciando cálculo: opção selecionada - " + escolha.getValue());
                realizarCalculo();
            } else {
                // Se algum campo estiver vazio
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

    }
}
