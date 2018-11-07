package GuiProject;

/**
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:27 PM
 * Description:
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static GuiProject.dbHandler.*;

public class Main extends Application {

    /**
     * Scenes to be used
     */
    private Scene mainMenu,
            atkScene,
            defScene;

    /**
     * Handling of Operator
     */
    public static String[] atkOperator;
    public static String[] defOperator;
    public String atkName = "";
    public String atkImage = "";
    public String defName = "";
    public String defImage = "";

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Rainbow Six Siege: Operator Auto-Picker");
        primaryStage.getIcons().add(new Image("GuiProject/images/rbsLogo.png"));

        GridPane root = new GridPane();
        root.setId("GridPane");
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setPadding(new Insets(10,10,100,10));
        root.setHgap(45);
        root.setVgap(10);

        /**
         * "Back" Buttons
         */
        Button atkMenuBtn = new Button("BACK");
        atkMenuBtn.setPrefSize(125,65);
        atkMenuBtn.setOnAction(event -> {
            System.out.println("You chose to go to menu.");
            primaryStage.setScene(mainMenu);

        });

        Button defMenuBtn = new Button("BACK");
        defMenuBtn.setPrefSize(125,65);
        defMenuBtn.setOnAction(event -> {
            System.out.println("You chose to go to menu.");
            primaryStage.setScene(mainMenu);

        });

        /**
         * Selection Scene Buttons
         */
        Button atk = new Button("ATTACKERS");
        atk.setPrefSize(125,65);
        GridPane.setRowIndex(atk, 0);
        GridPane.setColumnIndex(atk, 0);
        atk.setOnAction(event -> {
            System.out.println("You chose Attackers.");
            primaryStage.setScene(atkScene);

        });

        Button def = new Button("DEFENDERS");
        def.setPrefSize(125,65);
        GridPane.setRowIndex(def, 0);
        GridPane.setColumnIndex(def, 1);
        def.setOnAction(event -> {
            System.out.println("You chose Defenders.");
            primaryStage.setScene(defScene);
        });

        /**
         * Selection buttons
         */
        Button chooseAtk = new Button("PICK FOR ME");
        chooseAtk.setPrefSize(150,65);
        chooseAtk.setOnAction(event -> {
            System.out.println("Choosing Attacking Operator");

            try {
                atkOperator = getAtkOperator();
                System.out.println();
                atkName = atkOperator[0];
                atkImage = atkOperator[1];
                System.out.println("Name: " + atkName + "\nImage: " + atkImage);
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Button chooseDef = new Button("PICK FOR ME");
        chooseDef.setPrefSize(150,65);
        chooseDef.setOnAction(event -> {
            System.out.println("Choosing Defense Operator");

            try {
                defOperator = getDefOperator();
                System.out.println();
                defName = defOperator[0];
                defImage = defOperator[1];
                System.out.println("Name: " + defName + "\nImage: " + defImage);
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        /**
         * Attacker Selection Scene
         */
        BorderPane atkPane = new BorderPane();
        atkPane.setId("atkPane");
        atkScene = new Scene(atkPane, 950, 633);
        atkScene.getStylesheets().add("GuiProject/ProjectStyle.css");
        atkPane.setPadding(new Insets(10,10,50,10));

        Image opIconsAtk = new Image("GuiProject/images/attackerIconBatchRedesign.png");
        atkPane.setCenter(new ImageView(opIconsAtk));

        HBox atkBoxLeft = new HBox();
        atkBoxLeft.getChildren().addAll(atkMenuBtn);

        atkPane.setLeft(atkBoxLeft);
        atkBoxLeft.setAlignment(Pos.BOTTOM_LEFT);

        HBox atkBoxRight = new HBox();
        atkBoxRight.getChildren().add(chooseAtk);

        atkPane.setRight(atkBoxRight);
        atkBoxRight.setAlignment(Pos.BOTTOM_RIGHT);

        /**
         * Defender Selection Scene
         */
        BorderPane defPane = new BorderPane();
        defPane.setId("defPane");
        defScene = new Scene(defPane, 950, 633);
        defScene.getStylesheets().add("GuiProject/ProjectStyle.css");
        defPane.setPadding(new Insets(10,10,50,10));

        Image opIconsDef = new Image("GuiProject/images/defenderIconBatchRedesign.png");
        defPane.setCenter(new ImageView(opIconsDef));

        HBox defBoxLeft = new HBox();
        defBoxLeft.getChildren().add(defMenuBtn);

        defPane.setLeft(defBoxLeft);
        defBoxLeft.setAlignment(Pos.BOTTOM_LEFT);

        HBox defBoxRight = new HBox();
        defBoxRight.getChildren().add(chooseDef);

        defPane.setRight(defBoxRight);
        defBoxRight.setAlignment(Pos.BOTTOM_RIGHT);

        /**
         * Primary Scene
         */
        root.getChildren().add(atk);
        root.getChildren().add(def);

        root.setGridLinesVisible(false);

        mainMenu = new Scene(root, 950, 633);
        mainMenu.getStylesheets().add("GuiProject/ProjectStyle.css");

        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        createConnection();
        launch(args);
    }
}
