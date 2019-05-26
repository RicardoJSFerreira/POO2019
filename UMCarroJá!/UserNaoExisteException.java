public class UserNaoExisteException extends Exception {
    // instance variables - replace the example below with your own

    /**
     * Constructor vazio para a exceção
     */
    public UserNaoExisteException()
    {
        super();
    }
    /**
     * Constructor standart para a exceção
     */
    public UserNaoExisteException(String msg)
    {
        super(msg);
    }
}