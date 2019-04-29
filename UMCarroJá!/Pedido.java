import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Pedido implements Serializable {
    private int idPedido;
    private int idCliente;
    private int idProprietario; // Atraves do idVeiculo nao consigo chegar ao id do proprietario? Ou procurando na list de veiculos presente dentro do Proprietario ou meto uma variavel aqui ou no proprietario
    private int idVeiculo; // Tem de ter o carro pq tenho de saber qual carro esta a alugar
    private Ponto destino;
    private Time tempoQueDemora;

    public Pedido(){
        this.idPedido = -1;
        this.idCliente = -1;
        this.idProprietario = -1;
        this.idVeiculo = -1;
        this.destino = new Ponto();
        this.tempoQueDemora = new Time(0,0,0);

    }

    public Pedido(int idPedido, int idCliente, int idProprietario, int idVeiculo, double newPosX, double newPosY, Time tempoQueDemora){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.idVeiculo = idVeiculo;
        this.destino = new Ponto(newPosX,newPosY);
        this.tempoQueDemora = tempoQueDemora;
    }
    public Pedido(Pedido umPedido){
        this.idPedido = umPedido.getIdPedido();
        this.idCliente = umPedido.getIdCliente();
        this.idProprietario = umPedido.getIdProprietario();
        this.idVeiculo = umPedido.getIdVeiculo();
        this.destino = umPedido.getDestino();
        this.tempoQueDemora = umPedido.getTempoQueDemora();

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

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Ponto getDestino() {
        return destino;
    }

    public void setDestino(Ponto destino) {
        this.destino = destino;
    }

    public Time getTempoQueDemora() {
        return tempoQueDemora;
    }

    public void setTempoQueDemora(Time tempoQueDemora) {
        this.tempoQueDemora = tempoQueDemora;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getIdPedido() == pedido.getIdPedido() &&
                getIdCliente() == pedido.getIdCliente() &&
                getIdProprietario() == pedido.getIdProprietario() &&
                getIdVeiculo() == pedido.getIdVeiculo()
                ;
    }

    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", idProprietario=" + idProprietario +
                ", idVeiculo=" + idVeiculo +
                '}';
    }
    public Pedido clone() {
        return new Pedido(this);
    }
}
