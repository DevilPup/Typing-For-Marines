package marinetyping;

import javafx.beans.Observable;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Joe Pegram
 */
public class MarineTyping extends Application {
    
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CCI: Typing For Marines");
        Display display = new Display();
        primaryStage.setResizable(false);
        Scene scene = new Scene(display,788,788);
        primaryStage.setScene(scene);
        display.requestFocus();
        primaryStage.show();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Display.isChanged.addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if(Display.change()==true){
                    primaryStage.setWidth(1500);
                    primaryStage.setHeight(700);
                    primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2); 
                    primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
                } else {
                    primaryStage.setWidth(800);
                    primaryStage.setHeight(800);
                    primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2); 
                    primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
                }
            }
        });
        
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2); 
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
