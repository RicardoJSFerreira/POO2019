import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe genérica que representa um veículo.
 */

public class Veiculo{
    
    private String tipo;
    private double velocidadeMed;
    private double precoPorKm;
    private double consumoPorKm;
    private int classificacao;
    private int autonomia; //um inteiro que representa a percentagem?
    private Ponto posicao;
    private ArrayList<String> historico = new ArrayList<String>();

    /**
     * Construtor para objetos da classe Veiculo
     */
    public Veiculo(){
        this.tipo = "";
        this.velocidadeMed = 0.0;
        this.precoPorKm = 0.0;
        this.consumoPorKm = 0.0;
        this.classificacao = 0;
        this.autonomia = 0;
        this.posicao = new Ponto(0,0);
        this.historico = new ArrayList<String>();
    }
    
    public Veiculo (String tipo, double velocidadeMed, double precoPorKm, double consumoPorKm, int classificacao, int autonomia, double newPosX,
                    double newPosY, ArrayList<String> historico){
        this.tipo = tipo;
        this.velocidadeMed = velocidadeMed;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        this.classificacao = classificacao;
        this.autonomia = autonomia;
        this.posicao = new Ponto<Double>(newPosX, newPosY);
        this.historico = historico;
    }
    
    public Veiculo (Veiculo umVeiculo){
        this.tipo = umVeiculo.getTipo();
        this.velocidadeMed = umVeiculo.getVelocidadeMed();
        this.precoPorKm = umVeiculo.getPrecoPorKm();
        this.consumoPorKm = umVeiculo.getConsumoPorKm();
        this.classificacao = umVeiculo.getClassificacao();
        this.autonomia = umVeiculo.getAutonomia();
        this.posicao = umVeiculo.getPosicao();
        this.historico = umVeiculo.getHistorico();
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
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
    
    public ArrayList<String> getHistorico() {
        ArrayList<String> r = new ArrayList<String>();
        for(String s: this.historico)
            r.add(s);
        return r;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico=new ArrayList<String>();
        for(String s: historico)
            this.historico.add(s);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Veiculo v = (Veiculo) o;
        return Objects.equals(getTipo(), v.getTipo()) &&
               getVelocidadeMed() == v.getVelocidadeMed() &&
               getPrecoPorKm() == v.getPrecoPorKm() &&
               getConsumoPorKm() == v.getConsumoPorKm() &&
               getClassificacao() == v.getClassificacao() &&
               getAutonomia() == v.getAutonomia() &&
               Objects.equals(getPosicao(), v.getPosicao()) &&
               Objects.equals(getHistorico(), v.getHistorico());
    
    }
    
    @Override
    public String toString() {
        return "Veículo{" +
               "Tipo = " + tipo +
               ", Velocidade média por Km = " + velocidadeMed +
               ", Preço por Km = " + precoPorKm +
               ", Consumo por Km = " + consumoPorKm +
               ", Classificação = " + classificacao +
               ", Autonomia (%) = " + autonomia +
               ", Posição = " + posicao +
               ", Histórico = " + historico +
               "}";
    }
    
    public Veiculo clone() {
        return new Veiculo(this);
    }
}
