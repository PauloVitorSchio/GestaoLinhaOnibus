import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.Onibus.Onibus;
import modelos.Usuario.Usuario;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * menu_onibus
 */
public class menu_onibus {

    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        
        print_Menu();

        Onibus onibus = new Onibus();
        List<Usuario> usuarios = new ArrayList<Usuario>();

        int opcao;

        while (true) {
            System.out.println("Escolha uma opção:\n"
            + "0 - Sair do Sistema\n"
            + "1 - Ver Assentos Disponíveis\n"
            + "2 - Reservar Assento\n"
            + "3 - Comprar Assento\n"
            + "4 - Cadastrar Clientes\n"
            + "5 - Listar Clientes\n"
            + "6 - Cancelar Reserva");

            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 0:
                    return;
                case 1:
                    onibus.verAssentosDisponiveis();
                    break;
                case 2: 
                    onibus.reservarAssento();
                    break;
                case 3: 
                    onibus.comprarAssento();
                    break;
                case 4:
                    cadastrarUsuario(usuarios);
                    break;
                case 5:
                    listarUsuarios(usuarios);
                    break;
                case 6:
                    onibus.cancelarReserva();
                default:
                    return;
            }
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
        System.out.println("Usuário " + novoUsuario.getNome() + " cadastrado com sucesso.");
    }

    public static void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("USUÁRIOS:");
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " +usuario.getNome() + " | CPF: " + usuario.getCpf());
        }
    }

}
