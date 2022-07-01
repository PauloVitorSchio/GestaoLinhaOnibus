import java.io.File;

import modelos.Usuario.*;
import modelos.Utils.Utils;
public class test {
    public static void main(String[] args) {
        Usuario usu = new Usuario();
        usu.setCpf("11301563978");
        usu.setNome("Teste");
        Object[] obj = new Object[1];
        obj[0] = usu;
        File file = new File("usuarios.txt");
        Utils.salvaDados(obj, file);
    }
}
