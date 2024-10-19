package main.java.umg.edu;

public class Proceso {
    private int id;
    private int tiempoRafaga;
    private int tiempoLlegada;
    private int tiempoRestante;
    private int tiempoEspera;
    private int tiempoRetorno;

    public Proceso(int id, int tiempoRafaga, int tiempoLlegada) {
        this.id = id;
        this.tiempoRafaga = tiempoRafaga;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoRestante = tiempoRafaga;
        this.tiempoEspera = 0;
        this.tiempoRetorno = 0;
    }

    public int getId() { return id; }
    public int getTiempoRafaga() { return tiempoRafaga; }
    public int getTiempoLlegada() { return tiempoLlegada; }
    public int getTiempoRestante() { return tiempoRestante; }
    public void setTiempoRestante(int tiempoRestante) { this.tiempoRestante = tiempoRestante; }
    public int getTiempoEspera() { return tiempoEspera; }
    public void setTiempoEspera(int tiempoEspera) { this.tiempoEspera = tiempoEspera; }
    public int getTiempoRetorno() { return tiempoRetorno; }
    public void setTiempoRetorno(int tiempoRetorno) { this.tiempoRetorno = tiempoRetorno; }
}