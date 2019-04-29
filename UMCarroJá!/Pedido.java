import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que guarda a informação específica ao pedido de viagem.
 * O Pedido tem id do pedido, id do cliente que o fez, id do proprietário do veículo, id do veículo
 * correspondente, o destino e o tempo que demora.
 */
public class Pedido implements Serializable {
    
    //variáveis de instância
    private int idPedido;
    private int idCliente;
    private int idProprietario; // Atraves do idVeiculo nao consigo chegar ao id do proprietario? Ou procurando na list de veiculos presente dentro do Proprietario ou meto uma variavel aqui ou no proprietario
    private int idVeiculo; // Tem de ter o carro pq tenho de saber qual carro esta a alugar
    private Ponto destino;
    private Time tempoQueDemora;

    /**
    * Construtor por omissão de Pedido.
    */
    public Pedido(){
        this.idPedido = -1;
        this.idCliente = -1;
        this.idProprietario = -1;
        this.idVeiculo = -1;
        this.destino = new Ponto();
        this.tempoQueDemora = new Time(0,0,0);

    }

    /**
    * Construtor parametrizado de Pedido.
    */
    public Pedido(int idPedido, int idCliente, int idProprietario, int idVeiculo, double newPosX, double newPosY, Time tempoQueDemora){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.idVeiculo = idVeiculo;
        this.destino = new Ponto(newPosX,newPosY);
        this.tempoQueDemora = tempoQueDemora;
    }

    /**
    * Construtor de cópia de Pedido.
    * Aceita como parâmetro outro Pedido e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Pedido(Pedido umPedido){
        this.idPedido = umPedido.getIdPedido();
        this.idCliente = umPedido.getIdCliente();
        this.idProprietario = umPedido.getIdProprietario();
        this.idVeiculo = umPedido.getIdVeiculo();
        this.destino = umPedido.getDestino();
        this.tempoQueDemora = umPedido.getTempoQueDemora();

    }

    /**
    * Devolve o id do Pedido em idPedido.
    * @return id do Pedido.
    */
    public int getIdPedido() {
        return idPedido;
    }

    /**
    * Atualiza o id do Pedido em idPedido.
    * @param idPedido Novo id do Pedido.
    */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
    * Devolve o id do cliente associado ao Pedido em idCliente.
    * @return id do cliente.
    */
    public int getIdCliente() {
        return idCliente;
    }

    /**
    * Atualiza o id do cliente associado ao Pedido em idCliente.
    * @param idCliente Novo id do cliente.
    */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
    * Devolve o id do proprietário do veículo associado ao Pedido em idProprietario.
    * @return id do proprietário.
    */
    public int getIdProprietario() {
        return idProprietario;
    }

    /**
    * Atualiza o id do proprietário do veículo associado ao Pedido em idProprietario.
    * @param idProprietario Novo id do proprietario.
    */
    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    /**
    * Devolve o id do veículo associado ao Pedido em idVeiculo.
    * @return id do veículo.
    */
    public int getIdVeiculo() {
        return idVeiculo;
    }

    /**
    * Atualiza o id do veículo associado ao Pedido em idVeiculo.
    * @param idVeiculo Novo id do veículo.
    */
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
    * Devolve o destino associado ao Pedido em destino.
    * @return destino do pedido.
    */
    public Ponto getDestino() {
        return destino;
    }

    /**
    * Atualiza o destino associado ao Pedido em destino.
    * @param destino Novo destino do pedido.
    */
    public void setDestino(Ponto destino) {
        this.destino = destino;
    }

    /**
    * Devolve o tempo de demora da viagem correspondente ao Pedido em tempoQueDemora.
    * @return tempo que demora a viagem do pedido.
    */
    public Time getTempoQueDemora() {
        return tempoQueDemora;
    }

    /**
    * Atualiza o tempo de demora da viagem correspondente ao Pedido em tempoQueDemora.
    * @param tempoQueDemora Novo tempo que demora a viagem do pedido.
    */
    public void setTempoQueDemora(Time tempoQueDemora) {
        this.tempoQueDemora = tempoQueDemora;
    }


    /**
    * Implementação do método de igualdade entre dois Pedido.
    * Redifinição do método equals de Object.
    * @param o O Pedido que é comparado com o recetor.
    * @return Booleano true ou false.
    */
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

    /**
    * Método que devolve a representação em String de Pedido.
    * @return String de Pedido.
    */
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", idProprietario=" + idProprietario +
                ", idVeiculo=" + idVeiculo +
                '}';
    }

    /**
    * Implementação do método de clonagem de um Pedido.
    * @return Objeto do tipo Pedido.
    */
    public Pedido clone() {
        return new Pedido(this);
    }
}
