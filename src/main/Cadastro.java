import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class Cadastro {

    public static String leHorario(Scanner sc, String nome, Calendar atual) {
        System.out.println(String.format("Digite horario %s: ", nome));
        String horario = sc.nextLine();

        String[] horarioSeparado = horario.split(":");

        int hora = Integer.parseInt(horarioSeparado[0]);
        int minuto = Integer.parseInt(horarioSeparado[1]);

        if (hora > atual.getActualMaximum(Calendar.HOUR_OF_DAY)
                || hora < atual.getActualMinimum(Calendar.HOUR_OF_DAY)
                || minuto > atual.getActualMaximum(Calendar.MINUTE)
                || minuto < atual.getActualMinimum(Calendar.MINUTE)) {
            System.out.println("Digite um horario valido.\n");
            return leHorario(sc, nome, atual);
        }

        Calendar rightNow = Calendar.getInstance();

        if (atual.get(Calendar.YEAR) == rightNow.get(Calendar.YEAR)
                && atual.get(Calendar.MONTH) == rightNow.get(Calendar.MONTH)
                && atual.get(Calendar.DAY_OF_MONTH) == rightNow.get(Calendar.DAY_OF_MONTH)) {
            int horaAtual = rightNow.get(Calendar.HOUR_OF_DAY);
            int minutoAtual = rightNow.get(Calendar.MINUTE);

            if (hora < horaAtual || (hora == horaAtual && minuto < minutoAtual)) {
                System.out.println("Digite um horario valido.\n");
                return leHorario(sc, nome, atual);
            }
        }

        return horario;
    }

    public static int leAno(Scanner sc, String nome) {
        System.out.println(String.format("Digite ano %s: ", nome));
        int ano = sc.nextInt();
        sc.nextLine();

        Calendar rightNow = Calendar.getInstance();
        int anoAtual = rightNow.get(Calendar.YEAR);
        if (ano < anoAtual || ano > anoAtual + 2) {
            System.out.println("Digite um ano valido.\n");
            return leAno(sc, nome);
        }

        return ano;
    }

    public static int leMes(Scanner sc, String nome, int ano) {
        System.out.println(String.format("Digite mes %s: ", nome));
        int mes = sc.nextInt();
        sc.nextLine();

        mes--;

        if (mes < 0 || mes > 11) {
            System.out.println("Digite um mes valido.\n");
            return leMes(sc, nome, ano);
        }
        Calendar rightNow = Calendar.getInstance();
        int anoAtual = rightNow.get(Calendar.YEAR);
        if (ano == anoAtual) {
            int mesAtual = rightNow.get(Calendar.MONTH);
            if (mes < mesAtual) {
                System.out.println("Digite um mes valido.\n");
                return leMes(sc, nome, ano);
            }
        }
        return mes;
    }

    public static int leDia(Scanner sc, String nome, Calendar atual) {
        System.out.println(String.format("Digite dia %s: ", nome));
        int dia = sc.nextInt();
        sc.nextLine();

        if (dia > atual.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            System.out.println("Digite um dia valido.\n");
            return leDia(sc, nome, atual);
        }

        Calendar rightNow = Calendar.getInstance();

        if (atual.get(Calendar.MONTH) == rightNow.get(Calendar.MONTH)
                && atual.get(Calendar.YEAR) == rightNow.get(Calendar.YEAR)) {
            int diaAtual = rightNow.get(Calendar.DAY_OF_MONTH);
            if (dia < diaAtual) {
                System.out.println("Digite um dia valido.\n");
                return leDia(sc, nome, atual);
            }
        }
        return dia;
    }

    public static Calendar leData(Scanner sc, String nome) {

        Calendar data = Calendar.getInstance();

        int ano = leAno(sc, nome);
        data.set(Calendar.YEAR, ano);

        int mes = leMes(sc, nome, ano);
        data.set(Calendar.MONTH, mes);

        int dia = leDia(sc, nome, data);
        data.set(Calendar.DAY_OF_MONTH, dia);

        String horario = leHorario(sc, nome, data);

        String[] horarioSeparado = horario.split(":");

        int hora = Integer.parseInt(horarioSeparado[0]);
        data.set(Calendar.HOUR_OF_DAY, hora);

        int minuto = Integer.parseInt(horarioSeparado[1]);
        data.set(Calendar.MINUTE, minuto);

        return data;
    }

    public static Medico procuraMedico(Scanner sc, Listagem listagem) {
        String nome = leNome(sc, "medico deste exame");
        if (listagem.getMedicos().size() > 0) {
            ArrayList<Medico> medicos = listagem.getMedicos();

            for (Medico medico : medicos) {
                if (nome.equals(medico.getNome())) {
                    return medico;
                }
            }
        }
        System.out.println("Medico nao cadastrado");
        return procuraMedico(sc, listagem);
    }

    public static Consulta leConsulta(Scanner sc, Listagem listagem) {

        Medico medico = procuraMedico(sc, listagem);

        Calendar data = leData(sc, "da consulta");

        Consulta consulta = new Consulta(medico, data);

        listagem.addConsulta(consulta);

        Random rand = new Random();

        WriteObjectToFile(consulta, "./resources/localStorage/consultas/" + "consulta" + rand.nextInt(293092039));

        return consulta;
    }

    public static String leEmail(Scanner sc, String membro) {
        System.out.println(String.format("Digite email do %s: ", membro));
        String email = sc.nextLine();

        if (!email.contains("@") || !email.endsWith(".com")) {
            System.out.println("Email digitado invalido.");
            email = leEmail(sc, membro);
        }

        return email;
    }

    public static String leEndereco(Scanner sc, String membro) {
        System.out.println(String.format("Digite endereco do %s: ", membro));
        String endereco = sc.nextLine();

        return endereco;
    }

    public static String leImagem(Scanner sc, String membro) {
        System.out.println(String.format("Digite o caminho da imagem do %s: ", membro));
        String nomeArquivo = sc.nextLine();

        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado.");
            leImagem(sc, membro);
        }

        Random random = new Random();
        int numero = random.nextInt(100000);
        String nomeArquivoNovo = numero + "_" + arquivo.getName();
        String caminho = "./resources/localStorage/imagens/" + nomeArquivoNovo;
        String caminhoRetorno = "./localStorage/imagens/" + nomeArquivoNovo;
        File arquivoNovo = new File(caminho);

        WriteObjectToFile((Object) arquivo, arquivoNovo.getPath());

        return caminho;
    }

    public static String leTelefone(Scanner sc, String membro) {
        System.out.println(String.format("Digite o telefone (com DDD) para contato %s:\nEx: (xx) xxxxx-xxxx", membro));
        String telefone = sc.nextLine();

        if (telefone.charAt(0) != '(' || telefone.charAt(3) != ')' || telefone.charAt(4) != ' '
                || telefone.length() != 15 || telefone.charAt(10) != '-') {
            System.out.println("Numero invalido digitado.\n");
            return leTelefone(sc, membro);
        }
        return telefone;
    }

    public static String leNome(Scanner sc, String membro) {
        System.out.println(String.format("Digite o nome do %s completo: ", membro));
        String nome = sc.nextLine();

        if (countWordsUsingSplit(nome) <= 1) {
            System.out.println("Nome digitado invalido. Precisamos do nome completo");
            return leNome(sc, membro);
        }
        return nome;
    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }

    private static void WriteObjectToFile(Object serObj, String filepath) {
        try {
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Object ReadObjectFromFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() != true)
            return null;
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Paciente lePaciente(Scanner sc) {
        Paciente paciente = (Paciente) ReadObjectFromFile("./resources/localStorage/user");

        if (paciente == null) {
            String nome = leNome(sc, "paciente");

            String telefone = leTelefone(sc, "do paciente");

            String endereco = leEndereco(sc, "paciente");

            String email = leEmail(sc, "paciente");

            String imagem = leImagem(sc, "paciente");

            paciente = new Paciente(nome, telefone, endereco, email, imagem);

            WriteObjectToFile(paciente, "./resources/localStorage/user");
        }
        return paciente;
    }

    public static Familiar leFamiliar(Scanner sc, Listagem listagem) {

        String nome = leNome(sc, "familiar");

        String telefone = leTelefone(sc, "de emergencia do familiar");

        Familiar familiar = new Familiar(nome, telefone);

        Random rand = new Random();

        listagem.addFamiliar(familiar);

        WriteObjectToFile(familiar, "./resources/localStorage/familiares/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return familiar;
    }

    public static Medico leMedico(Scanner sc, Listagem listagem) {
        String nome = leNome(sc, "medico");

        String telefone = leTelefone(sc, "medico");

        String endereco = leEndereco(sc, "medico");

        String imagem = leImagem(sc, "medico");

        Medico medico = new Medico(nome, telefone, endereco, imagem);

        Random rand = new Random();

        listagem.addMedico(medico);

        WriteObjectToFile(medico, "./resources/localStorage/medicos/" + nome.split(" ")[0] + rand.nextInt(293092039));

        return medico;
    }

    public static InfoEme leInfoEme(Scanner sc) {
        System.out.println("Digite a informacao a ser cadastrada: ");
        String texto = sc.nextLine();

        InfoEme info = new InfoEme(texto);

        Random rand = new Random();

        WriteObjectToFile(info, "./resources/localStorage/infoeme/" + "ie" + rand.nextInt(293092039));

        return info;
    }
}