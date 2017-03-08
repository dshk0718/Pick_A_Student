
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Pick_A_Student extends Application {
    
    private Course course;
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane mainPane = new StackPane();
        
        GraphicsDevice screenInfo = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        double screenHeight = screenInfo.getDisplayMode().getHeight();
        double screenWidth = screenInfo.getDisplayMode().getWidth();
                
        Scene scene = new Scene(mainPane, screenHeight, screenWidth);
        
        ImageView background = new ImageView(new Image("Background.png"));
        
        primaryStage.getIcons().add(new Image("file:Rouse.jpg"));
        
        final URL buttonSoundFile = getClass().getResource("Click.mp3");
        final Media buttonSound = new Media(buttonSoundFile.toString());
        MediaPlayer playButtonSound = new MediaPlayer(buttonSound);
        
        //----------------------------------------------------------------------
        //Left GUI Elements
        //Menu Bar
        //Load & Create File Menu
        Menu fileMenu = new Menu("File");
        
        MenuItem loadClass = new MenuItem("Load Class");
            Stage loadClassStage = new Stage();
            StackPane loadClassPane = new StackPane();
            Scene loadClassScene = new Scene(loadClassPane, 512, 256);
            ChoiceBox courseNames = new ChoiceBox();
            courseNames.setMaxWidth(200);
            Button selectCourseName = new Button("Load");
            VBox loadClassVBox = new VBox(50);
            loadClassVBox.setAlignment(Pos.CENTER);
            loadClassVBox.getChildren().addAll(courseNames, selectCourseName);
            loadClassPane.getChildren().addAll(new ImageView(new Image("PopupBackground.png")), loadClassVBox);
        
        MenuItem createClass = new MenuItem("Create Class");
            Stage createClassStage = new Stage();
            StackPane createClassPane = new StackPane();
            Scene createClassScene = new Scene(createClassPane, 512, 256);
            createClassStage.setTitle("Create Class");
            createClassStage.setScene(createClassScene);
            final TextField courseName = new TextField("Course Name");
            courseName.setMaxWidth(300);
            final TextField importFileName = new TextField("File Location");
            importFileName.setMaxWidth(300);
            VBox createClassTxtFlds = new VBox(20);
            createClassTxtFlds.getChildren().addAll(courseName, importFileName);
            createClassTxtFlds.setAlignment(Pos.CENTER);
            
            Button importFileButton = new Button("Import File");
            Button okImportFile = new Button("Ok");
            HBox createClassButtons = new HBox(30);
            createClassButtons.getChildren().addAll(importFileButton, okImportFile);
            createClassButtons.setAlignment(Pos.BOTTOM_CENTER);
            createClassButtons.setPadding(new Insets(0, 0, 30, 0));
            createClassPane.getChildren().addAll(new ImageView(new Image("PopupBackground.png")), createClassTxtFlds, createClassButtons);
        
        
        fileMenu.getItems().addAll(createClass, loadClass);
        
            
        //Tally Menu
        Menu tallyMenu = new Menu("Tally");
        MenuItem absent = new MenuItem("Absent Tally");    
        MenuItem neutral = new MenuItem("Neutral Tally");
        MenuItem correct = new MenuItem("Correct Tally");
        tallyMenu.getItems().addAll(absent, neutral, correct);
        
            Stage absentTallyStage = new Stage();
            StackPane absentTallyPane = new StackPane();
            Scene absentTallyScene = new Scene(absentTallyPane, 512, 256);
            absentTallyStage.setTitle("Absent Tally");
            absentTallyStage.setScene(absentTallyScene);
            absentTallyPane.getChildren().addAll(new ImageView(new Image("AbsentTally.png")));
            
            Stage neutralTallyStage = new Stage();
            StackPane neutralTallyPane = new StackPane();
            Scene neutralTallyScene = new Scene(neutralTallyPane, 512, 256);
            neutralTallyStage.setTitle("Neutral Tally");
            neutralTallyStage.setScene(neutralTallyScene);
            neutralTallyPane.getChildren().addAll(new ImageView(new Image("NeutralTally.png")));
            
            Stage correctTallyStage = new Stage();
            StackPane correctTallyPane = new StackPane();
            Scene correctTallyScene = new Scene(correctTallyPane, 512, 256); 
            correctTallyStage.setTitle("Correct Tally");
            correctTallyStage.setScene(correctTallyScene);
            correctTallyPane.getChildren().addAll(new ImageView(new Image("CorrectTally.png")));
            
        Menu aboutMenu = new Menu("About");
        MenuItem instructionsMenu = new MenuItem("Instructions");
        aboutMenu.getItems().add(instructionsMenu);
        
            Stage instructionsMenuStage = new Stage();
            StackPane instructionsMenuPane = new StackPane();
            Scene instructionsMenuScene = new Scene(instructionsMenuPane, 512, 256);
            Label instructions = new Label();
            instructions.setTextFill(Color.WHITE);
            instructions.setFont(new Font("Comic Sans MS", 12));
            instructions.setText("1. Click File -> Create Class in order to create"
                    + " a class.\n"
                    + "2. Hit the Next Button to pick a student's name.\n"
                    + "3. Click the appropriate emoji face that reflects the\n "
                    + "student's answer (or lack thereof).\n"
                    + "4. Click the X at the top right in order to close.\n "
                    + "Your progress will be saved automatically.\n"
                    + "5. Load a class by clicking File -> Load Class. Select\n"
                    + " the course you would like to ask questions from.");
            instructions.setAlignment(Pos.CENTER);
            instructionsMenuPane.getChildren().addAll(new ImageView(new Image("PopupBackground.png")), instructions);
            
        MenuBar menu = new MenuBar();
        
        menu.getMenus().addAll(fileMenu, tallyMenu, aboutMenu);
        //End of Left Pain Elements
        
        //----------------------------------------------------------------------
       
        //Center GUI elements
        //Student Name Label
        Text studentName = new Text("Student Name");
        studentName.setFont(new Font("Comic Sans MS", 64));
                
        //Rectangle for student Name Label
        Rectangle whiteFilled = new Rectangle(650, 65);
        whiteFilled.setFill(Color.WHITE);
        whiteFilled.setStroke(Color.BLACK);
        studentName.setBoundsType(TextBoundsType.VISUAL); 
        StackPane stackStudentName = new StackPane();
        stackStudentName.getChildren().addAll(whiteFilled, studentName);
        stackStudentName.setAlignment(Pos.TOP_CENTER);
        stackStudentName.setPadding(new Insets(screenHeight * .1, 50, 50, 50));
        
        //Pick A Student Button
        Circle pickAStudent = new Circle(125);
        pickAStudent.setFill(Color.RED);
        ImagePattern nextPicture = new ImagePattern(new Image("Next.png"));
        pickAStudent.setFill(nextPicture); 
        HBox pickAStudentBtn = new HBox();
        pickAStudentBtn.getChildren().addAll(pickAStudent);
        pickAStudentBtn.setAlignment(Pos.CENTER);
        pickAStudentBtn.setPadding(new Insets(20, 20, screenHeight * .1, 20));
        
        //Tally Buttons
        //Creating and editing the gradeButtons themselves
        Circle horrifiedFace = new Circle(60);
        horrifiedFace.setFill(Color.WHITE);
        horrifiedFace.setStroke(Color.BLACK);
        ImagePattern horrifiedImage = new ImagePattern(new Image("Horrified.jpg"));
        horrifiedFace.setFill(horrifiedImage);
        
        Circle unsure = new Circle(60);
        unsure.setFill(Color.WHITE);
        unsure.setStroke(Color.BLACK);
        ImagePattern unsureImage = new ImagePattern(new Image("Unsure.jpg"));
        unsure.setFill(unsureImage);
        
        Circle happyFace = new Circle(60);
        happyFace.setFill(Color.WHITE);
        happyFace.setStroke(Color.BLACK);
        ImagePattern happyImage = new ImagePattern(new Image("HaloEmoji.jpg"));
        happyFace.setFill(happyImage);
        
        //Adding all gradeButtons to HBox
        HBox gradeButtons = new HBox(90);
        gradeButtons.getChildren().addAll(horrifiedFace, unsure, happyFace);
        gradeButtons.setAlignment(Pos.BOTTOM_CENTER);
        gradeButtons.setPadding(new Insets(50, 50, screenHeight * .05, 50));
        
        //VBox of all gradeButtons
        VBox centerElements = new VBox();
        centerElements.setAlignment(Pos.BOTTOM_CENTER);
        centerElements.getChildren().addAll(stackStudentName, gradeButtons, pickAStudentBtn);
        
        //End of Center GUI Elements
        
        //----------------------------------------------------------------------
        
        //Right GUI Elements
        //"Pick a Student" Label 
        Label title = new Label("Pick a Student");
        title.setFont(new Font("Comic Sans MS", 32));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(25, 25, 25, 25));
        
        //End of Right GUI Elements
        
        //----------------------------------------------------------------------
                
        //Aligning AnchorPanes
        mainPane.setAlignment(menu, Pos.TOP_LEFT);
        mainPane.setAlignment(stackStudentName, Pos.TOP_CENTER);
        mainPane.setAlignment(pickAStudentBtn, Pos.CENTER);
        mainPane.setAlignment(gradeButtons, Pos.BOTTOM_CENTER);
        mainPane.setAlignment(title, Pos.TOP_RIGHT);
        
        //This StackPane along with the VBox buttons allows for all Circle buttons
        //to be useable. Since a StackPane was used as the main pane, each node
        //is placed on it like a stack which makes previously placed items 
        //unavailable for use. Using this extra StackPane with a VBox for all buttons, 
        //makes all the buttons and the menubar available to the user.
        StackPane menuAndButtons = new StackPane();
        menuAndButtons.getChildren().addAll(centerElements, menu);
        
        mainPane.getChildren().addAll(background, title, menuAndButtons);
        
        //Main Scene
        primaryStage.setTitle("Pick a Student");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        //----------------------------------------------------------------------
        
        //Popup Context Menus
        //----------------------------------------------------------------------
     
        //createClass MenuItem Listeners
        createClass.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("Create");
                
                createClassStage.setTitle("Create Class");
                createClassStage.setScene(createClassScene);
                createClassStage.show();
                
                
                
            }
            
        });

        importFileButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Import Class");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(createClassStage);
                
                if (selectedFile != null) {
                    
                    System.out.println(selectedFile.toString());
                
                }
                
                importFileName.setText(selectedFile.toString());
                
            }
            
        });
                    
        okImportFile.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                course = new Course();
                course.newCourse(new File(importFileName.getText()), courseName.getText());
                createClassStage.close();
                
            }
            
        });        
        


        //End of createClass Listeners
        
        //----------------------------------------------------------------------
        
        //loadClass MenuItem Listener
        loadClass.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Class");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("DAT Files", "*.dat"));
                String selectedFile = fileChooser.showOpenDialog(primaryStage).toString();
                              
                System.out.println(selectedFile);
                
                course = new Course();
                
                course = course.loadCourse(selectedFile.toString());
                
            }
            
        });//End of loadClass Listener
        
        //----------------------------------------------------------------------
        
        //Absent Tally Menu Listener
        absent.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("Absent");
                
                absentTallyStage.setTitle("Absent Tally");
                absentTallyStage.setScene(absentTallyScene);
                absentTallyStage.show();
               
            }
            
        });//End of Absent Tally Menu Listener
        
        //----------------------------------------------------------------------
        
        //Neutral Tally Menu Listener
        neutral.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("Neutral");
                
                neutralTallyStage.setTitle("NeutralTally");
                neutralTallyStage.setScene(neutralTallyScene);
                neutralTallyStage.show();
               
            }
            
        });//End of Neutral Tally Menu Listener
        
        //----------------------------------------------------------------------
        
        //Correct Tally Menu Listener
        correct.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println("Correct");
                
                correctTallyStage.setTitle("Correct Tally");
                correctTallyStage.setScene(correctTallyScene);
                correctTallyStage.show();
               
            }
            
        });//End of Correct Tally Menu Listener
        
        //----------------------------------------------------------------------
        
        //About Menu Listener
        instructionsMenu.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //Open About Window
                System.out.println("About");
                
                instructionsMenuStage.setTitle("Instructions Menu");
                instructionsMenuStage.setScene(instructionsMenuScene);
                instructionsMenuStage.show();
               
            }
            
        });//End of About Menu Listener
        
        //----------------------------------------------------------------------
        
        //Save & Exit Listener
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                
                System.out.println("Closed");
                
                try{
                    
                    course.saveCourse();
                
                }catch(NullPointerException ex){
                
                    System.err.println("No class was ever instantiated so nothing was saved.");
                
                }
                    
                System.exit(0);
                
            }
            
        });//End of Save & Exit Listener
        
        //----------------------------------------------------------------------
        
        //Pick A Student Button Listener
        pickAStudentBtn.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                
                //Add student to the nice list fot that day.
                
                buttonSound(playButtonSound);
                
                System.out.println("Go");
                
            }
            
        });//End of Pick A Student Button Listener
        
        //----------------------------------------------------------------------
        
        //Happy Face Button Listener
        happyFace.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                
                //Add student to the nice list fot that day.
                
                buttonSound(playButtonSound);
                
                System.out.println("Happy");
                
            }
            
        });//End of Happy Face button Listener
        
        //----------------------------------------------------------------------
        
        //Horrified Face Button Listener
        horrifiedFace.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                
                //Add specific student to the not here list for that day.
                
                buttonSound(playButtonSound);
               
                System.out.println("Sad");
                
            }
            
        });//End of Horrified Face Button Listener
        
        //----------------------------------------------------------------------
        
        //Unsure Button Listener
        unsure.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                
                //Add specific student to Unsure List for that day.
                
                buttonSound(playButtonSound);
               
                System.out.println("unsure");
                
            }
            
        });//End of Unsure Button Listener
        
        //----------------------------------------------------------------------
        
        
    }//End of main method

    //--------------------------------------------------------------------------
    
    //Button Sound Effect
    public static void buttonSound(MediaPlayer buttonSound){
        
        buttonSound.play();
        
        try{
        
            TimeUnit.SECONDS.sleep(1);
        
        }catch(InterruptedException ex){
        
            System.err.println("An error occured whilst playing the audio file.");
        
        }
            
        buttonSound.stop();
        
    }
   
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) {
        
        launch(args);
    
    }
    
    //--------------------------------------------------------------------------
    
    //----------------------------------------------------------------------
    
}
