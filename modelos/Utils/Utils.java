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
