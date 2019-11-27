package ar.com.uade.Bean;

import java.sql.Blob;
import java.util.UUID;

public class Cancion {
    private UUID id;
    private String album;
    private String artista;
    private Blob data;
    private String titulo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Cancion(){
        id = UUID.randomUUID();
    }

}
