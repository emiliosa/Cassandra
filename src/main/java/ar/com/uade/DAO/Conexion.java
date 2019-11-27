package ar.com.uade.DAO;

import ar.com.uade.Bean.Cancion;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.*;

import java.util.*;

public class Conexion {

    private static Session session;
    private static String keySpace = "demo_cql_music";

    private Conexion(Session session) {
        Conexion.session = session;
    }

    private static Session getConexion() {
        if (session == null) {
            session = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .withPort(9042)
                    .build()
                    .connect();
        }

        return session;
    }

    public static void finalizar() {
        System.out.println("FINALIZAR");

        session.getCluster().close();
        session.close();
    }


    public static void guardar(Cancion cancion) {
        System.out.println("GUARDAR");

        Insert insert = QueryBuilder
                .insertInto(keySpace, "canciones")
                .value("id", cancion.getId())
                .value("album", cancion.getAlbum())
                .value("artista", cancion.getArtista())
                .value("titulo", cancion.getTitulo())
                .value("data", cancion.getData());


        getConexion().execute(insert);
    }

    public static List<Row> recuperar(UUID id) {
        System.out.println("RECUPERAR");

        List<Row> resultado = new ArrayList<>();

        Select builder = QueryBuilder
                .select("id", "album", "artista", "titulo", "data")
                .from(keySpace, "canciones");

        if (id != null) {
            builder.where(QueryBuilder.eq("id", id));
        }

        for (Row row : getConexion().execute(builder.where())) {
            resultado.add(row);
        }

        return resultado;
    }

    public static void actualizar(UUID id, Cancion cancion) {
        System.out.println("ACTUALIZAR");

        Update.Where update = QueryBuilder
                .update(keySpace, "canciones")
                .with(QueryBuilder.set("album", cancion.getAlbum()))
                .and(QueryBuilder.set("artista", cancion.getArtista()))
                .and(QueryBuilder.set("titulo", cancion.getTitulo()))
                .and(QueryBuilder.set("data", cancion.getData()))
                .where(QueryBuilder.eq("id", id));
        getConexion().execute(update);
    }

    public static void eliminar(UUID id) {
        System.out.println("ELIMINAR");

        Delete.Where delete = QueryBuilder
                .delete()
                .from(keySpace, "canciones")
                .where(QueryBuilder.eq("id", id));

        getConexion().execute(delete);
    }

}
