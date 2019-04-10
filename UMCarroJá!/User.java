import java.util.Objects;

/**
 * Classe que representa um qualquer utilizador, tanto o cliente como
 * proprietário. Aqui constam as informações em comum.
 * O utilizador tem email, nome, password, morada e data de nascimento.
 */

public abstract class User { // Perguntar se deve ser abstract e se atributos devem ser protected ou private
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataNascimento;

    public User() {
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = "";
    }
    public User(String email, String nome, String password, String morada, String dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    public User(User umUser){
        this.email = umUser.getEmail();
        this.nome = umUser.getNome();
        this.password = umUser.getPassword();
        this.morada = umUser.getMorada();
        this.dataNascimento = umUser.getDataNascimento();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getMorada(), that.getMorada()) &&
                Objects.equals(getDataNascimento(), that.getDataNascimento());
    }


    public String toString() {
        return "Utilizador{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", password='" + password + '\'' +
                ", morada='" + morada + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }

}