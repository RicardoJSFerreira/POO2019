import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main implements Serializable {

    public static void main() {
        
    	Cliente cli = new Cliente(1, "rjsf311@gmail.com", "Ricardo", "12345", "Rua das Flores", "15-08-1998", 2.0, 3.0, 10);
        System.out.println(cli);
        
        Veiculo v =  new Veiculo(1234,true,66.9,1.3,5.6,10,20,30,4,6);
        System.out.println(v);
        
        ArrayList<Veiculo> veiculoList = new ArrayList<Veiculo>();
        veiculoList.add(v);
        Proprietario p = new Proprietario(23,"pp@gmail.com","miguel","pass","rua das bolinhas","21/07/1998",2000,veiculoList);
        System.out.println(p);
        
        List<User> listaUsers = new ArrayList<>();
        listaUsers.add(cli);
        listaUsers.add(p);
        System.out.println(listaUsers);
        
        List<Veiculo> listaVeiculos = new ArrayList<>();
        listaVeiculos.add(v);
        System.out.println(listaVeiculos);
    }

}
