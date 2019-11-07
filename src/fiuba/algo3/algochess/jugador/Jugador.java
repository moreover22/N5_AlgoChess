package fiuba.algo3.algochess.jugador;

import fiuba.algo3.algochess.pieza.Pieza;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private int puntos;
    private List<Pieza> piezas;

    public Jugador(int puntos) {
        this.puntos = puntos;
        this.piezas = new ArrayList<>();
    }
    public Jugador() {
        this(20);
    }

    public void sacarPieza(Pieza pieza) {
        puntos += pieza.getCoste();
        piezas.remove(pieza);
    }

    public int getPuntos() {
        return puntos;
    }

    public void agregarPieza(Pieza pieza) throws CantidadDePuntosInsuficientesException {
        if (puntos - pieza.getCoste() < 0) {
            throw new CantidadDePuntosInsuficientesException();
        }
        puntos -= pieza.getCoste();
        piezas.add(pieza);
    }
    public boolean perdio() {

        for (Pieza pieza : piezas) {
            if (pieza.estaViva()) return false;
        }

        return true;
    }
}