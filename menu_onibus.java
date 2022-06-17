import java.util.Scanner;

import modelos.Onibus.Onibus;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * menu_onibus
 */
public class menu_onibus {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        print_Menu();
        System.out.println("-------------------------------------------------");
        // pede cpf e senha
        System.out.println("Digite o CPF: ");
        String cpf = teclado.nextLine();
        System.out.println("Digite a senha: ");
        String senha = teclado.nextLine();
        System.out.println("-------------------------------------------------");
        boolean usuarioAutenticado = confere_login(cpf, senha);

        if (usuarioAutenticado) {

            Onibus onibus = new Onibus();
            int opcao = -1;
            do {
                System.out.println("Escolha uma opção:\n"
                + "0 - Sair do Sistema\n"
                + "1 - Ver Assentos Disponíveis\n"
                + "2 - Reservar Assento\n"
                + "3 - Comprar Assento\n");

                opcao = teclado.nextInt();

                switch (opcao) {
                    case 1:
                        onibus.verAssentosDisponiveis();
                        break;
                    case 2: 
                        onibus.reservarAssento();
                        break;
                    case 3: 
                        onibus.comprarAssento();
                        break;
                }
            } while (opcao != 0);
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

    public static boolean confere_login(String cpf, String senha){
        if(cpf.equals("123456789") && senha.equals("123")){
            return true;
        }
        return false;
    } 
}
