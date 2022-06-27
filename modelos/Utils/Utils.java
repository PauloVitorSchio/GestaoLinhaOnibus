package modelos.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelos.Assento.Assento;
import modelos.Usuario.Usuario;

public class Utils {
    
    public static void salvaDados(Object[] array, File file) {
        /**
         * Recebe um array de objetos e grava-os em um arquivo.
         * @param array : um array de objetos qualquer
         * @param file : um arquivo do tipo File 
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

    public static void leArquivoUsuarios(Usuario[] array, File file) {
        /**
         * Lê um arquivo de objetos e os transcreve para um array de objetos do tipo Usuário.
         * @param array : um array de objetos do tipo Usuário
         * @param file : um arquivo do tipo File
         */
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            for (int i = 0; i < array.length; i++) {
                array[i] = (Usuario) objectInputStream.readObject();
            }

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leArquivoAssentos(Assento[] array, File file) {
        /**
         * Lê um arquivo de objetos e os transcreve para um array de objetos do tipo Assento.
         * @param array : um array de objetos do tipo Assento
         * @param file : um arquivo do tipo File
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
}