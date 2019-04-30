import java.util.Objects;

public class Carro extends Veiculo {
    
    //variáveis de instância
    private String tipoCombustivel;
    private String matricula;
    private String marca;

    /**
    * Construtor por omissão de Carro.
    */
    public Carro() {
        super();
        this.tipoCombustivel = "";
        this.matricula = "";
        this.marca = "";
    }

    /**
    * Construtor parametrizado de Carro.
    */
    public Carro(int id, boolean disponivel, double velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomiaMax, int autonomia, double newPosX,
                 double newPosY,String tipoCombustivel, String matricula, String marca) {
        super(id, disponivel, velocidadeMed, precoPorKm, consumoPorKm, classificacao, autonomiaMax, autonomia, newPosX, newPosY);
        this.tipoCombustivel = tipoCombustivel;
        this.matricula = matricula;
        this.marca = marca;
    }
    public Carro(int id, double velocidadeMed, double precoPorKm, double consumoPorKm, int autonomiaMax, int autonomia, double newPosX,
                 double newPosY,String tipoCombustivel, String matricula, String marca) {
        super(id, false, velocidadeMed, precoPorKm, consumoPorKm,0, autonomiaMax, autonomia, newPosX, newPosY);
        this.tipoCombustivel = tipoCombustivel;
        this.matricula = matricula;
        this.marca = marca;
    }

    /**
    * Construtor de cópia de Carro.
    * Aceita como parâmetro outro Carro e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Carro(Carro umCarro) {
        super(umCarro);
        this.tipoCombustivel = umCarro.getTipoCombustivel();
        this.matricula = umCarro.getMatricula();
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
    * Devolve a matrícula do Carro em matricula.
    * @return matrícula do Carro.
    */
    public String getMatricula() {
        return matricula;
    }

    /**
    * Atualiza a matrícula do Carro em matricula.
    * @param matricula Nova matrícula do Carro.
    */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
                ", matricula='" + matricula + '\'' +
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
                Objects.equals(getMatricula(), carro.getMatricula()) &&
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
