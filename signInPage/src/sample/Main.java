package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;

public class Main extends Application

{
    Button sendButton = new Button("send");
    Button logOutbtn = new Button("logOut");
    Button uploadbtn = new Button("upload");
    Button startChatbtn = new Button("start chat");
    TextField sendTextBox = new TextField();
    TextField recipientTextBox = new TextField();
    TextArea chatArea = new TextArea();
//////////////////////////////////////////////////////////////////////////////////
    TextField userFieldIn = new TextField();
    TextField passFieldIn = new TextField();
    Button signInbtnIn = new Button("Sign In");
    Button signUpbtnIn = new Button("Sign Up");
//////////////////////////////////////////////////////////////////////////////////\
    TextField firstnameFieldUp = new TextField();
    TextField lastnameFieldUp = new TextField();
    TextField userFieldUp = new TextField();
    TextField passFieldUp = new TextField();
    TextField emailFieldUp = new TextField();
    TextField fileDirectory = new TextField();
    Button signInbtnUp = new Button("Sign In");
    Button signUpbtnUp = new Button("Sign Up");
///////////////////////////////////////////////////////////////////////////////////
    StackPane pane, newpane;
    Scene signInScene, signUpScene, chatRoomScene;
    HBox hBox, newhbox, sendBox, chatView;
    VBox vBox, newvBox, chatVBox;
    Client client = new Client();

    @Override
    public void start(Stage primaryStage)
    {
        setSignInScene(primaryStage);
        setSignUpScene(primaryStage);
        setChatRoomScene(primaryStage);

        primaryStage.setTitle("MyChat");
        primaryStage.setScene(signInScene);
        primaryStage.show();
    }

    public void setSignInScene(Stage primaryStage)
    {
        pane = new StackPane();
        pane.setPadding(new Insets(10, 50, 10, 50));

        signInbtnIn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        signUpbtnIn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        vBox = new VBox();
        hBox = new HBox();

        HBox userhbox = new HBox();
        userhbox.getChildren().addAll(new Label("username"), userFieldIn);
        userhbox.setSpacing(10);

        HBox passhbox = new HBox();
        passhbox.getChildren().addAll(new Label("password"), passFieldIn);
        passhbox.setSpacing(10);

        vBox.getChildren().addAll(userhbox, passhbox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        hBox.getChildren().addAll(signInbtnIn, signUpbtnIn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        vBox.getChildren().addAll(hBox);
        pane.getChildren().addAll(vBox);

        signInScene = new Scene(pane, 400, 350);

        signInbtnIn.setOnAction(e -> {
            if (true)//userField.getText() != null
            {
                client.sendMSG("signIn");
                client.sendMSG(userFieldIn.getText());
                client.sendMSG(passFieldIn.getText());
                primaryStage.setScene(chatRoomScene);
                recieveMsgThread.start();
            }
        });

        signUpbtnIn.setOnAction(e -> {
            primaryStage.setScene(signUpScene);
        });
    }

    public void setSignUpScene(Stage primaryStage)
    {
        newpane = new StackPane();
        newpane.setPadding(new Insets(10, 50, 10, 50));

        signInbtnUp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        signUpbtnUp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        newvBox = new VBox();
        newhbox = new HBox();

        HBox firstnamehbox = new HBox();
        firstnamehbox.getChildren().addAll(new Label("firstname"), firstnameFieldUp);
        firstnamehbox.setSpacing(25);

        HBox lastnamehbox = new HBox();
        lastnamehbox.getChildren().addAll(new Label("lastname"), lastnameFieldUp);
        lastnamehbox.setSpacing(25);

        HBox userhbox = new HBox();
        userhbox.getChildren().addAll(new Label("username"), userFieldUp);
        userhbox.setSpacing(25);

        HBox passhbox = new HBox();
        passhbox.getChildren().addAll(new Label("password"), passFieldUp);
        passhbox.setSpacing(25);

        HBox emailhbox = new HBox();
        emailhbox.getChildren().addAll(new Label("Email"), emailFieldUp);
        emailhbox.setSpacing(50);

        HBox filehbox = new HBox();
        filehbox.getChildren().addAll(new Label("file directory"), fileDirectory);
        filehbox.setSpacing(5);


        newvBox.getChildren().addAll(firstnamehbox, lastnamehbox,
                userhbox, passhbox, emailhbox, filehbox);
        newvBox.setAlignment(Pos.CENTER);
        newvBox.setSpacing(10);

        newhbox.getChildren().addAll(signInbtnUp, signUpbtnUp);
        newhbox.setAlignment(Pos.CENTER);
        newhbox.setSpacing(10);

        newvBox.getChildren().add(newhbox);
        newpane.getChildren().add(newvBox);

        signUpScene = new Scene(newpane, 400, 350);

        signInbtnUp.setOnAction(e -> {
            if (true)//userField.getText() != null
            {
                primaryStage.setScene(signInScene);
            }
        });

        signUpbtnUp.setOnAction(e -> {
            client.sendMSG("signUp");
            client.sendMSG(userFieldUp.getText());
            client.sendMSG(passFieldUp.getText());
            client.sendMSG(emailFieldUp.getText());
            client.sendMSG(firstnameFieldUp.getText());
            client.sendMSG(lastnameFieldUp.getText());
            client.sendMSG(fileDirectory.getText());

            primaryStage.setScene(chatRoomScene);
            recieveMsgThread.start();
        });
    }

    public void setChatRoomScene(Stage primaryStage)
    {
        VBox chatPane = new VBox();
        chatPane.setSpacing(10);
        chatVBox = new VBox();

        sendBox = new HBox();
        HBox recipienthBox = new HBox();

        recipienthBox.getChildren().addAll(recipientTextBox, startChatbtn);
        recipienthBox.setSpacing(10);

        sendBox.getChildren().addAll(sendTextBox, sendButton, logOutbtn, uploadbtn);
        sendBox.setSpacing(10);

        chatPane.getChildren().addAll(recipienthBox, chatArea, sendBox);

        startChatbtn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        sendButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        logOutbtn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        uploadbtn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        chatRoomScene = new Scene(chatPane, 500, 350);
/*
        startChatbtn.setOnAction(e ->{
            try {
                System.out.println("AAAAAAAAAA");

                chatArea.setText(client.getHistory(recipientTextBox.getText()));
                System.out.println("AAAAAAAAAA");

            }
            catch (IOException ea)
            {

            }
            catch (InterruptedException is)
            {

            }
        });
*/
        sendButton.setOnAction(e ->{
            if (sendTextBox.getText() != null)
            {
                DateFormat df = new SimpleDateFormat("HH:mm");
                Date date = new Date();
                client.sendMSG(recipientTextBox.getText());
                client.sendMSG(sendTextBox.getText() +
                        " " + df.format(date));
                chatArea.appendText("you : " + sendTextBox.getText() +
                        " " + df.format(date) + "\n");
                sendTextBox.setText("");
            }
        });

        uploadbtn.setOnAction(e ->{
            client.sendMSG("upload");
            client.sendMSG(chatArea.getText());
            client.sendMSG(recipientTextBox.getText());
        });

        logOutbtn.setOnAction(e ->{
            if (sendTextBox.getText() != null)
            {
                client.sendMSG("logout");
                client.sendMSG("");
            }
        });
    }


    Thread recieveMsgThread = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
            while (true)
            {
                client.readMSG();
                if (!client.getRecievedMSG().equals(""))
                {
                    chatArea.appendText("other : " + client.recievedMSG + "\n");
                    client.recievedMSG = "";
                }
            }
        }
    });

    public static void main(String[] args) {
        launch(args);
    }
}
