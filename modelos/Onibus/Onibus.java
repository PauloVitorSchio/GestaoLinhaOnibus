package modelos.Onibus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.Assento.Assento;
import modelos.Assento.Status;
import modelos.Usuario.Relatorio;
import modelos.Usuario.Usuario;
import modelos.Utils.Utils;

public class Onibus implements Serializable {
    Object[] obj = new Object[20];
    Scanner teclado = new Scanner(System.in);
    String ANSI_RESET = "\u001B[0m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI;
    String cpf;

    private Assento[] assentos;

    public Onibus() {
        this.assentos = new Assento[20];
        for (int i = 0; i < assentos.length; i++) {
            assentos[i] = new Assento(i);
        }

    }

    public Assento[] getAssentos() {
        return this.assentos;
    }

    public void setAssentos(Assento[] assentos) {
        this.assentos = assentos;
    }

    public void verAssentosDisponiveis(int linha) {

        File file = new File("assentos_" + linha + ".txt");
        Assento[] assentos = new Assento[20];
        Utils.leArquivoAssentos(assentos, file);

        int index = -1;
        int aux = 0;
        System.out.println();
        System.out.println("---------------------------------------");
        for (int i = 0; i < 5; i++) {
            // Configura a listagem de assentos em formato de fileira de ônibus.
            // São 4 fileiras e um corredor, totalizando 5.
            if (i != 2) {
                // i == 2 representa o index do corredor, portanto não deve haver cadeiras ali.
                index++;
                aux = index;
                for (int j = 0; j < 5; j++) {
                    // Seta a cor de acordo com o status do assento.
                    if (assentos[aux].getStatus() == Status.LIVRE) {
                        ANSI = ANSI_GREEN;
                    } else if (assentos[aux].getStatus() == Status.OCUPADO) {
                        ANSI = ANSI_RED;
                    } else {
                        ANSI = ANSI_YELLOW;
                    }

                    System.out.print("| " + ANSI + assentos[aux].getNumero() + " " + assentos[aux].getStatus()
                            + ANSI_RESET + " |");
                    aux = aux + 4;
                }
                System.out.println();
            } else {
                System.out.println();
            }
        }
        System.out.println("---------------------------------------");

    }

    public void reservarAssento(int linha) {
        verAssentosDisponiveis(linha);
        // checar se o assento esta livre
        System.out.println("informe o assento desejado: ");
        int numero = Integer.parseInt(teclado.nextLine());
        File file = new File("assentos_" + linha + ".txt");
        Assento[] assentos = new Assento[20];
        Utils.leArquivoAssentos(assentos, file);

        if (((numero >= 0) && (numero <= 19)) && (assentos[numero].getStatus() == Status.LIVRE)) {
            // checa se o cliente esta cadastrado
            System.out.println("Digite o cpf do cliente: ");
            cpf = teclado.nextLine();
            Usuario usu = Utils.confereSeUsuarioExiste(cpf);
            if (usu != null) {
                // reserva e salva arquivo
                assentos[numero].setStatus(Status.RESERVADO);
                assentos[numero].setDono(usu);
                if (salvar(file, assentos)) {
                    System.out.println("Reservado Com Sucesso");
                    File file_reserva = new File("relatorio.txt");
                    Relatorio[] relatorio = new Relatorio[2];

                    Utils.leArquivoRelatorio(relatorio, file_reserva);
                    relatorio[linha-1].setCount_reservados(relatorio[linha-1].getCount_reservados()+1);
                    salvar_relatorio(file_reserva, relatorio);

                }
            } else {
                System.out.println("Usuario não Cadastrado");
            }
        } else {
            System.out.println("Assento não Disponível ou não encontrado");
        }
    }

    public void cancelarReserva(int linha) {
        int count_cancelar = 0;
        // checa se usuario existe
        System.out.println("Informe seu cpf: ");
        cpf = teclado.nextLine();
        Usuario usu = Utils.confereSeUsuarioExiste(cpf);
        if (usu != null) {
            // checa assentos reservados por ele
            File file = new File("assentos_" + linha + ".txt");
            Assento[] assentos = new Assento[20];
            System.out.println("Seus assentos");
            List<Integer> control = Controla_Reserva(assentos, file, cpf);

            // pede qual ele quer cancelar
            int count = control.size();
            while (count > 0) {
                System.out.println("Informe qual assento deseja cancelar:");
                    int numero = Integer.parseInt(teclado.nextLine());
                    if (control.contains(numero)) {
                        count_cancelar++;
                        assentos[numero].setStatus(Status.LIVRE);
                        assentos[numero].setDono(null);
                        count--;
                        System.out.println("Cancelado com sucesso");
                    } else {
                        System.out.println("Este assento não é seu.");
                        count = 0;
                    }
            }

            // salva arquivo
            if (control.size() == 0) {
                System.out.println("Voce ainda não possui reservas");
            } else if (salvar(file, assentos)) {
                File file_cancela = new File("relatorio.txt");
                Relatorio[] relatorio = new Relatorio[2];

                Utils.leArquivoRelatorio(relatorio, file_cancela);
                relatorio[linha-1].setCount_cancelados(relatorio[linha-1].getCount_cancelados()+count_cancelar);
                salvar_relatorio(file_cancela, relatorio);
            }

        }

    }

    public void comprarAssento(int linha) {
        System.out.println("Digite CPF do cliente: ");
        cpf = teclado.nextLine();
        Usuario usuario = Utils.confereSeUsuarioExiste(cpf);
        if (usuario != null) {
            File file = new File("assentos_" + linha + ".txt");
            Assento[] assentos = new Assento[20];
            List<Integer> control = Controla_Reserva(assentos, file, cpf);
            if (control.size() > 0) {
                System.out.println("Seus assentos");
                int opc;
                System.out.println("Digite 1 para comprar novo assento ou 2 para comprar assento reservado: ");
                opc = Integer.parseInt(teclado.nextLine());
                switch (opc) {
                    case 1:
                        comprar_livre(linha, usuario);
                        break;
                    case 2:
                        comprar_reservada(control, linha, usuario);
                        break;
                    default:
                        return;
                }
            } else {
                comprar_livre(linha, usuario);
            }
        } else {
            System.out.println("Usuário inválido");
        }
    }

    public void comprar_livre(int linha, Usuario usuario) {
        verAssentosDisponiveis(linha);
        System.out.println("Qual assento deseja comprar: ");
        int numero = Integer.parseInt(teclado.nextLine());
        File file = new File("assentos_" + linha + ".txt");
        Assento[] assentos = new Assento[20];
        Utils.leArquivoAssentos(assentos, file);

        if (((numero >= 0) && (numero <= 19)) && (assentos[numero].getStatus() == Status.LIVRE)) {
            assentos[numero].setStatus(Status.OCUPADO);
            assentos[numero].setDono(usuario);
            System.out.println("Comprado com sucesso");
            if (salvar(file, assentos)) {
                File file_comprar = new File("relatorio.txt");
                Relatorio[] relatorio = new Relatorio[2];

                Utils.leArquivoRelatorio(relatorio, file_comprar);
                relatorio[linha-1].setCount_comprados(relatorio[linha-1].getCount_comprados()+1);
                salvar_relatorio(file_comprar, relatorio);
            }
        } else {
            System.out.println("Assento inválido ou já ocupado");
        }
    }

    public void comprar_reservada(List<Integer> control, int linha, Usuario usuario) {
        int count = control.size();
        int count_comprar=0;
        File file = new File("assentos_" + linha + ".txt");
        Assento[] assentos = new Assento[20];
        Utils.leArquivoAssentos(assentos, file);
        while (count > 0) {
            System.out.println("Informe qual assento deseja comprar ou 'n' para sair:");
            try {
                int numero = Integer.parseInt(teclado.nextLine());
                if (control.contains(numero)) {
                    count_comprar++;
                    assentos[numero].setStatus(Status.OCUPADO);
                    assentos[numero].setDono(usuario);
                    count--;
                } else {
                    System.out.println("Este assento não é seu");
                }
            } catch (Exception ex) {
                count = 0;
            }
        }

        if (salvar(file, assentos)) {
            System.out.println("Comprado com sucesso");
            File file_comprar = new File("relatorio.txt");
            Relatorio[] relatorio = new Relatorio[2];

            Utils.leArquivoRelatorio(relatorio, file_comprar);
            relatorio[linha-1].setCount_comprados(relatorio[linha-1].getCount_comprados()+count_comprar);
            salvar_relatorio(file_comprar, relatorio);
        }
    }

    public List<Integer> Controla_Reserva(Assento[] assentos, File file, String cpf) {
        // verifica quais assentos são reservados pelo usuario
        Utils.leArquivoAssentos(assentos, file);

        List<Integer> control = new ArrayList<Integer>();
        for (Assento assento : assentos) {
            if (assento.getDono() != null) {
                if ((assento.getDono().getCpf().equals(cpf)) && (assento.getStatus() == Status.RESERVADO)) {
                    System.out.println(assento.getNumero());
                    control.add(assento.getNumero());
                }
            }
        }
        return control;
    }

    public void relatorio() {
        File file = new File("relatorio.txt");
        Relatorio[] array = new Relatorio[2];

        Utils.leArquivoRelatorio(array, file);
        for (Relatorio relatorio : array) {
            if (relatorio.getLinha() == 1) {
                System.out.println("Linha: Biopark/Toledo");
            } else {
                System.out.println("Linha: Toledo/Cascavel");
            }
            System.out.println("Cancelados: "+relatorio.getCount_cancelados());
            System.out.println("Comprados: "+relatorio.getCount_comprados());
            System.out.println("Reservados: "+relatorio.getCount_reservados());
            System.out.println("---------------------------------------------------------------");
        }
    }

    public boolean salvar(File file, Assento[] assentos) {
        for (int i = 0; i < assentos.length; i++) {
            obj[i] = assentos[i];
        }

        Utils.salvaDados(obj, file);
        return true;
    }


    public boolean salvar_relatorio(File file, Relatorio[] relatorio) {
        for (int i = 0; i < relatorio.length; i++) {
            obj[i] = relatorio[i];
        }

        Utils.salvaDados(obj, file);
        return true;
    }
}
