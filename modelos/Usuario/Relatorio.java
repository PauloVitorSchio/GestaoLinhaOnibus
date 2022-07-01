package modelos.Usuario;

import java.io.Serializable;

public class Relatorio implements Serializable {
    private int linha;
    private int count_cancelados;
    private int count_comprados;
    private int count_reservados;

    public Relatorio() {
    }

    public Relatorio(int linha, int count_cancelados, int count_comprados, int count_reservados) {
        this.linha = linha;
        this.count_cancelados = count_cancelados;
        this.count_comprados = count_comprados;
        this.count_reservados = count_reservados;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getCount_cancelados() {
        return count_cancelados;
    }

    public void setCount_cancelados(int count_cancelados) {
        this.count_cancelados = count_cancelados;
    }

    public int getCount_comprados() {
        return count_comprados;
    }

    public void setCount_comprados(int count_comprados) {
        this.count_comprados = count_comprados;
    }

    public int getCount_reservados() {
        return count_reservados;
    }

    public void setCount_reservados(int count_reservados) {
        this.count_reservados = count_reservados;
    }

    
}
