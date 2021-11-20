package sample;

public class Aventureiro {

    //FIELDS/CAMPOS
    private Integer idAventureiro;
    private String nome;
    private Localizacao local;

    /**
     * Construtor Aventureiro(nome e localização)
     * @param nome - nome do Aventureiro
     * @param x - coordX
     * @param y - coordY
     */
    public Aventureiro(String nome, int x, int y) {
        this.nome = nome;
        this.local = new Localizacao(x, y);
    }

    //GETTERS AND SETTERS
    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public Integer getIdAventureiro() {
        return idAventureiro;
    }

    public void setIdAventureiro(Integer idAventureiro) {
        this.idAventureiro = idAventureiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Id: " + idAventureiro +
                ", nome: " + nome +
                ", local: " + local;
    }
}