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
    static ConfigurableApplicationContext staticContext;
    static Stage primaryStage;


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(IntmedsysApplication.class)
                .run(args);
        staticContext = this.applicationContext;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage =stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buscarMedicamentos.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("DrugSys");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
     public static void changeScene(FXMLLoader fxmlLoader) throws IOException {
         fxmlLoader.setControllerFactory(staticContext::getBean);
         Parent pane = fxmlLoader.load();
         primaryStage.setScene(new Scene(pane));
    }
}
