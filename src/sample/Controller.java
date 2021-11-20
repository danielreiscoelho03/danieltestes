package sample;

import edu.princeton.cs.algs4.*;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class Controller {

    public TextArea consola;
    public TextArea consolaAventureiros;
    public TextField ApiAventureiro;
    public TextArea NewPathArea;
    public TextField NewCacheField;
    public TextField InsertCacheDist;
    public TextField Result;
    private GeoGraph gG;
    public Group graphGroup;
    private double radius = 20;
    GestaoAcessoCache gc = new GestaoAcessoCache();
    GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();

    public void createGraphGroup(GestaoAcessoCache gc){

        for(int i=0; i<gc.getGrafo().V(); i++){
            Circle c = new Circle(gc.getGrafo().getVertexPosX(i), gc.getGrafo().getVertexPosY(i), radius);
            c.setFill(Color.WHITESMOKE);

            StackPane stack = new StackPane();
            stack.setLayoutX(gc.getGrafo().getVertexPosX(i)-radius);
            stack.setLayoutY(gc.getGrafo().getVertexPosY(i)-radius);
            stack.getChildren().addAll(c, new Text(gc.getCaches().get(i).getLocal().getLocalizacao()));

            graphGroup.getChildren().add(stack);

            if(gc.getGrafo().E() > 0){
                for(Integer adj: gc.getGrafo().adj(i)){
                    Line line = new Line(gc.getGrafo().getVertexPosX(i), gc.getGrafo().getVertexPosY(i), gc.getGrafo().getVertexPosX(adj), gc.getGrafo().getVertexPosY(adj));
                    graphGroup.getChildren().add(line);
                }
            }
        }
    }

    public void createGraphGroup(GestaoAcessoAventureiro ga){

        for(int i=0; i<ga.getGrafo().V(); i++){
            Circle c = new Circle(ga.getGrafo().getVertexPosX(i), ga.getGrafo().getVertexPosY(i), radius);
            c.setFill(Color.LIGHTGREEN);

            StackPane stack = new StackPane();
            stack.setLayoutX(ga.getGrafo().getVertexPosX(i)-radius);
            stack.setLayoutY(ga.getGrafo().getVertexPosY(i)-radius);
            stack.getChildren().addAll(c, new Text(ga.getAventureiros().get(i).getNome()));

            graphGroup.getChildren().add(stack);

            if(ga.getGrafo().E() > 0){
                for(Integer adj: ga.getGrafo().adj(i)){
                    Line line = new Line(ga.getGrafo().getVertexPosX(i), ga.getGrafo().getVertexPosY(i), ga.getGrafo().getVertexPosX(adj), ga.getGrafo().getVertexPosY(adj));
                    graphGroup.getChildren().add(line);
                }
            }
        }
    }

    private void createNewGraph(int nVertices){
        if(gG == null){
            gG = new GeoGraph(nVertices, gc);
        } else
            gG = new GeoGraph(gG, nVertices, gc);
    }

    public void handleClearButtonAction(ActionEvent actionEvent) {
        DepthFirstSearch_AED2 dfs = new DepthFirstSearch_AED2(gc.getGrafo(), 1, 4);
        System.out.println(dfs.count());
        System.out.println("Debugger");
    }

    public void handleAddCamButton(ActionEvent actionEvent) {
        try {
            String[] lines = NewPathArea.getText().split("\n");
            for (String line : lines) {
                String[] args = line.split(";");
                int id = Integer.parseInt(args[0]);
                int edge = Integer.parseInt(args[1]);
                int edges[] = new int[1];
                edges[0] = edge;
                gc.adicionarCaminhos(id, edges);
            }
            createGraphGroup(gc);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void handleAddCacheButton(ActionEvent actionEvent) {
        try {
            String[] lines = NewCacheField.getText().split("\n");
            for (String line : lines) {
                String[] args = line.split(";");
                int dif = Integer.parseInt(args[0]);
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                String local = args[3];
                Cache c = new Cache(dif, x, y, local);
                gc.adicionaCache(c);
                if(consola.getText()!=null)
                    consola.setText(consola.getText() + "\n" + "Cache adicionada com sucesso\n" + c.toString());
                else
                    consola.setText("Cache adicionada com sucesso\n" + c.toString());
            }
            createGraphGroup(gc);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void handleCarregarFiheiro(ActionEvent actionEvent) {
        In infile = new In("data/caches.txt");
        String line;
        while((line = infile.readLine()) != null){
            String[] parts = line.split(";");
            int dif = Integer.parseInt(parts[0]);
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            String local = parts[3];
            Cache c = new Cache(dif,x,y,local);
            gc.adicionaCache(c);
        }
        createGraphGroup(gc);
        infile = new In("data/caminhos.txt");
        while((line = infile.readLine()) != null){
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            int edge = Integer.parseInt(parts[1]);
            int[] edges = new int[1];
            edges[0] = edge;
            gc.adicionarCaminhos(id, edges);
        }
        createGraphGroup(gc);
    }

    public void handleGuardarCache(ActionEvent actionEvent) {
        Out out1 = new Out("data/caches.txt");
        Out out2 = new Out("data/caminhos.txt");
        for (int i = 0; i < gc.getNumCache(); i++) {
            String tofile = gc.getCaches().get(i).getDificuldade() + ";" +
                    gc.getCaches().get(i).getLocal().getCoordenadaX() + ";" +
                    gc.getCaches().get(i).getLocal().getCoordenadaY() + ";" +
                    gc.getCaches().get(i).getLocal().getLocalizacao();
            out1.println(tofile);
        }
        for (int i = 0; i < gc.getGrafo().V(); i++) {
            for (int j = i+1; j < gc.getGrafo().V(); j++) {
                if(gc.getGrafo().containsEdge(i, j)){
                    String tofile2 = i + ";" + j;
                    out2.println(tofile2);
                }
            }
        }
    }

    public void handleCalcButton(ActionEvent actionEvent) {
        String dados = InsertCacheDist.getText();
        String[] data = dados.split(";");
        int dist = gc.verMenorCaminho(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        Result.setText(String.valueOf(dist));
    }

    public void handleAddAventureiro(ActionEvent actionEvent) {
        String avent = ApiAventureiro.getText();
        String[] parts = avent.split(";");
        String nome = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        Aventureiro a = new Aventureiro(nome,x,y);
        ga.regista(a);
        if(consola.getText()!=null)
            consola.setText(consola.getText() + "\n" + "Aventureiro adicionado com sucesso\n" + a.toString());
        else
            consola.setText("Aventureiro adicionado com sucesso\n" + a.toString());
    }

    public void handleEditAventureiro(ActionEvent actionEvent) {
    }

    public void handleRemoveAventureiro(ActionEvent actionEvent) {
    }

    public void HandleVerAventureiro(ActionEvent actionEvent) {
        int x = 0;
        while(x<ga.getAventureiros().size()){
            if(consola.getText()==null)
                consolaAventureiros.setText(ga.getAventureiros().get(x).toString() + "\n");
            else
                consolaAventureiros.setText(consolaAventureiros.getText() + ga.getAventureiros().get(x).toString() + "\n");
            x++;
        }
        createGraphGroup(ga);
    }
}
