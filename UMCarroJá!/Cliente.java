import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Cliente extends User implements Serializable {
    private Ponto posicao;
    private int classificacao;



    public Cliente() {
        super();
        this.posicao = new Ponto(0,0);
        this.classificacao = -1;
    }

    public Cliente(int idUser, String email, String nome, String password, String morada, Date dataNascimento, double newPosX, double newPosY, int classificacao) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.posicao = new Ponto<Double>(newPosX, newPosY);
        this.classificacao = classificacao;
    }

    public Cliente(int idUser, String email, String nome, String password, String morada, Date dataNascimento, double newPosX, double newPosY) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.posicao = new Ponto<Double>(newPosX, newPosY);
        this.classificacao = -1;
    }

// Construtor de copia de uma subclasse recebe a subclasse ou a classe principal?
    public Cliente(Cliente umCliente) { // Recebe um cliente ou um user ? Acho que é assim
        super(umCliente);
        this.posicao = umCliente.getPosicao();
        this.classificacao = umCliente.getClassificacao();
    }


    public Ponto getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getPosicao(), cliente.getPosicao());
    }


    public String toString() {
        return super.toString() +
                "Cliente{" +
                "posicao=" + posicao +
                '}';
    }
    public Cliente clone() {
        return new Cliente(this);
    }
}
