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
                    "Bem vindo %s\nPara cadastrar um familiar digite 1\nPara cadastrar um medico digite 2\nPara listar os medicos digite 3\nPara acessar o menu de consultas digite 4\nPara acessar o menu de info emergenciais 5\nPara sair digite 6\n",
                    user.getNome());

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Familiar familiar = Cadastro.leFamiliar(sc, listagem);
                    familiar.imprimeFamiliar();
                    break;
                case 2:
                    Medico medico = Cadastro.leMedico(sc, listagem);
                    medico.imprimeMedico();
                    break;
                case 3:
                    listagem.listaMedicos();
                    break;
                case 4:
                    boolean sair = false;
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
                case 5:
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
                case 6:
                    return;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
