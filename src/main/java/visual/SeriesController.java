package visual;

import entities.GenreData;
import entities.RatingQuantity;
import entities.Serie;
import handlers.SeriesReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import structures.TSBHashTableDA;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class SeriesController {
    @FXML
    private Label names;
    @FXML
    private Label title;
    @FXML
    private ComboBox<String> comboSeries;
    @FXML
    private Label lblCantidad;
    @FXML
    private TableView listPuntuacion;
    @FXML
    private TableView listSeries;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private TSBHashTableDA<String, GenreData> ht;
    private SeriesReader r;
    private Set<String> genres;

    /*
    Al presionar el botón de inicio, se abre la pantalla donde podremos elegir el género y visualizar los distintos
    datos
     */
    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("series.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /*
    Al presionar el botón de cargar géneros, se localiza el archivo con las series y es procesado por el lector. Se
    extraen todos los géneros con el keySet de la hashtable y se añaden al comboBox.
    */
    public void onGenreButtonClick(ActionEvent actionEvent) {
        File f = new File("src/main/resources/series_data_clean.csv");
        r = new SeriesReader();
        ht = r.readSeries(f);
        genres = ht.keySet();
        for (String g:
                genres) {

            comboSeries.getItems().add(g);

        }
    }

    public void onQuantityButtonClick(ActionEvent actionEvent) {

        listPuntuacion.setVisible(false);
        listSeries.setVisible(false);
        String genre = comboSeries.getValue();
        int quantity = ht.get(genre).getSeriesQuantity();
        lblCantidad.setText("HAY " + quantity + " SERIES DEL GÉNERO " + genre.toUpperCase());
        lblCantidad.setVisible(true);
    }

    /*
    Al presionar el botón para ver el listado de series, primero se obtiene el arraylist de series del género
    seleccionado en el combo, luego se procede a crear todas las columnas de la tabla, especificando el título y
    el atributo que deberan mapear de la clase Serie. Finalmente se recorre el array de series y se agregan una a una
    a la tabla.
     */
    public void onListButtonClick(ActionEvent actionEvent) {
        lblCantidad.setVisible(false);
        listPuntuacion.setVisible(false);
        listSeries.getItems().clear();
        listSeries.getColumns().clear();
        String genre = comboSeries.getValue();
        ArrayList<Serie> series = ht.get(genre).getGenreSeries();

        TableColumn<Integer, RatingQuantity> cl1 = new TableColumn<>("Título");
        cl1.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Integer, RatingQuantity> cl2 = new TableColumn<>("Duración serie");
        cl2.setCellValueFactory(new PropertyValueFactory<>("seriesRuntime"));
        TableColumn<Integer, RatingQuantity> cl3 = new TableColumn<>("Calificación");
        cl3.setCellValueFactory(new PropertyValueFactory<>("certificate"));
        TableColumn<Integer, RatingQuantity> cl4 = new TableColumn<>("Duración episodios");
        cl4.setCellValueFactory(new PropertyValueFactory<>("episodesRuntime"));
        TableColumn<Integer, RatingQuantity> cl5 = new TableColumn<>("Géneros");
        cl5.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TableColumn<Integer, RatingQuantity> cl6 = new TableColumn<>("Puntuación IMDB");
        cl6.setCellValueFactory(new PropertyValueFactory<>("ratingIMDB"));
        TableColumn<Integer, RatingQuantity> cl7 = new TableColumn<>("Descripción");
        cl7.setCellValueFactory(new PropertyValueFactory<>("overview"));
        TableColumn<Integer, RatingQuantity> cl8 = new TableColumn<>("Actor 1");
        cl8.setCellValueFactory(new PropertyValueFactory<>("star1"));
        TableColumn<Integer, RatingQuantity> cl9 = new TableColumn<>("Actor 2");
        cl9.setCellValueFactory(new PropertyValueFactory<>("star2"));
        TableColumn<Integer, RatingQuantity> cl10 = new TableColumn<>("Actor 3");
        cl10.setCellValueFactory(new PropertyValueFactory<>("star3"));
        TableColumn<Integer, RatingQuantity> cl11 = new TableColumn<>("Actor 4");
        cl11.setCellValueFactory(new PropertyValueFactory<>("star4"));
        TableColumn<Integer, RatingQuantity> cl12 = new TableColumn<>("Cantidad de votos");
        cl12.setCellValueFactory(new PropertyValueFactory<>("votes"));

        listSeries.getColumns().add(cl1);
        listSeries.getColumns().add(cl2);
        listSeries.getColumns().add(cl3);
        listSeries.getColumns().add(cl4);
        listSeries.getColumns().add(cl5);
        listSeries.getColumns().add(cl6);
        listSeries.getColumns().add(cl7);
        listSeries.getColumns().add(cl8);
        listSeries.getColumns().add(cl9);
        listSeries.getColumns().add(cl10);
        listSeries.getColumns().add(cl11);
        listSeries.getColumns().add(cl12);

        for (Serie s:
             series) {

            listSeries.getItems().add(s);

        }

        listSeries.setVisible(true);



    }

    public void onRatingButtonClick(ActionEvent actionEvent) {

        lblCantidad.setVisible(false);
        listSeries.setVisible(false);
        listPuntuacion.getColumns().clear();
        listPuntuacion.getItems().clear();
        String genre = comboSeries.getValue();
        TSBHashTableDA<String, Integer> cant = ht.get(genre).getQuantityByRating();
        ArrayList<RatingQuantity> ratings = new ArrayList<>();
        for (int i = 1; i < 11; i++) {

            ratings.add(new RatingQuantity(i, cant.get(String.valueOf(i))));

        }

        TableColumn<Integer, RatingQuantity> cl1 = new TableColumn<>("Puntuacion");
        cl1.setCellValueFactory(new PropertyValueFactory<>("rating"));
        TableColumn<Integer, RatingQuantity> cl2 = new TableColumn<>("Cantidad de series");
        cl2.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        listPuntuacion.getColumns().add(cl1);
        listPuntuacion.getColumns().add(cl2);

        for (RatingQuantity rate:
                ratings) {

            listPuntuacion.getItems().add(rate);

        }

        listPuntuacion.setVisible(true);

    }
}
