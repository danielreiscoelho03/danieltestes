package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*public static void main(String[] args) {
        GestaoAcessoCache gc = new GestaoAcessoCache();
        Cache c1 = new Cache(4, 12, 12, "porto");
        Cache c2 = new Cache(4, 22, 22, "porto");
        Cache c3 = new Cache(4, 32, 32, "porto");
        Cache c4 = new Cache(4, 42, 42, "porto");
        Cache c5 = new Cache(4, 52, 52, "porto");
        int []cIdEdges1 = {1,2,3,4};
        int []cIdEdges2 = {};
        int []cIdEdges3 = {};
        int []cIdEdges4 = {};
        int []cIdEdges5 = {};
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);
        gc.adicionaCache(c5);
        gc.adicionarCaminhos(0, cIdEdges1);
        gc.adicionarCaminhos(1, cIdEdges2);
        gc.adicionarCaminhos(2, cIdEdges3);
        gc.adicionarCaminhos(3, cIdEdges4);
        gc.adicionarCaminhos(4, cIdEdges5);
        System.out.println("SOU LINDO");
        gc.removeCache(c3.getIdCache());
         System.out.println();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
