package sample;

import Search.RedBlack_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestaoAcessoAventureiro {

    //FIELDS/CAMPOS
    private RedBlack_AED2<Integer,Aventureiro> aventureiros = new RedBlack_AED2<>(); //RedBlack de aventureiros
    private int numAventureiros;
    private GeoGraph grafo = new GeoGraph(numAventureiros, this);

    //GETTERS AND SETTERS
    public RedBlack_AED2<Integer, Aventureiro> getAventureiros() {
        return aventureiros;
    }

    public void setAventureiros(RedBlack_AED2<Integer, Aventureiro> aventureiros) {
        this.aventureiros = aventureiros;
    }

    public int getNumAventureiros() {
        return numAventureiros;
    }

    public void setNumAventureiros(int numAventureiros) {
        this.numAventureiros = numAventureiros;
    }

    public GeoGraph getGrafo() {
        return grafo;
    }

    public void setGrafo(GeoGraph grafo) {
        this.grafo = grafo;
    }

    public boolean regista(Aventureiro aventureiro) {
        aventureiro.setIdAventureiro(numAventureiros); //set ID(atribuid consoante o numAventureiros)
        aventureiros.put(numAventureiros, aventureiro); //adiciono o novo aventureiro à RedBlack de Aventureiros
        numAventureiros++; //itero o numero de aventureiros
        grafo = new GeoGraph(grafo, numAventureiros, this);
        return true;
    }

    public boolean remove(Integer idAventureiro) {
        if(aventureiros.contains(idAventureiro)){ //se esse aventureiro existir
            String nome = aventureiros.get(idAventureiro).getNome(); //obtemos o nome do mesmo
            aventureiros.delete(idAventureiro); //removemos o Aventureiro
            return true;
        }
        return false;
    }

    public boolean editar(Integer idAventureiro, String nome, int x, int y) {
        if(aventureiros.get(idAventureiro)!=null){ //se existir
            aventureiros.get(idAventureiro).setNome(nome); //altero-lhe o nome antigo para o novo
            aventureiros.get(idAventureiro).getLocal().setCoordenadaX(x); //altero-lhe a antiga coordX para a nova coordX
            aventureiros.get(idAventureiro).getLocal().setCoordenadaY(y); //altero-lhe a antiga coordY para a nova coordX
            return true;
        }
        return false;
    }
}