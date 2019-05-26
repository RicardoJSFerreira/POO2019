import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable {
    private int idPedido;
    private int idCliente;
    private int idProprietario; // Atraves do idVeiculo nao consigo chegar ao id do proprietario? Ou procurando na list de veiculos presente dentro do Proprietario ou meto uma variavel aqui ou no proprietario
    private String matricula;
    private Ponto origem;
    private Ponto destino;
    private double tempoQueDemoraemHoras;

    public Pedido(){
        this.idPedido = -1;
        this.idCliente = -1;
        this.idProprietario = -1;
        this.matricula = "";
        this.origem = new Ponto();
        this.destino = new Ponto();
        this.tempoQueDemoraemHoras = -1.0;

    }

    public Pedido(int idPedido, int idCliente, int idProprietario, String matricula,double oldx, double oldy ,double newPosX, double newPosY, double tempoQueDemora){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.matricula = matricula;
        this.origem = new Ponto(oldx,oldy);
        this.destino = new Ponto(newPosX,newPosY);
        this.tempoQueDemoraemHoras = tempoQueDemora;
    }
    public Pedido(int idPedido, int idCliente, int idProprietario, String matricula,Ponto oldPos,Ponto newPos, double tempoQueDemora){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.matricula = matricula;
        this.origem = oldPos;
        this.destino = newPos;
        this.tempoQueDemoraemHoras = tempoQueDemora;
    }

    public Pedido(Pedido umPedido){
        this.idPedido = umPedido.getIdPedido();
        this.idCliente = umPedido.getIdCliente();
        this.idProprietario = umPedido.getIdProprietario();
        this.matricula = umPedido.getMatricula();
        this.origem = umPedido.getOrigem();
        this.destino = umPedido.getDestino();
        this.tempoQueDemoraemHoras = umPedido.getTempoQueDemoraemHoras();

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Ponto getOrigem() {
        return origem;
    }

    public void setOrigem(Ponto origem) {
        this.origem = origem;
    }

    public Ponto getDestino() {
        return destino;
    }

    public void setDestino(Ponto destino) {
        this.destino = destino;
    }

    public double getTempoQueDemoraemHoras() {
        return tempoQueDemoraemHoras;
    }

    public void setTempoQueDemoraemHoras(double tempoQueDemoraemHoras) {
        this.tempoQueDemoraemHoras = tempoQueDemoraemHoras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getIdPedido() == pedido.getIdPedido() &&
                getIdCliente() == pedido.getIdCliente() &&
                getIdProprietario() == pedido.getIdProprietario() &&
                Double.compare(pedido.getTempoQueDemoraemHoras(), getTempoQueDemoraemHoras()) == 0 &&
                Objects.equals(getMatricula(), pedido.getMatricula()) &&
                Objects.equals(getOrigem(), pedido.getOrigem()) &&
                Objects.equals(getDestino(), pedido.getDestino());
    }



    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", idProprietario=" + idProprietario +
                ", Matricula=" + matricula +
                '}';
    }
    public Pedido clone() {
        return new Pedido(this);
    }
}
