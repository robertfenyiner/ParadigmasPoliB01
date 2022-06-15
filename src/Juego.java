import rooms.*;
import gameSystem.*;

import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Juego {
    public static void main(String[] args) {
        GameLoader gameLoader = new GameLoader();

        try {
            gameLoader.cargarJson();
        } catch (IOException e) {
            System.out.println("Hubo un error al leer el json");
            System.exit(1);
        } catch (ParseException e) {
            System.out.println("Hubo un error al parsear el json");
            System.exit(1);
        }

        gameLoader.cargarRooms();

        Juego juego = new Juego();
        Personaje personaje = new Personaje();
        InputCheck inputCheck = new InputCheck();

        juego.presentarJuego(gameLoader.dameNombreDelJuego());

        String descripcionInicial = gameLoader.dameDescripcionInicial();
        System.out.println(descripcionInicial);

        Room room = Room.dameRoom(1);
        room.describir();

        Scanner in = new Scanner(System.in);
        String[] inputParts;


        while (!Checkpoints.isActive(6) && !Checkpoints.isActive(5)) {

                while (true) {
                    String inputJugador = in.nextLine().trim().toLowerCase();
                    inputParts = inputJugador.split(" ");
                    boolean inputValid = inputCheck.chequearInput(inputParts, room.getObjetos());
                    if (inputValid) break;
                }

                String accionJugador = inputParts[0];

                if (accionJugador.equals("ir")) {
                    String direccion = inputParts[inputParts.length - 1];
                    Room roomNueva = personaje.desplazarse(direccion, room);

                    if (roomNueva != null) {
                        if (roomNueva instanceof RoomBloqueada && ((RoomBloqueada) roomNueva).estaBloqueada()) {
                            roomNueva.describir();
                            continue;
                        }

                        room = roomNueva;
                        room.describir();
                        continue;
                    }

                    continue;
                }

                String nombreObjeto = inputParts[inputParts.length - 1];
                personaje.realizarAccionConObjeto(nombreObjeto, accionJugador, room.getObjetos());
            }

        System.out.println(gameLoader.dameDescripcionFinal());
        System.exit(0);
    }

    private void presentarJuego(String nombreDelJuego) {
        System.out.println(nombreDelJuego);
        System.out.println();
        System.out.println("Antes de adentrarte en esta historia, es necesario que sepas que para poder salir debes saber como jugar.");
        System.out.println("Las acciones que vas a poder realizar son: 'go', 'in (interactuar)' y 'lo (observar)'.\nPara llevarlas a cabo debes escribirlas por consola, seguida de una direccion \nsi te mueves, o de un objeto si es otro caso.");
        System.out.println("Ejemplo de comando para desplazarse: 'go abajo' ");
        System.out.println("Ejemplo de comando para observar objeto: 'lo mesa' ");
        System.out.println("Ingresa tu nombre, seguido de enter para comenzar a jugar");

        Scanner in = new Scanner(System.in);
        in.nextLine();
        System.out.println("_______________________________________________________________________________________");
        System.out.println();
    }

}
