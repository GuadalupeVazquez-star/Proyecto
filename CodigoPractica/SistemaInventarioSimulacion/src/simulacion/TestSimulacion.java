package simulacion;

public class TestSimulacion { //el motor corre sin UI
    public static void main(String[] args) throws Exception {
        MotorSimulacion motor = new MotorSimulacion(); //crea motor. en el constructor carga productos y genera 30 dias de eventos
        motor.execute(); //lanza el hilo de doInBackground()
        Thread.sleep(10000); // Corre 10 seg . el main se duerme 10 seg, pero el motor sigue corriendo en otro hilo
        motor.detener();//para el while del motor
    }
}
