package marinetyping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author Joe Pegram
 */
public class Display extends Pane{
    private double wpm = 0;
    private double wpmLoaded = 0;
    private String name = "<EMPTY>";
    private String nameLoaded = "<EMPTY>";
    private TextField fNameBox;
    private int score = 0;
    private int scoreLoaded = 0;
    private int time = 0;
    private ImageView imageView = new ImageView();
    private boolean isSummer = false;
    private Player player;
    private int level;
    private String rankLoaded = " ";
    private int levelLoaded = 0;
    static BooleanProperty isChanged = new SimpleBooleanProperty(false);
    private int activeScore = 0;
    private boolean levelPassed = false;
    private String oneHighName = "Jody";
    private double oneHighScore = 5.1;
    private String twoHighName = "Schmuckatelli";
    private double twoHighScore = 8;
    private String threeHighName = "1stSgt Delgado-Ramirez";
    private double threeHighScore = 12;
    private int levelPlayed = 0;
    private Label mainDescription = new Label();
    private int hits = 0;
    private Label hitsLabel = new Label();
    private int misses = 0;
    private Label missesLabel = new Label();
    
    
    
    Display(){
        startScreen();
    }
    
    public void startScreen(){
        Image image = new Image("images/CCI_Logo.jpg");
        imageView = new ImageView(image);
        Button main = new Button();
        main.setStyle("-fx-background-color: transparent;");
        main.setPrefSize(800,800);
        
        getChildren().add(imageView);
        getChildren().add(main);
        
        main.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll(imageView,main);
                mainMenu();
            }
        });
       
    }
    
    public void mainMenu(){
        
        isChanged.set(false);
        Image image = new Image("images/mainMenu.jpg");
        imageView = new ImageView(image);
        Button playerSelect = new Button();
        
        mainDescription = new Label("Get Ready, Marine, You're About To Begin\n"
            + "Your Training To Become A Keyboard Warrior!\nMake Chesty"
            + " Proud, Devil Dog!\n\n\n\nType the letters as they appear"
            + " to increase\nyour Words Per Minute (WPM) accuracy."
            + "\nDo not button mash, Devil Dog!");
        mainDescription.setFont(new Font("Times New Roman", 30));
        
        
        mainDescription.setLayoutX(50);
        mainDescription.setLayoutY(150);
        playerSelect.setLayoutX(15);
        playerSelect.setLayoutY(9);
        
        getChildren().add(imageView);
        getChildren().add(mainDescription);
        getChildren().add(playerSelect);
        
        playerSelect.setPrefSize(165,90);
        
        playerSelect.setStyle("-fx-background-color: transparent;");
        
        playerSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                selectPlayer();
                System.out.println("playerSelect pressed");
            }
        });
        
        
        
        Button levelSelect = new Button();
        levelSelect.setLayoutX(215);
        levelSelect.setLayoutY(9);
        levelSelect.setPrefSize(165,90);
        getChildren().add(levelSelect);
        levelSelect.setStyle("-fx-background-color: transparent;");
        levelSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                System.out.println("levelSelect pressed");
                selectLevel();
            }
        });
        
        Button scoreSelect = new Button();
        scoreSelect.setLayoutX(415);
        scoreSelect.setLayoutY(9);
        scoreSelect.setPrefSize(165,90);
        getChildren().add(scoreSelect);
        scoreSelect.setStyle("-fx-background-color: transparent;");
        scoreSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                highScores();
                System.out.println("scoreSelect pressed");
            }
        });
        
        Button optionSelect = new Button();
        optionSelect.setLayoutX(615);
        optionSelect.setLayoutY(9);
        optionSelect.setPrefSize(165,90);
        getChildren().add(optionSelect);
        optionSelect.setStyle("-fx-background-color: transparent;");
        optionSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                options();
                System.out.println("optionSelect pressed");
            }
        });
        
        Label marine = new Label(name);
        marine.setFont(new Font("Times New Roman", 30));
        marine.setLayoutX(415);
        marine.setLayoutY(650);
        getChildren().add(marine);
        
        DecimalFormat df = new DecimalFormat("#.00");
        Label wpmLabel = new Label(df.format(wpm));
        wpmLabel.setFont(new Font("Times New Roman", 30));
        wpmLabel.setLayoutX(380);
        wpmLabel.setLayoutY(765);
        getChildren().add(wpmLabel);
        
        Label levelHigh = new Label(Integer.toString(level));
        levelHigh.setFont(new Font("Times New Roman", 30));
        levelHigh.setLayoutX(490);
        levelHigh.setLayoutY(725);
        getChildren().add(levelHigh);
        
        Button mainSelect = new Button();
        mainSelect.setLayoutX(615);
        mainSelect.setLayoutY(600);
        mainSelect.setPrefSize(180,40);
        getChildren().add(mainSelect);
        mainSelect.setStyle("-fx-background-color: transparent;");
        mainSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                System.out.println("mainSelect pressed");
                mainMenu();
            }
        });
    }
    
    public void showScore (){
        getChildren().removeAll();
        mainMenu();
        getChildren().remove(mainDescription);
        
        Label nameLabel = new Label("Name: " + name);
        nameLabel.setLayoutX(100);
        nameLabel.setLayoutY(200);
        getChildren().add(nameLabel);
        nameLabel.setFont(Font.font("Times New Roman",40));
        DecimalFormat df = new DecimalFormat("#.00");
        Label scoreLabel = new Label("Score/WPM: " + df.format(wpm));
        scoreLabel.setLayoutX(100);
        scoreLabel.setLayoutY(240);
        getChildren().add(scoreLabel);
        scoreLabel.setFont(Font.font("Times New Roman",40));
        Label levelPass;
        if(levelPassed == true){
            levelPass = new Label("Mission: Success!");
        } else {
            levelPass = new Label("Mission: Failure!");
        }
        levelPass.setFont(Font.font("Times New Roman",40));
        levelPass.setLayoutX(100);
        levelPass.setLayoutY(280);
        getChildren().add(levelPass);
        
        Button saveStats = new Button("Save Stats");
        saveStats.setLayoutX(100);
        saveStats.setLayoutY(330);
        saveStats.setPrefSize(180,40);
        getChildren().add(saveStats);
        saveStats.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("saveStats pressed");
                    if(levelPlayed == 1 && wpm > oneHighScore){
                        oneHighScore = wpm;
                        oneHighName = name;
                    } else if(levelPlayed == 2 && wpm > twoHighScore){
                        twoHighScore = wpm;
                        twoHighName = name;
                    } else if(levelPlayed == 3 && wpm > threeHighScore){
                        threeHighScore = wpm;
                        threeHighName = name;
                    }
                    
                    makeNewPlayer();
            }
        });
        
        Button mainSelect = new Button();
        mainSelect.setLayoutX(615);
        mainSelect.setLayoutY(600);
        mainSelect.setPrefSize(180,40);
        getChildren().add(mainSelect);
        mainSelect.setStyle("-fx-background-color: transparent;");
        mainSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                System.out.println("mainSelect pressed");
                mainMenu();
            }
        });
    }
    
    public void resetScores(){
        
        oneHighName = "Jody";
        oneHighScore = 5.1;
        twoHighName = "Schmuckatelli";
        twoHighScore = 8;
        threeHighName = "1stSgt Delgado-Ramirez";
        threeHighScore = 12;
        levelPlayed = 0;
    }
    
    public void selectPlayer(){
        mainMenu();
        getChildren().remove(mainDescription);
        loadPlayer();
        System.out.println(wpm);
        System.out.println(level);
        System.out.println(name);
        
        // Add a new Marine
        Label fName = new Label("Name");
        fName.setLayoutX(25);
        fName.setLayoutY(150);
        getChildren().add(fName);
        fNameBox = new TextField();
        getChildren().add(fNameBox);
        fNameBox.setLayoutX(100);
        fNameBox.setLayoutY(145);
        
        Button createPlayer = new Button("Create New Player");
        createPlayer.setLayoutX(100);
        createPlayer.setLayoutY(200);
        createPlayer.setPrefSize(180,40);
        getChildren().add(createPlayer);
        createPlayer.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(fNameBox.getText().length() > 0){
                    wpm = 0;
                    level = 0;
                    System.out.println("createPlayer pressed");
                    name = fNameBox.getText();
                    makeNewPlayer();
                    getChildren().removeAll();
                    mainMenu();
                }
            }
        });
        
        // Load a saved Marine
        Label savedGame = new Label("Saved Game");
        savedGame.setLayoutX(25);
        savedGame.setLayoutY(300);
        getChildren().add(savedGame);
        
        Label lName = new Label("Name");
        lName.setLayoutX(25);
        lName.setLayoutY(330);
        getChildren().add(lName);
        Label loadedName = new Label(nameLoaded);
        loadedName.setLayoutX(85);
        loadedName.setLayoutY(330);
        getChildren().add(loadedName);
        
        Label words = new Label("WPM");
        words.setLayoutX(25);
        words.setLayoutY(350);
        getChildren().add(words);
        wpm = wpmLoaded;
        DecimalFormat df = new DecimalFormat("#.00");
        Label loadedWords = new Label(df.format(wpm));
        loadedWords.setLayoutX(85);
        loadedWords.setLayoutY(350);
        getChildren().add(loadedWords);
        
        Label levelL = new Label("Level");
        levelL.setLayoutX(25);
        levelL.setLayoutY(370);
        getChildren().add(levelL);
        Label loadedLev = new Label(Integer.toString(levelLoaded));
        loadedLev.setLayoutX(85);
        loadedLev.setLayoutY(370);
        getChildren().add(loadedLev);
        
        Button selectMarine = new Button("Select Marine");
        selectMarine.setLayoutX(230);
        selectMarine.setLayoutY(300);
        selectMarine.setPrefSize(200,100);
        getChildren().add(selectMarine);
        selectMarine.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(!nameLoaded.equals("<EMPTY>")){
                    wpm = wpmLoaded;
                    name = nameLoaded;
                    level = levelLoaded;
                    System.out.println("selectMarine pressed");
                    mainMenu();
                }
            }
        });
        
        Button mainSelect = new Button();
        mainSelect.setLayoutX(615);
        mainSelect.setLayoutY(600);
        mainSelect.setPrefSize(180,40);
        getChildren().add(mainSelect);
        mainSelect.setStyle("-fx-background-color: transparent;");
        mainSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                System.out.println("mainSelect pressed");
                mainMenu();
            }
        });
    }
    
    public void makeNewPlayer(){
        player = new Player(name,wpm,level);
        System.out.println(player.toString());
        
        //save the file
        try {
            //save the name
            File file = new File("player.txt");
            PrintWriter out = new PrintWriter(file);
            player.save(out);
            out.close();
        } catch (IOException ex) {
                System.out.println("An error occured while writing the file.");
        }
        getChildren().removeAll();
        mainMenu();
        
    }
    
    public void loadPlayer(){
        player = new Player();
        try {
            //load the playerOne list (if it exists)
            File file = new File("player.txt");
            if(file.exists()) {
                    //load the file (if it exists)
                    Scanner in = new Scanner(file);
                    player.load(in);
                    in.close();
            }
        } catch(IOException ex) {
                System.out.println("Error occured while reading file.");
        }
        this.wpmLoaded = player.getWPM();
        this.nameLoaded = player.getName();
        this.levelLoaded = player.getLevel();
        
    }
    
    public void highScores(){
        mainMenu();
        getChildren().remove(mainDescription);
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(oneHighScore);
        System.out.println(oneHighName);
        
        Label highBanner = new Label("High Scores");
        highBanner.setFont(Font.font("Times New Roman",40));
        highBanner.setTranslateX(50);
        highBanner.setTranslateY(150);
        getChildren().add(highBanner);
        
        Label oneBanner = new Label("Level One");
        oneBanner.setFont(Font.font("Times New Roman",30));
        oneBanner.setTranslateX(70);
        oneBanner.setTranslateY(200);
        getChildren().add(oneBanner);
        
        Label highOneName = new Label("Name: " + oneHighName);
        highOneName.setFont(Font.font("Times New Roman",20));
        highOneName.setTranslateX(90);
        highOneName.setTranslateY(240);
        getChildren().add(highOneName);
        
        Label highOneScoree = new Label("WPM: " + df.format(oneHighScore));
        highOneScoree.setFont(Font.font("Times New Roman",20));
        highOneScoree.setTranslateX(90);
        highOneScoree.setTranslateY(260);
        getChildren().add(highOneScoree);
        
        Label twoBanner = new Label("Level Two");
        twoBanner.setFont(Font.font("Times New Roman",30));
        twoBanner.setTranslateX(70);
        twoBanner.setTranslateY(330);
        getChildren().add(twoBanner);
        
        Label highTwoName = new Label("Name: " + twoHighName);
        highTwoName.setFont(Font.font("Times New Roman",20));
        highTwoName.setTranslateX(90);
        highTwoName.setTranslateY(370);
        getChildren().add(highTwoName);
        
        Label highTwoScore = new Label("WPM: " + df.format(twoHighScore));
        highTwoScore.setFont(Font.font("Times New Roman",20));
        highTwoScore.setTranslateX(90);
        highTwoScore.setTranslateY(390);
        getChildren().add(highTwoScore);
        
        Label threeBanner = new Label("Level Three");
        threeBanner.setFont(Font.font("Times New Roman",30));
        threeBanner.setTranslateX(70);
        threeBanner.setTranslateY(460);
        getChildren().add(threeBanner);
        
        Label highThreeName = new Label("Name: " + threeHighName);
        highThreeName.setFont(Font.font("Times New Roman",20));
        highThreeName.setTranslateX(90);
        highThreeName.setTranslateY(500);
        getChildren().add(highThreeName);
        
        Label highThreeScore = new Label("WPM: " + df.format(threeHighScore));
        highThreeScore.setFont(Font.font("Times New Roman",20));
        highThreeScore.setTranslateX(90);
        highThreeScore.setTranslateY(520);
        getChildren().add(highThreeScore);
    }
    
    public void selectLevel(){
        mainMenu();
        getChildren().remove(mainDescription);
        
        Label banner = new Label("Select Your Training Mission");
        banner.setFont(new Font("Times New Roman", 30));
        banner.setLayoutX(50);
        banner.setLayoutY(150);
        getChildren().add(banner);
        
        Label descriptionOne = new Label("Home Row Is Under Attack!");
        descriptionOne.setFont(new Font("Times New Roman", 30));
        descriptionOne.setLayoutX(270);
        descriptionOne.setLayoutY(200);
        getChildren().add(descriptionOne);
        
        Button playLevelOne = new Button("Mission One");
        playLevelOne.setLayoutX(70);
        playLevelOne.setLayoutY(200);
        playLevelOne.setPrefSize(180,40);
        getChildren().add(playLevelOne);
        
        playLevelOne.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(name.equals("<EMPTY>")){
                    Label warning = new Label("You Must Select a Player First.");

                    Pane firstLayout = new Pane();
                    firstLayout.getChildren().add(warning);
                    warning.setLayoutX(10);
                    warning.setLayoutY(10);
                    Button ok = new Button("Ok");
                    ok.setLayoutX(85);
                    ok.setLayoutY(40);
                    firstLayout.getChildren().add(ok);

                    Scene secondScene = new Scene(firstLayout, 225, 80);
                    // New window (Stage)
                    Stage windowOne = new Stage();
                    windowOne.setScene(secondScene);

                    ok.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            windowOne.close();
                        }
                    });

                    windowOne.initStyle(StageStyle.UNDECORATED);
                    windowOne.show();
                } else{
                    isChanged.set(true);
                    getChildren().removeAll();
                    System.out.println("playLevel pressed");
                    playLevelOne();
                }
            }
        });
        
        Label descriptionTwo = new Label("Assault the Upper Row and Take It Back!");
        descriptionTwo.setFont(new Font("Times New Roman", 30));
        descriptionTwo.setLayoutX(270);
        descriptionTwo.setLayoutY(250);
        getChildren().add(descriptionTwo);
        
        Button playLevelTwo = new Button("Mission Two");
        playLevelTwo.setLayoutX(70);
        playLevelTwo.setLayoutY(250);
        playLevelTwo.setPrefSize(180,40);
        getChildren().add(playLevelTwo);
        playLevelTwo.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(name.equals("<EMPTY>")){
                    Label warning = new Label("You Must Select a Player First.");

                    Pane secondLayout = new Pane();
                    secondLayout.getChildren().add(warning);
                    warning.setLayoutX(10);
                    warning.setLayoutY(10);
                    Button ok = new Button("Ok");
                    ok.setLayoutX(85);
                    ok.setLayoutY(40);
                    secondLayout.getChildren().add(ok);

                    Scene secondScene = new Scene(secondLayout, 225, 80);
                    // New window (Stage)
                    Stage windowTwo = new Stage();
                    windowTwo.setScene(secondScene);

                    ok.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            windowTwo.close();
                        }
                    });

                    windowTwo.initStyle(StageStyle.UNDECORATED);
                    windowTwo.show();
                } else{
                    if(level >=1){
                        isChanged.set(true);
                        getChildren().removeAll();
                        System.out.println("playLevel pressed");
                        playLevelTwo();
                    } else{
                        Label warning = new Label("You Must Complete Mission 1 First.");

                        Pane secondaryLayout = new Pane();
                        secondaryLayout.getChildren().add(warning);
                        warning.setLayoutX(10);
                        warning.setLayoutY(10);
                        Button ok = new Button("Ok");
                        ok.setLayoutX(95);
                        ok.setLayoutY(40);
                        secondaryLayout.getChildren().add(ok);

                        Scene secondScene = new Scene(secondaryLayout, 255, 80);
                        // New window (Stage)
                        Stage newWindow = new Stage();
                        newWindow.setScene(secondScene);

                        ok.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                newWindow.close();
                            }
                        });

                        newWindow.initStyle(StageStyle.UNDECORATED);
                        newWindow.show();
                    }
                }
            }
        });
        
        Label descriptionThree = new Label("Advance on the Lower Row!");
        descriptionThree.setFont(new Font("Times New Roman", 30));
        descriptionThree.setLayoutX(270);
        descriptionThree.setLayoutY(300);
        getChildren().add(descriptionThree);
        
        Button playLevelThree = new Button("Mission Three");
        playLevelThree.setLayoutX(70);
        playLevelThree.setLayoutY(300);
        playLevelThree.setPrefSize(180,40);
        getChildren().add(playLevelThree);
        playLevelThree.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(name.equals("<EMPTY>")){
                    Label warning = new Label("You Must Select a Player First.");

                    Pane secondaryLayout = new Pane();
                    secondaryLayout.getChildren().add(warning);
                    warning.setLayoutX(10);
                    warning.setLayoutY(10);
                    Button ok = new Button("Ok");
                    ok.setLayoutX(85);
                    ok.setLayoutY(40);
                    secondaryLayout.getChildren().add(ok);

                    Scene secondScene = new Scene(secondaryLayout, 225, 80);
                    // New window (Stage)
                    Stage newWindow = new Stage();
                    newWindow.setScene(secondScene);

                    ok.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            newWindow.close();
                        }
                    });

                    newWindow.initStyle(StageStyle.UNDECORATED);
                    newWindow.show();
                } else{
                    if(level >= 2){
                        isChanged.set(true);
                        getChildren().removeAll();
                        System.out.println("playLevel pressed");
                        playLevelThree();
                    } else{
                        Label warning = new Label("You Must Complete Mission 2 First.");

                        Pane thirdLayout = new Pane();
                        thirdLayout.getChildren().add(warning);
                        warning.setLayoutX(10);
                        warning.setLayoutY(10);
                        Button ok = new Button("Ok");
                        ok.setLayoutX(95);
                        ok.setLayoutY(40);
                        thirdLayout.getChildren().add(ok);

                        Scene secondScene = new Scene(thirdLayout, 255, 80);
                        // New window (Stage)
                        Stage windowThree = new Stage();
                        windowThree.setScene(secondScene);

                        ok.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                windowThree.close();
                            }
                        });

                        windowThree.initStyle(StageStyle.UNDECORATED);
                        windowThree.show();
                    }
                }
            }
        });
        
        Label descriptionFoure = new Label("Reinforce the Forward Observation Post!");
        descriptionFoure.setFont(new Font("Times New Roman", 30));
        descriptionFoure.setLayoutX(270);
        descriptionFoure.setLayoutY(360);
        getChildren().add(descriptionFoure);
        
        Button playLevelFour = new Button("Mission Four");
        playLevelFour.setLayoutX(70);
        playLevelFour.setLayoutY(360);
        playLevelFour.setPrefSize(180,40);
        getChildren().add(playLevelFour);
        playLevelFour.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(name.equals("<EMPTY>")){
                    Label warning = new Label("You Must Select a Player First.");

                    Pane secondaryLayout = new Pane();
                    secondaryLayout.getChildren().add(warning);
                    warning.setLayoutX(10);
                    warning.setLayoutY(10);
                    Button ok = new Button("Ok");
                    ok.setLayoutX(85);
                    ok.setLayoutY(40);
                    secondaryLayout.getChildren().add(ok);

                    Scene secondScene = new Scene(secondaryLayout, 225, 80);
                    // New window (Stage)
                    Stage newWindow = new Stage();
                    newWindow.setScene(secondScene);

                    ok.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            newWindow.close();
                        }
                    });

                    newWindow.initStyle(StageStyle.UNDECORATED);
                    newWindow.show();
                } else{
                    if(level >= 2){
                        isChanged.set(true);
                        getChildren().removeAll();
                        System.out.println("playLevel pressed");
                        playLevelFour();
                    } else{
                        Label warning = new Label("You Must Complete Mission 3 First.");

                        Pane thirdLayout = new Pane();
                        thirdLayout.getChildren().add(warning);
                        warning.setLayoutX(10);
                        warning.setLayoutY(10);
                        Button ok = new Button("Ok");
                        ok.setLayoutX(95);
                        ok.setLayoutY(40);
                        thirdLayout.getChildren().add(ok);

                        Scene secondScene = new Scene(thirdLayout, 255, 80);
                        // New window (Stage)
                        Stage windowThree = new Stage();
                        windowThree.setScene(secondScene);

                        ok.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                windowThree.close();
                            }
                        });

                        windowThree.initStyle(StageStyle.UNDECORATED);
                        windowThree.show();
                    }
                }
            }
        });
        
        Button mainSelect = new Button();
        mainSelect.setLayoutX(615);
        mainSelect.setLayoutY(600);
        mainSelect.setPrefSize(180,40);
        getChildren().add(mainSelect);
        mainSelect.setStyle("-fx-background-color: transparent;");
        mainSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll();
                System.out.println("mainSelect pressed");
                mainMenu();
            }
        });
    }
    
    public void playLevelOne(){
        activeScore = 0;
        levelPassed = false;
        String levelOne = "jfjfjfjfkdkdkdkdalsalsalsals";
        
        char[] levelArray = levelOne.toCharArray();
        Label txtInput = new Label("60");
        
        Image image2 = new Image("images/Level_One.png");
        imageView = new ImageView(image2);
        getChildren().add(imageView);
        
        Text text = new Text("A");
        txtInput.setFont(Font.font("Times New Roman",40));
        txtInput.setLayoutX(750);
        txtInput.setLayoutY(20);
        getChildren().add(txtInput);
        
        // Set up and run the mechanics behind the timer, remove everything afterwards
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {txtInput.setText(String.valueOf(Integer.parseInt(txtInput.getText()) - 1));});
        Timeline time = new Timeline(kf);
        time.setCycleCount(Integer.parseInt(txtInput.getText())+1);
        time.play();
        
        getChildren().add(text);
        
        Image thumbUp = new Image("images/thumbUp.png");
        ImageView thumb = new ImageView(thumbUp);
        thumb.setLayoutX(600);
        thumb.setLayoutY(600);
        
        Image shot = new Image("images/shot.png");
        ImageView bang = new ImageView(shot);
        bang.setLayoutX(468); 
        bang.setLayoutY(325);
        // Timer for thumbs up/correct graphic
        KeyFrame kf3 = new KeyFrame(Duration.millis(300));
        Timeline bangTime = new Timeline(kf3);
        
        Label targetOne = new Label(String.valueOf(levelArray[activeScore]));
        targetOne.setFont(Font.font("Times New Roman",40));
        targetOne.setLayoutX(630);
        targetOne.setLayoutY(150);
        getChildren().add(targetOne);
        
        Label targetTwo = new Label(String.valueOf(levelArray[activeScore + 1]));
        targetTwo.setFont(Font.font("Times New Roman",40));
        targetTwo.setLayoutX(855);
        targetTwo.setLayoutY(150);
        getChildren().add(targetTwo);
        
        Label targetThree = new Label(String.valueOf(levelArray[activeScore + 2]));
        targetThree.setFont(Font.font("Times New Roman",40));
        targetThree.setLayoutX(1080);
        targetThree.setLayoutY(150);
        getChildren().add(targetThree);
        
        Label targetFour = new Label(String.valueOf(levelArray[activeScore + 3]));
        targetFour.setFont(Font.font("Times New Roman",40));
        targetFour.setLayoutX(1275);
        targetFour.setLayoutY(150);
        getChildren().add(targetFour);
        
        // Timer for thumbs up/correct graphic
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000));
        Timeline thumbTime = new Timeline(kf2);
        
        // When the player presses the correct key, do stuff
        if(levelPassed == false){
            text.setOnKeyPressed(e -> {
                getChildren().remove(bang);
                getChildren().add(bang);
                bangTime.play();
                bangTime.setOnFinished(event -> {
                        getChildren().remove(bang);
                    });
                if(activeScore < levelArray.length){
                if(e.getText().equals(String.valueOf(levelArray[activeScore]))){
                    

                    // Increase the score after hitting the right key
                    activeScore += 1;

                    // Check to see what's happening
                    System.out.println(e.getText() + " " + activeScore);

                    // If the player types fast enough, remove the previous thumb
                    // So that we can add it again.
                    getChildren().remove(thumb);

                    // Give a thumbs up for hitting the right key
                    getChildren().add(thumb);

                    // Start the timer
                    thumbTime.play();

                    // Remove the thumbs up after 1 second
                    thumbTime.setOnFinished(event -> {
                        getChildren().remove(thumb);
                    });

                    switch(activeScore%4){
                        case 1: targetOne.setText("");break;
                        case 2: targetTwo.setText("");break;
                        case 3: targetThree.setText("");break;
                    }

                    if(activeScore < levelArray.length){
                        if((activeScore) % 4 == 0){
                            targetOne.setText(String.valueOf(levelArray[activeScore]));
                            targetTwo.setText(String.valueOf(levelArray[activeScore + 1]));
                            targetThree.setText(String.valueOf(levelArray[activeScore + 2]));
                            targetFour.setText(String.valueOf(levelArray[activeScore + 3]));
                        }
                    }

                    if(activeScore == levelArray.length){
                        wpm = activeScore / 4.5;
                        getChildren().removeAll(txtInput,targetFour);
                        levelOver(true);
                        levelPlayed = 1;
                        bangTime.stop();
                        thumbTime.stop();
                        time.stop();
                        levelPassed = true;
                        if(level == 0){
                            level = 1;
                        }
                    }
                }}
            });
        }
        // Instantiate and position the timer
        
        
        time.setOnFinished(event -> {
            wpm = activeScore / 4.5;
            bangTime.stop();
            getChildren().removeAll(text,thumb,txtInput,targetOne,targetTwo,targetThree,targetFour);
            levelPlayed = 1;
            levelOver(false);
        });
        
        text.requestFocus();
    }
    
    public void playLevelTwo(){
        activeScore = 0;
        levelPassed = false;
        String levelOne = "ytytytytururururieieieieowowowowpqpqpqpq";
        
        char[] levelArray = levelOne.toCharArray();
        Label txtInput = new Label("60");
        
        Image image2 = new Image("images/Level_One.png");
        imageView = new ImageView(image2);
        getChildren().add(imageView);
        
        Text text = new Text("A");
        txtInput.setFont(Font.font("Times New Roman",40));
        txtInput.setLayoutX(750);
        txtInput.setLayoutY(20);
        getChildren().add(txtInput);
        
        // Set up and run the mechanics behind the timer, remove everything afterwards
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {txtInput.setText(String.valueOf(Integer.parseInt(txtInput.getText()) - 1));});
        Timeline time = new Timeline(kf);
        time.setCycleCount(Integer.parseInt(txtInput.getText())+1);
        time.play();
        
        getChildren().add(text);
        
        Image thumbUp = new Image("images/thumbUp.png");
        ImageView thumb = new ImageView(thumbUp);
        thumb.setLayoutX(600);
        thumb.setLayoutY(600);
        
        Image shot = new Image("images/shot.png");
        ImageView bang = new ImageView(shot);
        bang.setLayoutX(468); 
        bang.setLayoutY(325);
        // Timer for thumbs up/correct graphic
        KeyFrame kf3 = new KeyFrame(Duration.millis(300));
        Timeline bangTime = new Timeline(kf3);
        
        Label targetOne = new Label(String.valueOf(levelArray[activeScore]));
        targetOne.setFont(Font.font("Times New Roman",40));
        targetOne.setLayoutX(630);
        targetOne.setLayoutY(150);
        getChildren().add(targetOne);
        
        Label targetTwo = new Label(String.valueOf(levelArray[activeScore + 1]));
        targetTwo.setFont(Font.font("Times New Roman",40));
        targetTwo.setLayoutX(855);
        targetTwo.setLayoutY(150);
        getChildren().add(targetTwo);
        
        Label targetThree = new Label(String.valueOf(levelArray[activeScore + 2]));
        targetThree.setFont(Font.font("Times New Roman",40));
        targetThree.setLayoutX(1080);
        targetThree.setLayoutY(150);
        getChildren().add(targetThree);
        
        Label targetFour = new Label(String.valueOf(levelArray[activeScore + 3]));
        targetFour.setFont(Font.font("Times New Roman",40));
        targetFour.setLayoutX(1275);
        targetFour.setLayoutY(150);
        getChildren().add(targetFour);
        
        // Timer for thumbs up/correct graphic
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000));
        Timeline thumbTime = new Timeline(kf2);
        
        // When the player presses the correct key, do stuff
        if(levelPassed == false){
            text.setOnKeyPressed(e -> {
                getChildren().remove(bang);
                getChildren().add(bang);
                bangTime.play();
                bangTime.setOnFinished(event -> {
                        getChildren().remove(bang);
                    });
                if(activeScore < levelArray.length){
                if(e.getText().equals(String.valueOf(levelArray[activeScore]))){

                    // Increase the score after hitting the right key
                    activeScore += 1;

                    // Check to see what's happening
                    System.out.println(e.getText() + " " + activeScore);

                    // If the player types fast enough, remove the previous thumb
                    // So that we can add it again.
                    getChildren().remove(thumb);

                    // Give a thumbs up for hitting the right key
                    getChildren().add(thumb);

                    // Start the timer
                    thumbTime.play();

                    // Remove the thumbs up after 1 second
                    thumbTime.setOnFinished(event -> {
                        getChildren().remove(thumb);
                    });

                    switch(activeScore%4){
                        case 1: targetOne.setText("");break;
                        case 2: targetTwo.setText("");break;
                        case 3: targetThree.setText("");break;
                    }

                    if(activeScore < levelArray.length){
                        if((activeScore) % 4 == 0){
                            targetOne.setText(String.valueOf(levelArray[activeScore]));
                            targetTwo.setText(String.valueOf(levelArray[activeScore + 1]));
                            targetThree.setText(String.valueOf(levelArray[activeScore + 2]));
                            targetFour.setText(String.valueOf(levelArray[activeScore + 3]));
                        }
                    }

                    if(activeScore == levelArray.length){
                        wpm = activeScore / 4.5;
                        getChildren().removeAll(txtInput,targetFour);
                        levelOver(true);
                        levelPlayed = 2;
                        bangTime.stop();
                        thumbTime.stop();
                        time.stop();
                        levelPassed = true;
                        if(level == 1){
                            level = 2;
                        }
                    }
                }}
            });
        }
        // Instantiate and position the timer
        
        
        time.setOnFinished(event -> {
            wpm = activeScore / 4.5;
            bangTime.stop();
            getChildren().removeAll(text,thumb,txtInput,targetOne,targetTwo,targetThree,targetFour);
            levelPlayed = 2;
            levelOver(false);
        });
        
        text.requestFocus();
    }
    
    public void playLevelThree(){
        activeScore = 0;
        levelPassed = false;
        String levelOne = "bbbbnvnvnvnvmcmcmcmcxzxzxzxz";
        
        char[] levelArray = levelOne.toCharArray();
        Label txtInput = new Label("60");
        
        Image image2 = new Image("images/Level_One.png");
        imageView = new ImageView(image2);
        getChildren().add(imageView);
        
        Text text = new Text("A");
        txtInput.setFont(Font.font("Times New Roman",40));
        txtInput.setLayoutX(750);
        txtInput.setLayoutY(20);
        getChildren().add(txtInput);
        
        // Set up and run the mechanics behind the timer, remove everything afterwards
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {txtInput.setText(String.valueOf(Integer.parseInt(txtInput.getText()) - 1));});
        Timeline time = new Timeline(kf);
        time.setCycleCount(Integer.parseInt(txtInput.getText())+1);
        time.play();
        getChildren().add(text);
        
        Image thumbUp = new Image("images/thumbUp.png");
        ImageView thumb = new ImageView(thumbUp);
        thumb.setLayoutX(600);
        thumb.setLayoutY(600);
        
        Image shot = new Image("images/shot.png");
        ImageView bang = new ImageView(shot);
        bang.setLayoutX(468); 
        bang.setLayoutY(325);
        // Timer for thumbs up/correct graphic
        KeyFrame kf3 = new KeyFrame(Duration.millis(300));
        Timeline bangTime = new Timeline(kf3);
        
        Label targetOne = new Label(String.valueOf(levelArray[activeScore]));
        targetOne.setFont(Font.font("Times New Roman",40));
        targetOne.setLayoutX(630);
        targetOne.setLayoutY(150);
        getChildren().add(targetOne);
        
        Label targetTwo = new Label(String.valueOf(levelArray[activeScore + 1]));
        targetTwo.setFont(Font.font("Times New Roman",40));
        targetTwo.setLayoutX(855);
        targetTwo.setLayoutY(150);
        getChildren().add(targetTwo);
        
        Label targetThree = new Label(String.valueOf(levelArray[activeScore + 2]));
        targetThree.setFont(Font.font("Times New Roman",40));
        targetThree.setLayoutX(1080);
        targetThree.setLayoutY(150);
        getChildren().add(targetThree);
        
        Label targetFour = new Label(String.valueOf(levelArray[activeScore + 3]));
        targetFour.setFont(Font.font("Times New Roman",40));
        targetFour.setLayoutX(1275);
        targetFour.setLayoutY(150);
        getChildren().add(targetFour);
        
        // Timer for thumbs up/correct graphic
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000));
        Timeline thumbTime = new Timeline(kf2);
        
        // When the player presses the correct key, do stuff
        text.setOnKeyPressed(e -> {
            getChildren().remove(bang);
            getChildren().add(bang);
            bangTime.play();
            bangTime.setOnFinished(event -> {
                    getChildren().remove(bang);
                });
            if(activeScore < levelArray.length){
            if(e.getText().equals(String.valueOf(levelArray[activeScore]))){
                
                // Increase the score after hitting the right key
                activeScore += 1;
                
                // Check to see what's happening
                System.out.println(e.getText() + " " + activeScore);
                
                // If the player types fast enough, remove the previous thumb
                // So that we can add it again.
                getChildren().remove(thumb);
                
                // Give a thumbs up for hitting the right key
                getChildren().add(thumb);
                
                // Start the timer
                thumbTime.play();
                
                // Remove the thumbs up after 1 second
                thumbTime.setOnFinished(event -> {
                    getChildren().remove(thumb);
                });
                
                switch(activeScore%4){
                    case 1: targetOne.setText("");break;
                    case 2: targetTwo.setText("");break;
                    case 3: targetThree.setText("");break;
                }
                
                if(activeScore < levelArray.length){
                    if((activeScore) % 4 == 0){
                        targetOne.setText(String.valueOf(levelArray[activeScore]));
                        targetTwo.setText(String.valueOf(levelArray[activeScore + 1]));
                        targetThree.setText(String.valueOf(levelArray[activeScore + 2]));
                        targetFour.setText(String.valueOf(levelArray[activeScore + 3]));
                    }
                }
                
                if(activeScore == levelArray.length){
                        wpm = activeScore / 4.5;
                        getChildren().removeAll(txtInput,targetFour);
                        levelOver(true);
                        levelPlayed = 3;
                        bangTime.stop();
                        thumbTime.stop();
                        time.stop();
                        levelPassed = true;
                        if(level == 2){
                            level = 3;
                        }
                    }
            }}
        });
        
        // Instantiate and position the timer
        
        time.setOnFinished(event -> {
            wpm = activeScore / 4.5;
            getChildren().removeAll(text,thumb,txtInput,targetOne,targetTwo,targetThree,targetFour);
            levelPlayed = 3;
            levelOver(false);
        });
        
        text.requestFocus();
    }
    
    public void playLevelFour(){
        activeScore = 0;
        levelPassed = false;
        String levelOne = "Little Yellow Birdie With A Little Yellow Bill Landed On My Window Sill";
        Label ditty = new Label(levelOne);
        ditty.setFont(Font.font("Times New Roman",40));
        ditty.setLayoutX(100);
        ditty.setLayoutY(50);
        
        
        char[] levelArray = levelOne.toLowerCase().toCharArray();
        Label txtInput = new Label("60");
        
        Image image2 = new Image("images/Level_One.png");
        imageView = new ImageView(image2);
        getChildren().add(imageView);
        getChildren().add(ditty);
        
        Text text = new Text("A");
        txtInput.setFont(Font.font("Times New Roman",40));
        txtInput.setLayoutX(750);
        txtInput.setLayoutY(20);
        getChildren().add(txtInput);
        
        // Set up and run the mechanics behind the timer, remove everything afterwards
        KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {txtInput.setText(String.valueOf(Integer.parseInt(txtInput.getText()) - 1));});
        Timeline time = new Timeline(kf);
        time.setCycleCount(Integer.parseInt(txtInput.getText())+1);
        time.play();
        getChildren().add(text);
        
        Image thumbUp = new Image("images/thumbUp.png");
        ImageView thumb = new ImageView(thumbUp);
        thumb.setLayoutX(600);
        thumb.setLayoutY(600);
        
        Image shot = new Image("images/shot.png");
        ImageView bang = new ImageView(shot);
        bang.setLayoutX(468); 
        bang.setLayoutY(325);
        // Timer for thumbs up/correct graphic
        KeyFrame kf3 = new KeyFrame(Duration.millis(300));
        Timeline bangTime = new Timeline(kf3);
        
        Label targetOne = new Label(String.valueOf(levelArray[activeScore]));
        targetOne.setFont(Font.font("Times New Roman",40));
        targetOne.setLayoutX(630);
        targetOne.setLayoutY(150);
        getChildren().add(targetOne);
        
        Label targetTwo = new Label(String.valueOf(levelArray[activeScore + 1]));
        targetTwo.setFont(Font.font("Times New Roman",40));
        targetTwo.setLayoutX(855);
        targetTwo.setLayoutY(150);
        getChildren().add(targetTwo);
        
        Label targetThree = new Label(String.valueOf(levelArray[activeScore + 2]));
        targetThree.setFont(Font.font("Times New Roman",40));
        targetThree.setLayoutX(1080);
        targetThree.setLayoutY(150);
        getChildren().add(targetThree);
        
        Label targetFour = new Label(String.valueOf(levelArray[activeScore + 3]));
        targetFour.setFont(Font.font("Times New Roman",40));
        targetFour.setLayoutX(1275);
        targetFour.setLayoutY(150);
        getChildren().add(targetFour);
        
        // Timer for thumbs up/correct graphic
        KeyFrame kf2 = new KeyFrame(Duration.millis(1000));
        Timeline thumbTime = new Timeline(kf2);
        
        // When the player presses the correct key, do stuff
        text.setOnKeyPressed(e -> {
            getChildren().remove(bang);
            getChildren().add(bang);
            bangTime.play();
            bangTime.setOnFinished(event -> {
                    getChildren().remove(bang);
                });
            if(activeScore < levelArray.length){
            if(e.getText().equals(String.valueOf(levelArray[activeScore]))){
                
                // Increase the score after hitting the right key
                activeScore += 1;
                
                // Check to see what's happening
                System.out.println(e.getText() + " " + activeScore);
                
                // If the player types fast enough, remove the previous thumb
                // So that we can add it again.
                getChildren().remove(thumb);
                
                // Give a thumbs up for hitting the right key
                getChildren().add(thumb);
                
                // Start the timer
                thumbTime.play();
                
                // Remove the thumbs up after 1 second
                thumbTime.setOnFinished(event -> {
                    getChildren().remove(thumb);
                });
                
                switch(activeScore%4){
                    case 1: targetOne.setText("");break;
                    case 2: targetTwo.setText("");break;
                    case 3: targetThree.setText("");break;
                }
                
                if(activeScore < levelArray.length){
                    if((activeScore) % 4 == 0){
                        if(levelArray[activeScore] != levelArray.length){
                            targetOne.setText(String.valueOf(levelArray[activeScore]));
                        }
                        if(levelArray[activeScore + 1] != levelArray.length){
                            targetTwo.setText(String.valueOf(levelArray[activeScore + 1]));
                        }
                        if(levelArray[activeScore + 2] != levelArray.length){
                            targetThree.setText(String.valueOf(levelArray[activeScore + 2]));
                        }
                        if(levelArray[activeScore + 3] != levelArray.length){
                            targetFour.setText(String.valueOf(levelArray[activeScore + 3]));
                        }
                    }
                }
                
                if(activeScore == levelArray.length){
                        wpm = activeScore / 4.5;
                        getChildren().removeAll(txtInput,targetFour);
                        levelOver(true);
                        levelPlayed = 4;
                        bangTime.stop();
                        thumbTime.stop();
                        time.stop();
                        levelPassed = true;
                        if(level == 3){
                            level = 4;
                        }
                    }
            }}
        });
        
        // Instantiate and position the timer
        
        time.setOnFinished(event -> {
            wpm = activeScore / 4.5;
            getChildren().removeAll(text,thumb,txtInput,targetOne,targetTwo,targetThree,targetFour);
            levelPlayed = 4;
            levelOver(false);
        });
        
        text.requestFocus();
    }
    
    public void levelOver(boolean finished){
        // Instantiate and position the timer
        Label over = new Label("");
        if(finished == true){
            over = new Label("MISSION COMPLETE");
            over.setLayoutX(330);
            over.setLayoutY(300);
        } else {
            over = new Label("MISSION FAILED");
            over.setLayoutX(375);
            over.setLayoutY(300);
        }
        
        over.setFont(Font.font("Times New Roman",100));
        
        getChildren().add(over);
        
        // Set up and run the mechanics behind the timer, remove everything afterwards
        KeyFrame kf = new KeyFrame(Duration.millis(1000));
        Timeline time = new Timeline(kf);
        time.setCycleCount(5);
        time.play();
        time.setOnFinished(event -> {
            getChildren().removeAll();
            showScore();
        });
        
        
    }
    
    public void options(){
        mainMenu();
        getChildren().remove(mainDescription);
        
        Label overrideText = new Label("RTX/Stumps/Yuma Override");
        overrideText.setFont(new Font("Times New Roman", 30));
        getChildren().add(overrideText);
        overrideText.setLayoutX(25);
        overrideText.setLayoutY(150);
        
        RadioButton desertRB = new RadioButton("DESERTS");
        desertRB.setLayoutX(25);
        desertRB.setLayoutY(190);
        getChildren().add(desertRB);
        
        desertRB.setOnAction(e -> {
            if (desertRB.isSelected()) {
//                setUniform(true);
            }
        });
        
        RadioButton woodRB = new RadioButton("WOODLANDS");
        woodRB.setSelected(true);
        woodRB.setLayoutX(25);
        woodRB.setLayoutY(215);
        getChildren().add(woodRB);
        
        woodRB.setOnAction(e -> {
            if (woodRB.isSelected()) {
//                setUniform(false);
            }
        });
        
        ToggleGroup group = new ToggleGroup();
        woodRB.setToggleGroup(group);
        desertRB.setToggleGroup(group);
        
        Button resetScoresSelect = new Button("Reset High Scores");
        resetScoresSelect.setLayoutX(25);
        resetScoresSelect.setLayoutY(290);
        resetScoresSelect.setPrefSize(180,40);
        getChildren().add(resetScoresSelect);
        
        
        resetScoresSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("resetScoresSelect pressed");
                Button areSure = new Button("Yes, I'm sure!");
                areSure.setLayoutX(500);
                areSure.setLayoutY(290);
                areSure.setPrefSize(180,40);
                getChildren().add(areSure);
                Label sureText = new Label("Are you really sure you want to do that?");
                sureText.setLayoutX(225);
                sureText.setLayoutY(300);
                getChildren().add(sureText);
                areSure.setOnAction(new EventHandler<ActionEvent>() {
            
                    @Override
                    public void handle(ActionEvent event) {
                        resetScores();
                        System.out.println(oneHighScore);
                        System.out.println(oneHighName);
                    }
                });
            }
        });
        
        Button exitSelect = new Button("Exit");
        exitSelect.setLayoutX(25);
        exitSelect.setLayoutY(400);
        exitSelect.setPrefSize(180,40);
        getChildren().add(exitSelect);
        exitSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("exitSelect pressed");
                System.exit(0);
            }
        });
        
        Button mainSelect = new Button();
        mainSelect.setLayoutX(615);
        mainSelect.setLayoutY(600);
        mainSelect.setPrefSize(180,40);
        getChildren().add(mainSelect);
        mainSelect.setStyle("-fx-background-color: transparent;");
        mainSelect.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                getChildren().removeAll(woodRB,desertRB,exitSelect,mainSelect,overrideText);
                getChildren().removeAll();
                System.out.println("mainSelect pressed");
                mainMenu();
            }
        });
    }
    
    public static boolean change() {
        if(isChanged.get()==true){
        return true;} else {return false;}
    }
    
    @Override
    public String toString(){
        return String.format("%.2f %s %d", wpm, name, score);
    }
}
