package rooms;

import objetos.Objeto;

import java.util.*;

import gameSystem.Checkpoints;

public class RoomBloqueada extends Room {
    private boolean llaveNecesaria = false;
    private String descripcionBloqueada; // descripción de room bloqueada

    public RoomBloqueada(String descripcion, String descripcion2, List<Objeto> objs, Map<String, Integer> coordenadas, int id) {
        super(descripcion, objs, coordenadas, id);
        this.descripcionBloqueada = descripcion2;
    }


    public void describir() {
        if (!llaveNecesaria) {
            System.out.println(descripcionBloqueada);
            return;
        }

        // condición para que no me muestre las direcciones posibles y los objetos presentes una vez que ya salí 

        super.describir();
    }

    public boolean estaBloqueada() {
        switch (id) {
            case 3:
                if (!Checkpoints.isActive(2)) return true;

                llaveNecesaria = true;
                return false;

            case 7:
                if (!Checkpoints.isActive(4)) return true;

                llaveNecesaria = true;
                return false;
        }

        return false;
    }

}

