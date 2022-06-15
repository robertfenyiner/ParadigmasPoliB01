import gameSystem.Checkpoints;
import rooms.Room;
import objetos.Objeto;

import java.util.*;

public class Personaje {

     Room desplazarse(String direccion, Room roomActual) {
        if (!roomActual.getCoordenadas().containsKey(direccion)) {
            System.out.println("No hay nada en esa direccion. Intenta otra direccion");
            return null;
        }

        int idRoom = roomActual.getCoordenadas().get(direccion);
        Room room = Room.dameRoom(idRoom);

        // la room 7 es la salida, por lo que al salir activas el checkpoint 6 que indica el final del juego.
        if (room.getId() == 7 && Checkpoints.isActive(4)) Checkpoints.activarCheckpoint(6);

        return room;
    }

    void realizarAccionConObjeto(String nombreObjeto, String accionJugador, List<Objeto> objetosExistentes) {
        Objeto objetoSeleccionado = null;

        for (Objeto objeto: objetosExistentes) {
            if (objeto.getNombre().equals(nombreObjeto)) {
                objetoSeleccionado = objeto;
                break;
            }
        }

        if (accionJugador.equals("ob")) {
            objetoSeleccionado.observar();
        } else {
            objetoSeleccionado.interactuar();
        }
    }
}
