public class PedidoNaoExisteException extends Exception {
    // instance variables - replace the example below with your own

    /**
     * Constructor vazio para a exceção
     */
    public PedidoNaoExisteException()
    {
        super();
    }
    /**
     * Constructor standart para a exceção
     */
    public PedidoNaoExisteException(String msg)
    {
        super(msg);
    }
}