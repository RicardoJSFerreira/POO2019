import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;


public class Main{
    private UMCarroJa estado;



        public Integer getOption(){
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        }

        public  void progProprietario(int id){
        // Mal entra verifica se tem algum veiculo com menos de 10% de comb

            System.out.println( this.estado.getUser(id));
            this.grava();
            System.out.println("Alterações efetuadas com sucesso");
            this.verificaCombustivel(id);
            System.out.println("O que pretende ver?");
            System.out.println("0 - Sair");
            System.out.println("1 - Pedidos pendentes");
            System.out.println("2 - Meus Veiculos");
            System.out.println("3 - Visualizar Historico de Viagens");
            System.out.println("4 - Guardar estado");
            System.out.println("Insira a sua opçao: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 0: sair = 0;
                        break;
                    case 1:

                        List<Pedido> list = this.estado.getListPedidosToProprietario(id);
                        this.imprimeListPedidos(list);
                        System.out.println(list);
                        System.out.println("Indique o numero do pedido a Aceitar ou cancelar");
                        int ped = sc.nextInt();
                        System.out.println("Pretende aceitar ou cancelar");
                        System.out.println("(0) Aceitar");
                        System.out.println("(1) Recusar");
                        int i = sc.nextInt();
                        if (i == 0) {
                            Pedido p = list.get(ped);
                            this.estado.aceitarPedido(p);
                            System.out.println("Pedido aceite com sucesso.");
                            this.progProprietario(id);
                        }
                        if (i == 1) {
                            Pedido p = list.get(ped);
                            this.estado.recusaPedido(p);
                            System.out.println("Pedido recusado com sucesso.");
                            this.progProprietario(id);
                        } else {
                            System.out.println("Opção não encontrada");
                        }
                        sair=0;
                        break;
                        // Historico h = new Historico(pedido,e o resto dos argumentos que fazem do peiddo um historico);
                        // ucj.aceitarPedido(h);

                    case 2: this.menuVeiculosProprietario(id);
                        this.progProprietario(id);
                        sair=0;
                        break;
                    case 3:
                        System.out.println("Ver historico desde: (ano-mes-dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Ate: (ano-mes-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> l =this.estado.getListHistorico(id, begin, end);
                        this.imprimeListHistoricos(id,l);
                        this.progProprietario(id);
                        sair=0;
                        break;
                    case 4:
                        this.grava();
                        System.out.println("Estado guardado com sucesso");
                        break;

                }
            }while(sair == 1);
        }

        public void menuVeiculosProprietario (int id){
            this.imprimeListVeiculos(id);
            Scanner sc = new Scanner(System.in);
            System.out.println("O que pretende fazer?");
            System.out.println("0 - Sair");
            System.out.println("1 - Abastecer veiculos");
            System.out.println("2 - Sinalizar veiculo ocmo disponivel");
            System.out.println("3 - Alterar preço por Km");
            System.out.println("4 - Adicionar veiculo");
            System.out.println("5 - Visualziar total faturado");
            int sair = 1;
            do{
                switch(getOption()){
                    case 0: this.progProprietario(id);
                        sair=0;
                        break;

                    case 1:
                        System.out.println("Indique o numero do veiculo que pretende abastecer");
                        int v =sc.nextInt();
                        System.out.println("Indique a quantidade de combustivel que pretende depositar no veiculo" + v);
                        int comb = sc.nextInt();
                        String matricula = this.estado.getVeiculoMatricula(v,id);
                        this.estado.abastecerVeiculo(id,matricula, comb);
                        this.imprimeListVeiculos(id);
                        this.progProprietario(id);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Indique o nr do veiculo que pretende Sinalzizar como disponivel");
                        v =sc.nextInt();
                        matricula = this.estado.getVeiculoMatricula(v,id);
                        this.estado.sinalizarVeiculoComoDisponivel(id,matricula);
                        this.imprimeListVeiculos(id);
                        this.progProprietario(id);
                        sair=0;
                        break;

                    case 3:
                        System.out.println("Indique o nr do veiculo que pretende alterar o preço por km");
                        v = sc.nextInt();
                        System.out.println("Indique o novo preço por km para o veiculo" + v);
                        double price = sc.nextDouble();
                        matricula = this.estado.getVeiculoMatricula(v,id);
                        this.estado.alterarPrecoPorKm(id,matricula, price);
                        this.imprimeListVeiculos(id);
                        this.progProprietario(id);
                        sair=0;
                        break;
                    case 4:
                        this.menuAdicionarVeiculo(id);
                        this.imprimeListVeiculos(id);
                        this.progProprietario(id);
                        sair=0;
                        break;
                    case 5:
                        System.out.println("Ver Total faturado desde: (ano-mes-dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Ate: (ano-mes-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        System.out.println("Indique o nr do veiculo que pretende visualizar total faturado");
                        int veic =sc.nextInt();
                        this.getTotalFaturado(id,veic, begin, end);
                        this.imprimeListVeiculos(id);
                        this.progProprietario(id);
                        sair=0;
                        break;

                        default: System.out.println("Opção não conhecida");
                        break;
                }
            }while(sair == 1);
        }

    public void menuAdicionarVeiculo(int id){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique a matricula");
        String matricula = sc.next();
        System.out.println("Indique a velocidade media(double)");
        int velocidade = sc.nextInt();
        System.out.println("Indique o preço por km(double)");
        double precoPorKm = sc.nextDouble();
        System.out.println("Indique a Consumo por km (double)");
        double consumoPorKm = sc.nextDouble();
        System.out.println("Indique a autonimia maxima");
        int autonomiaMax = sc.nextInt();
        System.out.println("Indique a autonomia");
        int autonomia = sc.nextInt();
        System.out.println("Indique a posicao x");
        int x = sc.nextInt();
        System.out.println("Indique a posicao y");
        int y = sc.nextInt();
        System.out.println("Indique o tipo de combustivel");
        String tipoCombustivel= sc.next();
        System.out.println("Indique a marca");
        String marca = sc.next();

        Veiculo v = new Carro(matricula,id,velocidade,precoPorKm,consumoPorKm,autonomiaMax,autonomia,x,y,tipoCombustivel,marca);
        this.estado.addCarro(id,v);
    }
    public  void imprimeVeiculo(Veiculo v){
            System.out.println("Matricula: " + v.getMatricula() + ", Disponibilidade: " + v.getDisponivel() + ", Preço por Km: " + v.getPrecoPorKm()
                    + ", Consumo por Km" + v.getConsumoPorKm() + ", Classificacao: " + +this.estado.qualificacaoVeiculo(v.getMatricula()) + " Autonomia: " + v.getAutonomia()
                    + " Autonomia Maxima: " + v.getAutonomiaMax());

    }

    public  void imprimeListV(List<Veiculo> l){

        int i = 0;
        for (Veiculo v : l){
            System.out.println("("+i+")" + "Matracula: " + v.getMatricula() + ", Disponibilidade: " + v.getDisponivel() +  ", Preço por Km: " + v.getPrecoPorKm()
                    + ", Consumo por Km" + v.getConsumoPorKm() + ", Classifica��o:" + this.estado.qualificacaoVeiculo(v.getMatricula()) + "Autonomia: " + v.getAutonomia()
                    + " Autonomia Maxima: " + v.getAutonomiaMax());
            i++;
        }

    }

        public  List<Veiculo> imprimeListVeiculos(int id){

            User p = this.estado.getUser(id);
            List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
            int i=0;
            for (Veiculo v: veiculos) {
            System.out.println("("+i+") "+v.getMatricula() + " -> Disponibilidade: " + v.getDisponivel() + " Autonomia: " + v.getAutonomia() +" Autonomia Maxima: " + v.getAutonomiaMax() + " Preço por Km: " + v.getPrecoPorKm() + " tem classificação:" + v.getMediaClassificacao());
            i++;
        }
            return veiculos;
    }

        public  void imprimeListHistoricos( int id, List<Historico> list){

            User user = this.estado.getUser(id);
            int i=0;
            if(user instanceof Cliente){
                for(Historico h: list){
                    User prop = this.estado.getUser(id);
                    System.out.println("(" +i+")"+"Data de Viagem:" + h.getDataViagem() +
                            "Proprietario" + prop.getNome() +
                            " Veiculo "  //(((Proprietario) prop).getVeiculo(h.getIdVeiculo())).getId()
                            + h.getMatricula() +" Valor Pago:" + h.getValorPago()+" euros");
                    i++;

            }
        }
        else{
            for(Historico h: list){
                User cli = this.estado.getUser(id);
                System.out.println("Data de Viagem:" + h.getDataViagem() +
                        "CLiente" + cli.getNome() +
                        " Veiculo " //(((Proprietario) user).getVeiculo(h.getIdVeiculo())).getId()
                        + h.getMatricula() + " Valor Pago:" + h.getValorPago()+" euros");
                i++;
            }
        }
    }
    public  void imprimeListPedidos( List<Pedido> list) throws PedidoNaoExisteException{
        try{
            int i=0;
        for(Pedido p: list){
            User c = this.estado.getUser(p.getIdCliente());
            System.out.println("(" + i +") " +"Cliente: " + c.getNome() +
                    " Veiculo: "
                    + p.getMatricula() +
                    "Destino: " +p.getDestino());
            i++;
        }
        }catch (PedidoNaoExisteException e){System.out.println(" Pedido nao existe");}
    }

    public  void getTotalFaturado(int idUser, int idVeiculo, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Historico> l = this.estado.getListHistoricos();
        String matricula = this.estado.getVeiculoMatricula(idVeiculo,idUser);
        double totalFaturado=0.0;
        for (Historico historico: l){
            if((historico.getIdProprietario()== idUser) && (historico.getMatricula().equals(matricula)) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                totalFaturado=(((Historico) historico).getValorPago())+totalFaturado;
            }
        }
        System.out.println("veiculo com a matricula "+ matricula +" faturou: "+totalFaturado + " euros");

    }

    public  void verificaCombustivel(int id){
        User p = this.estado.getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if((double)((v.getAutonomia()*100.0f)/(v.getAutonomiaMax()))<10){
                System.out.println("Veiculo " + v.getMatricula()+ "na posicao" + v.getPosicao() + "está na reserva");
            }
        }
    }


        public void progCliente(int id){

            System.out.println("As Seguintes viagens foram aceites e ainda nao foram avaliadas:");
            List<Historico> historicosNaoClassificados = this.estado.verificaHistoricoNaoClassificado(id);
            imprimeListHistoricos(id,historicosNaoClassificados);
            this.grava();
            System.out.println("Alterações efetuadas com sucesso");
            System.out.println("Opçoes:");
            System.out.println("0 - Sair");
            System.out.println("1 - Solicitar viagem");
            System.out.println("2 - Visualizar Historico de viagens");
            System.out.println("3 - Classificar viagens");
            System.out.println("4 - Guardar estado");
            System.out.println("Insira a sua opçao: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 0: sair = 0;
                        break;

                    case 1:
                        this.menuClienteSolicitaViagem(id);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Ver historico desde: (ano-mes-dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Ate: (ano-mes-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> list = this.estado.getListHistorico(id, begin, end);
                        this.imprimeListHistoricos(id,list);
                        sair=0;
                        break;

                    case 3:

                        sair=0;
                        break;
                    default: System.out.println("Ação não conhecida");
                    break;

                    case 4:
                        this.grava();
                        System.out.println("Estado guardado com sucesso");
                        break;

                }


        }while(sair == 1);
}



    public  void menuClienteSolicitaViagem( int id){

        Scanner sc = new Scanner(System.in);
        System.out.println("Insira as suas coordenadas:");
        System.out.println("Coordenada x:");
        double x = sc.nextDouble();
        System.out.println("Coordenada y:");
        double y = sc.nextDouble();
        this.estado.setNewClientLocation(id,x,y);
        System.out.println("Indique o tipo de combustivel");
        String comb = sc.next();
        System.out.println("Insira as coordenadas para onde pretende ir:");
        System.out.println("Coordenada x:");
        double posx = sc.nextDouble();
        System.out.println("Coordenada y:");
        double posy = sc.nextDouble();

        Ponto origem = new Ponto(x,y);
        Ponto destino = new Ponto(posx,posy);
        System.out.println("Opçoes:");
        System.out.println("0 - Voltar");
        System.out.println("1 - Solicitar aluguer do carro mais pr�ximo das suas coordenadas");
        System.out.println("2 - Solicitar aluguer do carro mais barato");
        System.out.println("3 - Solicitar aluguer do carro mais barato dentro de determinada dist�ncia ");
        System.out.println("4 - Solicitar aluguer de um carro espec�fico: ");
        System.out.println("5 - Solicitar aluguer do carro com autonomia desejada ");
        System.out.println("Insira a sua op��o: ");

        int sair = 1;

        do{
            switch(getOption()){
                case 0: this.progCliente(id);

                case 1: this.alugCarroMaisProx(destino, id,comb);
                    sair = 0; break;

                case 2: this.alugCarroBarato(origem,destino,id,comb);
                    sair = 0; break;

                case 3: this.alugBaratoDist(origem,destino, id,comb);
                    sair = 0; break;

                case 4: this.alugCarroEspecif(origem,destino, id,comb);
                    sair = 0; break;

                case 5: this.alugCarroAuton(origem,destino,id,comb);
                    sair = 0; break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        } while(sair == 1);
    }

    public void alugCarroMaisProx( Ponto destino, int idCliente,String comb){
        Scanner sc = new Scanner(System.in);
        User u =(Cliente)this.estado.getUser(idCliente);
        Ponto origem = ((Cliente) u).getPosicao();
        Veiculo vMaisProx = this.estado.alugaCarroMaisProx(origem,destino,comb);
        double dist = origem.distanceTo(vMaisProx.getPosicao());
        System.out.println("O ve�culo mais pr�ximo �");
        imprimeVeiculo(vMaisProx);
        System.out.println(". Encontra-se � dist�ncia de" + dist);
        System.out.println("\n Pretende (0) Aceitar ou (1) Recusar?");
        dist = dist*4.0;
            int sair = 1;
            do {
                switch(getOption()){
                    case 0:
                        String matricula = vMaisProx.getMatricula();
                        int idPedido = this.estado.getTodasViagens().size();
                        int idProp = this.estado.getProprietarioFromMatricula(matricula);
                        Pedido p = new Pedido(idPedido,idCliente,idProp,matricula,vMaisProx.getPosicao(),destino,dist);
                        this.estado.addViagem(p);
                        System.out.println("Pedido enviado");
                    case 1: this.progCliente(idCliente);
                        sair = 0; break;

                    default: System.out.println("Ação não conhecida");
                        break;
                }
            } while(sair == 1);

    }

    public void alugCarroBarato(Ponto origem, Ponto destino, int idCliente, String comb){
        Scanner sc = new Scanner(System.in);
        Veiculo vMaisBarato  = this.estado.alugaCarroBarato(destino,comb);
        double dist = origem.distanceTo(vMaisBarato.getPosicao());
        dist = 4*dist;
        imprimeVeiculo(vMaisBarato);
        System.out.println(". Encontra-se � dist�ncia de" + dist);
        System.out.println("\n Pretende (0) Aceitar ou (1) Recusar?");
        int sair = 1;
        do {
            switch(getOption()){
                case 0:
                    String matricula = vMaisBarato.getMatricula();
                    int idPedido = this.estado.getTodasViagens().size();
                    int idProp = this.estado.getProprietarioFromMatricula(matricula);
                    Pedido p = new Pedido(idPedido,idCliente,idProp,matricula, vMaisBarato.getPosicao(),destino,dist);
                    this.estado.addViagem(p);
                    System.out.println("Pedido enviado");
                case 1: this.progCliente(idCliente);
                    sair = 0; break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        } while(sair == 1);

    }

    public  void alugBaratoDist(Ponto origem, Ponto destino, int idCliente,String comb){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira a distancia que esta disposto a percorrer: (double)");
        double dist = sc.nextDouble(); //dist�ncia que est� disposto a percorrer

        Veiculo vBaratoDist = this.estado.alugaCarroBaratoDentroDeDistancia(dist,origem,destino,comb);

        dist = origem.distanceTo(vBaratoDist.getPosicao());
        imprimeVeiculo(vBaratoDist);
        System.out.println(". Encontra-se � dist�ncia de" + dist);
        System.out.println("\n Pretende (0) Aceitar ou (1) Recusar?");
        dist = dist*4.0;
        int sair = 1;
        do {
            switch(getOption()){
                case 0:
                    System.out.println("Indique quanto tempo demora a chegar ao veiculo em minutos");
                    int tempo = sc.nextInt();
                    String matricula = vBaratoDist.getMatricula();
                    int idPedido = this.estado.getTodasViagens().size();
                    int idProp = this.estado.getProprietarioFromMatricula(matricula);
                    Pedido ped = new Pedido(idPedido,idCliente,idProp,matricula, vBaratoDist.getPosicao(),destino,dist);
                    this.estado.addViagem(ped);
                    System.out.println("Pedido enviado");
                case 1: this.progCliente(idCliente);
                    sair = 0; break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        } while(sair == 1);


    }

    public  void alugCarroEspecif(Ponto origem, Ponto destino, int idCliente, String comb){

        List<Veiculo> l = this.estado.getListTodosVeiculosDisponiveis(this.estado.getListTodosVeiculos(),destino,comb);

        System.out.println("A lista de ve�culos disponiveis:"); imprimeListV(l);
        System.out.println("Indique o n�mero do ve�culo que pretende alugar?");
        Scanner sc = new Scanner(System.in);
        int ped = sc.nextInt();

        Veiculo vParaAlugar = l.get(ped); //carro a alugar

        double dist = origem.distanceTo(vParaAlugar.getPosicao());
        imprimeVeiculo(vParaAlugar);
        System.out.println(". Encontra-se � dist�ncia de" + dist);
        System.out.println("\n Pretende (0) Aceitar ou (1) Recusar?");
        dist = dist*4.0;
        int sair = 1;
        do {
            switch(getOption()){
                case 0:
                    System.out.println("Indique quanto tempo demora a chegar ao veiculo em minutos");
                    int tempo = sc.nextInt();
                    String matricula = vParaAlugar.getMatricula();
                    int idPedido = this.estado.getTodasViagens().size();
                    int idProp = this.estado.getProprietarioFromMatricula(matricula);
                    Pedido p = new Pedido(idPedido,idCliente,idProp,matricula, vParaAlugar.getPosicao(),destino,dist);
                    this.estado.addViagem(p);
                    System.out.println("Pedido enviado");
                case 1:this.progCliente(idCliente);
                    sair = 0; break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        } while(sair == 1);



    }

    public  void alugCarroAuton(Ponto origem, Ponto destino, int idCliente, String comb){

        Scanner sc = new Scanner(System.in);
        System.out.println("Indique a autonomia desejada:");
        int aut = sc.nextInt();

        List<Veiculo> nova = this.estado.alugaCarroAutonimia(aut,destino,comb);


        System.out.println("A lista de ve�culos que correspondem � autonomia necess�ria:"); imprimeListV(nova);
        System.out.println("Indique o n�mero do ve�culo que pretende alugar?");
        int ped = sc.nextInt();

        Veiculo vParaAlugar = nova.get(ped);

        double dist = origem.distanceTo(vParaAlugar.getPosicao());
        imprimeVeiculo(vParaAlugar);
        System.out.println(". Encontra-se � dist�ncia de" + dist);
        System.out.println("\n Pretende (0) Aceitar ou (1) Recusar?");
        dist = dist*4.0;
        int sair = 1;
        do {
            switch(getOption()){
                case 0:
                    System.out.println("Indique quanto tempo demora a chegar ao veiculo em minutos");
                    int tempo = sc.nextInt();
                    String matricula = vParaAlugar.getMatricula();
                    int idPedido = this.estado.getTodasViagens().size();
                    int idProp = this.estado.getProprietarioFromMatricula(matricula);
                    Pedido p = new Pedido(idPedido,idCliente,idProp,matricula, vParaAlugar.getPosicao(),destino,dist);
                    this.estado.addViagem(p);
                    System.out.println("Pedido enviado");
                case 1: this.progCliente(idCliente);
                    sair = 0; break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        } while(sair == 1);

    }


        public void registo(){

            Scanner sc = new Scanner(System.in);
            String email = "";
            boolean existe = true;
            while(existe == true){
                System.out.print("Email: ");
                email = sc.nextLine();
                if (this.estado.getUserId(email)<0)
                existe = false;

                else{
                    System.out.println("Email ja existente. Tente de novo.");
                }
            }
            try {
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Morada: ");
                String morada = sc.nextLine();

                System.out.print("Password: ");
                String pass = sc.next();

                System.out.println("Data de nascimento: (ano-mes-dia)");
                String date = sc.next();
                LocalDate dataNascimento = LocalDate.parse(date);

                System.out.println("Pretende ser Proprietário de Veiculos(1) ou Cliente(2)?");
                int resposta = sc.nextInt();

                if (resposta == 1) {
                    Integer id = this.estado.getTodosUsers().size();
                    Proprietario p = new Proprietario(id, email, nome, pass, morada, dataNascimento);
                    this.estado.addUser(p);
                    System.out.println("Utilizador criado com sucesso");
                    // Criar exceção para o caso de nao gravar novo user

                }

                if (resposta == 2) {
                    System.out.println("Indique a sua localização\n");
                    System.out.println("Indique a sua posição X");
                    Double x = sc.nextDouble();
                    System.out.println("Indique a sua posição Y");
                    Double y = sc.nextDouble();

                    Integer id = this.estado.getTodosUsers().size();
                    Cliente c = new Cliente(id, email, nome, pass, morada, dataNascimento, x, y);
                    this.estado.addUser(c);
                    System.out.println("Utilizador criado com sucesso");
                }

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        public void loginMenu(){

            try{
            Scanner sc = new Scanner(System.in);
            String email = "";
            boolean existe = false;
            while(existe == false){
                System.out.print("Email: ");
                email = sc.nextLine();
                if( this.estado.getUserId(email)>=0){
                    existe = true;
                    //System.out.println("Encontrou o email");
                }
                else System.out.println("Email não existente. Tente de novo.");
            }
            int id = this.estado.getUserId(email);
            User u = this.estado.getUser(id);
            int acesso = 0;
            while(acesso == 0){
                System.out.print("PASSWORD: ");
                String pass = sc.next();

                if(u.getPassword().equals(pass)){
                    System.out.println("Acesso Garantido");
                    acesso=1;

                    if (u instanceof Proprietario) {// é um proprietario
                        this.progProprietario(id);
                        // Sabe que é um Proprietario
                        // System.out.println("Sabe que é um proprietario");
                    }
                    else if(u instanceof Cliente){ // é um cliente
                        // Executa programa de cliente
                        this.progCliente(id);
                        // System.out.println("Sabe que é um cliente");
                    }
                }
                else System.out.println("Password errada");
            }
            }

            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }




    /**



        public static void carregaDados (UMCarroJa ucj){
            // Este carregaDados nao vai poder ser assim pois nao vou poder criar desta maneira os objetos
            // Proprietário p1
            // Veiculos p1

            Veiculo v1 =  new Carro("PO-24-19", true,66,1.3,5.6,60,50,30,4,6,"Gasolina","Peugeot");
            Veiculo v2 =  new Carro("CG-34-25", true,70,2.1,6,80,80,60,1,3,"Gasoleo","Audi");

            ArrayList<Veiculo> veiculosp1 = new ArrayList<Veiculo>();
            veiculosp1.add(v1);
            veiculosp1.add(v2);
            Proprietario p1 = new Proprietario(0,"p1@gmail.com","miguel","pass","rua das bolinhas",LocalDate.of(1998,07,21),70,veiculosp1);

            // Proprietário p2
            // Veiculos p2

            Veiculo v3 =  new Carro("12-OR-23",false,90,6.3,7,90,100,8,0,0,"Gasoleo","BMW");
            Veiculo v4 =  new Carro("39-LQ-11",true,45,1.4,3,60,80,47,1,2,"Eletrico","Mercedes");

            ArrayList<Veiculo> veiculosp2 = new ArrayList<Veiculo>();
            veiculosp2.add(v3);
            veiculosp2.add(v4);
            Proprietario p2 = new Proprietario(1,"p2@gmail.com","Carlos","pass","rua das tortilhas",LocalDate.of(1995,9,21),78,veiculosp2);

            // Clientes
            Cliente c1 = new Cliente(2, "c1@gmail.com", "Ricardo", "pass", "Rua das Flores", LocalDate.of(1998,8,15), 2.0, 3.0, 10);
            Cliente c2 = new Cliente(3, "c2@gmail.com", "Joao", "pass", "Rua das Ortigas", LocalDate.of(1996,04,19), 1.0, 5.0, 8);

            // Pedidos
            Pedido ped1 = new Pedido(0,2,1,"12-OR-23",5,4,3,6,new Time(0,0,0));
            Pedido ped2 = new Pedido(1,2,1,"39-LQ-11",5,3,7,1,new Time(0,0,0));

            // Historico
            Historico h1 = new Historico(2,2,0,"CG-34-25",3,2,4,6,new Time(0,2,0),10.4,LocalDate.of(2019, 10, 15));
            Historico h2 = new Historico(3,2,0,"CG-34-25",0,3,7,0,new Time(0,3,0),30.4,LocalDate.of(2019, 10, 20));
            Historico h3 = new Historico(4,3,0,"PO-24-19",4,0,4,3,new Time(0,3,0),30.0,LocalDate.of(2019, 10, 20));



            // Carrega Proprietarios

            if(ucj.userExiste(p1) == false)ucj.addUser(p1);
            if(ucj.userExiste(p2) == false)ucj.addUser(p2);

            // Carrega Clientes

            if(ucj.userExiste(c1) == false)ucj.addUser(c1);
            if(ucj.userExiste(c2) == false)ucj.addUser(c2);

            // Carrega Pedidos
            if(ucj.pedidoExiste(ped1) == false)ucj.addViagem(ped1);
            if(ucj.pedidoExiste(ped2) == false)ucj.addViagem(ped2);

            // Carrega Historico

            if(ucj.pedidoExiste(h1) == false)ucj.addViagem(h1);
            if(ucj.pedidoExiste(h2) == false)ucj.addViagem(h2);
            if(ucj.pedidoExiste(h3) == false)ucj.addViagem(h3);
        }

     */
    public UMCarroJa carregaEstado(String save)throws FileNotFoundException, IOException, ClassNotFoundException{
        try{
            return UMCarroJa.loadEstado(save);
        }
        catch (FileNotFoundException e){
            System.out.println("Ficheiro nao encontrado!");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Erro a aceder ao ficheiro!");
        }catch(ClassNotFoundException e){
            System.out.println("Classe nao encontrada");
        }
        return new UMCarroJa();
    }

    public void grava(){
        try {
            this.estado.guardaEstado("BaseDeDados");
        }catch(FileNotFoundException e){
            System.out.println("Ficheiro nao encontrado!");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Erro a aceder ao ficheiro 2!");
        }
    }
    private void UMCarroJa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1) Carregar ficheiro logs ");
        System.out.println("(2) Carregar ficheiro Base de dados ");

        try {
            switch(getOption()) {
                case 1:
                    this.estado = UMCarroJa.importaCSV("logsPOO_carregamentoInicial.bak");
                    break;

                case 2:
                    this.estado = carregaEstado("BaseDeDados");
                    break;
            }
        }
        catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Parece que é a primeira utilização...");
            this.estado = new UMCarroJa();
        }
        catch (IOException e) {
            System.out.println("Ops! Erro de leitura!");
            this.estado = new UMCarroJa();
        }



        System.out.println(estado);

        int menu = 0;

        do{
            System.out.println("O que pretende fazer?");
            System.out.println("(1) Login  (2) Registo  (3) Sair da aplicação (4) Top 10 Users Por numero de vezes (5) Guardar estado");

            switch(getOption()){
                case 1: this.loginMenu();
                    break;

                case 2: this.registo();
                    break;

                case 3: menu = 3;

                    break;

                case 4: System.out.println(this.estado.top10UtilizadoresPorNrVezes());


                    break;
                case 5: menu = 3;
                    this.grava();
                    System.out.println("Estado guardado com sucesso");
                    break;

                default: System.out.println("Ação não conhecida");
                    break;
            }
        }while(menu == 0);
    }

    ////////////////////////////////// Main
    public static void main(String[] args) {
        Main u = new Main();
        u.UMCarroJa();
    }

    }

