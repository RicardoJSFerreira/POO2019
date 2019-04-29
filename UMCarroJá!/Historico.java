import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que é subclasse de Pedido, e que guarda o histórico correspondente a uma viagem.
 * O Historico tem um valor pago e a data de realização da viagem.
 */

public class Historico extends Pedido {
    
	//variáveis de instância
    private int valorPago; // vai ter de ser calculado atraves da distancia percorrida e o preço/km
    private LocalDate dataViagem;


    /**
    * Construtor por omissão de Historico.
    */
    public Historico() {
        super();
        this.valorPago = -1;
        this.dataViagem = LocalDate.now();
    }

    /**
    * Construtor parametrizado de Historico.
    */
    public Historico(int idPedido, int idCliente, int idProprietario, int idVeiculo, double newPosX, double newPosY, Time tempoQueDemora, int valorPago, LocalDate dataViagem) {
        super(idPedido, idCliente, idProprietario, idVeiculo, newPosX, newPosY, tempoQueDemora);
        this.valorPago = valorPago;
        this.dataViagem = dataViagem;
    }

    /**
    * Construtor de cópia de Historico.
    * Aceita como parâmetro outro Historico e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Historico(Historico umHistorico) {
        super(umHistorico);
        this.valorPago = umHistorico.getValorPago();
        this.dataViagem = umHistorico.getDataViagem();
    }

    public Historico(Pedido umPedido, int valorPago, LocalDate dataViagem) {
        super(umPedido);
        this.valorPago = valorPago;
        this.dataViagem = dataViagem;
    }

    /**
    * Devolve o valor pago na viagem guardada neste histórico em valorPago.
    * @return valor pago no Historico.
    */
    public int getValorPago() {
        return valorPago;
    }

    /**
    * Atualiza o valor pago na viagem guardada neste histórico em valorPago.
    * @param valorPago Novo valor pago do Historico.
    */
    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    /**
    * Devolve a data da viagem guardada neste histórico em dataViagem.
    * @return data da viagem no Historico.
    */
    public LocalDate getDataViagem() {
        return dataViagem;
    }

    /**
    * Atualiza a data da viagem guardada neste histórico em dataViagem.
    * @param dataViagem Nova data da viagem realizada no Historico.
    */
    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    /**
    * Implementação do método de igualdade entre dois Historico.
    * Redifinição do método equals de Object.
    * @param o O Historico que é comparado com o recetor.
    * @return Booleano true ou false.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Historico)) return false;
        if (!super.equals(o)) return false;
        Historico historico = (Historico) o;
        return getValorPago() == historico.getValorPago() &&
                Objects.equals(getDataViagem(), historico.getDataViagem());
    }

    /**
    * Método que devolve a representação em String de Historico.
    * @return String de Historico.
    */
    @Override
    public String toString() {
        return super.toString()+"Historico{" +
                "valorPago=" + valorPago +
                ", dataViagem=" + dataViagem +
                '}';
    }

    /**
    * Implementação do método de clonagem de um Historico.
    * @return Objeto do tipo Historico.
    */
    public Historico clone() {
        return new Historico(this);
    }
}
