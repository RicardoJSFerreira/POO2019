import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe genérica que representa um veículo.
 */

public abstract class Veiculo{ // vou ter de criar uma subclasse? Ja a criei mas ainda n tenho a certwza
    
    //variáveis de instância
    private int id; // tenho de ter um idVeiculo para saber qual deles é alugado
    private boolean disponivel;
    private double velocidadeMed;
    private double precoPorKm;
    private double consumoPorKm;
    private int classificacao;
    private int autonomiaMax;
    private int autonomia;
    private Ponto posicao;


    /**
    * Construtor por omissão de Veiculo.
    */
    public Veiculo(){
        this.id = -1;
        this.disponivel = false;
        this.velocidadeMed = 0.0;
        this.precoPorKm = 0.0;
        this.consumoPorKm = 0.0;
        this.classificacao = 0;
        this.autonomia = 0;
        this.posicao = new Ponto(0,0);
    }
    
    /**
    * Construtor parametrizado de Veiculo.
    */
    public Veiculo (int id, boolean disponivel, double velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomiaMax, int autonomia, double newPosX,
                    double newPosY){
        this.id = id;
        this.disponivel = disponivel;
        this.velocidadeMed = velocidadeMed;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        this.classificacao = classificacao;
        this.autonomiaMax = autonomiaMax;
        this.autonomia = autonomia;
        this.posicao = new Ponto<Double>(newPosX, newPosY);
    }
    
    /**
    * Construtor de cópia de Veiculo.
    * Aceita como parâmetro outro Veiculo e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public Veiculo (Veiculo umVeiculo){
        this.id = umVeiculo.getId();
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
    * Devolve o id do Veiculo em id.
    * @return id do Veiculo.
    */
    public int getId() {
        return id;
    }

    /**
    * Atualiza o id do Veiculo em id.
    * @param id Novo id do Veiculo.
    */
    public void setId(int id) { // meter a fazer +1 em relaçao ao anterior
        this.id = id;
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
    public double getVelocidadeMed(){
        return this.velocidadeMed;
    }
    
    /**
    * Atualiza a velocidade média do Veiculo em velocidadeMed.
    * @param velocidadeMed Nova velocidade média do Veiculo.
    */
    public void setVelocidadeMed(double velocidadeMed){
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
    
    /**
    * Devolve a classificação do Veiculo em classificacao.
    * @return classificação do Veiculo.
    */
    public int getClassificacao() {
        return classificacao;
    }

    /**
    * Atualiza a classificação do Veiculo em classificacao.
    * @param classificacao Nova classificação do Veiculo.
    */
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
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

    /**
    * Implementação do método de igualdade entre dois Veiculo.
    * Redifinição do método equals de Object.
    * @param o O Veiculo que é comparado com o recetor.
    * @return Booleano true ou false.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo)) return false;
        Veiculo veiculo = (Veiculo) o;
        return getDisponivel() == veiculo.getDisponivel() &&
                Double.compare(veiculo.getVelocidadeMed(), getVelocidadeMed()) == 0 &&
                Double.compare(veiculo.getPrecoPorKm(), getPrecoPorKm()) == 0 &&
                Double.compare(veiculo.getConsumoPorKm(), getConsumoPorKm()) == 0 &&
                getClassificacao() == veiculo.getClassificacao() &&
                getAutonomiaMax() == veiculo.getAutonomiaMax() &&
                getAutonomia() == veiculo.getAutonomia() &&
                Objects.equals(getPosicao(), veiculo.getPosicao());
    }

    /**
    * Método que devolve a representação em String de Veiculo.
    * @return String de Veiculo.
    */
    @Override
    public String toString() {
        return "Veiculo{" +
                ", disponivel=" + disponivel +
                ", velocidadeMed=" + velocidadeMed +
                ", precoPorKm=" + precoPorKm +
                ", consumoPorKm=" + consumoPorKm +
                ", classificacao=" + classificacao +
                ", autonomiaMax=" + autonomiaMax +
                ", autonomia=" + autonomia +
                ", posicao=" + posicao +
                '}';
    }

    /**
    * Implementação do método de clonagem de um Veiculo.
    */
    public abstract Veiculo clone();
}
