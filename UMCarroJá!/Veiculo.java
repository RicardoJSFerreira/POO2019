import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe genérica que representa um veículo.
 */

public abstract class Veiculo implements Serializable { // vou ter de criar uma subclasse? Ja a criei mas ainda n tenho a certwza
    
    //variáveis de instância
    private String matricula;
    private int nifProprietario;
    private boolean disponivel;
    private int velocidadeMed;
    private double precoPorKm;
    private double consumoPorKm;
    private List<Integer> classificacao;
    private int autonomiaMax;
    private int autonomia;
    private Ponto posicao;


    /**
    * Construtor por omissão de Veiculo.
    */
    public Veiculo(){
        this.matricula= "";
        this.nifProprietario = -1;
        this.disponivel = false;
        this.velocidadeMed = 0;
        this.precoPorKm = 0.0;
        this.consumoPorKm = 0.0;
        this.classificacao =  new ArrayList<Integer>();
        this.autonomia = 0;
        this.posicao = new Ponto(0,0);
    }
    
    /**
    * Construtor parametrizado de Veiculo.
    */
    public Veiculo (String matricula, int nifProp, boolean disponivel, int velocidadeMed, double precoPorKm, double consumoPorKm, int autonomiaMax, int autonomia, double newPosX,
                    double newPosY){
        this.matricula = matricula;
        this.nifProprietario = nifProp;
        this.disponivel = disponivel;
        this.velocidadeMed = velocidadeMed;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        List<Integer> cla = new ArrayList<>();
        this.classificacao = cla;
        this.autonomiaMax = autonomiaMax;
        this.autonomia = autonomia;
        this.posicao = new Ponto<Double>(newPosX, newPosY);
    }
    public Veiculo (String matricula, int nifProp, boolean disponivel, int velocidadeMed, double precoPorKm, double consumoPorKm, int autonomiaMax, int autonomia, Ponto p){
        this.matricula = matricula;
        this.nifProprietario = nifProp;
        this.disponivel = disponivel;
        this.velocidadeMed = velocidadeMed;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        List<Integer> cla = new ArrayList<>();
        this.classificacao = cla;
        this.autonomiaMax = autonomiaMax;
        this.autonomia = autonomia;
        this.posicao = p;
    }

    public Veiculo (String matricula, int nifProp, boolean disponivel, int velocidadeMed, double precoPorKm, double consumoPorKm, ArrayList<Integer> classificacoes, int autonomiaMax, int autonomia,Ponto posicao){
        this.matricula = matricula;
        this.nifProprietario = nifProp;
        this.disponivel = disponivel;
        this.velocidadeMed = velocidadeMed;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        this.classificacao =new ArrayList<Integer>(classificacoes);
        this.autonomiaMax = autonomiaMax;
        this.autonomia = autonomia;
        this.posicao = posicao;
    }
    /**
    * Construtor de cópia de Veiculo.
    * Aceita como parâmetro outro Veiculo e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Veiculo (Veiculo umVeiculo){
        this.matricula = umVeiculo.getMatricula();
        this.nifProprietario = umVeiculo.getNifProprietario();
        this.disponivel = umVeiculo.getDisponivel();
        this.velocidadeMed = umVeiculo.getVelocidadeMed();
        this.precoPorKm = umVeiculo.getPrecoPorKm();
        this.consumoPorKm = umVeiculo.getConsumoPorKm();
        this.classificacao = umVeiculo.getClassificacao();
        this.autonomiaMax = umVeiculo.getAutonomiaMax();
        this.autonomia = umVeiculo.getAutonomia();
        this.posicao = umVeiculo.getPosicao();

    }

    /**
    * Devolve a matricula do Veiculo em .
    * @return matricula do Veiculo.
    */
    public String getMatricula() {
        return matricula;
    }

    /**
    * Atualiza a matricula do veiculo.
    * @param matricula Novo id do Veiculo.
    */
    public void setMatricula(String matricula) { // meter a fazer +1 em relaçao ao anterior
        this.matricula = matricula;
    }

    public int getNifProprietario() {
        return nifProprietario;
    }

    public void setNifProprietario(int nifProprietario) {
        this.nifProprietario = nifProprietario;
    }

    /**
    * Devolve a disponibilidade Veiculo em disponivel.
    * @return disponibilidade do Veiculo.
    */
    public boolean getDisponivel() {
        return disponivel;
    }

    /**
    * Atualiza a disponibilidade do Veiculo em disponivel.
    * @param disponivel Nova disponibilidade do Veiculo.
    */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
    * Devolve a velocidade média do Veiculo em velocidadeMed.
    * @return velocidade média do Veiculo.
    */
    public int getVelocidadeMed(){
        return this.velocidadeMed;
    }
    
    /**
    * Atualiza a velocidade média do Veiculo em velocidadeMed.
    * @param velocidadeMed Nova velocidade média do Veiculo.
    */
    public void setVelocidadeMed(int velocidadeMed){
        this.velocidadeMed = velocidadeMed;
    }
    
    /**
    * Devolve o preço médio do Veiculo por km em precoPorKm.
    * @return preço médio por km do Veiculo.
    */
    public double getPrecoPorKm(){
        return this.precoPorKm;
    }
    
    /**
    * Atualiza o preço médio do Veiculo por km em precoPorKm.
    * @param precoPorKm Novo preço médio por km do Veiculo.
    */
    public void setPrecoPorKm(double precoPorKm){
        this.precoPorKm = precoPorKm;
    }
    
    /**
    * Devolve o consumo médio do Veiculo por km em consumoPorKm.
    * @return consumo médio por km do Veiculo.
    */
    public double getConsumoPorKm(){
        return this.consumoPorKm;
    }
    
    /**
    * Atualiza o consumo médio do Veiculo por km em consumoPorKm.
    * @param consumoPorKm Novo consumo médio por km do Veiculo.
    */
    public void setConsumoPorKm(double consumoPorKm){
        this.consumoPorKm = consumoPorKm;
    }


    public List<Integer> getClassificacao() {
        List<Integer> res = new ArrayList<>();
        for(Integer s : this.classificacao) {
            res.add(s);
        }
        return res;
    }


    public void setClassificacao(List<Integer> res) {
        this.classificacao = new ArrayList<Integer>();
        for(Integer s : res) {
            this.classificacao.add(s);
        }
    }

    /**
    * Devolve a autonomia máxima do Veiculo em autonomiaMax.
    * @return autonomia máxima do Veiculo.
    */
    public int getAutonomiaMax() {
        return autonomiaMax;
    }

    /**
    * Atualiza a autonomia máxima do Veiculo em autonomiaMax.
    * @param autonomiaMax Nova autonomia máxima do Veiculo.
    */
    public void setAutonomiaMax(int autonomiaMax) {
        this.autonomiaMax = autonomiaMax;
    }

    /**
    * Devolve a autonomia do Veiculo em autonomia.
    * @return autonomia do Veiculo.
    */
    public int getAutonomia() {
        return autonomia;
    }

    /**
    * Atualiza a autonomia do Veiculo em autonomia.
    * @param autonomia Nova autonomia do Veiculo.
    */
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
    
    /**
    * Devolve a posição do Veiculo em posicao.
    * @return posição do Veiculo.
    */
    public Ponto getPosicao() {
        return this.posicao;
    }

    /**
    * Atualiza a posição do Veiculo em posicao.
    * @param posicao Nova posiçao do Veiculo.
    */
    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return getNifProprietario() == veiculo.getNifProprietario() &&
                getDisponivel() == veiculo.getDisponivel() &&
                Double.compare(veiculo.getVelocidadeMed(), getVelocidadeMed()) == 0 &&
                Double.compare(veiculo.getPrecoPorKm(), getPrecoPorKm()) == 0 &&
                Double.compare(veiculo.getConsumoPorKm(), getConsumoPorKm()) == 0 &&
                getClassificacao() == veiculo.getClassificacao() &&
                getAutonomiaMax() == veiculo.getAutonomiaMax() &&
                getAutonomia() == veiculo.getAutonomia() &&
                getMatricula().equals(veiculo.getMatricula()) &&
                getPosicao().equals(veiculo.getPosicao());
    }





    @Override
    public int hashCode() {
        return Objects.hash(getMatricula(), getDisponivel(), getVelocidadeMed(), getPrecoPorKm(), getConsumoPorKm(), getClassificacao(), getAutonomiaMax(), getAutonomia(), getPosicao());
    }

    /**
    * Método que devolve a representação em String de Veiculo.
    * @return String de Veiculo.
    */
    @Override
    public String toString() {
        return "Veiculo{" +
                "matricula='" + matricula + '\'' +
                ", nifProprietario=" + nifProprietario +
                ", disponivel=" + disponivel +
                ", velocidadeMed=" + velocidadeMed +
                ", precoPorKm=" + precoPorKm +
                ", consumoPorKm=" + consumoPorKm +
                ", classificacao=" + classificacao +
                "Media de classificacao: " + getMediaClassificacao() +
                ", autonomiaMax=" + autonomiaMax +
                ", autonomia=" + autonomia +
                ", posicao=" + posicao +
                '}';
    }

    /**
    * Implementação do método de clonagem de um Veiculo.
    */
    public abstract Veiculo clone();

    public List<Integer> addClassificacao(int novaClassificacao) {
        List<Integer> l = new ArrayList<>();
        for(Integer i : this.classificacao) {
            l.add(i);
        }
        l.add(novaClassificacao);

        return l;

    }

    public void adicionaClassificacao(int novaClassificacao) {
        this.classificacao = addClassificacao(novaClassificacao);
    }

    public double getMediaClassificacao() {
        return this.getClassificacao().stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }
}
