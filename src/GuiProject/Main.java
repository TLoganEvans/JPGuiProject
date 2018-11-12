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
            defScene,
            atkOpScene,
            defOpScene;

    /**
     * Handling of Operator
     */
    private static String[] atkOperator;
    private static String[] defOperator;

    private String atkName = "";
    private String atkImage = "";

    private String defName = "";
    private String defImage = "";

    public Operator atkOperatorObj = new Operator();
    public Operator defOperatorObj = new Operator();

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Rainbow Six Siege: Operator Auto-Picker");
        primaryStage.getIcons().add(new Image("GuiProject/images/rbsLogo.png"));

        GridPane root = new GridPane();
        root.setId("GridPane");

        /**
         * "Back To Menu" Buttons
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

        Button atkOpMenuBtn = new Button("BACK");
        atkOpMenuBtn.setOnAction(event -> {
            System.out.println("You chose to go to menu.");
            primaryStage.setScene(mainMenu);
        });

        Button defOpMenuBtn = new Button("BACK");
        defOpMenuBtn.setOnAction(event -> {
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
        chooseAttackOp(primaryStage, chooseAtk);

        Button chooseAtkAgain = new Button("PICK FOR ME");
        chooseAttackOp(primaryStage, chooseAtkAgain);

        Button chooseDef = new Button("PICK FOR ME");
        chooseDefOp(primaryStage, chooseDef);

        Button chooseDefAgain = new Button("PICK FOR ME");
        chooseDefOp(primaryStage, chooseDefAgain);

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
         * Selected Attack Operator Scene
         */
        BorderPane atkOpPane = new BorderPane();
        atkOpPane.setId("atkOpPane");
        atkOpScene = new Scene(atkOpPane,950,633);
        atkOpScene.getStylesheets().add("GuiProject/ProjectStyle.css");

        Image atkOperatorImage = new Image(atkOperatorObj.getImagePath());
        ImageView atkOpImage = new ImageView(atkOperatorImage);
        atkOpImage.setFitHeight(575);
        atkOpImage.setPreserveRatio(true);
        atkOpPane.setCenter(atkOpImage);

        HBox atkOpRight = new HBox();
        atkOpRight.setId("atkOpRight");
        atkOpRight.getChildren().add(chooseAtkAgain);

        HBox atkOpLeft = new HBox();
        atkOpLeft.setId("atkOpLeft");
        atkOpLeft.getChildren().add(atkOpMenuBtn);

        atkOpPane.setRight(atkOpRight);
        atkOpPane.setLeft(atkOpLeft);

        /**
         * Selected Defender Operator Scene
         */
        BorderPane defOpPane = new BorderPane();
        defOpPane.setId("defOpPane");
        defOpScene = new Scene(defOpPane, 950,633);
        defOpScene.getStylesheets().add("GuiProject/ProjectStyle.css");

        Image defOperatorImage = new Image(atkOperatorObj.getImagePath());
        ImageView defOpImage = new ImageView(defOperatorImage);
        defOpImage.setFitHeight(575);
        defOpImage.setPreserveRatio(true);
        defOpPane.setCenter(defOpImage);

        HBox defOpRight = new HBox();
        defOpRight.setId("defOpRight");
        defOpRight.getChildren().add(chooseDefAgain);

        HBox defOpLeft = new HBox();
        defOpLeft.setId("defOpLeft");
        defOpLeft.getChildren().add(defOpMenuBtn);

        defOpPane.setRight(defOpRight);
        defOpPane.setLeft(defOpLeft);

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

    private void chooseAttackOp(Stage primaryStage, Button chooseAtkOp) {
        chooseAtkOp.setOnAction(event -> {
            System.out.println("Choosing Attacking Operator");

            try {
                atkOperator = getAtkOperator();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
            atkName = atkOperator[0];
            atkImage = atkOperator[1];
            System.out.println("Name: " + atkName + "\nImage: " + atkImage);
            System.out.println();

            atkOperatorObj.setName(atkName);
            atkOperatorObj.setImagePath(atkImage);

            primaryStage.setScene(atkOpScene);
        });
    }

    private void chooseDefOp(Stage primaryStage, Button chooseDefOp) {
        chooseDefOp.setOnAction(event -> {
            System.out.println("Choosing Defense Operator");

            try {
                defOperator = getDefOperator();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
            defName = defOperator[0];
            defImage = defOperator[1];
            System.out.println("Name: " + defName + "\nImage: " + defImage);
            System.out.println();

            defOperatorObj.setName(defName);
            defOperatorObj.setImagePath(defImage);

            primaryStage.setScene(defOpScene);
        });
    }


    public static void main(String[] args) throws Exception {
        dbHandler.createConnection();
        launch(args);
    }
}
