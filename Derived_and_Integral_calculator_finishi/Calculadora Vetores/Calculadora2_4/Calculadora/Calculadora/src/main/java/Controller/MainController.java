// Pacote da classe
package Controller;

// Importações das classes necessárias
import Model.Calculadora;
import Model.CalculadoraPolinomial;
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


// Classe principal do controlador da interface
public class MainController {

    // Instância do modelo de cálculo
    private Calculadora calculadora;
    //Reprodutor de som
    private MediaPlayer mediaPlayer;

    // Referências aos elementos da interface
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

    // Variáveis auxiliares para movimentação da janela
    private double x, y;
    // Flag para o modo claro/escuro
    private boolean isLightMode = true;

    // Lista de valores usados para cálculos
    private final List<Double> valor = new ArrayList<>();
    // Variável de controle de fases
    private int fase = 0;

    // Construtor da classe inicializando a calculadora
    public MainController() {
        this.calculadora = new Calculadora();
    }

    // Método que inicia a interface
    @FXML
    public void inicio(Stage stage, MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;

        // Inicializa no modo escuro e define a troca de tema
        setDarkMode();
        changeMode();

        // Configurações da janela
        System.out.println("Iniciando o controlador");
        stage.setTitle("AstaXMcLovin Calculator");
        stage.setResizable(false);

        paneTitulo.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
        // Fecha o aplicativo
        btnFechar.setOnMouseClicked(mouseEvent -> stage.close());
        // Minimiza a janela
        btnMinimizar.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        // Adiciona as opções ao menu suspenso
        escolha.getItems().addAll("Integral", "Derivada", "Derivada 2º Ordem");
        escolha.setValue(null);
    }

    // Alterna entre modo claro e escuro ao clicar no botão de tema
    @FXML
    public void changeMode() {
        paneBtnMode.setOnMouseClicked(mouseEvent -> {
            isLightMode = !isLightMode; // Alterna flag
            String path;
            Media media;

            // Define mídia e visual com base no modo selecionado
            if (isLightMode) {
                path = getClass().getResource("/Songs/McLovinsong.mp3").toExternalForm();
                mediaPlayer.setVolume(100);
                setLightMode();
            } else {
                path = getClass().getResource("/Songs/lol.mp3").toExternalForm();
                mediaPlayer.setVolume(0.4);
                setDarkMode();
            }

            // Atualiza o reprodutor de som
            media = new Media(path);

            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        });

    }

    // Define o modo claro: altera CSS e imagens
    @FXML
    private void setLightMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/DarkMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/LightMode.css").toExternalForm());

        // Define imagem do botão de alternância
        Image image = new Image(getClass().getResource("/Imagens/dark.png").toExternalForm());
        imgMode.setImage(image);

        // Define imagem de fundo
        Image fundoImage = new Image(getClass().getResource("/Imagens/Mclovin.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    // Define o modo escuro: altera CSS e imagens
    @FXML
    private void setDarkMode() {
        fundoPane.getStylesheets().remove(getClass().getResource("/styles/LightMode.css").toExternalForm());
        fundoPane.getStylesheets().add(getClass().getResource("/styles/DarkMode.css").toExternalForm());

        // Define imagem do botão de alternância
        Image image = new Image(getClass().getResource("/Imagens/light.png").toExternalForm());
        imgMode.setImage(image);

        // Define imagem de fundo
        Image fundoImage = new Image(getClass().getResource("/Imagens/Asta.png").toExternalForm());
        fundoPane.setStyle("-fx-background-image: url('" + fundoImage.getUrl() + "'); -fx-background-size: cover;");
    }

    // Ação quando o botão de igual é clicado
    @FXML
    private void noSinal(MouseEvent event) {
        String simbolo = ((Pane) event.getSource()).getId().replace("btn", "");

        // Quando o botão "Igual" for clicado
        if (simbolo.equals("Igual")) {
            if (!tfNumero.getText().isEmpty()) {
                String expressao = tfNumero.getText().trim();

                // Define a função a ser calculada
                calculadora.setFuncao(expressao);
                System.out.println("Iniciando cálculo: opção selecionada - " + escolha.getValue());
                realizarCalculo();
            } else {
                // Campo vazio caso a pessoa não digite nada
                System.out.println("Erro:  está incompleta");
                labelResultado.setText("Complete a função!");
            }
        }
    }

    // Realiza o cálculo com base na opção selecionada
    private void realizarCalculo() {
        String opcao = escolha.getValue();
        String resultado = "";

        // Switch para escolher dentre as opções
        switch (opcao) {
            case "Derivada" -> resultado = calculadora.calcularDerivada();
            case "Integral" -> resultado = calculadora.calcularIntegral();
            case "Derivada 2º Ordem" -> resultado = calculadora.calcularD2();
            default -> {
                System.out.println("Erro: Nenhuma operação selecionada");
                labelResultado.setText("Selecione uma operação");
                return;
            }
        }

        // Exibe o resultado
        labelResultado.setText(resultado);
        System.out.println(resultado);

    }
}
