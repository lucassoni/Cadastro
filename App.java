import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Cadastro cadastro = new Cadastro();
        Paciente user = cadastro.lePaciente(sc);
        while (true) {
            System.out.printf(
                    "\nBem vindo %s\nPara cadastrar um familiar digite 1\nPara cadastrar um medico digite 2\nPara cadastrar um exame/consulta digite 3\nPara cadastrar info emergencial digite 4\nPara ver seus exames/consultas digite 5\nPara excluir exames/consultas digite 6\nPara sair digite 7\n",user.getNome());

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Familiar familiar = cadastro.leFamiliar(sc);
                    familiar.imprimeFamiliar();
                    break;
                case 2:
                    Medico medico = cadastro.leMedico(sc);
                    medico.imprimeMedico();
                    break;
                case 3:
                    ConEx conex = cadastro.leConEx(sc);
                    conex.imprimeConEx();
                    break;
                case 4:
                    InfoEme inf = cadastro.leInfoEme(sc);
                    inf.imprimeInfoEme();
                    break;
                case 5:
                    ConEx reader = new ConEx();
                    reader.readConEx();
                    break;
                case 6:
                    ConEx deleter = new ConEx();
                    deleter.deleteConEx();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
