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
        List<JSONObject> listaRooms = (ArrayList)json.get("rooms");

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
