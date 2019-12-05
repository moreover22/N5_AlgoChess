package fiuba.algo3.algochess.view;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PiezaView extends StackPane implements PropertyChangeListener {
    private IndicadorDeCambioDeVida indicadorDeCambio;

    public PiezaView(String tipoPieza, String color) {
        getStyleClass().add("contenedor-pieza-view");

        ImageView pieza = new ImageView(ImagenesPieza.getImage(color, tipoPieza));
        pieza.getStyleClass().add("pieza-view");
        pieza.setPreserveRatio(true);
        pieza.setFitWidth(50);
        StackPane.setAlignment(pieza, Pos.CENTER);

        TrianguloEquilateroInvertido triangulo = new TrianguloEquilateroInvertido(12);
        triangulo.getStyleClass().add("indicador-target");
        triangulo.fillProperty().addListener((observableValue, paint, t1) -> triangulo.fadeUp());
        StackPane.setAlignment(triangulo, Pos.TOP_CENTER);

        indicadorDeCambio = new IndicadorDeCambioDeVida();
        StackPane.setAlignment(indicadorDeCambio, Pos.TOP_RIGHT);
        getChildren().add(pieza);
        getChildren().add(triangulo);
        getChildren().add(indicadorDeCambio);
        StackPane.setAlignment(this, Pos.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        float vidaVieja = (float) propertyChangeEvent.getOldValue();
        float vidaNueva = (float) propertyChangeEvent.getNewValue();
        indicadorDeCambio.huboCambio(vidaVieja, vidaNueva);
    }

    public void desaparecer() {
        FadeTransition ocultarAnimacion = new FadeTransition();
        ocultarAnimacion.setDuration(Duration.millis(400));
        ocultarAnimacion.setFromValue(1.0);
        ocultarAnimacion.setToValue(0.0);
        ocultarAnimacion.setNode(this);

        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(400));
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.setNode(this);
        ocultarAnimacion.play();
        scaleTransition.play();
    }

    private static class TrianguloEquilateroInvertido extends Polygon {
        private final double ANGULO = Math.toRadians(60.0);

        public TrianguloEquilateroInvertido(double lado) {
            getPoints().addAll(
                0.0, 0.0,
                lado, 0.0,
                lado * Math.cos(ANGULO), lado * Math.sin(ANGULO)
            );
        }

        public void fadeUp() {
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.millis(100));
            transition.setNode(this);
            transition.setFromY(0);
            transition.setToY(5);
            transition.play();
        }
    }

    private static class IndicadorDeCambioDeVida extends Text {
        public void huboCambio(float valorViejo, float valorNuevo) {
            float delta = valorNuevo - valorViejo;
            int intDelta = (int) delta;
            String deltaHp = Integer.toString(intDelta);
            getStyleClass().clear();
            setStyle("");
            setEffect(null);

            if (delta < 0) {
                getStyleClass().add("indicador-danio");
            } else {
                deltaHp = "+" + deltaHp;
                getStyleClass().add("indicador-curacion");
            }
            deltaHp += "hp";
            setText(deltaHp);

            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.millis(100));
            transition.setNode(this);
            transition.setFromY(-5);
            transition.setToY(-15);

            FadeTransition ocultarAnimacion = new FadeTransition();
            ocultarAnimacion.setDuration(Duration.millis(400));
            ocultarAnimacion.setFromValue(1.0);
            ocultarAnimacion.setToValue(0.0);
            ocultarAnimacion.setNode(this);

            PauseTransition delay = new PauseTransition();
            delay.setDuration(Duration.millis(1000));
            SequentialTransition secuencia = new SequentialTransition(transition, delay, ocultarAnimacion);
            secuencia.play();
        }
    }

}
