package ar.com.uade.TPOCassandra;

import ar.com.uade.Bean.Cancion;
import ar.com.uade.DAO.Conexion;
import com.datastax.driver.core.Row;

public class App {
    public static void main(String[] args) {
        Cancion cancion = new Cancion();
        cancion.setTitulo("Toxi-taxi");
        cancion.setArtista("Patricio Rey y los redonditos de ricota");
        cancion.setAlbum("La mosca y la sopa");

        Conexion.guardar(cancion);

        for (Row row: Conexion.recuperar(null)) {
            System.out.println(row.getUUID("id") + "\n" + row.getString("titulo") + "\n" + row.getString("artista") + "\n" + row.getString("album") + "\n\n");
        }

        cancion.setArtista("El indio Solari y Skay");
        Conexion.actualizar(cancion.getId(), cancion);

        for (Row row: Conexion.recuperar(null)) {
            System.out.println(row.getUUID("id") + "\n" + row.getString("titulo") + "\n" + row.getString("artista") + "\n" + row.getString("album") + "\n\n");
        }

        Conexion.eliminar(cancion.getId());

        for (Row row: Conexion.recuperar(cancion.getId())) {
            System.out.println(row.getUUID("id") + "\n" + row.getString("titulo") + "\n" + row.getString("artista") + "\n" + row.getString("album") + "\n\n");
        }

        Conexion.finalizar();
    }
}
