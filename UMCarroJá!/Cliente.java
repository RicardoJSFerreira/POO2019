import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.io.Serializable;

/**
 * Classe que é subclasse de Utilizador, e que
 * guarda a informação específica ao cliente.
 */

public class Cliente extends Utilizador implements Serializable{
    private Ponto posicao;
    protected ArrayList<String> historico = new ArrayList<String>();
    
    public Cliente(String newEmail, String newNome, String newPassword, String newMorada, String newDataNascimento, double newPosX, double newPosY) {
        this.email = newEmail;
        this.nome = newNome;
        this.password = newPassword;
        this.morada = newMorada;
        this.dataNascimento = newDataNascimento;
        this.posicao = new Ponto<Double>(newPosX, newPosY);
    }

    /**
      * Obter a posição do cliente
      */
    public Ponto getPosition() {
        return this.posicao;
    }

    /**
      * Definir a posição do cliente
      */
    public void setPosition(Ponto newPos) {
        this.posicao = newPos;
    }
    
    @Override
    public String toString() {
        return "<html>Email: " + this.email
             + "<br>Nome: " + this.nome
             + "<br>Password: " + this.password
             + "<br>Morada: " + this.morada
             + "<br>Data Nascimento " + this.dataNascimento
             + "<br>Localização: " + this.posicao;
    }
}
