package modelos.Assento;

import modelos.Usuario.*;

public class Assento {

    private int numero;
    private Status status;
    private Usuario dono;

    public Assento(int numero, Status status, Usuario dono) {
        this.numero = numero;
        this.status = status;
        this.dono = dono;
    }

    public Assento(int numero) {
        this.numero = numero;
        this.status = Status.LIVRE;
        this.dono = null;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getDono() {
        return this.dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }


    
}
