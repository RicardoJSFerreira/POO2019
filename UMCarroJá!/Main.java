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
            verificaCombustivel(ucj,id);
            System.out.println("Seja bem vindo! O que pretende ver?");
            System.out.println("0 - Sair");
            System.out.println("1 - Pedidos pendentes");
            System.out.println("2 - Meus Veiculos");
            System.out.println("3 - Historico de Viagens");
            System.out.println("Insira a sua opção:");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 1:
                        List <Pedido> list= getListPedidosToProprietario(ucj,id);
                        imprimeListPedidos(ucj,list,id);
                        System.out.println("Indique o número do pedido a aceitar ou cancelar:");
                        int ped = sc.nextInt();
                        System.out.println("Pretende aceitar ou cancelar?");
                        System.out.println("(0) Aceitar");
                        System.out.println("(1) Cancelar");
                        int i =sc.nextInt();
                        if (i == 0){
                            Pedido p = getPedidoFromPrint(id,ped,ucj);
                            ucj.aceitarPedido(p);
                        }
                        if (i == 1){
                            Pedido p = getPedidoFromPrint(id,ped,ucj);
                            list.remove(p);
                        }
                        else{System.out.println("Opção não encontrada");}
                        sair=0;
                        break;

                    case 2: menuVeiculosProprietario(id,ucj);
                        sair=0;
                        break;
                    case 3:
                        System.out.println("Ver histórico desde: (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até: (Ano-Mês-Dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> l =getListHistorico(ucj,id, begin, end);
                        imprimeListHistoricos(ucj,id,l);
                        sair=0;
                        break;
                }
            }while(sair == 1);
        }

        public static void menuVeiculosProprietario (int id, UMCarroJa ucj){
            imprimeListVeiculos(id,ucj);
            Scanner sc = new Scanner(System.in);
            System.out.println("O que pretende fazer?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Abastecer veículos");
            System.out.println("2 - Sinalizar veículo como disponível");
            System.out.println("3 - Alterar preço por Km");
            System.out.println("4 - Adicionar veículo");
            int sair = 1;
            do{
                switch(getOption()){
                    case 0: progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 1:
                        System.out.println("Indique o número do veículo que pretende abastecer:");
                        int v =sc.nextInt();
                        System.out.println("Indique a quantidade de combustível que pretende depositar no veículo" + v + ":");
                        int comb = sc.nextInt();
                        ucj.abastecerVeiculo(id,getVeiculoFromPrint(id,v,ucj), comb);
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Indique o número do veículo que pretende sinalizar como disponível:");
                        v =sc.nextInt();
                        ucj.sinalizarVeiculoComoDisponivel(id,getVeiculoFromPrint(id,v,ucj));
                        imprimeListVeiculos(id,ucj);
                        progProprietario(id,ucj);
                        sair=0;
                        break;

                    case 3:
                        System.out.println("Indique o número do veículo que pretende alterar o preço por km:");
                        v = sc.nextInt();
                        System.out.println("Indique o novo preço por km para o veículo" + v + ":");
                        double preco = sc.nextDouble();
                        ucj.alterarPrecoPorKm(id,getVeiculoFromPrint(id,v,ucj), preco);
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
                        System.out.println("Indique o número do veículo que pretende visualizar o total faturado:");
                        v = sc.nextInt();
                        System.out.println("Ver total faturado desde: (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até: (Ano-Mês-dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        getTotalFaturado(ucj,id,v, begin, end);
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
        System.out.println("Indique a matrícula:");
        String matricula = sc.next();
        System.out.println("Indique a velocidade média:");
        int velocidade = sc.nextInt();
        System.out.println("Indique o preço por Km:");
        int precoPorKm = sc.nextInt();
        System.out.println("Indique a consumo por Km:");
        int consumoPorKm = sc.nextInt();
        System.out.println("Indique a autonomia máxima:");
        int autonomiaMax = sc.nextInt();
        System.out.println("Indique a autonomia:");
        int autonomia = sc.nextInt();
        System.out.println("Indique a posicao x:");
        int x = sc.nextInt();
        System.out.println("Indique a posicao y:");
        int y = sc.nextInt();
        System.out.println("Indique o tipo de combustível:");
        String tipoCombustivel= sc.next();
        System.out.println("Indique a marca:");
        String marca = sc.next();

        Veiculo v = new Carro(matricula,velocidade,precoPorKm,consumoPorKm,autonomiaMax,autonomia,x,y,tipoCombustivel,marca);
        ucj.adicionaCarro(v,id);
    }
    
        public static void imprimeListVeiculos(int id,UMCarroJa ucj){
            User p = ucj.getUser(id);
            List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
            int i=0;
            for (Veiculo v: veiculos) {
            System.out.println("("+i+") : "+v.getMatricula() + ", Autonomia: " + v.getAutonomia() +", Autonomia máxima: " + v.getAutonomiaMax() + ".");
            i++;
        }
    }

    public static String getVeiculoFromPrint(int id,int veic, UMCarroJa ucj){
        User p = ucj.getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        int i=0;
        for (Veiculo v: veiculos) {
        if(i==veic) return v.getMatricula();
            i++;
        }
        return null;
    }
    
        public static void imprimeListHistoricos(UMCarroJa ucj, int id, List<Historico> list){
            User user = ucj.getUser(id);
            if(user instanceof Cliente){
                for(Historico h: list){
                    User prop = ucj.getUser(id);
                    System.out.println("Data de Viagem: " + h.getDataViagem() +
                            ", Proprietário: " + prop.getNome() +
                            ", Veículo: "  //(((Proprietario) prop).getVeiculo(h.getIdVeiculo())).getId()
                            + h.getMatricula() +", Valor Pago: " + h.getValorPago()+" euros.");

            }
        }
        else{
            for(Historico h: list){
                User cli = ucj.getUser(id);
                System.out.println("Data de Viagem: " + h.getDataViagem() +
                        ", Cliente: " + cli.getNome() +
                        ", Veiculo: " //(((Proprietario) user).getVeiculo(h.getIdVeiculo())).getId()
                        + h.getMatricula() + ", Valor Pago: " + h.getValorPago()+" euros.");

            }
        }
    }
    public static void imprimeListPedidos(UMCarroJa ucj, List<Pedido> list, int id){
        User prop = ucj.getUser(id);
        for(Pedido p: list){
            User c = ucj.getUser(p.getIdCliente());
            System.out.println("Cliente: " + c.getNome() +
                    ", Veiculo: " + p.getMatricula() +
                    ", Destino: " +p.getDestino() + ".");

        }
    }

    public static Pedido getPedidoFromPrint(int id,int ped, UMCarroJa ucj){
        User prop = ucj.getUser(id);
        List <Pedido> list= getListPedidosToProprietario(ucj,id);
        int i=0;
        for (Pedido p: list) {
        if(i==ped) return p;
            i++;
        }
        return null;
    }
    
    public static void getTotalFaturado(UMCarroJa ucj,int id, int veic, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Pedido> l = ucj.getListViagens();
        double totalFaturado=0;
        for (Pedido historico: l){
            if( historico instanceof Historico && (historico.getIdProprietario()== id) && (historico.getMatricula().equals(veic)) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                totalFaturado=((Historico) historico).getValorPago()+totalFaturado;
            }
        }
        System.out.println("O veículo "+ veic +" faturou no total: " + totalFaturado + " euros.");

    }

    public static void verificaCombustivel(UMCarroJa ucj,int id){
        User p = ucj.getUser(id);
        List<Veiculo> veiculos = ((Proprietario) p).getVeiculos();
        for (Veiculo v: veiculos) {
            if((double)((v.getAutonomia()*100.0f)/(v.getAutonomiaMax()))<10){
                System.out.println("O veículo " + v.getMatricula()+ " na posicão " + v.getPosicao() + "está na reserva.");
            }
        }
    }

    public static List<Historico> getListHistorico(UMCarroJa ucj,int id, LocalDate begin, LocalDate end){
        List<Historico> res = new ArrayList<Historico>();
        List<Pedido> l = ucj.getListViagens();
        for (Pedido historico: l){
            if( historico instanceof Historico && ((historico.getIdCliente()== id) || (historico.getIdProprietario())==id) && (((Historico) historico).getDataViagem().isAfter(begin)) && (((Historico) historico).getDataViagem().isBefore(end))) {
                res.add((Historico) historico.clone());
            }
        }
        return res;
    }

    public static List<Pedido> getListPedidosToProprietario(UMCarroJa ucj,int id){
        List<Pedido> res = new ArrayList<Pedido>();
        List<Pedido> l = ucj.getListViagens();
        for (Pedido pedido: l){
            if( (pedido instanceof Pedido) && pedido.getIdProprietario()==id ) {
                res.add(pedido.clone());
            }
        }
        return res;
    }

        public static void progCliente(int id,UMCarroJa ucj){
            System.out.println("Seja bem vindo. O que pretende fazer?");
            System.out.println("0 - Sair");
            System.out.println("1 - Solicitar viagem");
            System.out.println("2 - Visualizar histórico de viagens");
            System.out.println("Insira a sua opção: ");
            Scanner sc = new Scanner(System.in);
            int sair = 1;

            do{
                switch(getOption()){
                    case 1:
                        menuClienteSolicitaViagem(ucj,id);
                        sair=0;
                        break;

                    case 2:
                        System.out.println("Ver histórico desde: (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até: (Ano-Mês-Dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        List<Historico> list = getListHistorico(ucj,id, begin, end);
                        imprimeListHistoricos(ucj,id,list);
                        sair=0;
                        break;

                    default: System.out.println("Opção não conhecida");
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


            System.out.println("Opções:");
            System.out.println("0 - Voltar");
            System.out.println("1 - Solicitar aluguer do carro mais próximo das suas coordenadas");
            System.out.println("2 - Solicitar aluguer do carro mais barato");
            System.out.println("3 - Solicitar aluguer do carro mais barato dentro de determinada distância");
            System.out.println("4 - Solicitar aluguer de um carro específico");
            System.out.println("5 - Solicitar aluguer do carro com uma autonomia por si desejada");
            System.out.println("Insira a sua opção:");
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

                    default: System.out.println("Opção não conhecida");
                        break;
                }
            } while(sair == 1);
        }
    public static void alugCarroMaisProx(UMCarroJa ucj, int id){

        //implementar metodo que mostra a lista dos veï¿½culos mais prï¿½ximos


    }

    public static void alugCarroBarato(UMCarroJa ucj, int id){
        //implementar metodo que apresenta o(s) carro(s) mais barato(s)
    }

    public static void alugBaratoDist(UMCarroJa ucj, int id){
        System.out.println("Insira a distï¿½ncia que estï¿½ disposto a percorrer:");
        int dist = getOption();
        //implementar busca que apresente a lista de carros que estï¿½o dentro da distï¿½ncia referida
    }

    public static void alugCarroEspecif(UMCarroJa ucj, int id){
        //por marca? ou matrï¿½cula? ou outras opï¿½ï¿½es?
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
                System.out.print("E-mail:");
                email = sc.nextLine();
                if (ucj.getUserId(email)<0)
                existe = false;

                else{
                    System.out.println("E-mail já existente. Tente de novo.");
                }
            }
            System.out.print("Nome:");
            String nome = sc.nextLine();

            System.out.print("Morada:");
            String morada = sc.nextLine();

            System.out.print("Password:");
            String pass = sc.next();

            System.out.print("Data Nascimento:");

            System.out.print("Ano:");
            Integer ano = sc.nextInt();

            System.out.print("Mês:");
            Integer mes = sc.nextInt();

            System.out.print("Dia:");
            Integer dia = sc.nextInt();

            System.out.println("Pretende utilizar a aplicação enquanto Proprietário(1) ou Cliente(2)?");
            int resposta = sc.nextInt();
            if(resposta ==1){
                Integer id = ucj.getTodosUsers().size();
                LocalDate dataNascimento = LocalDate.of(ano, mes , dia);
                Proprietario p = new Proprietario(id,email,nome,pass,morada,dataNascimento);
                ucj.addUser(p);
                System.out.println("Utilizador criado com sucesso");
                // Criar exceÃ§Ã£o para o caso de nao gravar novo user

            }

            if(resposta ==2){
                System.out.println("Indique a sua localização:");
                System.out.println("A sua posição X:");
                Double x = sc.nextDouble();
                System.out.println("Indique a sua posição Y:");
                Double y = sc.nextDouble();

                Integer id = ucj.getTodosUsers().size();
                LocalDate dataNascimento = LocalDate.of(ano, mes , dia);
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
                System.out.print("Password: ");
                String pass = sc.next();

                if(u.getPassword().equals(pass)){
                    System.out.println("Acesso garantido");
                    acesso=1;

                    if (u instanceof Proprietario) {// Ã© um proprietario
                        progProprietario(id,ucj);
                        // Sabe que Ã© um Proprietario
                        // System.out.println("Sabe que Ã© um proprietario");
                    }
                    else if(u instanceof Cliente){ // Ã© um cliente
                        // Executa programa de cliente
                        progCliente(id,ucj);
                        // System.out.println("Sabe que Ã© um cliente");
                    }
                }
                else System.out.println("Password errada. Tente de novo");
            }

        }





        public static void carregaDados (UMCarroJa ucj){
            // Este carregaDados nao vai poder ser assim pois nao vou poder criar desta maneira os objetos
            // ProprietÃ¡rio p1
            // Veiculos p1

            Veiculo v1 =  new Carro("PO-24-19", true,66,1.3,5.6,60,50,30,4,6,"Gasolina","Peugeot");
            Veiculo v2 =  new Carro("CG-34-25", true,70,2,6,80,80,60,1,3,"Gasoleo","Audi");

            ArrayList<Veiculo> veiculosp1 = new ArrayList<Veiculo>();
            veiculosp1.add(v1);
            veiculosp1.add(v2);
            Proprietario p1 = new Proprietario(0,"p1@gmail.com","miguel","pass","rua das bolinhas",LocalDate.of(1998,07,21),70,veiculosp1);

            // ProprietÃ¡rio p2
            // Veiculos p2

            Veiculo v3 =  new Carro("12-OR-23",false,90,6,7,90,100,8,0,0,"Gasoleo","BMW");
            Veiculo v4 =  new Carro("39-LQ-11",true,45,1,3,60,80,47,1,2,"Eletrico","Mercedes");

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
            Historico h1 = new Historico(2,2,1,"CG-34-25",3,2,4,6,new Time(0,2,0),10,LocalDate.of(2019, 10, 15));
            Historico h2 = new Historico(3,2,1,"CG-34-25",0,3,7,0,new Time(0,3,0),30,LocalDate.of(2019, 10, 20));
            Historico h3 = new Historico(3,3,1,"PO-24-19",4,0,4,3,new Time(0,3,0),30,LocalDate.of(2019, 10, 20));



            // Carrega Proprietarios

            if(ucj.userExiste(p1) == false)ucj.addUser(p1);
            if(ucj.userExiste(p2) == false)ucj.addUser(p2);

            // Carrega Clientes

            if(ucj.userExiste(c1) == false)ucj.addUser(c1);
            if(ucj.userExiste(c2) == false)ucj.addUser(c2);

            // Carrega Historico

            if(ucj.pedidoExiste(h1) == false)ucj.addViagem(h1);
            if(ucj.pedidoExiste(h2) == false)ucj.addViagem(h2);
        }
        ////////////////////////////////// Main
        public static void main(String[] args) {
            // Isto estÃ¡ provisorio
            UMCarroJa ucj = new UMCarroJa();
            carregaDados(ucj);

            System.out.println(ucj);
            Scanner sc = new Scanner(System.in);

            int menu = 0;

            do{
                System.out.println("Seja bem vindo! O que pretende fazer?");
                System.out.println("(1) Login  (2) Registo  (3) Sair da aplicação");

                switch(getOption()){
                    case 1: loginMenu(ucj);
                        break;

                    case 2: registo(ucj);
                        break;

                    case 3: menu = 3;
                        // guarda tudo o que foi alterado
                        break;

                    default: System.out.println("Opção não conhecida");
                        break;
                }
            }while(menu == 0);
        }

    }

