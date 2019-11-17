package fiuba.algo3.algochess.tablero;

import fiuba.algo3.algochess.Aliable;
import fiuba.algo3.algochess.Posicion;
import fiuba.algo3.algochess.Rango;
import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.tablero.casillero.*;

import java.util.HashMap;
import java.util.Map;

public class Tablero implements Aliable {
    private Map<Posicion, Casillero> casilleros;
    private int cantFilas;
    private int cantColumnas;
    private Rango rango;

    public Tablero() {
        this(20, 20);
    }
    public Tablero(int cantFilas, int cantColumnas) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.rango = new Rango(0, 0, cantFilas, cantColumnas);
        this.casilleros = new HashMap<>();
        inicializarCasilleros();
    }

    private void inicializarCasilleros() {
        for (int i = 0; i < cantFilas; i++) {
            iniciarFila(i);
        }
    }

    private void iniciarFila(int fila) {
        for (int j = 0; j < cantColumnas; j++){
            Casillero casillero = new Casillero();
            casilleros.put(new Posicion(fila, j), casillero);
        }
        for (int j = cantColumnas / 2; j < cantColumnas; j++){
            casilleros.get(new Posicion(fila, j)).cambiarAlianza();
        }
    }

    private Casillero getCasillero(Posicion posicion) throws FueraDelTableroException {
        if (!posicion.estaDentroDe(rango)) throw new FueraDelTableroException();
        return casilleros.get(posicion);
    }

    public void posicionar(Posicion posicion) throws FueraDelTableroException, PosicionarEnCasilleroEnemigoException {
        getCasillero(posicion).posicionar();
    }

    public void ocupar(Posicion posicion, Pieza pieza) throws FueraDelTableroException {
        getCasillero(posicion).ocupar(pieza);
    }

    public void sacar(Posicion posicion) throws FueraDelTableroException {
        getCasillero(posicion).sacar();
    }

    @Override
    public void cambiarAlianza() {
        casilleros.forEach((posicion, casillero) -> casillero.cambiarAlianza());
    }
}







