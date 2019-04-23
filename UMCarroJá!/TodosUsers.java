import java.util.*;
import java.util.stream.Collectors;

public class TodosUsers {
    private Map<Integer,User> todos;

    public TodosUsers(){
        this.todos = new HashMap<>();
    }

    public TodosUsers(Map<Integer, User> novos) {
        this.todos = new HashMap<>();
        for(Map.Entry<Integer,User> par:novos.entrySet()){
            this.todos.put(par.getKey(),(par.getValue()).clone()); // Class User nao pode ser abstract se nao nao posso fazer clone e ter todos os users numa hashmap
        }
    }

    public TodosUsers(TodosUsers tu){
        this.todos = tu.getTodosUsers();
    }
    public User getUser(int id){
        for(User c : this.todos.values()){
            if(c.getUserId() == id) return c.clone();
        }
        return null;
    }
    public User getUserEmail(String email){
        for(User c : this.todos.values()){
            if(c.getEmail().equals(email)) return c.clone();
        }
        return null;
    }

    public List<User> getUsers(){
        List<User> res = new ArrayList<User>();
        for(User c : this.todos.values())
            res.add(c.clone());
        return res;
    }

    public Map<Integer,User> getTodosUsers() {
        return this.todos.values().stream().
                collect(Collectors.toMap((c) -> c.getUserId(),(c) -> c.clone()));
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
        if (!(o instanceof TodosUsers)) return false;
        TodosUsers that = (TodosUsers) o;
        return Objects.equals(todos, that.todos);
    }

    public TodosUsers clone() {
        return new TodosUsers(this);
    }

    public void addUser(User user){
        this.todos.put(user.getUserId(), user);
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
