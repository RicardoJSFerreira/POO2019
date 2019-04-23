import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa um qualquer utilizador, tanto o cliente como
 * proprietário. Aqui constam as informações em comum.
 * O utilizador tem email, nome, password, morada e data de nascimento.
 */
// Teoricamente esta classe deve ser abstract pois nao pode ser iterada, a pergunta é,
// para ter uma class de todos os users se fizer Users.clone fico com todos os proprietarios e todos os clientes tambem?
// E faz sentido fazer isto assim?

public class User { // Perguntar se deve ser abstract ou nao  -> Se for abstract tenho de definir metodos nas subclasses penso eu, tenho d eir ver
    private  int idUser;
    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date dataNascimento;

    public User() {
        this.idUser = -1;
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = new Date();
    }
    public User(int idUser, String email, String nome, String password, String morada, Date dataNascimento) {
        this.idUser = idUser;
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    public User(User umUser){
        this.idUser = umUser.getUserId();
        this.email = umUser.getEmail();
        this.nome = umUser.getNome();
        this.password = umUser.getPassword();
        this.morada = umUser.getMorada();
        this.dataNascimento = umUser.getDataNascimento();
    }

    public int getUserId() {
        return idUser;
    }

    public void setUserId(int idUser) {
        this.idUser = idUser;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


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


    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", password='" + password + '\'' +
                ", morada='" + morada + '\'' +
                ", dataNascimento='" + dataNascimento.getDate() + '-' + dataNascimento.getMonth() + '-' + dataNascimento.getYear() + '\'' +
                '}';
    }

    public User clone() {
        return new User(this);
    }
}