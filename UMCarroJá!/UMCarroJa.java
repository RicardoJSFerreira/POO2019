import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class UMCarroJa { // Vai ter o implements Comparator
    private Map<Integer, Pedido> viagens;
    private Map<Integer, User> users;


    public UMCarroJa() {
        this.viagens = new HashMap<>();
        this.users = new HashMap<>();
    }

    public UMCarroJa(Map<Integer, Pedido> viagens, Map<Integer, User> users) {

        // Viagens
        this.viagens = new HashMap<>();
        for (Map.Entry<Integer, Pedido> par : viagens.entrySet()) {
            this.viagens.put(par.getKey(), (par.getValue()).clone()); // Class User nao pode ser abstract se nao nao posso fazer clone e ter todos os users numa hashmap
        }
        // Users
        this.users = new HashMap<>();
        for (Map.Entry<Integer, User> par : users.entrySet()) {
            this.users.put(par.getKey(), (par.getValue()).clone()); // Class User nao pode ser abstract se nao nao posso fazer clone e ter todos os users numa hashmap
        }
    }

    public UMCarroJa(UMCarroJa uc) {
        this.viagens = uc.getTodasViagens();
        this.users = uc.getTodosUsers();
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
        return Objects.equals(getTodasViagens(), umCarroJa.getTodasViagens()) &&
                Objects.equals(getTodosUsers(), umCarroJa.getTodosUsers());
    }

    public UMCarroJa clone() {
        return new UMCarroJa(this);
    }
    // Users

    public User getUserEmail(String email) {
        for (User c : this.users.values()) {
            if (c.getEmail().equals(email)) return c.clone();
        }
        return null;
    }

    private User getUser(int id) {
        for (User c : this.users.values()) {
            if (c.getUserId() == id) return c.clone();
        }
        return null;
    }

    public Map<Integer, User> getTodosUsers() {
        return this.users.values().stream().
                collect(Collectors.toMap((c) -> c.getUserId(), (c) -> c.clone()));
    }

    // Viagens

    public Pedido getViagem(int id) {
        for (Pedido c : this.viagens.values()) {
            if (c.getIdPedido() == id) return c.clone();
        }
        return null;
    }

    public Map<Integer, Pedido> getTodasViagens() {
        return this.viagens.values().stream().
                collect(Collectors.toMap((c) -> c.getIdPedido(), (c) -> c.clone()));
    }

    public List<Pedido> getListViagens(){
        List<Pedido> res = new ArrayList<Pedido>();
        for(Pedido c : this.viagens.values())
            res.add(c.clone());

        return res;
    }
    // Veiculos
    public void addUser(User user) {
        this.users.put(user.getUserId(), user);
    }

    public void addViagem(Pedido pedido) {
        this.viagens.put(pedido.getIdPedido(), pedido);
    }


    public boolean idPedidoExiste(Integer key) {
        if (this.viagens.containsKey(key)) return true;
        else return false;
    }

    public boolean idUserExiste(Integer key) {
        if (this.users.containsKey(key)) return true;
        else return false;
    }

    public boolean pedidoExiste(Pedido value) {
        if (this.viagens.containsValue(value)) return true;
        else return false;
    }

    public boolean userExiste(User value) {
        if (this.users.containsValue(value)) return true;
        else return false;
    }

    public boolean procuraUserId(Integer id) {
        if (this.getUser(id) == null) return false;
        else return true;
    }

    public boolean procuraUserEmail(String email) {
        if (this.getUserEmail(email) == null) return false;
        else return true;
    }

    public int getUserId(String email) {
        return this.getUserEmail(email).getUserId();
    }

    public boolean verificaPasswordUser(int id, String pass) {
        User u = this.getUser(id);
        if (u.getPassword().equals(pass)) return true;
        else return false;
    }

    public void registaProprietario(String email, String nome, String pass, String morada, int ano, int mes, int dia) {
        Integer id = this.getTodosUsers().size();
        LocalDate dataNascimento = LocalDate.of(ano, mes , dia);
        Proprietario p = new Proprietario(id, email, nome, pass, morada, dataNascimento);
        this.addUser(p);
        System.out.println("Utilizador criado com sucesso");

    }

    public void registaCliente(String email, String nome, String pass, String morada, int ano, int mes, int dia,int x,int y) {
        Integer id = this.getTodosUsers().size();
        LocalDate dataNascimento = LocalDate.of(ano, mes, dia);
        Cliente c = new Cliente(id, email, nome, pass, morada, dataNascimento,x,y);
        this.addUser(c);
        System.out.println("Utilizador criado com sucesso");

    }

    public int verificaTipoUser (int id){
        // 0 cliente
        // 1 proprietario
        User u = this.getUser(id);
        if (u instanceof Cliente) return 0;
        else if (u instanceof Proprietario) return 1;

        // exception??
        return -1;
    }

    public void setNewClientLocation(int id, int x, int y){
        User u = (Cliente)this.getUser(id);
        Ponto p = new Ponto(x,y);
        ((Cliente) u).setPosicao(p);
    }
    public void getListHistorico(int id, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Pedido> l = this.getListViagens();
        for (Pedido historico: l){
            if( historico instanceof Historico && ((historico.getIdCliente()== id) || (historico.getIdProprietario())==id) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                res.add((Historico) historico.clone());
            }
        }
        imprimeListHistoricos(id, res);
    }
    public void imprimeListHistoricos(int id, List<Historico> list){
        User user = getUser(id);
        if(user instanceof Cliente){
        for(Historico h: list){
            User prop = getUser(id);
            System.out.println("Data de Viagem:" + h.getDataViagem() +
                    "Proprietario" + prop.getNome() +
                    " Veiculo "  //(((Proprietario) prop).getVeiculo(h.getIdVeiculo())).getId()
                    + h.getIdVeiculo() +" Valor Pago:" + h.getValorPago()+" euros");

        }
        }
        else{
            for(Historico h: list){
                User cli = getUser(id);
                System.out.println("Data de Viagem:" + h.getDataViagem() +
                        "CLiente" + cli.getNome() +
                        " Veiculo " //(((Proprietario) user).getVeiculo(h.getIdVeiculo())).getId()
                        + h.getIdVeiculo() + " Valor Pago:" + h.getValorPago()+" euros");

            }
        }
    }

    public void getListPedidosToProprietario(int id){
        List<Pedido> res = new ArrayList<Pedido>();
        List<Pedido> l = this.getListViagens();
        for (Pedido pedido: l){
            if( (pedido instanceof Pedido) && pedido.getIdProprietario()==id ) {
                res.add(pedido.clone());
            }
        }
        imprimeListPedidos(res,id);
    }
    public void imprimeListPedidos(List<Pedido> list, int id){
        User prop = getUser(id);
        for(Pedido p: list){
            User c = getUser(p.getIdCliente());
                System.out.println("Cliente:" + c.getNome() +
                        "Veiculo: "+ // Nao esta a funcionar (((Proprietario) prop).getVeiculo(p.getIdVeiculo())).getId()
                         + p.getIdVeiculo() +
                        "Destino: " +p.getDestino());

            }
    }

}
