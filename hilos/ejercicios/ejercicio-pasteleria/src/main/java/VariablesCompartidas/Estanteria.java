package VariablesCompartidas;

public class Estanteria {
    int pasteles = 0;
    int servidos = 0;

    public Estanteria(int pasteles, int servidos) {
        this.pasteles = pasteles;
        this.servidos = servidos;
    }

    public int getPasteles(){return pasteles;}
    public int getServidos(){return servidos;}

    public void setPasteles(int pasteles) {this.pasteles = pasteles;}
    public void setServidos(int servidos) {this.servidos = servidos;}
}
