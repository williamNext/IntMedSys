package intmedsys;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;

public class IntMedSys extends Application {
    private ConfigurableApplicationContext applicationContext;
    private Stage primaryStage;
    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(IntmedsysApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage =stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buscarMedicamentos.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();
        this.primaryStage.setTitle("IntMedSys");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }
    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(
                getClass().getResource(fxml));

        this.primaryStage.getScene().setRoot(pane);
    }
}
