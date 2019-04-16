import java.util.Objects;

public class Carro extends Veiculo {
    private String tipoCombustivel;
    private String matricula;
    private String marca;


    public Carro() {
        super();
        this.tipoCombustivel = "";
        this.matricula = "";
        this.marca = "";
    }

    public Carro(int id, boolean disponivel, double velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomiaMax, int autonomia, double newPosX,
                 double newPosY,String tipoCombustivel, String matricula, String marca) {
        super(id, disponivel, velocidadeMed, precoPorKm, consumoPorKm, classificacao, autonomiaMax, autonomia, newPosX, newPosY);
        this.tipoCombustivel = tipoCombustivel;
        this.matricula = matricula;
        this.marca = marca;
    }

    public Carro(Carro umCarro) {
        super(umCarro);
        this.tipoCombustivel = umCarro.getTipoCombustivel();
        this.matricula = umCarro.getMatricula();
        this.marca = umCarro.getMarca();
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return super.toString()+"Carro{" +
                "tipoCombustivel='" + tipoCombustivel + '\'' +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro)) return false;
        if (!super.equals(o)) return false;
        Carro carro = (Carro) o;
        return Objects.equals(getTipoCombustivel(), carro.getTipoCombustivel()) &&
                Objects.equals(getMatricula(), carro.getMatricula()) &&
                Objects.equals(getMarca(), carro.getMarca());
    }

    public Carro clone() {
        return new Carro(this);
    }
}
