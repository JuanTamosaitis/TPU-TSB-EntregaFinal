package handlers;

import entities.GenreData;
import entities.Serie;
import structures.TSBHashTableDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeriesReader {

    //Método utilizado para generar la tabla hash con los datos de la serie a partir de un archivo pasado por parámetro.
    public TSBHashTableDA<String, GenreData> readSeries(File f) {

        /*
        Cada entrada de la tabla hash tendrá como key un String que será el género y un objeto del tipo GenreData, el
        cual tiene los datos solicitados en el enunciado.
        */
        TSBHashTableDA<String, GenreData> ht = new TSBHashTableDA<>();

        try (Scanner sc = new Scanner(f)) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");

                Serie serie = new Serie();
                serie.setTitle(line[0]);
                serie.setSeriesRuntime(line[1]);
                serie.setCertificate(line[2]);
                serie.setEpisodesRuntime(line[3]);
                serie.setGenre(line[4]);
                serie.setRatingIMDB(line[5]);
                serie.setOverview(line[6]);
                serie.setStar1(line[7]);
                serie.setStar2(line[8]);
                serie.setStar3(line[9]);
                serie.setStar4(line[10]);
                serie.setVotes(line[11]);

                /*
                Se recorre el array de generos al cargar cada serie, si el género ya está cargado se accede al value
                (GenreData) y se le añade la serie, llamando a los métodos correspondientes para aumentar el contador
                de series y guardar el contador de puntuaciones.
                */
                for (String g:
                        serie.getGenre()) {

                    if(!ht.containsKey(g)){
                        ht.put(g, new GenreData());
                    }

                    GenreData datos = ht.get(g);
                    datos.sumSerie();
                    datos.addSerie(serie);
                    datos.addRating(serie.getRatingIMDB());

                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("No existe el archivo...");
        }

        return ht;

    }

}
