package sample;

import Search.RedBlack_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class GestaoAcessoCache{

    //FIELDS/CAMPOS
    private RedBlack_AED2<Integer, Cache> caches = new RedBlack_AED2<>(); //RedBlack de caches
    private int numCache;
    private GeoGraph grafo = new GeoGraph(numCache, this);

    //GETTERS AND SETTERS
    public int getNumCache() {
        return numCache;
    }

    public void setNumCache(int numCache) {
        this.numCache = numCache;
    }

    public RedBlack_AED2<Integer, Cache> getCaches() {
        return caches;
    }

    public void setCaches(RedBlack_AED2<Integer, Cache> caches) {
        this.caches = caches;
    }

    public GeoGraph getGrafo() {
        return grafo;
    }

    public void setGrafo(GeoGraph grafo) {
        this.grafo = grafo;
    }

    public boolean adicionaCache(Cache cache) {
        cache.setIdCache(numCache); //set ID(ID criado/adicionado consoante a variável numCache)
        caches.put(numCache,cache); //adicionar a nova cache na RedBlack de Caches
        numCache++;
        //iteramos a cada nova adição de uma cache(essencial para a criação dos ID´s)
        // codigo novo
        grafo = new GeoGraph(grafo, numCache, this);
        return true;
    }

    public void adicionarCaminhos(int id ,int[] edges){
        for (int i = 0; i < edges.length; i++) {
            grafo.addEdge(id, edges[i]);
        }
    }

    public int verMenorCaminho(int v, int e){
        if(getGrafo().containsEdge(v, e)){
            return (int)getCaches().get(v).getLocal().distancia(getCaches().get(e).getLocal());
        }else{
            int num = Integer.parseInt(String.valueOf(getGrafo().adj(v))); //  num de edges que tem cada vertice(unica forma que consegui chegar a este valor)
            return -1;
        }
    }

    public boolean removeCache(Integer idCache){
        if(caches.contains(idCache)){ //verificamos se a mesma existe na RedBlack de caches
            caches.delete(idCache); //removemos a cache especifica
            numCache--;
            //grafo = new GeoGraph(grafo, numCache, this, idCache);
            for(int i=0; i<grafo.V(); i++){
                if(i != idCache){
                    grafo.positionsX[i] = grafo.positionsX[i];
                    grafo.positionsY[i] = grafo.positionsY[i];
                }else{
                    grafo.positionsX[i] = 0;
                    grafo.positionsY[i] = 0;
                }
            }
            return true;
        }
        return false;
    }


    public boolean existeCache(Integer idCache) {
        if(caches.contains(idCache)){ //se o array contêm a cache recebida retornamos True
            return true;
        }
        return false;
    }
}
