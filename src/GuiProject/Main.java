package GuiProject;

/*
 * Author: Trevor Evans
 * Date: 06-Nov-18
 * Time: 5:27 PM
 * Description:
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static GuiProject.DbHandler.getAtkOperator;
import static GuiProject.DbHandler.getDefOperator;

public class Main extends Application {

  private Scene mainMenu, atkScene, defScene, atkOpScene, defOpScene;

  private static String[] atkOperator;
  private static String[] defOperator;

  @Override
  public void start(Stage primaryStage) throws Exception {

    primaryStage.setTitle("Rainbow Six Siege: Operator Auto-Picker");
    primaryStage.getIcons().add(new Image("GuiProject/images/rbsLogo.png"));

    GridPane root = new GridPane();
    root.setId("GridPane");

    /* "Back To Menu" Buttons */

    // Button atkMenuBtn -> Scene mainMenu
    // Node in Scene atkScene
    Button atkMenuBtn = new Button("BACK");
    backToMenu(primaryStage, atkMenuBtn);

    // Button defMenuBtn -> Scene mainMenu
    // Node in Scene defScene
    Button defMenuBtn = new Button("BACK");
    backToMenu(primaryStage, defMenuBtn);

    /* Selection Scene Buttons */

    // Button toAttackers -> Scene atkScene
    // Node in Scene mainMenu
    Button toAttackers = new Button("ATTACKERS");
    GridPane.setRowIndex(toAttackers, 0);
    GridPane.setColumnIndex(toAttackers, 0);
    toAttackers.setOnAction(
        event -> {
          System.out.println("You chose Attackers.");
          primaryStage.setScene(atkScene);
        });

    // Button toDefenders -> Scene defScene
    // Node in Scene mainMenu
    Button toDefenders = new Button("DEFENDERS");
    GridPane.setRowIndex(toDefenders, 0);
    GridPane.setColumnIndex(toDefenders, 1);
    toDefenders.setOnAction(
        event -> {
          System.out.println("You chose Defenders.");
          primaryStage.setScene(defScene);
        });

    /* Selection buttons */
    Button chooseAtk = new Button("PICK FOR ME");
    chooseAttackOp(primaryStage, chooseAtk);

    Button chooseDef = new Button("PICK FOR ME");
    chooseDefOp(primaryStage, chooseDef);

    /* Attacker Selection Scene */
    BorderPane atkPane = new BorderPane();
    atkPane.setId("atkPane");

    // ImageView opIconsAtkView, Image opIconsAtk
    // Node in Scene atkScene
    Image opIconsAtk = new Image("GuiProject/images/attackerIconBatchRedesign.png");

    // HBox atkBoxLeft
    // Node in Scene atkScene
    // Holds Button atkToMenu
    HBox atkBoxLeft = new HBox();
    atkBoxLeft.setId("atkBoxLeft");
    atkBoxLeft.getChildren().addAll(atkMenuBtn);

    // Hbox atkBoxRight
    // Node in Scene atkScene
    // Holds Button atkToOperator
    HBox atkBoxRight = new HBox();
    atkBoxRight.setId("atkBoxRight");
    atkBoxRight.getChildren().add(chooseAtk);

    atkPane.setCenter(new ImageView(opIconsAtk));
    atkPane.setLeft(atkBoxLeft);
    atkPane.setRight(atkBoxRight);

    atkScene = new Scene(atkPane, 950, 633);
    atkScene.getStylesheets().add("GuiProject/ProjectStyle.css");

    /* Defender Selection Scene */
    BorderPane defPane = new BorderPane();
    defPane.setId("defPane");

    // Image opIconsDef
    // Node in Scene defScene
    Image opIconsDef = new Image("GuiProject/images/defenderIconBatchRedesign.png");

    // HBox defBoxLeft
    // Node in Scene atkScene
    // Holds Button atkToMenu
    HBox defBoxLeft = new HBox();
    defBoxLeft.setId("defBoxLeft");
    defBoxLeft.getChildren().add(defMenuBtn);

    // Hbox defBoxRight
    // Node in Scene atkScene
    // Holds Button atkToOperator
    HBox defBoxRight = new HBox();
    defBoxRight.setId("defBoxRight");
    defBoxRight.getChildren().add(chooseDef);

    defPane.setCenter(new ImageView(opIconsDef));
    defPane.setLeft(defBoxLeft);
    defPane.setRight(defBoxRight);

    defScene = new Scene(defPane, 950, 633);
    defScene.getStylesheets().add("GuiProject/ProjectStyle.css");

    /* Primary Scene */
    root.getChildren().add(toAttackers);
    root.getChildren().add(toDefenders);

    root.setGridLinesVisible(false);

    mainMenu = new Scene(root, 950, 633);
    mainMenu.getStylesheets().add("GuiProject/ProjectStyle.css");

    primaryStage.setScene(mainMenu);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * Recursive method to generate/refresh Scene atkOpScene, calls to method in "DbHandler.java" to
   * read operator * information from derby database "OperatorData".
   *
   * @param primaryStage - utilized for application Stage control.
   * @param chooseAtkOp -
   */
  private void chooseAttackOp(Stage primaryStage, Button chooseAtkOp) {
    chooseAtkOp.setOnAction(
        event -> {
          System.out.println("Choosing Attacking Operator");

          try {
            atkOperator = getAtkOperator();
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.println();
          System.out.println("Name: " + atkOperator[0] + "\nImage: " + atkOperator[1]);
          System.out.println();

          // Button chooseAtkAgain -> refreshes Scene atkOpScene
          // Node in Scene atkOpScene
          // Recursive method
          Button chooseAtkAgain = new Button("PICK FOR ME");
          chooseAttackOp(primaryStage, chooseAtkAgain);

          // Button atkOpMenuBtn -> returns to Scene mainMenu
          // Node in Scene atkOpScene
          Button atkOpMenuBtn = new Button("BACK");
          backToMenu(primaryStage, atkOpMenuBtn);

          /* Selected Attack Operator Scene */
          BorderPane atkOpPane = new BorderPane();
          atkOpPane.setId("atkOpPane");

          // Label atkOpName
          // Displays selected attack operator's name at top of Scene atkOpScene
          Label atkOpName = new Label(atkOperator[0]);
          BorderPane.setAlignment(atkOpName, Pos.CENTER);

          // Image atkOperatorImage, ImageView atkOpImage
          // Displays selected attack operator in center of Scene atkOpScene
          Image atkOperatorImage = new Image(atkOperator[1]);
          ImageView atkOpImage = new ImageView(atkOperatorImage);

          atkOpImage.setFitHeight(525);
          atkOpImage.setPreserveRatio(true);

          // Hbox atkOpRight
          // Node in Scene atkOpScene
          // Holds Button chooseAtkAgain
          HBox atkOpRight = new HBox();
          atkOpRight.setId("atkOpRight");
          atkOpRight.getChildren().add(chooseAtkAgain);

          // Hbox atkOpLeft
          // Node in Scene atkOpScene
          // Holds Button atkOpMenuBtn
          HBox atkOpLeft = new HBox();
          atkOpLeft.setId("atkOpLeft");
          atkOpLeft.getChildren().add(atkOpMenuBtn);

          atkOpPane.setTop(atkOpName);
          atkOpPane.setCenter(atkOpImage);
          atkOpPane.setRight(atkOpRight);
          atkOpPane.setLeft(atkOpLeft);

          atkOpScene = new Scene(atkOpPane, 950, 633);
          atkOpScene.getStylesheets().add("GuiProject/ProjectStyle.css");

          primaryStage.setScene(atkOpScene);
        });
  }

  /**
   * Recursive method to generate/refresh Scene defOpScene, calls to method in "DbHandler.java" to
   * read operator information from derby database "OperatorData".
   *
   * @param primaryStage - utilized for application Stage control.
   * @param chooseDefOpBtn - controls actionEvent for button(s) passed in.
   */
  private void chooseDefOp(Stage primaryStage, Button chooseDefOpBtn) {
    chooseDefOpBtn.setOnAction(
        event -> {
          System.out.println("Choosing Defense Operator");

          try {
            defOperator = getDefOperator();
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.out.println();
          System.out.println("Name: " + defOperator[0] + "\nImage: " + defOperator[1]);
          System.out.println();

          // Button chooseDefAgain -> refreshes Scene defOpScene
          // Node in Scene defOpScene
          // Recursive method
          Button chooseDefAgain = new Button("PICK FOR ME");
          chooseDefOp(primaryStage, chooseDefAgain);

          // Button defOpMenuBtn -> return to Scene mainMenu
          // Node in Scene defOpScene
          Button defOpMenuBtn = new Button("BACK");
          backToMenu(primaryStage, defOpMenuBtn);

          /* Selected Defender Operator Scene */
          BorderPane defOpPane = new BorderPane();
          defOpPane.setId("defOpPane");

          // Label defOpName
          // Displays selected defense operator name at top of Scene defOpScene
          Label defOpName = new Label(defOperator[0]);
          BorderPane.setAlignment(defOpName, Pos.CENTER);

          // Image defOperatorImage, ImageView defOpImage
          // Displays selected defense operator in center of Scene defOpScene
          Image defOperatorImage = new Image(defOperator[1]);
          ImageView defOpImage = new ImageView(defOperatorImage);

          defOpImage.setFitHeight(525);
          defOpImage.setPreserveRatio(true);

          // Hbox defOpRight
          // Node in Scene defOpScene
          // Holds Button chooseDefAgain
          HBox defOpRight = new HBox();
          defOpRight.setId("defOpRight");
          defOpRight.getChildren().add(chooseDefAgain);

          // Hbox defOpLeft
          // Node in Scene defOpScene
          // Holds Button chooseDefAgain
          HBox defOpLeft = new HBox();
          defOpLeft.setId("defOpLeft");
          defOpLeft.getChildren().add(defOpMenuBtn);

          defOpPane.setTop(defOpName);
          defOpPane.setCenter(defOpImage);
          defOpPane.setRight(defOpRight);
          defOpPane.setLeft(defOpLeft);

          defOpScene = new Scene(defOpPane, 950, 633);
          defOpScene.getStylesheets().add("GuiProject/ProjectStyle.css");

          primaryStage.setScene(defOpScene);
        });
  }

  /**
   * Method to reduce redundant code for buttons that return to Scene mainMenu.
   *
   * @param primaryStage - utilized for application Stage control.
   * @param menuButton - controls actionEvent for button(s) passed in.
   */
  private void backToMenu(Stage primaryStage, Button menuButton) {
    menuButton.setOnAction(
        event -> {
          System.out.println("You chose to go to menu.");
          primaryStage.setScene(mainMenu);
        });
  }

  public static void main(String[] args) throws Exception {
    DbHandler.createConnection();
    launch(args);
  }
}
