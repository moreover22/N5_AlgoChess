package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.Parseable;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.PiezaAlianza;

import java.util.Set;

public interface Habilidad extends Parseable {
    void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException, CuracionACatapultaException;
    float recibirHabilidad(float cantidad, Habilidad habilidad, Pieza pieza, PiezaAlianza alianza) throws AtaqueAAliadoException, CuracionAEnemigoException, CuracionACatapultaException;
    float recibirHabilidad(float cantidad,Ataque ataque,Pieza pieza,PiezaAlianza alianza) throws AtaqueAAliadoException;
    float recibirHabilidad(float cantidad,Curacion curacion,Pieza pieza,PiezaAlianza alianza) throws AtaqueAAliadoException, CuracionAEnemigoException, CuracionACatapultaException;
    void aplicarEnGrupo(Set<Pieza> objetivos);
}




















