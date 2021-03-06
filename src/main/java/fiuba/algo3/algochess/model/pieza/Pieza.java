package fiuba.algo3.algochess.model.pieza;

import fiuba.algo3.algochess.model.Aliable;
import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.alcance.AlcanceInmediato;
import fiuba.algo3.algochess.model.pieza.habilidad.*;
import fiuba.algo3.algochess.model.pieza.movimiento.Direccion;
import fiuba.algo3.algochess.model.pieza.movimiento.Movimiento;
import fiuba.algo3.algochess.model.pieza.movimiento.MovimientoFueraDeAlcanceException;
import fiuba.algo3.algochess.model.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.model.tablero.Tablero;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Pieza implements Aliable, Movible, Parseable {
    private Vida vida;
    private int coste;

    protected String tipoPieza;
    protected Posicion posicion;
    protected Habilidad habilidad;
    protected Movimiento movimiento;
    protected PiezaAlianza alianza;
    private String color;
    private List<Pieza> piezas;


    static final int PORCENTAJE_DANIO_TERRITORIO = 5;

    protected Pieza(float vidaInicial, int coste) {
        this.vida = new Vida(vidaInicial);
        this.coste = coste;
        this.alianza = new PiezaAliada();
        this.movimiento = new Movimiento(new AlcanceInmediato());
        piezas = new ArrayList<>();
    }

    public void usarHabilidadEn(Tablero tablero, Pieza objetivo) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, FueraDelTableroException, CuracionACatapultaException {
        habilidad.usarCon(objetivo, posicion);
        if (!objetivo.estaViva()) tablero.vaciar(objetivo.getPosicion());
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void recibirHabilidad(Habilidad habilidad, float cantidad ) throws AtaqueAAliadoException, CuracionAEnemigoException, CuracionACatapultaException {
        float vidaActual = habilidad.recibirHabilidad(cantidad, habilidad, this, this.alianza);
        vida.recibirHabilidad(vidaActual);
    }

    public float getVida() {
        return vida.vidaActual();
    }

    public boolean estaViva(){
        return vida.tieneVida();
    }

    public int descontarCoste(int puntos){
        return puntos - coste;
    }

    public int agregarCoste(int puntos){
        return puntos + coste;
    }

    public void recibirDanioTerritorio() {
        alianza.recibirDanioTerritorio(vida, PORCENTAJE_DANIO_TERRITORIO);
    }

    public int contarAliadosDeCaballeria(int cantidadAliadosCaballeria) {
        return cantidadAliadosCaballeria;
    }

    public int contarEnemigo(int cantidadEnemigos) {
        return alianza.contarEnemigo(cantidadEnemigos);
    }

    public int contarAliado(int cantidadAliados) {
        return alianza.contarAliado(cantidadAliados);
    }

    public void actualizarHabilidad(Tablero tablero) {
    }

    @Override
    public void mover(Direccion direccion, Tablero tablero) throws MovimientoFueraDeAlcanceException, FueraDelTableroException {
        tablero.vaciar(posicion);
        posicion = movimiento.mover(posicion, direccion);
        tablero.ocupar(posicion, this);
    }

    @Override
    public void deshacerMovimiento() {
        posicion = movimiento.deshacerMovimiento();
    }

    @Override
    public void cambiarAlianza(){
        alianza = alianza.cambiar();
    }

    protected void enlistarABatallon(List<Pieza> lista) {
    }

    public Movible seleccionarParaMover(Tablero tablero) throws FueraDelTableroException {
        return this;
    }

    public void recibirHabilidad(float nuevaVida) {
        vida.recibirHabilidad(nuevaVida);
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public ParserObjeto parsear() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("vida", vida.parsear());
        parser.put("coste", coste);
        parser.put("habilidad", habilidad.parsear());
        parser.put("alianza", alianza.getAlianza());
        parser.put("movimiento", movimiento.parsear());
        parser.put("posicion", posicion);
        parser.put("tipo_pieza", tipoPieza);
        parser.put("color", color);
        return parser;
    }

    public void agregarChangeListener(PropertyChangeListener notificado) {
        vida.agregarChangeListener(notificado);
    }
}