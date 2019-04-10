import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends User implements Serializable {
    private Ponto posicao; // Perguntar se subclasses sao protected ou podem ser private
    private ArrayList<String> historico = new ArrayList<String>(); // ainda nao sei como vamos representar o historico

    public Cliente() {
        super();
        this.posicao = new Ponto(0,0);
        this.historico = new ArrayList<String>();
    }

    public Cliente(String email, String nome, String password, String morada, String dataNascimento, double newPosX,double newPosY, ArrayList<String> historico) {
        super(email, nome, password, morada, dataNascimento);
        this.posicao = new Ponto<Double>(newPosX, newPosY); // Se recebo um ponto faço logo this.posicao = posicao que recebo
        this.historico = historico;
    }


    public Cliente(Cliente umCliente) {
        super(umCliente);
        this.posicao = umCliente.getPosicao();
        this.historico = umCliente.getHistorico();
    }


    public Ponto getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
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
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getPosicao(), cliente.getPosicao()) &&
                Objects.equals(getHistorico(), cliente.getHistorico());
    }


    public String toString() {
        return "Cliente{" +
                "posicao=" + posicao +
                ", historico=" + historico + // Confirmar se historico pode ser assim
                '}';
    }
    public Cliente clone() {
        return new Cliente(this);
    }
}
