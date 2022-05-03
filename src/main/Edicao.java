import java.util.Scanner;
import java.io.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Edicao {
    public static void menuEdicaoConsulta(Scanner sc, Listagem listagem) {
        ArrayList<Consulta> consultas = listagem.getConsultas();
        if(consultas.size() == 0) {
            System.out.println("\nNenhuma consulta cadastrada\n");
            return;
        }

        System.out.println("Escolha a consulta a ser editada\n");
        listagem.listaConsultas();
        int option = sc.nextInt();
        sc.nextLine();

        if(option > consultas.size() || option < 1) {
            System.out.println("\nNumero invalido\n");
            return;
        }

        Consulta consultaEscolhida = consultas.get(option - 1);

        System.out.println("\nConsulta escolhida:\n");
        consultaEscolhida.imprimeConsulta();

        boolean sair = false;
        while (!sair) {
            System.out.println("Quer editar ou cancelar a consulta?\nDigite 1 para editar\nDigite 2 para cancelar\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaConsulta(sc, consultaEscolhida, listagem);
                    sair = true;
                    break;
                case 2:
                    deletaConsulta(consultaEscolhida, listagem, option - 1);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }

    public static File procuraConsulta(Consulta consulta, Listagem listagem) {
        File file = new File("./resources/localStorage/consultas");
        File fileConsulta = null;
        if (file.exists()) {
            File[] consultas = file.listFiles();
            for (File c : consultas) {
                Consulta candidata = (Consulta) ReadObjectFromFile(c.getPath());
                if (comparaConsulta(candidata, consulta)) {
                    fileConsulta = c;
                }
            }
        }
        return fileConsulta;
    }

    public static void deletaConsulta(Consulta consulta, Listagem listagem, int index) {
        File fileConsulta = procuraConsulta(consulta, listagem);
        fileConsulta.delete();

        ArrayList<Consulta> consultas = listagem.getConsultas();
        consultas.clear();
        listagem.carregarConsultas();
    }

    public static void editaConsulta(Scanner sc, Consulta consulta, Listagem listagem) {
        File fileConsulta = procuraConsulta(consulta, listagem);

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Para editar o nome do medico digite 1\nPara editar o horario e data da consulta digite 2\n");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaNome(sc, consulta, listagem);
                    sair = true;
                    break;
                case 2:
                    editaData(sc, consulta);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }

        WriteObjectToFile(consulta, fileConsulta.getPath());
    }

    public static void menuEdicaoFamiliar(Scanner sc, Listagem listagem) {
        ArrayList<Familiar> familiares = listagem.getFamiliares();
        if(familiares.size() == 0){
            System.out.println("Nenhum familiar cadastrado");
            return;
        }

        System.out.println("Escolha o familiar a ser editado\n");
        listagem.listaFamiliares();
        int option = sc.nextInt();
        sc.nextLine();

        if(option > familiares.size() || option < 1) {
            System.out.println("Numero invalido");
            return;
        }

        Familiar familiarEscolhido = familiares.get(option - 1);

        System.out.println("\nFamiliar escolhido:\n");
        familiarEscolhido.imprimeFamiliar();

        boolean sair = false;
        while (!sair) {
            System.out.println("Quer editar ou deletar o familiar?\nDigite 1 para editar\nDigite 2 para deletar\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaFamiliar(sc, familiarEscolhido, listagem);
                    sair = true;
                    break;
                case 2:
                    deletaFamiliar(familiarEscolhido, listagem, option - 1);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }

    public static File procuraFamiliar(Familiar familiar, Listagem listagem) {
        File file = new File("./resources/localStorage/familiares");
        File fileFamiliar = null;
        if (file.exists()) {
            File[] familiares = file.listFiles();
            for (File c : familiares) {
                Familiar candidata = (Familiar) ReadObjectFromFile(c.getPath());
                if (comparaFamiliar(candidata, familiar)) {
                    fileFamiliar = c;
                }
            }
        }
        return fileFamiliar;
    }

    public static void deletaFamiliar(Familiar familiar, Listagem listagem, int index) {
        File fileFamiliar = procuraFamiliar(familiar, listagem);
        fileFamiliar.delete();

        ArrayList<Familiar> familiares = listagem.getFamiliares();
        familiares.clear();
        listagem.carregarFamiliares();
    }

    public static void editaFamiliar(Scanner sc, Familiar familiar, Listagem listagem) {
        File fileFamiliar = procuraFamiliar(familiar, listagem);

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Para editar o nome do familiar digite 1\nPara editar o telefone digite 2\n");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    familiar.setNome(Cadastro.leNome(sc, "familiar"));
                    sair = true;
                    break;
                case 2:
                    familiar.setTelefone(Cadastro.leTelefone(sc, "de emergencia do familiar"));
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }

        WriteObjectToFile(familiar, fileFamiliar.getPath());
    }

    public static void menuEdicaoMedico(Scanner sc, Listagem listagem) {
        ArrayList<Medico> medicos = listagem.getMedicos();
        if(medicos.size() == 0){
            System.out.println("Nenhum medico cadastrado");
            return;
        }

        System.out.println("Escolha o medico a ser editado\n");
        listagem.listaMedicos();
        int option = sc.nextInt();
        sc.nextLine();

        if(option > medicos.size() || option < 1) {
            System.out.println("Numero invalido");
            return;
        }

        Medico medicoEscolhido = medicos.get(option - 1);

        System.out.println("\nMedico escolhido:\n");
        medicoEscolhido.imprimeMedico();

        boolean sair = false;
        while (!sair) {
            System.out.println("Quer editar ou deletar o medico?\nDigite 1 para editar\nDigite 2 para deletar\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaMedico(sc, medicoEscolhido, listagem);
                    sair = true;
                    break;
                case 2:
                    deletaMedico(medicoEscolhido, listagem, option - 1);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }

    public static File procuraMedico(Medico medico, Listagem listagem) {
        File file = new File("./resources/localStorage/medicos");
        File fileMedico = null;
        if (file.exists()) {
            File[] medicos = file.listFiles();
            for (File c : medicos) {
                Medico candidata = (Medico) ReadObjectFromFile(c.getPath());
                if (comparaMedico(candidata, medico)) {
                    fileMedico = c;
                }
            }
        }
        return fileMedico;
    }

    public static void menuEdicaoExame(Scanner sc, Listagem listagem) {
        ArrayList<Exame> exames = listagem.getExames();
        if(exames.size() == 0) {
            System.out.println("\nNenhum exame cadastrado\n");
            return;
        }

        System.out.println("Escolha o exame a ser editado\n");
        listagem.listaExames();
        int option = sc.nextInt();
        sc.nextLine();

        if(option > exames.size() || option < 1) {
            System.out.println("\nNumero invalido\n");
            return;
        }

        Exame exameEscolhido = exames.get(option - 1);

        System.out.println("\nExame escolhido:\n");
        exameEscolhido.imprimeExame();

        boolean sair = false;
        while (!sair) {
            System.out.println("Quer editar ou cancelar o exame?\nDigite 1 para editar\nDigite 2 para cancelar\n");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    editaExame(sc, exameEscolhido, listagem);
                    sair = true;
                    break;
                case 2:
                    deletaExame(exameEscolhido, listagem, option - 1);
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }

    public static File procuraExame(Exame exame, Listagem listagem) {
        File file = new File("./resources/localStorage/exames");
        File fileExame = null;
        if (file.exists()) {
            File[] exames = file.listFiles();
            for (File c : exames) {
                Exame candidata = (Exame) ReadObjectFromFile(c.getPath());
                if (comparaExame(candidata, exame)) {
                    fileExame = c;
                }
            }
        }
        return fileExame;
    }

    public static void deletaExame(Exame exame, Listagem listagem, int index) {
        File fileExame = procuraExame(exame, listagem);
        fileExame.delete();
        if(exame.getImagem() != null){
            File imagem = new File(exame.getImagem());
            imagem.delete();
        }
        if(exame.getVideo() != null){
            File video = new File(exame.getVideo());
            video.delete();
        }

        ArrayList<Exame> exames = listagem.getExames();
        exames.clear();
        listagem.carregarExames();
    }

    public static void editaExame(Scanner sc, Exame exame, Listagem listagem) {
        File fileExame = procuraExame(exame, listagem);

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Para editar o nome do exame digite 1\nPara editar o horario e data do exame digite 2\n");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    exame.setNome(Cadastro.leNome(sc, "exame"));
                    sair = true;
                    break;
                case 2:
                    exame.setData(Cadastro.leData(sc, "do exame"));
                    sair = true;
                    break;
                default:
                    System.out.println("Numero invalido");
            }
        }

        WriteObjectToFile(exame, fileExame.getPath());
    }


    public static void deletaMedico(Medico medico, Listagem listagem, int index) {
        File fileMedico = procuraMedico(medico, listagem);
        fileMedico.delete();
        File imagem = new File(medico.getImagem());
        imagem.delete();

        ArrayList<Medico> medicos = listagem.getMedicos();
        medicos.clear();
        listagem.carregarMedicos();
    }

    public static void editaMedico(Scanner sc, Medico medico, Listagem listagem) {
        File fileMedico = procuraMedico(medico, listagem);

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Para editar o nome do medico digite 1\nPara editar o telefone digite 2\nPara editar o endereço digite 3\nPara editar a imagem digite 4\n");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    medico.setNome(Cadastro.leNome(sc, "medico"));
                    sair = true;
                    break;
                case 2:
                    medico.setTelefone(Cadastro.leTelefone(sc, "medico"));
                    sair = true;
                    break;
                case 3:
                    medico.setEndereco(Cadastro.leEndereco(sc, "medico"));
                    sair = true;
                    break;
                case 4:
                    File imagem = new File(medico.getImagem());
                    imagem.delete();                
                    medico.setImagem(Cadastro.leImagem(sc, "medico"));
                    sair = true;
                    break;

                default:
                    System.out.println("Numero invalido");
            }
        }

        WriteObjectToFile(medico, fileMedico.getPath());
    }


    public static void menuUpload(Scanner sc, Listagem listagem, ArrayList<Consulta> consultas) {
        if (consultas.isEmpty()) {
            System.out.println("Nao existem consultas expiradas para fazer upload");
            return;
        }

        System.out.println("Essas sao as consultas disponiveis para upload de diagnostico e prescricao:\n");
        int i = 0;
        for (Consulta c : consultas) {
            System.out.println(String.format("Consulta %d", i + 1));
            c.imprimeConsulta();
            i++;
        }

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Favor digitar o numero da consulta para fazer upload\n");
            int option = sc.nextInt();
            sc.nextLine();
            if (consultas.size() + 1 > option && option > 0) {
                File fileConsulta = procuraConsulta(consultas.get(option - 1), listagem);

                System.out.println("Favor digitar diagnostico da consulta\n");
                consultas.get(option - 1).setDiagnostico(sc.nextLine());

                System.out.println("Favor digitar a prescricao da consulta\n");
                consultas.get(option - 1).setPrescricoes(sc.nextLine());

                WriteObjectToFile(consultas.get(option - 1), fileConsulta.getPath());

                sair = true;
                listagem.getConsultas().clear();
                listagem.carregarConsultas();
            } else {
                System.out.println("Numero invalido");
            }

        }
    }

        public static void menuUploadExames(Scanner sc, Listagem listagem, ArrayList<Exame> exames) {
        if (exames.isEmpty()) {
            System.out.println("Nao existem exames expirados para fazer upload");
            return;
        }

        System.out.println("Esses sao os exames disponiveis para upload de diagnostico e prescricao:\n");
        int i = 0;
        for (Exame c : exames) {
            System.out.println(String.format("Exame %d", i + 1));
            c.imprimeExame();
            i++;
        }

        boolean sair = false;
        while (!sair) {
            System.out.println(
                    "Favor digitar o numero do exame para fazer upload\n");
            int option = sc.nextInt();
            sc.nextLine();
            if (exames.size() + 1 > option && option > 0) {
                File fileExame = procuraExame(exames.get(option - 1), listagem);

                System.out.println("Favor digitar o resultado do exame\n");
                exames.get(option - 1).setResultado(sc.nextLine());

                System.out.println("Deseja cadastrar uma imagem?\n1 - Sim\n2 - Nao\n");
                int opcao = sc.nextInt();
                sc.nextLine();
                if (opcao == 1) {
                    exames.get(option - 1).setImagem(Cadastro.leImagem(sc, "exame"));
                }
                System.out.println("Deseja cadastrar um video?\n1 - Sim\n2 - Nao\n");
                opcao = sc.nextInt();
                sc.nextLine();
                if (opcao == 1) {
                    exames.get(option - 1).setVideo(Cadastro.leVideo(sc, "exame"));
                }

                WriteObjectToFile(exames.get(option - 1), fileExame.getPath());

                sair = true;
                listagem.getExames().clear();
                listagem.carregarExames();
            } else {
                System.out.println("Numero invalido");
            }

        }
    }
    

    public static void editaData(Scanner sc, Consulta consulta) {
        Calendar novaData = Cadastro.leData(sc, "da consulta");
        consulta.setData(novaData);
    }

    public static void editaNome(Scanner sc, Consulta consulta, Listagem listagem) {
        Medico medico = Cadastro.procuraMedico(sc, listagem);
        consulta.setMedico(medico);
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

    public static boolean comparaExame(Exame x, Exame y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return x.getNome().equals(y.getNome()) &&
                x.getResultado().equals(y.getResultado())&&
                x.getData().equals(y.getData());
    }


    public static boolean comparaFamiliar(Familiar x, Familiar y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return x.getNome().equals(y.getNome()) &&
                x.getTelefone().equals(y.getTelefone());
    }

    public static boolean comparaMedico(Medico x, Medico y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return x.getNome().equals(y.getNome()) &&
                x.getTelefone().equals(y.getTelefone()) &&
                x.getEndereco().equals(y.getEndereco());

    }

    public static boolean comparaConsulta(Consulta x, Consulta y) {
        if (x == y)
            return true;
        if (x == null || y == null || x.getClass() != y.getClass())
            return false;
        return comparaMedico(x.getMedico(), y.getMedico()) &&
                x.getData().equals(y.getData());
    }

}
