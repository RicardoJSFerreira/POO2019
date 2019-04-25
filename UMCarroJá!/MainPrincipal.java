import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;


public class MainPrincipal{

        public static Boolean procuraUserId(Integer id, UMCarroJa ucj){
            if(ucj.getUser(id) == null) return false;
            else return true;
        }

        public static Boolean procuraUserEmail(String email, UMCarroJa ucj){
            if(ucj.getUserEmail(email) == null) return false;
            else return true;
        }


        public static Integer getOpcao(){
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        }

        public static void progProprietario(Proprietario pro,UMCarroJa ucj){

        }

        public static void progCliente(Cliente cli,UMCarroJa ucj){

        }


        public static void registo(UMCarroJa ucj){

            Scanner sc = new Scanner(System.in);
            String email = "";
            boolean existe = true;
            while(existe == true){
                System.out.print("Email: ");
                email = sc.nextLine();

                if (procuraUserEmail(email,ucj) == false)

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

            System.out.print("Data Nascimento: ");

            System.out.print("Ano: ");
            Integer ano = sc.nextInt();

            System.out.print("Mês: ");
            Integer mes = sc.nextInt();

            System.out.print("Dia: ");
            Integer dia = sc.nextInt();

            Date dataNascimento = new Date(ano, mes-1, dia); // Nao tenho a certeza se está a carregar bem o mes
            System.out.println("Pretende ser Proprietário de Veiculos(1) ou Cliente(2)?");
            int resposta = sc.nextInt();

            if(resposta ==1){
                Integer id = ucj.getTodosUsers().size();
                Proprietario p = new Proprietario(id,email,nome, pass, morada, dataNascimento);
                ucj.addUser(p);
                System.out.println("Utilizador criado com sucesso");

                // Confirmação que insere nos Users
                // User p1 = tu.getUser(id);
                // System.out.println(p1);
            }

            if(resposta ==2){
                Integer id = ucj.getTodosUsers().size();

                System.out.println("Indique a sua localização");

                System.out.println("Indique a sua posição X");
                Integer x = sc.nextInt();
                System.out.println("Indique a sua posição Y");
                Integer y = sc.nextInt();

                Cliente c = new Cliente(id,email,nome, pass, morada, dataNascimento, x,y);
                ucj.addUser(c);
                System.out.println("Utilizador criado com sucesso");
                // Confirmação que insere nos Users
                // User c1 = tu.getUser(id);
                // System.out.println(c1);
            }



        }

        public static void acesso(UMCarroJa ucj){

            Scanner sc = new Scanner(System.in);

            String email = "";

            boolean existe = false;
            while(existe == false){
                System.out.print("Email: ");
                email = sc.nextLine();
                if(procuraUserEmail(email,ucj) == true){
                    existe = true;
                    //System.out.println("Encontrou o email");
                }
                else System.out.println("Email não existente. Tente de novo.");
            }

            int acesso = 0;
            while(acesso == 0){
                System.out.print("PASSWORD: ");
                String pass = sc.next();


                User u = ucj.getUserEmail(email);

                if((u.getPassword()).equals(pass)){
                    System.out.println("Acesso Garantido");
                    acesso=1;

                    if (u instanceof Proprietario) {
                        Proprietario pro = (Proprietario) u;
                        progProprietario(pro,ucj);
                        // Sabe que é um Proprietario
                        // System.out.println("Sabe que é um proprietario");
                    }
                    else{ // é um cliente
                        Cliente cli = (Cliente) u;
                        progCliente(cli,ucj);
                        // Sabe que é um cliente
                        // System.out.println("Sabe que é um cliente");
                    }
                }
                else System.out.println("Password errada");
            }

        }





        public static void carregaDados (UMCarroJa ucj){
            // Proprietário p1
            // Veiculos p1

            Veiculo v1 =  new Carro(1,true,66,1.3,5.6,60,50,30,4,6,"Gasolina","PO-24-19","Peugeot");
            Veiculo v2 =  new Carro(2,true,70,2,6,80,80,60,1,3,"Gasoleo","CG-34-25","Audi");

            ArrayList<Veiculo> veiculosp1 = new ArrayList<Veiculo>();
            veiculosp1.add(v1);
            veiculosp1.add(v2);
            Proprietario p1 = new Proprietario(0,"p1@gmail.com","miguel","pass","rua das bolinhas",new Date(1998,07,21),70,veiculosp1);

            // Proprietário p2
            // Veiculos p2

            Veiculo v3 =  new Carro(3,true,90,6,7,90,100,80,0,0,"Gasoleo","12-OR-23","BMW");
            Veiculo v4 =  new Carro(4,true,45,1,3,60,80,47,1,2,"Eletrico","39-LQ-11","Mercedes");

            ArrayList<Veiculo> veiculosp2 = new ArrayList<Veiculo>();
            veiculosp2.add(v3);
            veiculosp2.add(v4);
            Proprietario p2 = new Proprietario(1,"p2@gmail.com","Carlos","pass","rua das tortilhas",new Date(1995,9,21),78,veiculosp2);

            // Clientes
            Cliente c1 = new Cliente(2, "c1@gmail.com", "Ricardo", "pass", "Rua das Flores", new Date(1998,8,15), 2.0, 3.0, 10);
            Cliente c2 = new Cliente(3, "c2@gmail.com", "Joao", "pass", "Rua das Ortigas", new Date(1996,04,19), 1, 5.0, 8);

            // Historico
            Historico h1 = new Historico(1,3,1,1,4,6,new Time(0,2,0),10,new Date(2019, 10, 15));
            Historico h2 = new Historico(2,4,2,3,0,0,new Time(0,3,0),30,new Date(2019, 10, 15));




            // Carrega Proprietarios

            if(ucj.containsValue_(p1) == false)ucj.addUser(p1);
            if(ucj.containsValue_(p2) == false)ucj.addUser(p2);

            // Carrega Clientes

            if(ucj.containsValue_(c1) == false)ucj.addUser(c1);
            if(ucj.containsValue_(c2) == false)ucj.addUser(c2);

            // Carrega Historico

            if(ucj.containsValue_(h1) == false)ucj.addViagem(h1);
            if(ucj.containsValue_(h2) == false)ucj.addViagem(h2);
        }
        ////////////////////////////////// Main
        public static void main(String[] args) {


            UMCarroJa ucj = new UMCarroJa();


            carregaDados(ucj);

            System.out.println(ucj);
            Scanner sc = new Scanner(System.in);

            int menu = 0;

            do{
                System.out.println("O que pretende fazer?");
                System.out.println("(1) Login  (2) Registo  (3) Sair da aplicação");

                switch(getOpcao()){
                    case 1: acesso(ucj);
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