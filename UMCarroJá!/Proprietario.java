import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Proprietario extends User implements Serializable {

    //variáveis de instância
    private List<Veiculo> veiculos;


    public Proprietario() {
        super();
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Construtor parametrizado de Proprietario.
     */
    public Proprietario(int idUser, String email, String nome, String password, String morada, LocalDate dataNascimento, int classificacao, ArrayList<Veiculo> listVeiculos ) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.veiculos = new ArrayList<Veiculo>(listVeiculos);
    }

    public Proprietario(int idUser, String email, String nome, String password, String morada, LocalDate dataNascimento) {
        super(idUser,email, nome, password, morada, dataNascimento);
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Construtor de cópia de Proprietario.
     * Aceita como parâmetro outro Proprietario e utiliza os métodos de acesso aos valores das variáveis de instância.
     */
    public Proprietario(Proprietario umProprietario) {
        super(umProprietario);
        this.veiculos = umProprietario.getVeiculos();

    }


    /**
     * Devolve a lista de Veículo(s) do Proprietario em veiculos.
     * @return veículos do Proprietario.
     */
    public List<Veiculo> getVeiculos() {
        List<Veiculo> res = new ArrayList<>();
        for(Veiculo s : this.veiculos) {
            res.add(s);
        }
        return res;
    }

    /**
     * Atualiza a lista de Veiculo(s) do Proprietario em veiculos.
     * @param veic Nova lista de veículos do Proprietario.
     */
    public void setVeiculos(List<Veiculo> veic) {
        this.veiculos = new ArrayList<Veiculo>();
        for(Veiculo s : veic) {
            this.veiculos.add(s);
        }
    }

    /**
     * Implementação do método de igualdade entre dois Proprietario.
     * Redifinição do método equals de Object.
     * @param o O Proprietario que é comparado com o recetor.
     * @return Booleano true ou false.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proprietario)) return false;
        if (!super.equals(o)) return false;
        Proprietario that = (Proprietario) o;
        return Objects.equals(getVeiculos(), that.getVeiculos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVeiculos());
    }

    /**
     * Método que devolve a representação em String de Proprietario.
     * @return String de Proprietario.
     */
    @Override
    public String toString() {
        return
                super.toString()+"Proprietario{" +
                        "veiculos " +veiculos +
                        '}';
    }

    /**
     * Implementação do método de clonagem de um Proprietario.
     * @return Objeto do tipo Proprietario.
     */
    public Proprietario clone() {
        return new Proprietario(this);
    }
    public Veiculo getVeiculo(String matricula) {
        for (Veiculo v : veiculos) {
            if (v.getMatricula().equals(matricula)) return v;
        }
        return null;
    }
    public void classificaVeiculo (String matricula,int classificacao){
        Veiculo v = getVeiculo(matricula);
        v.adicionaClassificacao(classificacao);

    }
}
