package modelos.Onibus;

import java.io.Serializable;

import modelos.Assento.Assento;
import modelos.Assento.Status;

public class Onibus implements Serializable {

    String ANSI_RESET = "\u001B[0m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_YELLOW = "\u001B[33m";
    String ANSI;

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

    public void verAssentosDisponiveis() {
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
                    }else if(assentos[aux].getStatus() == Status.OCUPADO){
                         ANSI = ANSI_RED;
                    }else{
                            ANSI = ANSI_YELLOW;
                    }



                    System.out.print("| "+ANSI+ assentos[aux].getNumero() + " " + assentos[aux].getStatus() + ANSI_RESET+" |");
                    aux = aux + 4;
                }
                System.out.println("");
            } else {
                System.out.println("\n");
            }
        }
        System.out.println("---------------------------------------");

    }

    public void reservarAssento() {
        System.out.println("Entrou na função de reservar assento");
    }

    public void comprarAssento() {
        System.out.println("Entrou na função de comprar assento");
    }

    public void cancelarReserva() {
        System.out.println("Entrou na função de cancelar reserva");
    }
}
