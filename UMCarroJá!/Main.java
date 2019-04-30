import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;


public class Main{
    private UMCarroJa estado;



    public static Integer getOption(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void progProprietario(int id,UMCarroJa ucj){
        // Mal entra verifica se tem algum veiculo com menos de 10% de comb
        ucj.verificaCombustivel(id);
        System.out.println("Seja bem vindo!");
        System.out.println("0 - Sair");
        System.out.println("1 - Pedidos pendentes");
        System.out.println("2 - Meus Veículos");
        System.out.println("3 - Visualizar Historico de Viagens");
        System.out.println("Insira a sua opção: ");
        Scanner sc = new Scanner(System.in);
        int sair = 1;

        do{
            switch(getOption()){
                case 1: ucj.getListPedidosToProprietario(id);
                        sair=0;
                        break;

                case 2: menuVeiculosProprietario(id,ucj);
                        sair=0;
                        break;
                case 3:
                        System.out.println("A partir de que data pretente consultar o histórico? (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até que data pretende obter o histórico? (Ano-Mês-Dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        ucj.getListHistorico(id, begin, end);
                        sair=0;
                        break;
                }
        }while(sair == 1);
        }

    public static void menuVeiculosProprietario (int id, UMCarroJa ucj){
        ucj.imprimeListVeiculos(id);
        Scanner sc = new Scanner(System.in);
        System.out.println("O que pretende fazer?");
        System.out.println("0 - Sair");
        System.out.println("1 - Abastecer veículos");
        System.out.println("2 - Sinalizar veículo como disponivel");
        System.out.println("3 - Alterar preço por Km");
        System.out.println("4 - Adicionar veículo");
        int sair = 1;
        do{
            switch(getOption()){
                case 0: progProprietario(id,ucj);
                        sair=0;
                        break;

                case 1:
                        System.out.println("Indique qual o veículo que pretende abastecer:");
                        int v =sc.nextInt();
                        System.out.println("Indique a quantidade de combustível que pretende depositar no veículo" + v + ":");
                        int comb = sc.nextInt();
                        ucj.abastecerVeiculo(id,v, comb);
                        sair=0;
                        break;

                case 2:
                        System.out.println("Indique qual o veículo que pretende sinalizar como disponível:");
                        v =sc.nextInt();
                        ucj.sinalizarVeiculoComoDisponivel(id, v);
                        sair=0;
                        break;

                case 3:
                        System.out.println("Indique o veículo para o qual pretende alterar o preço por Km:");
                        v = sc.nextInt();
                        System.out.println("Indique o novo preço por Km para o veículo" + v + ":");
                        double preco = sc.nextDouble();
                        ucj.alterarPrecoPorKm(id,v, preco);
                        sair=0;
                        break;
                case 4:
                        menuAdicionarVeiculo(id, ucj);
                        sair=0;
                        break;
                case 5:
                        System.out.println("Indique qual o veículo do qual pretende obter o total faturado:");
                        v = sc.nextInt();
                        System.out.println("A partir de que data deseja ver o total faturado? (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até que data deseja obter o total faturado? (Ano-Mês-Dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        ucj.getTotalFaturado(id,v, begin, end);
                        sair=0;
                        break;

                default: System.out.println("Opção não conhecida");
                        break;
                }
        }while(sair == 1);
    }

    public static void menuAdicionarVeiculo(int id, UMCarroJa ucj){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique a velocidade média:");
        int velocidade = sc.nextInt();
        System.out.println("Indique o preço por Km:");
        int precoPorKm = sc.nextInt();
        System.out.println("Indique o consumo médio por Km:");
        int consumoPorKm = sc.nextInt();
        System.out.println("Indique a autonomia máxima do veículo:");
        int autonomiaMax = sc.nextInt();
        System.out.println("Indique a autonomia atual:");
        int autonomia = sc.nextInt();
        System.out.println("Indique qual a posição x do veículo:");
        int x = sc.nextInt();
        System.out.println("Indique qual a posicao y do veículo:");
        int y = sc.nextInt();
        System.out.println("Indique o tipo de combustível:");
        String tipoCombustivel= sc.next();
        System.out.println("Indique a matrícula:");
        String matricula = sc.next();
        System.out.println("Indique a marca:");
        String marca = sc.next();

        ucj.adicionaCarro(velocidade,precoPorKm,consumoPorKm,autonomiaMax,autonomia,x,y,tipoCombustivel,matricula,marca,id);
    }

    public static void progCliente(int id,UMCarroJa ucj){
            System.out.println("Seja bem vindo!");
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
                        System.out.println("A partir de que data pretente consultar o histórico? (Ano-Mês-Dia)");
                        String date = sc.nextLine();
                        LocalDate begin = LocalDate.parse(date);
                        System.out.println("Até que data pretende obter o histórico? (Ano-Mês-Dia)");
                        date = sc.nextLine();
                        LocalDate end = LocalDate.parse(date);
                        ucj.getListHistorico(id, begin, end);
                        sair=0;
                        break;

                default: System.out.println("Opção não conhecida");
                    break;
            }
        }while(sair == 1);
    }



        public static void menuClienteSolicitaViagem(UMCarroJa ucj, int id){
            
            System.out.println("Insira as suas coordenadas atuais:");
            System.out.println("Coordenada x:");
            int x = getOption();
            System.out.println("Coordenada y:");
            int y = getOption();

            System.out.println("Insira as coordenadas do local para onde pretende ir:");
            System.out.println("Coordenada x:");
            int posx = getOption();
            System.out.println("Coordenada y:");
            int posy = getOption();




            System.out.println("Qual a solicitação que pretende efetuar?");
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

        //implementar metodo que mostra a lista dos veículos mais próximos


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
                System.out.print("Insira o seu e-mail:");
                email = sc.nextLine();

                if (ucj.procuraUserEmail(email) == false)

                    existe = false;

                else{
                    System.out.println("E-mail já existente. Tente de novo.");

                }
            }

            System.out.print("Nome:");
            String nome = sc.nextLine();

            System.out.print("Morada: ");
            String morada = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.next();

            System.out.print("Data Nascimento: \n");

            System.out.print("Ano: ");
            Integer ano = sc.nextInt();

            System.out.print("MÃªs: ");
            Integer mes = sc.nextInt();

            System.out.print("Dia: ");
            Integer dia = sc.nextInt();

            System.out.println("Pretende utilizar a aplicação enquanto Proprietário (insira '1') ou Cliente (insira '2')?");
            int resposta = sc.nextInt();

            if(resposta ==1){
                ucj.registaProprietario(email,nome,pass,morada,ano,mes,dia);
                System.out.println("Utilizador criado com sucesso");
                // Criar exceÃ§Ã£o para o caso de nao gravar novo user
                // ConfirmaÃ§Ã£o que insere nos Users
                // User p1 = tu.getUser(id);
                // System.out.println(p1);
            }

            if(resposta ==2){
                System.out.println("A sua localização:");

                System.out.println("Indique a sua posição x:");
                Integer x = sc.nextInt();
                System.out.println("Indique a sua posição y:");
                Integer y = sc.nextInt();

                ucj.registaCliente(email,nome,pass,morada,ano,mes,dia,x,y);
                System.out.println("Utilizador criado com sucesso");
                // ConfirmaÃ§Ã£o que insere nos Users
                // User c1 = tu.getUser(id);
                // System.out.println(c1);
            }



        }

        public static void loginMenu(UMCarroJa ucj){

            Scanner sc = new Scanner(System.in);

            String email = "";

            boolean existe = false;
            while(existe == false){
                System.out.print("Email: ");
                email = sc.nextLine();
                if(ucj.procuraUserEmail(email) == true){
                    existe = true;
                    //System.out.println("Encontrou o email");
                }
                else System.out.println("E-mail não existente. Tente de novo.");
            }
            int id = ucj.getUserId(email);
            int acesso = 0;
            while(acesso == 0){
                System.out.print("Password: ");
                String pass = sc.next();

                if(ucj.verificaPasswordUser(id,pass) == true){
                    System.out.println("Acesso Garantido");
                    acesso=1;

                    if (ucj.verificaTipoUser(id) == 1) {// Ã© um proprietario
                        progProprietario(id,ucj);
                        // Sabe que Ã© um Proprietario
                        // System.out.println("Sabe que Ã© um proprietario");
                    }
                    else if(ucj.verificaTipoUser(id) == 0){ // Ã© um cliente
                        // Executa programa de cliente
                        progCliente(id,ucj);
                        // System.out.println("Sabe que Ã© um cliente");
                    }
                }
                else System.out.println("Password errada. Tente de novo.");
            }

        }





        public static void carregaDados (UMCarroJa ucj){
            // Este carregaDados nao vai poder ser assim pois nao vou poder criar desta maneira os objetos
            // ProprietÃ¡rio p1
            // Veiculos p1

            Veiculo v1 =  new Carro(1,true,66,1.3,5.6,60,50,30,4,6,"Gasolina","PO-24-19","Peugeot");
            Veiculo v2 =  new Carro(2,true,70,2,6,80,80,60,1,3,"Gasoleo","CG-34-25","Audi");

            ArrayList<Veiculo> veiculosp1 = new ArrayList<Veiculo>();
            veiculosp1.add(v1);
            veiculosp1.add(v2);
            Proprietario p1 = new Proprietario(0,"p1@gmail.com","miguel","pass","rua das bolinhas",LocalDate.of(1998,07,21),70,veiculosp1);

            // ProprietÃ¡rio p2
            // Veiculos p2

            Veiculo v3 =  new Carro(3,false,90,6,7,90,100,8,0,0,"Gasoleo","12-OR-23","BMW");
            Veiculo v4 =  new Carro(4,true,45,1,3,60,80,47,1,2,"Eletrico","39-LQ-11","Mercedes");

            ArrayList<Veiculo> veiculosp2 = new ArrayList<Veiculo>();
            veiculosp2.add(v3);
            veiculosp2.add(v4);
            Proprietario p2 = new Proprietario(1,"p2@gmail.com","Carlos","pass","rua das tortilhas",LocalDate.of(1995,9,21),78,veiculosp2);

            // Clientes
            Cliente c1 = new Cliente(2, "c1@gmail.com", "Ricardo", "pass", "Rua das Flores", LocalDate.of(1998,8,15), 2.0, 3.0, 10);
            Cliente c2 = new Cliente(3, "c2@gmail.com", "Joao", "pass", "Rua das Ortigas", LocalDate.of(1996,04,19), 1, 5.0, 8);

            // Pedidos
            Pedido ped1 = new Pedido(0,2,1,1,5,3,new Time(0,0,0));
            Pedido ped2 = new Pedido(1,2,1,2,5,3,new Time(0,0,0));

            // Historico
            Historico h1 = new Historico(2,2,1,1,4,6,new Time(0,2,0),10,LocalDate.of(2019, 10, 15));
            Historico h2 = new Historico(3,2,1,3,0,0,new Time(0,3,0),30,LocalDate.of(2019, 10, 20));
            Historico h3 = new Historico(3,3,1,3,0,0,new Time(0,3,0),30,LocalDate.of(2019, 10, 20));



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
                System.out.println("Bem vindo! ");
                System.out.println("(1) Efetuar Login  (2) Efetuar Registo  (3) Sair da aplicação");

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

