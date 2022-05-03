import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Paciente user = Cadastro.lePaciente(sc);
        Listagem listagem = new Listagem();
        listagem.carregarMedicos();
        listagem.carregarFamiliares();
        listagem.carregarConsultas();
        listagem.procuraConsultasDoDia();
        listagem.procuraConsultasExpiradas();
        while (true) {
            System.out.printf(
                    "Bem vindo %s\nPara acessar o menu de familiares digite 1\nPara acessar o menu de medicos digite 2\nPara acessar o menu de consultas digite 3\nPara acessar o menu de info emergenciais 4\nPara sair digite 5\n",
                    user.getNome());

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    boolean sair = false;
                    while (!sair) {
                        System.out.printf(
                                "\nPara visualizar os familiares cadastrados digite 1\nPara editar os familiares digite 2\nPara cadastrar novos familiares digite 3\nPara voltar ao menu inicial digite 4\n");
                        int optionConsult = sc.nextInt();
                        sc.nextLine();

                        switch (optionConsult) {
                            case 1:
                                listagem.listaFamiliares();
                                break;
                            case 2:
                                Edicao.menuEdicaoFamiliar(sc, listagem);
                                break;
                            case 3:
                                Familiar novoFamiliar = Cadastro.leFamiliar(sc, listagem);
                                novoFamiliar.imprimeFamiliar();
                                break;
                            case 4:
                                sair = true;
                                break;
                            default:
                                System.out.println("\nNumero invalido");
                        }
                    }
                    break;
                case 2:
                    sair = false;
                    while (!sair) {
                        System.out.printf(
                                "\nPara visualizar os medicos cadastrados digite 1\nPara editar os medicos digite 2\nPara cadastrar novos medicos digite 3\nPara voltar ao menu inicial digite 4\n");
                        int optionConsult = sc.nextInt();
                        sc.nextLine();

                        switch (optionConsult) {
                            case 1:
                                listagem.listaMedicos();
                                sair = false;
                                while (!sair) {
                                    if(listagem.getMedicos().size() == 0) {
                                        System.out.println("\nNenhum médico cadastrado\n");
                                        sair = true;
                                    } else {
                                        System.out.printf("\nPara visualizar imagem de um medico digite 1\nPara voltar ao menu inicial digite 2\n");
                                        optionConsult = sc.nextInt();
                                        sc.nextLine();
                                        switch (optionConsult) {
                                            case 1:
                                                System.out.printf("\nDigite o numero do medico\n");
                                                option = sc.nextInt();
                                                sc.nextLine();
                                                listagem.getMedicos().get(option - 1).mostraImagem();
                                                break;
                                            case 2:
                                                sair = true;
                                                break;
                                            default:
                                            System.out.println("\nNumero invalido");
                                        }
                                    }
                                }
                                break;
                            case 2:
                                Edicao.menuEdicaoMedico(sc, listagem);
                                break;
                            case 3:
                                Medico novoMedico = Cadastro.leMedico(sc, listagem);
                                novoMedico.imprimeMedico();
                                novoMedico.mostraImagem();
                                break;
                            case 4:
                                sair = true;
                                break;
                            default:
                                System.out.println("\nNumero invalido");
                        }
                    }
                    break;
                case 3:
                    sair = false;
                    while (!sair) {
                        System.out.printf(
                                "\nPara visualizar consultas cadastradas digite 1\nPara editar consultas digite 2\nPara cadastrar novas consultas digite 3\nPara fazer upload de resultados de consulta, digite 4\nPara voltar ao menu inicial digite 5\n");
                        int optionConsult = sc.nextInt();
                        sc.nextLine();

                        switch (optionConsult) {
                            case 1:
                                listagem.listaConsultas();
                                break;
                            case 2:
                                Edicao.menuEdicaoConsulta(sc, listagem);
                                break;
                            case 3:
                                Consulta consulta = Cadastro.leConsulta(sc, listagem);
                                consulta.imprimeConsulta();
                                break;
                            case 4:
                                ArrayList<Consulta> consultas = listagem.resgataConsultasExpiradas();
                                Edicao.menuUpload(sc, listagem, consultas);
                                break;
                            case 5:
                                sair = true;
                                break;
                            default:
                                System.out.println("\nNumero invalido");
                        }
                    }
                    break;
                case 4:
                    sair = false;
                    while (!sair) {
                        System.out.printf(
                                "\nPara cadastrar informacao emergencial/necessidades/cuidados digite 1\nPara editar informacao emergencial/necessidades/cuidados digite 2\nPara remover informacao emergencial/necessidades/cuidados digite 3\nPara voltar ao menu inicial digite 4\n");
                        int optionInfo = sc.nextInt();
                        sc.nextLine();
                        switch (optionInfo) {
                            case 1:
                                InfoEme inf = Cadastro.leInfoEme(sc);
                                System.out.println("Informacao cadastrada: ");
                                inf.imprimeInfoEme();
                                break;
                            case 2:
                                InfoEme edit = new InfoEme();
                                edit.edita(sc, listagem);
                                break;
                            case 3:
                                InfoEme deleter = new InfoEme();
                                deleter.deleta(sc, listagem);
                                break;
                            case 4:
                                sair = true;
                                break;
                            default:
                                System.out.println("\nNumero invalido");
                        }
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
