import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.Onibus.Onibus;
import modelos.Usuario.Usuario;
import modelos.Utils.Utils;

import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * menu_onibus
 */
public class menu_onibus {

    static Scanner teclado = new Scanner(System.in);
    static Onibus onibus = new Onibus();

    public static void main(String[] args) throws IOException, InterruptedException {

        print_Menu();
        List<Usuario> usuarios = new ArrayList<Usuario>();

        int opc;
        boolean flag = true;
        while (flag) {
            System.out.println("\n");
            System.out.println(
                    "*******************************************************************************************************************************************************************************\n"
                            + "                                                                          Escolha uma opção:\n"
                            + "                                                                          0 - Sair do Sistema \n"
                            + "                                                                         1 - Cadastrar Usuario\n"
                            + "                                                                         2 - Listar Usuarios\n"
                            +"                                                                         3 - Gerar Relatório de vendas\n"
                            + "                                                                         4 - Linha Biopark/Toledo\n"
                            + "                                                                         5 - Linha Toledo/Cascavel\n"
                            + "*******************************************************************************************************************************************************************************\n");

            opc = Integer.parseInt(teclado.nextLine());

            switch (opc) {
                case 0:
                    limpaConsole();
                    System.out.println("Saindo do Sistema...");
                    return;
                case 1:
                    limpaConsole();
                    cadastrarUsuario(usuarios);
                    break;
                case 2:
                    limpaConsole();
                    listarUsuarios(usuarios);
                    break;
                case 3:
                    limpaConsole();
                    onibus.relatorio();
                    break;
                case 4:
                    limpaConsole();
                    menu(1);
                    break;
                case 5:
                    limpaConsole();
                    menu(2);
                default:
                    System.out.println("Opção inválida!");
                    return;
            }
        }
    }

    public static void menu(int linha) throws IOException, InterruptedException {
        int opcao;

        while (true) {
            System.out.println("\n");
            System.out.println(
                    "*******************************************************************************************************************************************************************************\n"
                            + "                                                                          Escolha uma opção:\n"
                            + "                                                                          0 - Sair do Sistema \n"
                            + "                                                                         1 - Ver Assentos Disponíveis\n"
                            + "                                                                         2 - Reservar Assento\n"
                            + "                                                                         3 - Comprar Assento\n"
                            + "                                                                         4 - Cancelar Reserva\n"
                            + "*******************************************************************************************************************************************************************************\n");

            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 0:
                    limpaConsole();
                    return;
                case 1:
                    limpaConsole();
                    onibus.verAssentosDisponiveis(linha);
                    break;
                case 2:
                    limpaConsole();
                    onibus.reservarAssento(linha);
                    break;
                case 3:
                    limpaConsole();
                    onibus.comprarAssento(linha);
                    break;
                case 4:
                    limpaConsole();
                    onibus.cancelarReserva(linha);
                    break;
                default:
                    return;
            }

            Thread.sleep(1000);
        }
    }

    public static void print_Menu() {
        int width = 150;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("arial", Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("Bem-vindo", 0, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "*");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }

    }


    public static void cadastrarUsuario(List<Usuario> usuarios) {
        File file = new File("usuarios.txt"); /**/
        Utils.leArquivoUsuarios(usuarios, file);
        System.out.println("Digite o CPF:");
        String cpf = teclado.nextLine();
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                System.out.println("Usuário já cadastrado.");
                return;
            }
        }
        System.out.println("Digite o nome:");
        String nome = teclado.nextLine();
        Usuario novoUsuario = new Usuario(nome, cpf);
        usuarios.add(novoUsuario);
        Object[] array = new Object[usuarios.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = usuarios.get(i);
        }
        Utils.salvaDados(array, file);
        System.out.println("Usuário " + novoUsuario.getNome() + " foi cadastrado com sucesso!");
    }

    public static void listarUsuarios(List<Usuario> usuarios) {
        File file = new File("usuarios.txt");
        Utils.leArquivoUsuarios(usuarios, file);
        System.out.println("USUÁRIOS:");
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome() + " | CPF: " + usuario.getCpf());
        }
    }
    public static void limpaConsole() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }

}
