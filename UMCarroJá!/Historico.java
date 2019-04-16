import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Historico extends Pedido {
    private int valorPago;
    private Date dataViagem;


    public Historico() {
        super();
        this.valorPago = -1;
        this.dataViagem = new Date();
    }

    public Historico(int idPedido, int idCliente, int idProprietario, int idVeiculo, double newPosX, double newPosY, Time tempoQueDemora, int valorPago, Date dataViagem) {
        super(idPedido, idCliente, idProprietario, idVeiculo, newPosX, newPosY, tempoQueDemora);
        this.valorPago = valorPago;
        this.dataViagem = dataViagem;
    }

    public Historico(Historico umHistorico) {
        super(umHistorico);
        this.valorPago = umHistorico.getValorPago();
        this.dataViagem = umHistorico.getDataViagem();
    }

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
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
