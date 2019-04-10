import java.io.Serializable;
/**
 * Classe genérica que representa um ponto.
 * Pode ser instanciada com valores de qualquer tipo que
 * herde to tipo Number. Todos os valores são convertidos para double,
 * após calculos. 
 */
// Acho que classe ponto vai ser com ints e nao é necessario ser um T extends Number
public class Ponto<T extends Number> implements Serializable{

    private T x;
    private T y;

    /*
     * Construtor principal para objetos da classe Ponto
     */
    public Ponto(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Ponto(Ponto<T> p) {
        this(p.getX(), p.getY());
    }

    public Ponto() {
        throw new java.lang.Error("Não é possível instanciar um novo ponto sem fornecer coordenadas iniciais.");
    }

    /**
     * Retorna a coordenada X do ponto
     *
     * @return     A coordenada x
     */
    public T getX() {
        return this.x;
    }

    /**
     * Retorna a coordenada Y do ponto
     *
     * @return     A coordenada y
     */
    public T getY() {
        return this.y;
    }

    /**
     * Define a coordenada X do ponto
     *
     * @return    A coordenada x
     */
    public void setX(T x) {
        this.x = x;
    }

    /**
     * Define a coordenada Y do ponto
     *
     * @return    A coordenada y
     */
    public void setY(T y) {
        this.y = y;
    }

    /**
     * Retorna a distância deste ponto para outro.
     * O valor double dos dois pontos são usados no cálculo.
     *
     * @param  b   Ponto para o qual se calcula a distância
     * @return     A distância entre este ponto e o b
     */
    public double distanceTo(Ponto b) {

        double dX = Math.abs( b.getX().doubleValue() - this.x.doubleValue() );
        double dY = Math.abs( b.getY().doubleValue() - this.y.doubleValue() );

        return Math.sqrt( Math.pow(dX, 2) + Math.pow(dY, 2) );
    }

    @Override
    public String toString() {
        return "x: " + this.x + ", "
                + "y: " + this.y;
    }
}