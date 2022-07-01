package modelos.Onibus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.Assento.Assento;
import modelos.Assento.Status;
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
        System.out.println("\n");
        System.out.println("---------------------------------------");
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                index++;
                aux = index;
                for (int j = 0; j < 5; j++) {
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
                System.out.println("");
            } else {
                System.out.println("\n");
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

        if (((numero >= 0) && (numero < 19)) && (assentos[numero].getStatus() == Status.LIVRE)) {
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
                }
            } else {
                System.out.println("Usuario não Cadastrado");
            }
        } else {
            System.out.println("Assento não Disponível ou não encontrado");
        }
    }

    public void cancelarReserva(int linha) {
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
                System.out.println("Informe qual assento deseja cancelar ou 'n' para sair:");
                try {
                    int numero = Integer.parseInt(teclado.nextLine());
                    if (control.contains(numero)) {
                        assentos[numero].setStatus(Status.LIVRE);
                        assentos[numero].setDono(null);
                        count--;
                    } else {
                        System.out.println("Este assento não é seu");
                    }
                } catch (Exception ex) {
                    count = 0;
                }
            }

            // salva arquivo
            if (control.size() == 0) {
                System.out.println("Voce ainda não possui reservas");
            } else if (salvar(file, assentos)) {
                System.out.println("cancelado com sucesso");
            }

        }

    }

    public void comprarAssento(int linha) {
        System.out.println("Digite cpf do cliente: ");
        cpf = teclado.nextLine();
        Usuario usuario = Utils.confereSeUsuarioExiste(cpf);
        if (usuario != null) {
            File file = new File("assentos_" + linha + ".txt");
            Assento[] assentos = new Assento[20];
            List<Integer> control = Controla_Reserva(assentos, file, cpf);
            if (control.size() > 0) {
                System.out.println("seus assentos");
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
                        break;
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

        if (((numero >= 0) && (numero < 19)) && (assentos[numero].getStatus() == Status.LIVRE)) {
            assentos[numero].setStatus(Status.OCUPADO);
            assentos[numero].setDono(usuario);
            if (salvar(file, assentos)) {
                System.out.println("Comprado com sucesso");
            }
        } else {
            System.out.println("Assento inválido ou já ocupado");
        }
    }

    public void comprar_reservada(List<Integer> control, int linha, Usuario usuario) {
        int count = control.size();
        File file = new File("assentos_" + linha + ".txt");
        Assento[] assentos = new Assento[20];
        Utils.leArquivoAssentos(assentos, file);
        while (count > 0) {
            System.out.println("Informe qual assento deseja comprar ou 'n' para sair:");
            try {
                int numero = Integer.parseInt(teclado.nextLine());
                if (control.contains(numero)) {
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

    }

    public boolean salvar(File file, Assento[] assentos) {
        for (int i = 0; i < assentos.length; i++) {
            obj[i] = assentos[i];
        }

        Utils.salvaDados(obj, file);
        return true;
    }

}
