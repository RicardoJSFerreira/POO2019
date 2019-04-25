import java.util.*;
import java.util.stream.Collectors;

public class UMCarroJa {
        private Map<Integer,Pedido> viagens;
        private Map<Integer, User> users;



    public UMCarroJa() {
        this.viagens = new HashMap<>();
        this.users = new HashMap<>() ;
    }
    public UMCarroJa(Map<Integer, Pedido> viagens, Map<Integer, User> users) {

        // Viagens
        this.viagens = new HashMap<>();
        for(Map.Entry<Integer,Pedido> par:viagens.entrySet()){
            this.viagens.put(par.getKey(),(par.getValue()).clone()); // Class User nao pode ser abstract se nao nao posso fazer clone e ter todos os users numa hashmap
        }
        // Users
        this.users = new HashMap<>();
        for(Map.Entry<Integer,User> par:users.entrySet()){
            this.users.put(par.getKey(),(par.getValue()).clone()); // Class User nao pode ser abstract se nao nao posso fazer clone e ter todos os users numa hashmap
        }
    }

    public UMCarroJa(UMCarroJa uc){
            this.viagens = uc.getTodasViagens();
            this.users = uc.getTodosUsers();
        }

    // Users

    public Map<Integer,User> getTodosUsers() {
        return this.users.values().stream().
                collect(Collectors.toMap((c) -> c.getUserId(),(c) -> c.clone()));
    }

    public List<User> getUsers(){ // para que é isto
        List<User> res = new ArrayList<User>();
        for(User c : this.users.values())
            res.add(c.clone());
        return res;
    }
    public User getUserEmail(String email){
        for(User c : this.users.values()){
            if(c.getEmail().equals(email)) return c.clone();
        }
        return null;
    }
    public User getUser(int id){
        for(User c : this.users.values()){
            if(c.getUserId() == id) return c.clone();
        }
        return null;
    }

    public Map<Integer,Pedido> getTodasViagens() {
        return this.viagens.values().stream().
                collect(Collectors.toMap((c) -> c.getIdPedido(),(c) -> c.clone()));
    }

    public Pedido getPedido(int id){
        for(Pedido c : this.viagens.values()){
            if(c.getIdPedido() == id) return c.clone();
        }
        return null;
    }


    public List<Pedido> getViagens(){
        List<Pedido> res = new ArrayList<Pedido>();
        for(Pedido p : this.viagens.values())
            res.add(p.clone());
        return res;
    }


    @Override
    public String toString() {
        return "UMCarroJa{" +
                "viagens=" + viagens +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UMCarroJa)) return false;
        UMCarroJa umCarroJa = (UMCarroJa) o;
        return Objects.equals(getViagens(), umCarroJa.getViagens()) &&
                Objects.equals(getUsers(), umCarroJa.getUsers());
    }

    public UMCarroJa clone() {
        return new UMCarroJa(this);
    }

    public void addUser(User user){
        this.users.put(user.getUserId(), user);
    }

    public void addViagem(Pedido pedido){
        this.viagens.put(pedido.getIdPedido(), pedido);
    }



    public boolean containsKeyViagem_(Integer key){
        if(this.viagens.containsKey(key)) return true;
        else return false;
    }

    public boolean containsKeyUser_(Integer key){
        if(this.users.containsKey(key)) return true;
        else return false;
    }

    public boolean containsValue_(Pedido value){
        if(this.viagens.containsValue(value)) return true;
        else return false;
    }
    public boolean containsValue_(User value){
        if(this.users.containsValue(value)) return true;
        else return false;
    }



}
