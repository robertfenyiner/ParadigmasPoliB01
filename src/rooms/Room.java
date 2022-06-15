package rooms;

import gameSystem.Checkpoints;
import objetos.Objeto;

import java.util.*;

import org.json.simple.JSONObject;

public class Room {
    protected int id;
    private String descripcion;
    private Map<String,Integer> coordenadas;
    private List<Objeto> objetos;
    private static Map<Integer,Room> rooms = new HashMap<>();

    public Room(String descripcion, List<Objeto> objs, Map<String, Integer> coordenadas, int id) {
        this.id = id;
        this.descripcion = descripcion;
        this.objetos = objs;
        this.coordenadas = coordenadas;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getCoordenadas() {
        return coordenadas;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public static void construir(JSONObject json) {
        List<JSONObject> listaRooms = (ArrayList)json.get("room");

        for (JSONObject room :listaRooms) {
            int id = (int)(long) room.get("id");
            String description = (String) room.get("descripcion");
            List<JSONObject> jsonItemsList = (ArrayList) room.get("objetos");
            List<Objeto> roomItems = Objeto.construir(jsonItemsList);

            Map<String,Integer> roomCoordenates = new HashMap<>();
            String[] direccionRelativa= {"arriba", "derecha", "abajo", "izquierda"};

            for (int i = 0; i < direccionRelativa.length; i++) {
                if (room.containsKey(direccionRelativa[i])) {
                    int idRoom = (int)(long) room.get(direccionRelativa[i]);
                    roomCoordenates.put(direccionRelativa[i],idRoom);
                }
            }

            Room newRoom = null;

            if (room.containsKey("bloqueada")) {
                String description2 = (String) room.get("descripcionBloqueada");
                newRoom = new RoomBloqueada(description, description2, roomItems, roomCoordenates, id);
            } else {
                newRoom = new Room(description, roomItems, roomCoordenates, id);
            }

            rooms.put(id, newRoom);
        }
    }

    public static Room dameRoom(int id) {
        Room room = rooms.get(id);

        if (room.id == 5) {
            Checkpoints.activarCheckpoint(1);
        }

        return rooms.get(id);
    }

    public void describir() {
        System.out.println();
        System.out.println(descripcion);

        if (id == 7) return;

        String objetosStr;
        String coordenadasStr;

        if (coordenadas.size() > 1) {
            coordenadasStr = "Las direcciones posibles son:";
        } else {
            coordenadasStr = "La direccion posible es:";
        }

        System.out.println(coordenadasStr);

        for (String coordenada:coordenadas.keySet()){
            System.out.println("_ " + coordenada);
        }

        if (objetos.size() > 1) {
            objetosStr = "Los objetos en el lugar son:";
        } else {
            objetosStr = "El objeto en el lugar es:";
        }

        System.out.println(objetosStr);

        for (Objeto objeto:objetos) {
            System.out.println("_ " + objeto.getNombre());
        }

        System.out.println();
    }
}
