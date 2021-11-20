package sample;
import java.util.Random;


public class GeoGraph extends grafo_AED {

    public int[] positionsX;
    public int[] positionsY;

    public GeoGraph(GeoGraph gG) {
        super(gG.V());
        positionsX = gG.positionsX;
        positionsY = gG.positionsY;
    }

    public GeoGraph(int nVertices, GestaoAcessoCache gc) {
        super(nVertices);
        positionsX = new int[nVertices];
        positionsY = new int[nVertices];

        setCachePositions(0, gc);
    }

    public GeoGraph(GeoGraph gG, int newSize, GestaoAcessoCache gc){
        super(newSize);
        positionsX = new int[newSize];
        positionsY = new int[newSize];

        for(int i=0; i<gG.V() && i<newSize; i++){
            positionsX[i] = gG.positionsX[i];
            positionsY[i] = gG.positionsY[i];
        }

        setCachePositions(gG.V(), gc);

        for(int v=0; v<gG.V(); v++){
            for(Integer adj: gG.adj(v)){
                this.addEdge(v, adj);
            }
        }
    }

    public GeoGraph(int nVertices, GestaoAcessoAventureiro ga) {
        super(nVertices);
        positionsX = new int[nVertices];
        positionsY = new int[nVertices];

        setCachePositions(0, ga);
    }

    public GeoGraph(GeoGraph gG, int newSize, GestaoAcessoAventureiro ga){
        super(newSize);
        positionsX = new int[newSize];
        positionsY = new int[newSize];

        for(int i=0; i<gG.V() && i<newSize; i++){
            positionsX[i] = gG.positionsX[i];
            positionsY[i] = gG.positionsY[i];
        }

        setCachePositions(gG.V(), ga);

        for(int v=0; v<gG.V(); v++){
            for(Integer adj: gG.adj(v)){
                this.addEdge(v, adj);
            }
        }
    }

    public GeoGraph(GeoGraph gG, int newSize, GestaoAcessoCache gc, int id){
        super(newSize);
        positionsX = new int[newSize];
        positionsY = new int[newSize];

        for(int i=0; i<gG.V() && i<newSize; i++){
            if(i != id){
                positionsX[i] = gG.positionsX[i];
                positionsY[i] = gG.positionsY[i];
            }else{
                positionsX[i] = 0;
                positionsY[i] = 0;
            }
        }

        setCachePositions(gG.V(), gc);
        for(int v=0; v<gG.V(); v++){
            if(v<id){
                for(Integer adj: gG.adj(v)){
                    if(adj!=id){
                        if(adj>id)
                            adj--;
                        this.addEdge(v, adj);
                    }
                }
            }/*else{
                for(Integer adj: gG.adj(v)){
                    if(adj>=id){
                        System.out.println("1 " + (adj) + "->" + (adj-1));
                        this.addEdge(v-1, adj-1);
                    }else{
                        System.out.println("1 " + adj);
                        this.addEdge(v-1, adj);
                    }
                }
            }*/

        }
    }

    private void setVertexPosition(int vertexIDx, int x, int y){
        positionsX[vertexIDx] = x;
        positionsY[vertexIDx] = y;
    }

    public int getVertexPosX(int vertexIDx) { return positionsX[vertexIDx];}

    public int getVertexPosY(int vertexIDx) { return positionsY[vertexIDx];}

    private void setCachePositions(int pos, GestaoAcessoCache gc){
        for(int i=pos; i<this.V(); i++){
            Random r = new Random();
            positionsX[i] = gc.getCaches().get(i).getLocal().getCoordenadaX();
            positionsY[i] = gc.getCaches().get(i).getLocal().getCoordenadaY();
        }
    }

    private void setCachePositions(int pos, GestaoAcessoAventureiro ga){
        for(int i=pos; i<this.V(); i++){
            Random r = new Random();
            positionsX[i] = ga.getAventureiros().get(i).getLocal().getCoordenadaX();
            positionsY[i] = ga.getAventureiros().get(i).getLocal().getCoordenadaY();
        }
    }

    public boolean containsEdge(int v, int a){
        for(Integer adj: this.adj(v))
            if(adj == a) return true;

        for(Integer adj: this.adj(a))
            if(adj==v) return true;

        return false;
    }

}