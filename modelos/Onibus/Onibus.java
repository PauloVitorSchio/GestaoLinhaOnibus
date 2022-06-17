package modelos.Onibus;

import modelos.Assento.Assento;

public class Onibus {
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
                    System.out.print("| " + assentos[aux].getNumero() + " " + assentos[aux].getStatus() + " |");
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
