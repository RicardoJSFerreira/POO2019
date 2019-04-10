import java.util.ArrayList;
import java.util.Objects;

public class Proprietario extends User{
    private int classificacao;
    private ArrayList<String> historico = new ArrayList<String>(); // ainda nao sei como vamos representar o historico

    public Proprietario() {
        super();
        this.classificacao = 0;
        this.historico = new ArrayList<String>();
    }

    public Proprietario(String email, String nome, String password, String morada, String dataNascimento, int classificacao, ArrayList<String> historico) {
        super(email, nome, password, morada, dataNascimento);
        this.classificacao = classificacao;
        this.historico = historico;
    }

    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.classificacao = umProprietario.getClassificacao();
        this.historico = umProprietario.getHistorico();
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public ArrayList<String> getHistorico() {
        ArrayList<String> r = new ArrayList<String>();
        for(String s: this.historico)
            r.add(s);
        return r;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico=new ArrayList<String>();
        for(String s: historico)
            this.historico.add(s);
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proprietario)) return false;
        if (!super.equals(o)) return false;
        Proprietario that = (Proprietario) o;
        return getClassificacao() == that.getClassificacao() &&
                Objects.equals(getHistorico(), that.getHistorico());
    }

    @Override
    public String toString() {
        return "Proprietario{" +
                "classificacao=" + classificacao +
                ", historico=" + historico +
                '}';
    }
    public Proprietario clone() {
        return new Proprietario(this);
    }
}
