/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelha;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;

/**
 *
 * @author diego e luciano
 */
public class JogoVelha extends Application {

    private Label lbl1 = new Label("Jogador 1");
    private Label lbl2 = new Label("Jogador 2");
    private Label lbl3 = new Label("Aguardando Seleção");
    
    private TextField txf1 = new TextField();
    private TextField txf2 = new TextField();

    private Button btnX = new Button("X");
    private Button btn0 = new Button("0");
    

    private Button btn1 = new Button();
    private Button btn2 = new Button();
    private Button btn3 = new Button();
    private Button btn4 = new Button();
    private Button btn5 = new Button();
    private Button btn6 = new Button();
    private Button btn7 = new Button();
    private Button btn8 = new Button();
    private Button btn9 = new Button();

    private boolean jogador1, jogador2;
    private String marcacao, marcacao1, marcacao2;

    @Override
    public void start(Stage primaryStage) {
        btnX.setMinSize(30, 30);
        btn0.setMinSize(30, 30);

        btnX.setOnAction(j -> selecionaJogador(j));
        btn0.setOnAction(j -> selecionaJogador(j));

        btn1.setMinSize(60, 60);
        btn2.setMinSize(60, 60);
        btn3.setMinSize(60, 60);
        btn4.setMinSize(60, 60);
        btn5.setMinSize(60, 60);
        btn6.setMinSize(60, 60);
        btn7.setMinSize(60, 60);
        btn8.setMinSize(60, 60);
        btn9.setMinSize(60, 60);

        btn1.setOnAction(e -> joga(e));
        btn2.setOnAction(e -> joga(e));
        btn3.setOnAction(e -> joga(e));
        btn4.setOnAction(e -> joga(e));
        btn5.setOnAction(e -> joga(e));
        btn6.setOnAction(e -> joga(e));
        btn7.setOnAction(e -> joga(e));
        btn8.setOnAction(e -> joga(e));
        btn9.setOnAction(e -> joga(e));

        BorderPane bp = new BorderPane();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(4);
        gp.setVgap(4);

        HBox jogador1 = new HBox(3, lbl1, btnX, btn0);
        VBox jogadores = new VBox(3, jogador1, lbl2, lbl3);

        bp.setTop(jogadores);

        bp.setCenter(gp);

        gp.add(btn1, 0, 0, 1, 1);
        gp.add(btn2, 1, 0, 1, 1);
        gp.add(btn3, 2, 0, 1, 1);
        gp.add(btn4, 0, 1, 1, 1);
        gp.add(btn5, 1, 1, 1, 1);
        gp.add(btn6, 2, 1, 1, 1);
        gp.add(btn7, 0, 2, 1, 1);
        gp.add(btn8, 1, 2, 1, 1);
        gp.add(btn9, 2, 2, 1, 1);

//        StackPane root = new StackPane();
//        root.getChildren().add(btn1);
        Scene scene = new Scene(bp, 300, 345);

        primaryStage.setTitle("Jogo da Velha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void limpaJogador() {
        marcacao1 = null;
        marcacao2 = null;
        marcacao = null;
        jogador1 = false;
        jogador2 = true;
    }

    public void limpar() {
        btn1.setText(null);
        btn2.setText(null);
        btn3.setText(null);
        btn4.setText(null);
        btn5.setText(null);
        btn6.setText(null);
        btn7.setText(null);
        btn8.setText(null);
        btn9.setText(null);
        
        
    }

    public void selecionaJogador(ActionEvent j) {
        Button botao = (Button) j.getSource();
        
        if (botao.getText() == "X") {
            marcacao1 = "X";
            marcacao2 = "0";
        } else {
            marcacao1 = "0";
            marcacao2 = "X";
        }
        iniciaJogo();
    }

    public void iniciaJogo() {
        limpar();
        
        lbl3.setText("Partida iniciada!");
        
        habilitaJogador1();
    }

//    private void finalizarPartida(){
//        limpar();
//        lbl3.setText("Partida finalizada!");
//    }
    private void habilitaJogador1() {
        this.jogador1 = true;
        this.jogador2 = false;
        this.marcacao = marcacao1;
        lbl3.setText("Aguardando jogador 1!");
    }

    private void habilitaJogador2() {
        this.jogador1 = false;
        this.jogador2 = true;
        this.marcacao = marcacao2;
        lbl3.setText("Aguardando jogador 2!");
    }

    private void controleDeJogada() {
        if (jogador1 == true) {
            habilitaJogador2();
        } else {
            habilitaJogador1();
        }
    }

    //jogada
    private void joga(ActionEvent e) {
        Button botao = (Button) e.getSource();
        if (botao.getText() == null) {
            botao.setText(marcacao);
            controleDeJogada();
            verificaGanhador();
        } else {
            lbl3.setText("Jogada inválida!");
        }

    }

    //coletar os dados
    private String[][] coletarJogadas() {
        String[][] jogadas = new String[3][3];
        jogadas[0][0] = btn1.getText();
        jogadas[0][1] = btn2.getText();
        jogadas[0][2] = btn3.getText();
        jogadas[1][0] = btn4.getText();
        jogadas[1][1] = btn5.getText();
        jogadas[1][2] = btn6.getText();
        jogadas[2][0] = btn7.getText();
        jogadas[2][1] = btn8.getText();
        jogadas[2][2] = btn9.getText();
        return jogadas;
    }

//se houver ganhador
    private void ganhou(int ganhador) {
        
        if (ganhador == 1) {
            lbl3.setText("O jogador n° 1 venceu!");
        } else {
            lbl3.setText("O jogador n° 2 venceu!");
        }
        limpaJogador();
    }

//verifica se houve ganhador
    private void verificaGanhador() {
        String[][] jogada = coletarJogadas();
        String um = "X", dois = "0";

        //diagonal principal
        if ((jogada[0][0] != null) && (jogada[1][1] != null) && (jogada[2][2] != null)) {
            if ((jogada[0][0].equals(um)) && (jogada[1][1].equals(um)) && (jogada[2][2].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][0].equals(dois)) && (jogada[1][1].equals(dois)) && (jogada[2][2].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //diagonal secundaria
        if ((jogada[0][2] != null) && (jogada[1][1] != null) && (jogada[2][0] != null)) {
            if ((jogada[0][2].equals(um)) && (jogada[1][1].equals(um)) && (jogada[2][0].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][2].equals(dois)) && (jogada[1][1].equals(dois)) && (jogada[2][0].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //primeira linha
        if ((jogada[0][0] != null) && (jogada[0][1] != null) && (jogada[0][2] != null)) {
            if ((jogada[0][0].equals(um)) && (jogada[0][1].equals(um)) && (jogada[0][2].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][0].equals(dois)) && (jogada[0][1].equals(dois)) && (jogada[0][2].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //segunda linha
        if ((jogada[1][0] != null) && (jogada[1][1] != null) && (jogada[1][2] != null)) {
            if ((jogada[1][0].equals(um)) && (jogada[1][1].equals(um)) && (jogada[1][2].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[1][0].equals(dois)) && (jogada[1][1].equals(dois)) && (jogada[1][2].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //treceira linha
        if ((jogada[2][0] != null) && (jogada[2][1] != null) && (jogada[2][2] != null)) {
            if ((jogada[2][0].equals(um)) && (jogada[2][1].equals(um)) && (jogada[2][2].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[2][0].equals(dois)) && (jogada[2][1].equals(dois)) && (jogada[2][2].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //primeira coluna
        if ((jogada[0][0] != null) && (jogada[1][0] != null) && (jogada[2][0] != null)) {
            if ((jogada[0][0].equals(um)) && (jogada[1][0].equals(um)) && (jogada[2][0].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][0].equals(dois)) && (jogada[1][0].equals(dois)) && (jogada[2][0].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //segunda coluna
        if ((jogada[0][1] != null) && (jogada[1][1] != null) && (jogada[2][1] != null)) {
            if ((jogada[0][1].equals(um)) && (jogada[1][1].equals(um)) && (jogada[2][1].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][1].equals(dois)) && (jogada[1][1].equals(dois)) && (jogada[2][1].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //treceira coluna
        if ((jogada[0][2] != null) && (jogada[1][2] != null) && (jogada[2][2] != null)) {
            if ((jogada[0][2].equals(um)) && (jogada[1][2].equals(um)) && (jogada[2][2].equals(um))) {
                ganhou(1);
                return;
            }
            if ((jogada[0][2].equals(dois)) && (jogada[1][2].equals(dois)) && (jogada[2][2].equals(dois))) {
                ganhou(2);
                return;
            }
        }

        //se ocorrer empate
        if ((jogada[0][0] != null) && (jogada[0][1] != null) && (jogada[0][2] != null)
                && (jogada[1][0] != null) && (jogada[1][1] != null) && (jogada[1][2] != null)
                && (jogada[2][0] != null) && (jogada[2][1] != null) && (jogada[2][2] != null)) {
            lbl3.setText("Empate!");
            limpaJogador();
        }

    }

}
