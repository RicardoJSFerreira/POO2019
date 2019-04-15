import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proprietario extends User{
    private int classificacao;
    private List<Veiculo> veiculos;

    public Proprietario() {
        super();
        this.classificacao = 0;
        this.veiculos = new ArrayList<>();
    }

    public Proprietario(int idUser, String email, String nome, String password, String morada, String dataNascimento, int classificacao) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.classificacao = classificacao;
        this.veiculos = new ArrayList<>();
    }

    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.classificacao = umProprietario.getClassificacao();

    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proprietario)) return false;
        if (!super.equals(o)) return false;
        Proprietario that = (Proprietario) o;
        return getClassificacao() == that.getClassificacao();
    }

    @Override
    public String toString() {
        return
                super.toString()+"Proprietario{" +
                "classificacao=" + classificacao +
                '}';
    }
    public Proprietario clone() {
        return new Proprietario(this);
    }
}
