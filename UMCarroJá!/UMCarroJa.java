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

    private Map<Integer, User> getTodosUsers() {
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

    public void adicionaCarro(int velocidade,int precoPorKm,int consumoPorKm, int autonomiaMax, int autonomia, int x, int y, String tipoCombustivel, String matricula, String marca, int id){// { // Acho que nao é preciso pq nao chega aqui se n for um prop
        //if (verificaTipoUser(id) != 1) throw new UserNaoeProprietarioException("Está logado como um cliente");
        int idVeiculo = getIdUltimoVeiculo();
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        Veiculo v = new Carro(idVeiculo,velocidade,precoPorKm,consumoPorKm,autonomiaMax,autonomia,x,y,tipoCombustivel,matricula,marca);
        veiculos.add(v);
        ((Proprietario) p).setVeiculos(veiculos);

        System.out.println(p);
    }

    public void imprimeListVeiculos(int id){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        int i = 0;
        for (Veiculo v: veiculos) {
            System.out.println(i);
            System.out.println(v);
            // verificar se esta a imprimir matricula
            i++;
        }
    }

    public void abastecerVeiculo(int id, int vei, int comb){ // Funciona
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        int i = 0;
        for (Veiculo v: veiculos) {
            if(i==vei){
                int aut = v.getAutonomia();
                if((aut+comb)>v.getAutonomiaMax())
                    v.setAutonomia(v.getAutonomiaMax());

                else v.setAutonomia(aut+comb);
            }
            i++;
        }
        ((Proprietario) p).setVeiculos(veiculos);
        System.out.println(veiculos);
    }

    public void sinalizarVeiculoComoDisponivel(int id, int veic){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        int i = 0;
        for (Veiculo v: veiculos) {
            if(i==veic){
            v.setDisponivel(true);
            }
            i++;
        }
        ((Proprietario) p).setVeiculos(veiculos);
        System.out.println(veiculos);
    }

    public void alterarPrecoPorKm(int id, int veic, double preco){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        int i = 0;
        for (Veiculo v: veiculos) {
            if(i==veic){
                v.setConsumoPorKm(preco);
            }
            i++;
        }
        ((Proprietario) p).setVeiculos(veiculos);
        System.out.println(veiculos);
    }
// Há aqui uma coisa mal
// idVeiculo no historico, posso ter varios ids iguais e pertencerem a um prop diferente
// idVeiculo vai ter de ser em relaçao a todos os veiculos dos users criados anteriormente
// Tenho de arranjar maneira de identificar o veiculo

    public void getTotalFaturado(int id, int veic, LocalDate begin, LocalDate end){
            List<Historico> res = new ArrayList<Historico>();
            List<Pedido> l = this.getListViagens();
            int totalFaturado=0;
            for (Pedido historico: l){
                if( historico instanceof Historico && (historico.getIdProprietario()== id) && (historico.getIdVeiculo())==veic && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                    totalFaturado=((Historico) historico).getValorPago()+totalFaturado;
                }
            }
            System.out.println("veiculo "+ veic +"faturou: "+totalFaturado);

    }

    public void verificaCombustivel(int id){
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if((double)((v.getAutonomia()*100.0f)/(v.getAutonomiaMax()))<10){
                System.out.println("Veiculo " + v.getId()+ "na posicao" + v.getPosicao() + "está na reserva");
        }
        }
    }

    public int getIdUltimoVeiculo(){
        List<User> us = getListUsers();
        int i=0;
        for (User p :us) {
            if(p instanceof Proprietario){
                List<Veiculo> list =((Proprietario) p).getVeiculos();
                for (Veiculo v: list) {
                    if((v.getId())>i){i=v.getId();
                    }
                }
            }
        }
        System.out.println(i+1);
        return i+1;

    }

}


