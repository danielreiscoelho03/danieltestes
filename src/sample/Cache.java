package sample;


public class Cache {
    //FIELDS/CAMPOS
    public Integer idCache;
    private Integer dificuldade;
    public Localizacao local;

    public Cache(Integer dificuldade, int x, int y, String local) {
        this.dificuldade = dificuldade;
        this.local = new Localizacao(x, y, local);
    }

    public Integer getIdCache() {
        return idCache;
    }

    public void setIdCache(Integer idCache) {
        this.idCache = idCache;
    }

    public Integer getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "CacheId: " + idCache +
                ", dificuldade: " + dificuldade +
                ", local: " + local +
                '}';
    }
}
