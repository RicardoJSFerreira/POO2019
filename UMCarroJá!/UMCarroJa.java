import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



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

    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static UMCarroJa loadEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream(nomeFicheiro);
        ObjectInputStream oos = new ObjectInputStream(fos);
        UMCarroJa h = (UMCarroJa) oos.readObject();
        oos.close();
        return h;
    }

    public static UMCarroJa importaCSV(String fich)
            throws FileNotFoundException, IOException {

        UMCarroJa ucj = new UMCarroJa();
        List<String> linhas = UMCarroJa.lerCSV(fich);
        linhas.forEach(s -> ucj.csv2UCJ(s));
        return ucj;
    }

    private static List<String> lerCSV(String fich) throws FileNotFoundException, IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fich));
        String linha;

        while ((linha = br.readLine()) != null) {
            linhas.add(linha);
        }
        br.close();
        return linhas;
    }


    private void csv2UCJ(String csv) {

            UMCarroJa ucj = new UMCarroJa();
            String[] atributos;
            String tipo;
            String[] divide = csv.split(":|\\,",2);
            tipo = divide[0];

        // é preciso testar as possíveis excepções de as strings que forem lidas
        // não estarem no formato adequado
        // Devem ser testadas as excepções e, neste caso, opta-se por retornar null.

        String nome;
        int nif;
        String email;
        String morada;
        String pass = "pass";
        LocalDate dataNascimento;

        switch (tipo) {
            case "NovoProp":
                atributos = divide[1].split(",");
                nome = atributos[0];
                nif = Integer.parseInt(atributos[1]);
                email = atributos[2];
                morada = atributos[3];
                dataNascimento = LocalDate.now();
                Proprietario p = new Proprietario(nif, email, nome, pass, morada, dataNascimento);
                this.addUser(p);

                break;
            case "NovoCliente":
                atributos = divide[1].split(",");
                nome = atributos[0];
                nif = Integer.parseInt(atributos[1]);
                email = atributos[2];
                morada = atributos[3];
                dataNascimento = LocalDate.now();
                Ponto posicaoCLiente = new Ponto(Double.parseDouble(atributos[4]), Double.parseDouble(atributos[4]));
                Cliente c = new Cliente(nif, email, nome, pass, morada, dataNascimento, posicaoCLiente);
                this.addUser(c);
                break;
            case "NovoCarro":
                atributos = divide[1].split(",");
                String tipoCombustivel = atributos[0];
                String marca = atributos[1];
                String matricula = atributos[2];
                int nifProp = Integer.parseInt(atributos[3]);
                int velocidadeMedia = Integer.parseInt(atributos[4]);
                double precoKM = Double.parseDouble(atributos[5]);
                double consumoKM = Double.parseDouble(atributos[6]);
                int autonomia = Integer.parseInt(atributos[7]);
                Ponto posicaoVeiculo = new Ponto(Double.parseDouble(atributos[8]), Double.parseDouble(atributos[9]));
                Veiculo car = new Carro(matricula, nifProp,true, velocidadeMedia, precoKM, consumoKM, autonomia, autonomia, posicaoVeiculo, tipoCombustivel, marca);
                this.addCarro(nifProp, car);
                break;

            case "Aluguer":
                atributos = divide[1].split(",");
                int nifCliente = Integer.parseInt(atributos[0]);
                Ponto posicaoDestino = new Ponto(Double.parseDouble(atributos[1]), Double.parseDouble(atributos[2]));
                tipoCombustivel = atributos[3];
                String prefere = atributos[4];
                this.geraPedido(nifCliente, posicaoDestino, tipoCombustivel, prefere);
                break;
            case "Classificar":
                atributos = divide[1].split(",");
                int classificacao = Integer.parseInt(atributos[1]);
                if (getProprietarioFromMatricula(atributos[0]) > 0) {
                    int propi = getProprietarioFromMatricula(atributos[0]);
                    matricula = atributos[0];
                    this.addClassificacaoVeiculo(propi, matricula, classificacao);
                } else if (this.users.containsKey(Integer.parseInt(atributos[0]))) {
                    int user = Integer.parseInt(atributos[0]);
                    this.addClassificacaoUser(user, classificacao);
                }
                break;


        }

    }


    // Users

    public int getUserId(String email) {
        for (User c : this.users.values()) {
            if (c.getEmail().equals(email)) return c.getNif();
        }
        return -1;
    }

    public User getUser(int id) {
        return this.users.get(id).clone();
    }

    public Map<Integer, User> getTodosUsers() {
        return this.users.values().stream().
                collect(Collectors.toMap((c) -> c.getNif(), (c) -> c.clone()));
    }

    public List<User> getListUsers() {
        List<User> res = new ArrayList<User>();
        for (User c : this.users.values())
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

    public List<Pedido> getListViagens() {
        List<Pedido> res = new ArrayList<Pedido>();
        for (Pedido c : this.viagens.values())
            res.add(c.clone());

        return res;
    }

    public List<Pedido> getListPedidos() {
        List<Pedido> res = new ArrayList<Pedido>();
        for (Pedido c : this.viagens.values()) {

            if (!(c instanceof Historico))
                res.add(c.clone());
        }

        return res;
    }

    public List<Historico> getListHistoricos() {
        List<Historico> res = new ArrayList<Historico>();
        for (Pedido c : this.viagens.values()) {
            if (c instanceof Historico)
                res.add(((Historico) c).clone());
        }
        return res;
    }

    public List<Historico> getListHistorico(int id, LocalDate begin, LocalDate end) {
        List<Historico> res = new ArrayList<Historico>();
        List<Historico> l = getListHistoricos();
        for (Historico historico : l) {
            if (((historico.getIdCliente() == id) || (historico.getIdProprietario()) == id) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                res.add((Historico) historico);
            }
        }
        return res;
    }

    public List<Pedido> getListPedidosToProprietario(int id) {
        List<Pedido> res = new ArrayList<Pedido>();
        List<Pedido> l = getListPedidos();
        for (Pedido pedido : l) {
            if (pedido.getIdProprietario() == id) {
                res.add(pedido);
            }
        }
        return res;
    }

    // Veiculos
    public void addUser(User user) {
        this.users.put(user.getNif(), user);
    }

    public void addViagem(Pedido pedido) {
        this.viagens.put(pedido.getIdPedido(), pedido);
    }

    public void addCarro(int nifProp, Veiculo v) {
        User p = getUser(nifProp);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        veiculos.add(v);
        ((Proprietario) this.users.get(nifProp)).setVeiculos(veiculos);
    }

    public void addClassificacaoUser(int nif, int cla) {
        this.users.get(nif).adicionaClassificacao(cla);
    }

    public void addClassificacaoVeiculo(int prop, String matricula, int classificacao) {
        ((Proprietario) this.users.get(prop)).classificaVeiculo(matricula, classificacao);
    }

    public int qualificacaoUser(int id){
        User u = getUser(id);
        return u.getClassificacao().get(0);
    }
    public double qualificacaoVeiculo(String matricula){
        Veiculo u = getVeiculoFromMatricula(matricula);
        return u.getMediaClassificacao();
    }
    public void geraPedido(int nifCliente, Ponto posicaoDestino, String tipoCombustivel, String prefere) {

        String matricula;
        double dist;
        int idPedido;
        int idProp;

        if(prefere.equals("MaisBarato")) {
                Veiculo vMaisBarato = alugaCarroBarato(posicaoDestino, tipoCombustivel);
                matricula = vMaisBarato.getMatricula();
                User c = getUser(nifCliente);

                dist = ((Cliente) c).getPosicao().distanceTo(vMaisBarato.getPosicao());
                idPedido = getTodasViagens().size();
                idProp = getProprietarioFromMatricula(matricula);
                Pedido p = new Pedido(idPedido, nifCliente, idProp, matricula, vMaisBarato.getPosicao(), posicaoDestino, dist);
                addViagem(p);

        }
            else if(prefere.equals("MaisPerto")){
            User cli = getUser(nifCliente);
            Ponto origem = ((Cliente) cli).getPosicao();
            Veiculo vMaisPerto = alugaCarroMaisProx(origem,posicaoDestino, tipoCombustivel);
            matricula = vMaisPerto.getMatricula();

            dist = ((Cliente) cli).getPosicao().distanceTo(vMaisPerto.getPosicao());
            idPedido = getTodasViagens().size();
            idProp = getProprietarioFromMatricula(matricula);
            Pedido ped = new Pedido(idPedido, nifCliente, idProp, matricula, vMaisPerto.getPosicao(), posicaoDestino, dist);
            addViagem(ped);
        }





    }

    public boolean pedidoExiste(Pedido value) {
        if (this.viagens.containsValue(value)) return true;
        else return false;
    }

    public boolean userExiste(int id) {
        if (this.users.containsKey(id)) return true;
        else return false;
    }


    public void setNewClientLocation(int id, double x, double y) {
        Ponto p = new Ponto(x, y);
        ((Cliente) this.users.get(id)).setPosicao(p);
    }


    public void abastecerVeiculo(int id, String matricula, int comb) { // Funciona
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();

        for (Veiculo v : veiculos) {
            if (v.getMatricula().equals(matricula)) {
                int aut = v.getAutonomia();
                if ((aut + comb) > v.getAutonomiaMax())
                    v.setAutonomia(v.getAutonomiaMax());

                else v.setAutonomia(aut + comb);
            }
        }
        ((Proprietario) this.users.get(id)).setVeiculos(veiculos);
    }

    public void sinalizarVeiculoComoDisponivel(int id, String matricula) {
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v : veiculos) {
            if (v.getMatricula().equals(matricula)) {
                v.setDisponivel(true);
            }
        }
        ((Proprietario) this.users.get(id)).setVeiculos(veiculos);
    }

    public void alterarPrecoPorKm(int id, String matricula, double preco) {
        User p = getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v : veiculos) {
            if (v.getMatricula().equals(matricula)) {
                v.setPrecoPorKm(preco);
            }
        }
        System.out.println(veiculos);
        ((Proprietario) this.users.get(id)).setVeiculos(veiculos);
    }

    public void aceitarPedido(Pedido p) {

        double valorPago = 0.0;
        double dist = (p.getOrigem()).distanceTo(p.getDestino());
        LocalDate dataViagem = LocalDate.now();

        User prop = getUser(p.getIdProprietario());
        List<Veiculo> veiculos = ((Proprietario) prop).getVeiculos();
        for (Veiculo v : veiculos) {
            if (v.getMatricula().equals(p.getMatricula())) {
                valorPago = dist * v.getPrecoPorKm();
            }
        }

        Historico h = new Historico(p, valorPago, dataViagem);
        System.out.println(h);
        this.viagens.put(h.getIdPedido(), h);
    }

    public Veiculo getVeiculoByID(int idVeiculo, int idUser) {
        User u = (Proprietario) getUser(idUser);
        Veiculo v = ((Proprietario) u).getVeiculos().get(idVeiculo).clone();
        return v;
    }

    public String getVeiculoMatricula(int idVeiculo, int idUser) {
        User u = (Proprietario) getUser(idUser);
        Veiculo v = ((Proprietario) u).getVeiculos().get(idVeiculo);
        return v.getMatricula();
    }

    public void recusaPedido(Pedido p) {
        this.viagens.remove(p.getIdPedido());
    }

    public List<Veiculo> getListTodosVeiculos() {
        List<Veiculo> res = new ArrayList<Veiculo>();
        for (User u : this.users.values()) {
            if (u instanceof Proprietario) {
                res.addAll(((Proprietario) u).getVeiculos());
            }
        }
        ((ArrayList<Veiculo>) res).clone();
        return res;
    }

    public List<Veiculo> getListTodosVeiculosDisponiveis(List<Veiculo> l, Ponto destino, String comb) {
        List<Veiculo> res = new ArrayList<Veiculo>();
        for (Veiculo v : l) {
            if (v.getDisponivel() == true && verificaAutonomiaParaViagem(v, v.getPosicao(), destino) && ((Carro) v).getTipoCombustivel().equals(comb) ) res.add(v);
        }
        ((ArrayList<Veiculo>) res).clone();
        return res;
    }

    public int getProprietarioFromMatricula(String matricula) {
        List<Veiculo> l = new ArrayList<Veiculo>();
        for (User u : this.users.values()) {
            if (u instanceof Proprietario) {
                if (((Proprietario) u).getVeiculo(matricula) != null) return u.getNif();
            }
        }
        return -1;
    }
    public Veiculo getVeiculoFromMatricula(String matricula) {
        List<Veiculo> l = new ArrayList<Veiculo>();
        for (User u : this.users.values()) {
            if (u instanceof Proprietario) {
                if (((Proprietario) u).getVeiculo(matricula) != null) return ((Proprietario) u).getVeiculo(matricula).clone();
            }
        }
        return null;
    }

    public boolean verificaAutonomiaParaViagem(Veiculo v, Ponto origem, Ponto destino) {
        double d = origem.distanceTo(destino);
        if (v.getConsumoPorKm() * d < v.getAutonomia()) return true;

        return false;
    }
    public Veiculo alugaCarroMaisProx(Ponto origem,Ponto destino, String combustivel) {
        List<Veiculo> listVeiculos = getListTodosVeiculos();
        List<Veiculo> disponiveis = getListTodosVeiculosDisponiveis(listVeiculos, destino,combustivel);
        return (Veiculo) disponiveis.stream().min(Comparator.comparing(v -> v.getPosicao().distanceTo(origem))).get().clone();
    }


    public Veiculo alugaCarroBarato(Ponto destino, String combustivel) {
        List<Veiculo> listVeiculos = getListTodosVeiculos();
        List<Veiculo> disponiveis = getListTodosVeiculosDisponiveis(listVeiculos, destino,combustivel);
        return (Veiculo) disponiveis.stream().min(Comparator.comparing(v -> v.getPrecoPorKm())).get().clone();
    }
    public Veiculo alugaCarroBaratoDentroDeDistancia(double distancia, Ponto origem,Ponto destino, String combustivel) {
        List<Veiculo> listVeiculos = getListTodosVeiculos();
        List<Veiculo> disponiveis = getListTodosVeiculosDisponiveis(listVeiculos, destino,combustivel);
        return (Veiculo) disponiveis.stream().filter(v->v.getPosicao().distanceTo(origem)<distancia).min(Comparator.comparing(v -> v.getPrecoPorKm())).get().clone();
    }
    public List<Veiculo> alugaCarroAutonimia(int autonomia,Ponto destino, String combustivel) {
        List<Veiculo> listVeiculos = getListTodosVeiculos();
        List<Veiculo> disponiveis = getListTodosVeiculosDisponiveis(listVeiculos, destino,combustivel);
        return (ArrayList<Veiculo>) disponiveis.stream().filter(v -> v.getAutonomia()>autonomia ).map(Veiculo::clone)
                .collect(Collectors.toList());
    }
    public ArrayList<Historico> verificaHistoricoNaoClassificado(int idCliente){

        return (ArrayList<Historico>) getListHistoricos().stream().filter(v -> v.getIdCliente() == idCliente).collect(Collectors.toList());
    }
    public Map<Integer, Long> top10UtilizadoresPorNrVezes(){
         Map<Integer, Long>  l1 =  getListViagens().stream().collect(Collectors.groupingBy(Pedido::getIdCliente, Collectors.counting()));
        Map<Integer, Long> result2 = new LinkedHashMap<>();
         l1.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()).limit(10)
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
         return result2;
    }

    public double distanciaPed(Pedido p){
            return p.getDestino().distanceTo(p.getOrigem());
    }
}
