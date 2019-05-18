import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class UMCarroJa implements Serializable { // Vai ter o implements Comparator
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

    public void guardaEstado(String nomeFicheiro)throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    public static UMCarroJa loadEstado(String nomeFicheiro)throws FileNotFoundException,IOException,ClassNotFoundException{
        FileInputStream fos = new FileInputStream(nomeFicheiro);
        ObjectInputStream oos = new ObjectInputStream(fos);
        UMCarroJa h = (UMCarroJa) oos.readObject();
        oos.close();
        return h;
    }





    // Users

    public int getUserId(String email) {
        for (User c : this.users.values()) {
            if (c.getEmail().equals(email)) return c.getUserId();
        }
        return -1;
    }

    public User getUser(int id) {
    return this.users.get(id).clone();
    }

    public Map<Integer, User> getTodosUsers() {
        return this.users.values().stream().
                collect(Collectors.toMap((c) -> c.getUserId(), (c) -> c.clone()));
    }

    public List<User> getListUsers(){
        List<User> res = new ArrayList<User>();
        for(User c : this.users.values())
            res.add(c.clone());

        return res;
    }
    // Viagens

    private Pedido getViagem(int id) {
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
    public List<Pedido> getListPedidos(){
        List<Pedido> res = new ArrayList<Pedido>();
        for(Pedido c : this.viagens.values()){

            if(!(c instanceof Historico))
                res.add(c.clone());
        }

        return res;
    }
    public List<Historico> getListHistoricos(){
        List<Historico> res = new ArrayList<Historico>();
        for( Pedido c : this.viagens.values()) {
            if(c instanceof  Historico)
            res.add(((Historico) c).clone());
        }
        return res;
    }

    public List<Historico> getListHistorico(int id, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Historico> l = getListHistoricos();
        for (Historico historico: l){
            if(((historico.getIdCliente()== id) || (historico.getIdProprietario())==id) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                res.add((Historico) historico);
            }
        }
        return res;
    }

    public List<Pedido> getListPedidosToProprietario(int id){
        List<Pedido> res = new ArrayList<Pedido>();
        List<Pedido> l = getListPedidos();
        for (Pedido pedido: l){
            if( pedido.getIdProprietario()==id ) {
                res.add(pedido);
            }
        }
        return res;
    }
    // Veiculos
    public void addUser(User user) {
        this.users.put(user.getUserId(), user);
    }

    public void addViagem(Pedido pedido) {
        this.viagens.put(pedido.getIdPedido(), pedido);
    }




    public boolean pedidoExiste(Pedido value) {
        if (this.viagens.containsValue(value)) return true;
        else return false;
    }

    public boolean userExiste(User value) {
        if (this.users.containsValue(value)) return true;
        else return false;
    }


    public void setNewClientLocation(int id, int x, int y){
        Ponto p = new Ponto(x,y);
        ((Cliente) this.users.get(id)).setPosicao(p);
    }



    public void adicionaCarro(Veiculo v, int id){// { // Acho que nao é preciso pq nao chega aqui se n for um prop
        //if (verificaTipoUser(id) != 1) throw new UserNaoeProprietarioException("Está logado como um cliente");
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        veiculos.add(v);
        ((Proprietario)this.users.get(id)).setVeiculos(veiculos);

    }


    public void abastecerVeiculo(int id, String matricula, int comb){ // Funciona
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();

        for (Veiculo v: veiculos) {
            if(v.getMatricula().equals(matricula)){
                int aut = v.getAutonomia();
                if((aut+comb)>v.getAutonomiaMax())
                    v.setAutonomia(v.getAutonomiaMax());

                else v.setAutonomia(aut+comb);
            }
        }
        ((Proprietario)this.users.get(id)).setVeiculos(veiculos);
    }

    public void sinalizarVeiculoComoDisponivel(int id, String matricula){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if(v.getMatricula().equals(matricula)){
            v.setDisponivel(true);
            }
        }
        ((Proprietario)this.users.get(id)).setVeiculos(veiculos);
    }

    public void alterarPrecoPorKm(int id, String matricula, double preco){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if(v.getMatricula().equals(matricula)){
                v.setPrecoPorKm(preco);
            }
        }
        System.out.println(veiculos);
        ((Proprietario)this.users.get(id)).setVeiculos(veiculos);
    }

    public void aceitarPedido(Pedido p){

        double valorPago = 0.0;
        double dist = (p.getOrigem()).distanceTo(p.getDestino());
        LocalDate dataViagem = LocalDate.now();

        User prop = getUser(p.getIdProprietario());
        List<Veiculo> veiculos = ((Proprietario) prop).getVeiculos();
        for(Veiculo v: veiculos){
            if(v.getMatricula().equals(p.getMatricula())){
                valorPago = dist * v.getPrecoPorKm();
            }
        }

        Historico h = new Historico(p,valorPago,dataViagem);
        System.out.println(h);
        this.viagens.put(h.getIdPedido(),h);
    }
    public Veiculo getVeiculoByID(int idVeiculo, int idUser){
        User u = (Proprietario)getUser(idUser);
        Veiculo v =((Proprietario) u).getVeiculos().get(idVeiculo);
        return v;
    }
    public String getVeiculoMatricula(int idVeiculo, int idUser){
        User u = (Proprietario)getUser(idUser);
        Veiculo v =((Proprietario) u).getVeiculos().get(idVeiculo);
        return v.getMatricula();
    }
    public void recusaPedido(Pedido p){
        this.viagens.remove(p.getIdPedido());
    }

    public List<Veiculo> getListTodosVeiculos(){
        List<Veiculo> res = new ArrayList<Veiculo>();
        for(User u : this.users.values()){
            if (u instanceof Proprietario){
            res.addAll(((Proprietario) u).getVeiculos());
            }
        }
        ((ArrayList<Veiculo>) res).clone();
        return res;
    }
    public List<Veiculo> getListTodosVeiculosDisponiveis(List<Veiculo> l, Ponto origem, Ponto destino){
        List<Veiculo> res = new ArrayList<Veiculo>();
        for(Veiculo v : l){
            if(v.getDisponivel()==true && verificaAutonomiaParaViagem(v,origem,destino)) res.add(v);
        }
        return res;
    }

    public  int getProprietarioFromMatricula(String matricula) {
        List<Veiculo> l = new ArrayList<Veiculo>();
        for (User u : this.users.values()) {
            if (u instanceof Proprietario) {
                if(((Proprietario) u).getVeiculo(matricula)!=null) return u.getUserId();
            }
        }
        return -1;
    }
    public boolean verificaAutonomiaParaViagem(Veiculo v, Ponto origem, Ponto destino){
        double d = origem.distanceTo(destino);
        if(v.getConsumoPorKm()*d <v.getAutonomia()) return true;

        return false;
    }

}


