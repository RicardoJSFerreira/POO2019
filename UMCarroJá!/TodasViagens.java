import java.util.*;
import java.util.stream.Collectors;

public class TodasViagens {
    private Map<Integer,Historico> todos; // Deverá ser de Historico ou Pedido?
    // Se for Pedido consigo ter acesso ao historico?
    // Quero ter acesso ao historico e aos pedidos atraves daqui

    public TodasViagens(){
        this.todos = new HashMap<>();
    }

    public TodasViagens(Map<Integer, Historico> novos) {
        this.todos = new HashMap<>();
        for(Map.Entry<Integer,Historico> par:novos.entrySet()){
            this.todos.put(par.getKey(),(par.getValue()).clone());
        }
    }
    public TodasViagens(TodasViagens tv){
        this.todos = tv.getTodasViagens();
    }
    public Historico getHistorico(int id){
        for(Historico c : this.todos.values()){
            if(c.getIdPedido() == id) return c.clone();
        }
        return null;
    }
    public List<Historico> getViagens(){
        List<Historico> res = new ArrayList<Historico>();
        for(Historico c : this.todos.values())
            res.add(c.clone());
        return res;
    }

    public Map<Integer,Historico> getTodasViagens() {
        return this.todos.values().stream().
                collect(Collectors.toMap((c) -> c.getIdPedido(),(c) -> c.clone()));
    }

    @Override
    public String toString() {
        return "TodosUsers{" +
                "todos=" + todos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodasViagens)) return false;
        TodasViagens that = (TodasViagens) o;
        return Objects.equals(todos, that.todos);
    }

    public TodasViagens clone() {
        return new TodasViagens(this);
    }
    public void addViagem(Historico hist){
        this.todos.put(hist.getIdPedido(), hist);
    }

    public boolean containsKey_(Integer key){
        if(this.todos.containsKey(key)) return true;
        else return false;
    }

    public boolean containsValue_(User value){
        if(this.todos.containsValue(value)) return true;
        else return false;
    }

}
