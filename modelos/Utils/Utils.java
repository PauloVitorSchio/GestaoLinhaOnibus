package modelos.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import modelos.Assento.Assento;
import modelos.Usuario.Relatorio;
import modelos.Usuario.Usuario;

public class Utils {

    public static void salvaDados(Object[] array, File file) {
        /**
         * Recebe um array de objetos e grava-os em um arquivo.
         * 
         * @param array : um array de objetos qualquer
         * @param file  : um arquivo do tipo File
         */
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int i = 0; i < array.length; i++) {
                objectOutputStream.writeObject(array[i]);
            }

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leArquivoUsuarios(List<Usuario> usuarios, File file) {
        /**
         * Lê um arquivo de objetos e os transcreve para um array de objetos do tipo Usuário.
         * @param array : um array de objetos do tipo Usuário
         * @param file : um arquivo do tipo File
         */
        try {
            usuarios.clear();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            boolean control = true;
            while(control){
                Usuario usu = null;
                try{
                    usu = (Usuario) objectInputStream.readObject();
                }catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }

                if(usu!=null){
                    usuarios.add(usu);
                }else{
                    control = false;
                }
            
            
            }
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            
        }
    }

    public static void leArquivoAssentos(Assento[] array, File file) {
        /**
         * Lê um arquivo de objetos e os transcreve para um array de objetos do tipo
         * Assento.
         * 
         * @param array : um array de objetos do tipo Assento
         * @param file  : um arquivo do tipo File
         */
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (int i = 0; i < array.length; i++) {
                array[i] = (Assento) objectInputStream.readObject();
            }

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void leArquivoRelatorio(Relatorio[] array, File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (int i = 0; i < array.length; i++) {
                array[i] = (Relatorio) objectInputStream.readObject();
            }

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Usuario confereSeUsuarioExiste(String cpf) {
        /*
         * recebe cpf como parametro
         * return um objeto se usuario existe
         * return null se usuario nao existe
         */
        File file_usu = new File("usuarios.txt");
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Utils.leArquivoUsuarios(usuarios, file_usu);
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }
}
