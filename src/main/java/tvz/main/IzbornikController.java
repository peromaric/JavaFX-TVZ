package tvz.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import tvz.main.Main;

import java.io.IOException;

public class IzbornikController {

    public void showProfesorScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profesori.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }

    public void showStudentScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("studenti.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }

    public void showPredmetScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("predmeti.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }

}
