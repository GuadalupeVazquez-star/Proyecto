package simulacion;

public class TestSimulacion {
    public static void main(String[] args) throws Exception {
        MotorSimulacion motor = new MotorSimulacion();
        motor.execute();
        Thread.sleep(10000); // Corre 10 seg
        motor.detener();
    }
}
