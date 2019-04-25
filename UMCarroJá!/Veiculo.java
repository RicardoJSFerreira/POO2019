import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe genérica que representa um veículo.
 */

public abstract class Veiculo{ // vou ter de criar uma subclasse? Ja a criei mas ainda n tenho a certwza
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
     * Construtor para objetos da classe Veiculo
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

    public int getId() {
        return id;
    }

    public void setId(int id) { // meter a fazer +1 em relaçao ao anterior
        this.id = id;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getVelocidadeMed(){
        return this.velocidadeMed;
    }
    
    public void setVelocidadeMed(double velocidadeMed){
        this.velocidadeMed = velocidadeMed;
    }
    
    public double getPrecoPorKm(){
        return this.velocidadeMed;
    }
    
    public void setPrecoPorKm(double precoPorKm){
        this.precoPorKm = precoPorKm;
    }
    
    public double getConsumoPorKm(){
        return this.consumoPorKm;
    }
    
    public void setConsumoPorKm(double consumoPorKm){
        this.consumoPorKm = consumoPorKm;
    }
    
    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getAutonomiaMax() {
        return autonomiaMax;
    }

    public void setAutonomiaMax(int autonomiaMax) {
        this.autonomiaMax = autonomiaMax;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
    
    public Ponto getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Ponto posicao) {
        this.posicao = posicao;
    }

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

    public abstract Veiculo clone();
}
