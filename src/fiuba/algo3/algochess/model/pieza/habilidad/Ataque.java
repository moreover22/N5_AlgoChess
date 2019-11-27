package fiuba.algo3.algochess.model.pieza.habilidad;

import fiuba.algo3.algochess.model.ParserObjeto;
import fiuba.algo3.algochess.model.Posicion;
import fiuba.algo3.algochess.model.pieza.Pieza;
import fiuba.algo3.algochess.model.pieza.habilidad.armas.Arma;

public class Ataque implements Habilidad{
    private Arma arma;

    public Ataque(Arma arma) {
        this.arma = arma;
    }

    public void actualizarArma(Iterable<Pieza> piezas) {
        arma = arma.actualizar(piezas);
    }

    @Override
    public void usarCon(Pieza objetivo, Posicion desde) throws HabilidadFueraDeAlcanceException, HabilidadConObjetivoInvalidoException {
        arma.atacarA(objetivo, desde, this);
    }
    @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza,Habilidad habilidad){
        return aplicarHabilidad(cantidad,pieza,this);
    }
    @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza,Ataque ataque){
        return (pieza.getVida()-cantidad);
    }
    @Override
    public float aplicarHabilidad(float cantidad, Pieza pieza,Curacion curacion){
        return (pieza.getVida()+cantidad);
    }

    @Override
    public ParserObjeto getEstado() {
        ParserObjeto parser = new ParserObjeto();
        parser.put("tipo", "ataque");
        parser.put("detalle", arma.getEstado());
        return parser;
    }
}