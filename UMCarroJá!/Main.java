import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import java.io.IOException;


public class Main{
    private UMCarroJa estado;



        public static Integer getOption(){
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        }

        public static void progProprietario(int id,UMCarroJa ucj){
        // Mal entra verifica se tem algum veiculo com menos de 10% de comb
            grava(ucj);
            System.out.println("Alterações efetuadas com sucesso");
            verificaCombustivel(ucj,id);
            System.out.println("O que pretende ver?");
            System.out.println("0 - Sair");
            System.out.println("1 - Pedidos pendentes");
            System.out.println("2 - Meus Veiculos");
            System.out.println("3 - Visualizar Historico de Viagens");
            System.out.println("Insira a sua opçao: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 0: sair = 0;
                        break;
                    case 1:
                        List <Pedido> list= ucj.getListPedidosToProprietario(id);
                        imprimeListPedidos(ucj,list,id);
                        System.out.println(list);
                        System.out.println("Indique o numero do pedido a Aceitar ou cancelar");
                        int ped = sc.nextInt();
                        System.out.println("Pretende aceitar ou cancelar");
                        System.out.println("(0) Aceitar");
                        System.out.println("(1) Recusar");
                        int i =sc.nextInt();
                        if (i == 0){
                            Pedido p = list.get(ped);
                            ucj.aceitarPedido(p);
                            System.out.println("Pedido aceite com sucesso.");
                            progProprietario(id,ucj);
                        }
                        if (i == 1){
                            Pedido p = list.get(ped);
                            ucj.recusaPedido(p);
                            System.out.println("Pedido recusado com sucesso.");
                            progProprietario(id,ucj);
                        }
                        else{System.out.println("Opção não encontrada");}
                        sair=0;
                        break;
                        // Historico h = new Historico(pedido,e o resto dos argumentos que fazem do peiddo um historico);
                        // ucj.aceitarPedido(h);

                    case 2: menuVeiculosProprietario(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;
                    case 3:
                        System.out.println("Ver historico desde: (ano-mes-dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Ate: (ano-mes-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> l =ucj.getListHistorico(id, begin, end);
                        imprimeListHistoricos(ucj,id,l);
                        progProprietario(id,ucj);
                        sair=0;
                        break;
                }
            }while(sair == 1);
        }

        public static void menuVeiculosProprietario (int id, UMCarroJa ucj){
            imprimeListVeiculos(id,ucj);
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
                    case 0: progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 1:
                        System.out.println("Indique o numero do veiculo que pretende abastecer");
                        int v =sc.nextInt();
                        System.out.println("Indique a quantidade de combustivel que pretende depositar no veiculo" + v);
                        int comb = sc.nextInt();
                        String matricula = ucj.getVeiculoMatricula(v,id);
                        ucj.abastecerVeiculo(id,matricula, comb);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Indique o nr do veiculo que pretende Sinalzizar como disponivel");
                        v =sc.nextInt();
                        matricula = ucj.getVeiculoMatricula(v,id);
                        ucj.sinalizarVeiculoComoDisponivel(id,matricula);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 3:
                        System.out.println("Indique o nr do veiculo que pretende alterar o preço por km");
                        v = sc.nextInt();
                        System.out.println("Indique o novo preço por km para o veiculo" + v);
                        double price = sc.nextDouble();
                        matricula = ucj.getVeiculoMatricula(v,id);
                        ucj.alterarPrecoPorKm(id,matricula, price);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;
                    case 4:
                        menuAdicionarVeiculo(id, ucj);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
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
                        getTotalFaturado(ucj,id,veic, begin, end);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;

                        default: System.out.println("Opção não conhecida");
                        break;
                }
            }while(sair == 1);
        }

    public static void menuAdicionarVeiculo(int id, UMCarroJa ucj){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique a matricula");
        String matricula = sc.next();
        System.out.println("Indique a velocidade media(double)");
        double velocidade = sc.nextDouble();
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

        Veiculo v = new Carro(matricula,velocidade,precoPorKm,consumoPorKm,autonomiaMax,autonomia,x,y,tipoCombustivel,marca);
        ucj.adicionaCarro(v,id);
    }
        public static List<Veiculo> imprimeListVeiculos(int id,UMCarroJa ucj){
            User p = ucj.getUser(id);
            List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
            int i=0;
            for (Veiculo v: veiculos) {
            System.out.println("("+i+") "+v.getMatricula() + " -> Disponibilidade: " + v.getDisponivel() + " Autonomia: " + v.getAutonomia() +" Autonomia Maxima: " + v.getAutonomiaMax() + " Preço por Km: " + v.getPrecoPorKm() + " tem classificação:" + v.getClassificacao());
            i++;
        }
            return veiculos;
    }

        public static void imprimeListHistoricos(UMCarroJa ucj, int id, List<Historico> list){
            User user = ucj.getUser(id);
            if(user instanceof Cliente){
                for(Historico h: list){
                    User prop = ucj.getUser(id);
                    System.out.println("Data de Viagem:" + h.getDataViagem() +
                            "Proprietario" + prop.getNome() +
                            " Veiculo "  //(((Proprietario) prop).getVeiculo(h.getIdVeiculo())).getId()
                            + h.getMatricula() +" Valor Pago:" + h.getValorPago()+" euros");

            }
        }
        else{
            for(Historico h: list){
                User cli = ucj.getUser(id);
                System.out.println("Data de Viagem:" + h.getDataViagem() +
                        "CLiente" + cli.getNome() +
                        " Veiculo " //(((Proprietario) user).getVeiculo(h.getIdVeiculo())).getId()
                        + h.getMatricula() + " Valor Pago:" + h.getValorPago()+" euros");

            }
        }
    }
    public static void imprimeListPedidos(UMCarroJa ucj, List<Pedido> list, int id){
        User prop = ucj.getUser(id);
        int i=0;
        for(Pedido p: list){
            User c = ucj.getUser(p.getIdCliente());
            System.out.println("(" + i +") " +"Cliente: " + c.getNome() +
                    " Veiculo: "
                    + p.getMatricula() +
                    "Destino: " +p.getDestino());
            i++;
        }
    }

    public static void getTotalFaturado(UMCarroJa ucj,int idUser, int idVeiculo, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Historico> l = ucj.getListHistoricos();
        String matricula = ucj.getVeiculoMatricula(idVeiculo,idUser);
        double totalFaturado=0.0;
        for (Historico historico: l){
            if((historico.getIdProprietario()== idUser) && (historico.getMatricula().equals(matricula)) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                totalFaturado=(((Historico) historico).getValorPago())+totalFaturado;
            }
        }
        System.out.println("veiculo com a matricula "+ matricula +" faturou: "+totalFaturado + " euros");

    }

    public static void verificaCombustivel(UMCarroJa ucj,int id){
        User p = ucj.getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if((double)((v.getAutonomia()*100.0f)/(v.getAutonomiaMax()))<10){
                System.out.println("Veiculo " + v.getMatricula()+ "na posicao" + v.getPosicao() + "está na reserva");
            }
        }
    }


        public static void progCliente(int id,UMCarroJa ucj){

            grava(ucj);
            System.out.println("Alterações efetuadas com sucesso");
            System.out.println("Opçoes:");
            System.out.println("0 - Sair");
            System.out.println("1 - Solicitar viagem");
            System.out.println("2 - Visualizar Historico de viagens");
            System.out.println("Insira a sua opçao: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 0: sair = 0;
                        break;

                    case 1:
                        menuClienteSolicitaViagem(ucj,id);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Ver historico desde: (ano-mes-dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Ate: (ano-mes-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> list = ucj.getListHistorico(id, begin, end);
                        imprimeListHistoricos(ucj,id,list);
                        sair=0;
                        break;

                    default: System.out.println("Ação não conhecida");
                    break;
            }
        }while(sair == 1);
}



        public static void menuClienteSolicitaViagem(UMCarroJa ucj, int id){
            System.out.println("Insira as suas coordenadas:");
            System.out.println("Coordenada x:");
            int x = getOption();
            System.out.println("Coordenada y:");
            int y = getOption();
            ucj.setNewClientLocation(id,x,y);

            System.out.println("Insira as coordenadas para onde pretende ir:");
            System.out.println("Coordenada x:");
            int posx = getOption();
            System.out.println("Coordenada y:");
            int posy = getOption();


            System.out.println("Opçoes:");
            System.out.println("0 - Voltar");
            System.out.println("1 - Solicitar aluguer do carro mais pr�ximo das suas coordenadas");
            System.out.println("2 - Solicitar aluguer do carro mais barato");
            System.out.println("3 - Solicitar aluguer do carro mais barato dentro de determinada dist�ncia ");
            System.out.println("4 - Solicitar aluguer de um carro espec�fico: ");
            System.out.println("5 - Solicitar aluguer do carro com autonomia desejada ");
            System.out.println("Insira a sua op��o: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 0: progCliente(id,ucj);

                    case 1: alugCarroMaisProx(ucj, id);
                        sair = 0; break;

                    case 2: alugCarroBarato(ucj,id);
                        sair = 0; break;

                    case 3: alugBaratoDist(ucj, id);
                        sair = 0; break;

                    case 4: alugCarroEspecif(ucj, id);
                        sair = 0; break;

                    case 5: alugCarroAuton(ucj, id);
                        sair = 0; break;

                    default: System.out.println("Ação não conhecida");
                        break;
                }
            } while(sair == 1);
        }
    public static void alugCarroMaisProx(UMCarroJa ucj, int id){

        //implementar metodo que mostra a lista dos ve�culos mais pr�ximos


    }

    public static void alugCarroBarato(UMCarroJa ucj, int id){
        //implementar metodo que apresenta o(s) carro(s) mais barato(s)
    }

    public static void alugBaratoDist(UMCarroJa ucj, int id){
        System.out.println("Insira a dist�ncia que est� disposto a percorrer:");
        int dist = getOption();
        //implementar busca que apresente a lista de carros que est�o dentro da dist�ncia referida
    }

    public static void alugCarroEspecif(UMCarroJa ucj, int id){
        //por marca? ou matr�cula? ou outras op��es?
    }

    public static void alugCarroAuton(UMCarroJa ucj, int id){
        System.out.println("Indique a autonomia desejada:");
        int aut = getOption();
        //apresenta lista de carros com no minimo a autonomia desejada
    }

        public static void registo(UMCarroJa ucj){

            Scanner sc = new Scanner(System.in);
            String email = "";
            boolean existe = true;
            while(existe == true){
                System.out.print("Email: ");
                email = sc.nextLine();
                if (ucj.getUserId(email)<0)
                existe = false;

                else{
                    System.out.println("Email ja existente. Tente de novo.");
                }
            }
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Morada: ");
            String morada = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.next();

            System.out.println("Data de nascimento: (ano-mes-dia)\n");
            String date = sc.nextLine();
            LocalDate dataNascimento = LocalDate.parse(date);

            System.out.println("Pretende ser Proprietário de Veiculos(1) ou Cliente(2)?");
            int resposta = sc.nextInt();
            if(resposta ==1){
                Integer id = ucj.getTodosUsers().size();
                Proprietario p = new Proprietario(id,email,nome,pass,morada,dataNascimento);
                ucj.addUser(p);
                System.out.println("Utilizador criado com sucesso");
                // Criar exceção para o caso de nao gravar novo user

            }

            if(resposta ==2){
                System.out.println("Indique a sua localização\n");
                System.out.println("Indique a sua posição X");
                Double x = sc.nextDouble();
                System.out.println("Indique a sua posição Y");
                Double y = sc.nextDouble();

                Integer id = ucj.getTodosUsers().size();
                Cliente c = new Cliente(id,email,nome,pass,morada,dataNascimento,x,y);
                ucj.addUser(c);
                System.out.println("Utilizador criado com sucesso");
            }



        }

        public static void loginMenu(UMCarroJa ucj){

            Scanner sc = new Scanner(System.in);
            String email = "";
            boolean existe = false;
            while(existe == false){
                System.out.print("Email: ");
                email = sc.nextLine();
                if( ucj.getUserId(email)>=0){
                    existe = true;
                    //System.out.println("Encontrou o email");
                }
                else System.out.println("Email não existente. Tente de novo.");
            }
            int id = ucj.getUserId(email);
            User u = ucj.getUser(id);
            int acesso = 0;
            while(acesso == 0){
                System.out.print("PASSWORD: ");
                String pass = sc.next();

                if(u.getPassword().equals(pass)){
                    System.out.println("Acesso Garantido");
                    acesso=1;

                    if (u instanceof Proprietario) {// é um proprietario
                        progProprietario(id,ucj);
                        // Sabe que é um Proprietario
                        // System.out.println("Sabe que é um proprietario");
                    }
                    else if(u instanceof Cliente){ // é um cliente
                        // Executa programa de cliente
                        progCliente(id,ucj);
                        // System.out.println("Sabe que é um cliente");
                    }
                }
                else System.out.println("Password errada");
            }

        }





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

    public static UMCarroJa carregaEstado(String save){
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

    public static void grava(UMCarroJa ucj){
        try {
            ucj.guardaEstado("BaseDeDados");
        }catch(FileNotFoundException e){
            System.out.println("Ficheiro nao encontrado!");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Erro a aceder ao ficheiro 2!");
        }
    }
    ////////////////////////////////// Main
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            UMCarroJa ucj = new UMCarroJa();
            ucj = carregaEstado("BaseDeDados");


            System.out.println(ucj);

            int menu = 0;

            do{
                System.out.println("O que pretende fazer?");
                System.out.println("(1) Login  (2) Registo  (3) Sair da aplicação");

                switch(getOption()){
                    case 1: loginMenu(ucj);
                        break;

                    case 2: registo(ucj);
                        break;

                    case 3: menu = 3;
                        // guarda tudo o que foi alterado
                        break;

                    default: System.out.println("Ação não conhecida");
                        break;
                }
            }while(menu == 0);
        }

    }

