package entities;

import structures.TSBHashTableDA;

import java.util.ArrayList;

//Clase que representará la información por cada género
public class GenreData {

    private int seriesQuantity;
    private ArrayList<Serie> genreSeries;
    private TSBHashTableDA<String, Integer> quantityByRating;

    /*
    El constructor inicia la cantidad de series en 0, crea el ArrayList para guardar las series del género, y crea otra
    hashtable donde guardaremos la cantidad de series que tienen x puntuación. Para ello ponemos como key los valores
    del 1 al 10, y los values iniciado en 0 que se irán incrementando al guardar cada serie según el puntaje de la
    misma.
     */
    public GenreData() {

        seriesQuantity = 0;
        genreSeries = new ArrayList<>();
        quantityByRating = new TSBHashTableDA<String, Integer>();
        for (int i = 1; i < 11; i++) {

            quantityByRating.put(String.valueOf(i), 0);

        }

    }

    public void sumSerie(){
        seriesQuantity += 1;
    }

    public void addSerie(Serie serie){
        genreSeries.add(serie);
    }

    public void addRating(String p){

        String[] temp = p.split("\\.");
        int cantAnterior = quantityByRating.get(temp[0]);
        quantityByRating.put(temp[0], cantAnterior+1);

    }

    public int getSeriesQuantity() {
        return seriesQuantity;
    }

    public ArrayList<Serie> getGenreSeries() {
        return genreSeries;
    }

    public TSBHashTableDA<String, Integer> getQuantityByRating() {
        return quantityByRating;
    }
}
