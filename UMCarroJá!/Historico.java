import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class Historico extends Pedido {
    private int valorPago; // vai ter de ser calculado atraves da distancia percorrida e o preço/km
    private LocalDate dataViagem;


    public Historico() {
        super();
        this.valorPago = -1;
        this.dataViagem = LocalDate.now();
    }

    public Historico(int idPedido, int idCliente, int idProprietario, int idVeiculo, double newPosX, double newPosY, Time tempoQueDemora, int valorPago, LocalDate dataViagem) {
        super(idPedido, idCliente, idProprietario, idVeiculo, newPosX, newPosY, tempoQueDemora);
        this.valorPago = valorPago;
        this.dataViagem = dataViagem;
    }

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

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Historico)) return false;
        if (!super.equals(o)) return false;
        Historico historico = (Historico) o;
        return getValorPago() == historico.getValorPago() &&
                Objects.equals(getDataViagem(), historico.getDataViagem());
    }

    @Override
    public String toString() {
        return super.toString()+"Historico{" +
                "valorPago=" + valorPago +
                ", dataViagem=" + dataViagem +
                '}';
    }

    public Historico clone() {
        return new Historico(this);
    }
}
