import Services.Function;
import Services.Menubar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    public static ImageView image = Function.image;
    public static final VBox pane = Function.pane;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);

        pane.getChildren().add(Menubar.createMenu());

        pane.getChildren().add(image);

        Function.createTitleProduct();
        Function.createTitleOrder();
        Function.titleOrder.setMaxSize(0, 0);
        Function.titleOrder.setMinSize(0, 0);
        Function.titleProduct.setMaxSize(0, 0);
        Function.titleProduct.setMinSize(0, 0);
        pane.getChildren().addAll(Function.titleOrder, Function.titleProduct);

        Scene scene = new Scene(pane, 800, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Quản Lý Bán Hàng");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}