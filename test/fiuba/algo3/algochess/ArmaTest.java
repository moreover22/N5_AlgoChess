package fiuba.algo3.algochess;


import fiuba.algo3.algochess.pieza.Pieza;
import fiuba.algo3.algochess.pieza.SoldadoDeInfanteria;
import fiuba.algo3.algochess.pieza.habilidad.*;
import fiuba.algo3.algochess.pieza.habilidad.armas.*;
import fiuba.algo3.algochess.tablero.FueraDelTableroException;
import fiuba.algo3.algochess.tablero.Tablero;
import fiuba.algo3.algochess.tablero.casillero.PosicionarEnCasilleroEnemigoException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmaTest {

    @Test
    public void test00EspadaPesadaMataAUnaPiezaQueEstaACortoAlcanceQueTiene10DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Posicion posOrigen = new Posicion(0, 0);
        Tablero tablero = new Tablero();
        Pieza objetivo = new SoldadoDeInfanteria();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(2, 0));
        objetivo.recibirDanio(90);
        //Act
        espadaPesada.atacarA(objetivo, posOrigen);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void test01EspadaPesadaNoMataAUnaPiezaQueEstaACortoAlcanceQueTiene11DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(2, 0));
        objetivo.recibirDanio(89);
        //Act
        espadaPesada.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void test02EspadaPesadaAtacarAPiezaQueNoEstaACortoAlcanceLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaPesada = new EspadaPesada();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.posicionar(tablero, new Posicion(7, 8));

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    espadaPesada.atacarA(objetivo, posOrigen);
                });
    }

    @Test
    public void test03EspadaLivianaMataAUnaPiezaQueEstaACortoAlcanceQueTiene5DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(2, 0));
        objetivo.recibirDanio(95);
        //Act
        espadaLiviana.atacarA(objetivo, posOrigen);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void test04EspadaLivianaNoMataAUnaPiezaQueEstaACortoAlcanceQueTiene6DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(2, 0));
        objetivo.recibirDanio(94);
        //Act
        espadaLiviana.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void test05EspadaLivianaAtacarAPiezaQueNoEstaACortoAlcanceLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma espadaLiviana = new EspadaLiviana();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(7, 8));

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    espadaLiviana.atacarA(objetivo, posOrigen);
                });
    }

    @Test
    public void test06ArcoMataAUnaPiezaQueEstaAAlcanceMedioQueTiene15DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(4, 4));
        objetivo.recibirDanio(85);
        //Act
        arco.atacarA(objetivo, posOrigen);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void test07ArcoNoMataAUnaPiezaQueEstaAAlcanceMedioQueTiene16DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(4, 4));
        objetivo.recibirDanio(84);
        //Act
        arco.atacarA(objetivo,posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void test08ArcoAtacarAPiezaQueNoEstaAAlcanceMedioLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma arco = new ArcoYFlecha();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(1, 1));
        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    arco.atacarA(objetivo, posOrigen);
                });
    }

    @Test
    public void test09ProyectilMataAUnaPiezaQueEstaAAlcanceLejanoQueTiene20DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma proyectil= new Proyectil();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        tablero.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(10, 10));
        objetivo.recibirDanio(80);
        //Act
        proyectil.atacarA(objetivo, posOrigen);
        //Assert
        assertFalse(objetivo.estaViva());
    }

    @Test
    public void test10ProyectilNoMataAUnaPiezaQueEstaAAlcanceLejanoQueTiene21DeVida() throws HabilidadFueraDeAlcanceException, AtaqueAAliadoException, PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma proyectil = new Proyectil();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        tablero.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(10, 10));
        objetivo.recibirDanio(79);
        //Act
        proyectil.atacarA(objetivo, posOrigen);
        //Assert
        assertTrue(objetivo.estaViva());
    }

    @Test
    public void test11ProyectilAtacarAPiezaQueNoEstaAAlcanceLejanoLanzaHabilidadFueraDeAlcanceException() throws PosicionarEnCasilleroEnemigoException, FueraDelTableroException {
        //Arrange
        Arma proyectil = new Proyectil();
        Posicion posOrigen = new Posicion(0, 0);
        Pieza objetivo = new SoldadoDeInfanteria();
        Tablero tablero = new Tablero();
        objetivo.cambiarAlianza();
        objetivo.posicionar(tablero, new Posicion(1, 1));

        //Act-Assert
        assertThrows(HabilidadFueraDeAlcanceException.class,
                () -> {
                    proyectil.atacarA(objetivo, posOrigen);
                });
    }
}
