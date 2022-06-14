package gameSystem;

import java.util.HashMap;
import java.util.Map;

public class Checkpoints {
    private static Map<Integer,Boolean> checkpointMap = createMap();

    private static Map<Integer,Boolean> createMap() {
        Map<Integer,Boolean> nuevoMapa = new HashMap<>();

        nuevoMapa.put(1,false); // Pasa a ser true cuando entras a la cocina (room.id:5)
        nuevoMapa.put(2,false); // Pasa a ser true cuando tienes la llave del cuarto del dueño. Se desbloquea esa room
        nuevoMapa.put(3,false); // Pasa a ser true cuando tienes el cargardor. Puedes usar el celular, y llamar a la policía
        nuevoMapa.put(4,false); // Pasa a ser true cuando tienes la llave de la puerta principal. Se desbloquea la puerta principal
        nuevoMapa.put(5,false); // Pasa a ser true si repondes que SI cuando se te pregunta si quieres llamar a la policía. Cuando es true termina el ciclo while del main principal y así termina el programa
        nuevoMapa.put(6,false); // Pasa a ser true cuando abres la puerta principal

        return nuevoMapa;
    }

    public static void activarCheckpoint(int nCheckpoint) {
        checkpointMap.put(nCheckpoint,true);
    }

    public static boolean isActive(int nCheckpoint) {
        return checkpointMap.get(nCheckpoint);
    }

}
