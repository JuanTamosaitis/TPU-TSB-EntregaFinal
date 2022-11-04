package entities;

import java.util.ArrayList;

//Clase utilizada para representar a cada serie del csv provisto en el enunciado
public class Serie {
    private String title;
    private String seriesRuntime;
    private String certificate;
    private String episodesRuntime;
    private ArrayList<String> genre;
    private String ratingIMDB;
    private String overview;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private String votes;

    public Serie() {

        this.genre = new ArrayList<String>();

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeriesRuntime(String seriesRuntime) {
        this.seriesRuntime = seriesRuntime;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public void setEpisodesRuntime(String episodesRuntime) {
        this.episodesRuntime = episodesRuntime;
    }

    /*
    Este método recibirá el String completo que tiene el atributo géneros en el csv y se encargará de dividirlo y
    agregar cada género al ArrayList de la clase
    */
    public void setGenre(String genre) {
        String[] list = genre.split("\\|");
        for (String g:
             list) {

            this.genre.add(g);

        }
    }

    public void setRatingIMDB(String ratingIMDB) {
        this.ratingIMDB = ratingIMDB;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setStar1(String star1) {
        this.star1 = star1;
    }

    public void setStar2(String star2) {
        this.star2 = star2;
    }

    public void setStar3(String star3) {
        this.star3 = star3;
    }

    public void setStar4(String star4) {
        this.star4 = star4;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public String getSeriesRuntime() {
        return seriesRuntime;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getEpisodesRuntime() {
        return episodesRuntime;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getRatingIMDB() {
        return ratingIMDB;
    }

    public String getOverview() {
        return overview;
    }

    public String getStar1() {
        return star1;
    }

    public String getStar2() {
        return star2;
    }

    public String getStar3() {
        return star3;
    }

    public String getStar4() {
        return star4;
    }

    public String getVotes() {
        return votes;
    }
}
