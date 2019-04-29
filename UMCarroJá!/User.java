import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa um qualquer utilizador, tanto o cliente como
 * proprietário. Aqui constam as informações em comum.
 * O User tem email, nome, password, morada e data de nascimento.
 */
// Teoricamente esta classe deve ser abstract pois nao pode ser iterada, a pergunta é,
// para ter uma class de todos os users se fizer Users.clone fico com todos os proprietarios e todos os clientes tambem?
// E faz sentido fazer isto assim?

public abstract class User { // Perguntar se deve ser abstract ou nao  -> Se for abstract tenho de definir metodos nas subclasses penso eu, tenho d eir ver
    
    //variáveis de instância
    private  int idUser;
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate dataNascimento;

    /**
    * Construtor por omissão de User.
    */
    public User() {
        this.idUser = -1;
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = LocalDate.now();
    }

    /**
    * Construtor parametrizado de User.
    */
    public User(int idUser, String email, String nome, String password, String morada, LocalDate dataNascimento) {
        this.idUser = idUser;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }

    /**
    * Construtor de cópia de User.
    * Aceita como parâmetro outro User e utiliza os métodos de acesso aos valores das variáveis de instância.
    */
    public User(User umUser){
        this.idUser = umUser.getUserId();
        this.email = umUser.getEmail();
        this.nome = umUser.getNome();
        this.password = umUser.getPassword();
        this.morada = umUser.getMorada();
        this.dataNascimento = umUser.getDataNascimento();
    }

    /**
    * Devolve o id do User em idUser.
    * @return id do User.
    */
    public int getUserId() {
        return idUser;
    }

    /**
    * Atualiza o id do User em idUser.
    * @param idUser Novo id do User.
    */
    public void setUserId(int idUser) {
        this.idUser = idUser;
    }

    /**
    * Devolve o email de contacto em email.
    * @return email.
    */
    public String getEmail() {
        return email;
    }

    /**
    * Atualiza o email de contacto em email.
    * @param email Novo email de contacto.
    */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    * Devolve o Nome em nome.
    * @return o nome.
    */
    public String getNome() {
        return nome;
    }

    /**
    * Atualiza o Nome em nome.
    * @param nome Novo nome.
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
    * Devolve a Password de acesso em password.
    * @return password.
    */
    public String getPassword() {
        return password;
    }

    /**
    * Atualiza a password de acesso em password.
    * @param password Nova password de acesso.
    */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
    * Devolve a Morada em morada.
    * @return a morada.
    */
    public String getMorada() {
        return morada;
    }

    /**
    * Atualiza a Morada em morada.
    * @param morada Nova morada.
    */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
    * Devolve a Data de Nascimento em dataNascimento.
    * @return a data de nascimento.
    */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
    * Atualiza a Data de Nascimento em dataNascimento.
    * @param dataNascimento Nova data de nascimento.
    */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
    * Implementação do método de igualdade entre dois User.
    * Redifinição do método equals de Object.
    * @param o O User que é comparado com o recetor.
    * @return Booleano true ou false.
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getNome(), user.getNome()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getMorada(), user.getMorada()) &&
                Objects.equals(getDataNascimento(), user.getDataNascimento());
    }

    /**
    * Método que devolve a representação em String de User.
    * @return String de User.
    */
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", password='" + password + '\'' +
                ", morada='" + morada + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }

    /**
    * Implementação do método de clonagem de um User.
    */
    public abstract User clone();

}