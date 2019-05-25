import java.io.Serializable;
import java.util.Objects;

public class Carro extends Veiculo implements Serializable {
    
    //variáveis de instância
    private String tipoCombustivel;
    private String marca;


    /**
    * Construtor por omissão de Carro.
    */
    public Carro() {
        super();
        this.tipoCombustivel = "";
        this.marca = "";
    }

    /**
    * Construtor parametrizado de Carro.
    */
    public Carro(String matricula,int nifProp, boolean disponivel, int velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomiaMax, int autonomia, double newPosX,
                 double newPosY,String tipoCombustivel, String marca) {
        super(matricula, nifProp,disponivel, velocidadeMed, precoPorKm, consumoPorKm, classificacao, autonomiaMax, autonomia, newPosX, newPosY);
        this.tipoCombustivel = tipoCombustivel;
        this.marca = marca;
    }
    public Carro(String matricula,int nifProp, int velocidadeMed, double precoPorKm, double consumoPorKm, int autonomiaMax, int autonomia, double newPosX,
                 double newPosY,String tipoCombustivel, String marca) {
        super(matricula,nifProp, false, velocidadeMed, precoPorKm, consumoPorKm,0, autonomiaMax, autonomia, newPosX, newPosY);
        this.tipoCombustivel = tipoCombustivel;
        this.marca = marca;
    }
    public Carro(String matricula,int nifProp, int velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomiaMax, int autonomia,Ponto posicao,String tipoCombustivel, String marca) {
        super(matricula, nifProp,false, velocidadeMed, precoPorKm, consumoPorKm, classificacao, autonomiaMax, autonomia,posicao);
        this.tipoCombustivel = tipoCombustivel;
        this.marca = marca;
    }
    public Carro(String matricula,int nifProp, int velocidadeMed, double precoPorKm, double consumoPorKm, int autonomiaMax, int autonomia,Ponto posicao,String tipoCombustivel, String marca) {
        super(matricula, nifProp,false, velocidadeMed, precoPorKm, consumoPorKm,-1, autonomiaMax, autonomia,posicao);
        this.tipoCombustivel = tipoCombustivel;
        this.marca = marca;
    }
    /**
    * Construtor de cópia de Carro.
    * Aceita como parâmetro outro Carro e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Carro(Carro umCarro) {
        super(umCarro);
        this.tipoCombustivel = umCarro.getTipoCombustivel();
        this.marca = umCarro.getMarca();
    }

    /**
    * Devolve o tipo combustivel do Carro em tipoCombustivel.
    * @return tipo combustivel do Carro.
    */
    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    /**
    * Atualiza o tipo combustivel do Carro em tipoCombustivel.
    * @param tipoCombustivel Novo tipo combustivel do Carro.
    */
    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    /**
    * Devolve a marca do Carro em marca.
    * @return marca do Carro.
    */
    public String getMarca() {
        return marca;
    }

    /**
    * Atualiza a marca do Carro em marca.
    * @param marca Nova marca do Carro.
    */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
    * Método que devolve a representação em String de Carro.
    * @return String de Carro.
    */
    @Override
    public String toString() {
        return super.toString()+"Carro{" +
                "tipoCombustivel='" + tipoCombustivel + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    /**
    * Implementação do método de igualdade entre dois Carro.
    * Redifinição do método equals de Object.
    * @param o O Carro que é comparado com o recetor.
    * @return Booleano true ou false.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro)) return false;
        if (!super.equals(o)) return false;
        Carro carro = (Carro) o;
        return Objects.equals(getTipoCombustivel(), carro.getTipoCombustivel()) &&
                Objects.equals(getMarca(), carro.getMarca());
    }

    /**
    * Implementação do método de clonagem de um Carro.
    * @return Objeto do tipo Carro.
    */
    public Carro clone() {
        return new Carro(this);
    }
}
