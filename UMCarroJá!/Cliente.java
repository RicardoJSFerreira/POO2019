import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que é subclasse de User, e que guarda a informação específica ao cliente.
 * O Cliente tem posição e classificação.
 */

public class Cliente extends User implements Serializable {
    
    //variáveis de instância
    private Ponto posicao;
    private int classificacao;

    /**
    * Construtor por omissão de Cliente.
     */
    public Cliente() {
        super();
        this.posicao = new Ponto(0,0);
        this.classificacao = -1;
    }

    /**
    * Construtor parametrizado de Cliente.
    */
    public Cliente(int idUser, String email, String nome, String password, String morada, LocalDate dataNascimento, Double newPosX, Double newPosY, int classificacao) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.posicao = new Ponto<Double>(newPosX, newPosY);
        this.classificacao = classificacao;
    }

    public Cliente(int idUser, String email, String nome, String password, String morada, LocalDate dataNascimento, Double newPosX, Double newPosY) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.posicao = new Ponto<Double>(newPosX, newPosY);
        this.classificacao = -1;
    }

    /**
    * Construtor de cópia de Cliente.
    * Aceita como parâmetro outro Cliente e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Cliente(Cliente umCliente) {
        super(umCliente);
        this.posicao = umCliente.getPosicao();
        this.classificacao = umCliente.getClassificacao();
    }

    /**
    * Devolve a posição do Cliente em posicao.
    * @return posição do Cliente.
    */
    public Ponto getPosicao() {
        return this.posicao;
    }

    /**
    * Atualiza a posicao do Cliente em posicao.
    * @param posicao Nova posição do Cliente.
    */
    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    /**
    * Devolve a classificação do Cliente em classificacao.
    * @return classificação do Cliente.
    */
    public int getClassificacao() {
        return classificacao;
    }

    /**
    * Atualiza a classificação do Cliente em classificacao.
    * @param classificacao Nova classificação do Cliente.
    */
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    /**
    * Implementação do método de igualdade entre dois Cliente.
    * Redifinição do método equals de Object.
    * @param o O Cliente que é comparado com o recetor.
    * @return Booleano true ou false.
    */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getPosicao(), cliente.getPosicao());
    }

    /**
    * Método que devolve a representação em String de Cliente.
    * @return String de Cliente.
    */
    public String toString() {
        return super.toString() +
                "Cliente{" +
                "posicao=" + posicao +
                '}';
    }

    /**
    * Implementação do método de clonagem de um Cliente.
    * @return Objeto do tipo Cliente.
    */
    public Cliente clone() {
        return new Cliente(this);
    }
}
