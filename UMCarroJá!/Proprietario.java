import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.io.Serializable;

/**
 * Classe que é subclasse de Utilizador, e que
 * guarda a informação específica ao proprietário.
 * 
 */
public class Proprietario extends Utilizador implements Serializable{
    
    protected ArrayList<String> alugueres = new ArrayList<String>();
}
