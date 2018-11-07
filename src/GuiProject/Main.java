package GuiProject;

/**
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:27 PM
 * Description:
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private static String[] atkOperator;
    private static String[] defOperator;
    private String atkName = "";
    private String atkImage = "";
    private String defName = "";
    private String defImage = "";

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Rainbow Six Siege: Operator Auto-Picker");
        primaryStage.getIcons().add(new Image("GuiProject/images/rbsLogo.png"));

        GridPane root = new GridPane();
        root.setId("GridPane");

        /**
         * "Back" Buttons
         */
        Button atkMenuBtn = new Button("BACK");
        atkMenuBtn.setOnAction(event -> {
            System.out.println("You chose to go to menu.");
            primaryStage.setScene(mainMenu);

        });

        Button defMenuBtn = new Button("BACK");
        defMenuBtn.setOnAction(event -> {
            System.out.println("You chose to go to menu.");
            primaryStage.setScene(mainMenu);

        });

        /**
         * Selection Scene Buttons
         */
        Button atk = new Button("ATTACKERS");
        GridPane.setRowIndex(atk, 0);
        GridPane.setColumnIndex(atk, 0);
        atk.setOnAction(event -> {
            System.out.println("You chose Attackers.");
            primaryStage.setScene(atkScene);

        });

        Button def = new Button("DEFENDERS");
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

        Image opIconsAtk = new Image("GuiProject/images/attackerIconBatchRedesign.png");
        atkPane.setCenter(new ImageView(opIconsAtk));

        HBox atkBoxLeft = new HBox();
        atkBoxLeft.setId("atkBoxLeft");
        atkBoxLeft.getChildren().addAll(atkMenuBtn);

        atkPane.setLeft(atkBoxLeft);

        HBox atkBoxRight = new HBox();
        atkBoxRight.setId("atkBoxRight");
        atkBoxRight.getChildren().add(chooseAtk);

        atkPane.setRight(atkBoxRight);

        /**
         * Defender Selection Scene
         */
        BorderPane defPane = new BorderPane();
        defPane.setId("defPane");
        defScene = new Scene(defPane, 950, 633);
        defScene.getStylesheets().add("GuiProject/ProjectStyle.css");

        Image opIconsDef = new Image("GuiProject/images/defenderIconBatchRedesign.png");
        defPane.setCenter(new ImageView(opIconsDef));

        HBox defBoxLeft = new HBox();
        defBoxLeft.setId("defBoxLeft");
        defBoxLeft.getChildren().add(defMenuBtn);

        defPane.setLeft(defBoxLeft);

        HBox defBoxRight = new HBox();
        defBoxRight.setId("defBoxRight");
        defBoxRight.getChildren().add(chooseDef);

        defPane.setRight(defBoxRight);

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
