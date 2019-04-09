
/**
 * Classe que representa um qualquer utilizador, tanto o cliente como 
 * proprietário. Aqui constam as informações em comum.
 * O utilizador tem email, nome, password, morada e data de nascimento.
 */
public abstract class Utilizador{
   
    protected String email;
    protected String nome;
    protected String password;
    protected String morada;
    protected String dataNascimento;

    public String getEmail () {
        return this.email;
    }

    public String getNome () {
        return this.nome;
    }

    public String getPassword () {
        return this.password;
    }

    public String getMorada() {
        return this.morada;
    }

    public String getDataNascimento () {
        return this.dataNascimento;
    }
}
