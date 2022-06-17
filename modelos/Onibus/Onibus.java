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
        for (int i = 0; i < 20; i++) {
            System.out.println(
                "Número: " + assentos[i].getNumero() + " - Status: " + assentos[i].getStatus()
            );
        }
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
