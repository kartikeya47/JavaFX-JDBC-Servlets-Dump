import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import java.sql.*;

public class CalculatorApp extends Application {

    public void connect(int x) {
        try {
            String url = "jdbc:mysql://localhost:3306/db1";
            String user_id = "root";
            String passwd = "admin";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user_id, passwd);
            Statement state = connection.createStatement();

            String query = "insert into answer values(" + x + ")";

            state.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(Stage s) {
        s.setTitle("Calculator App");
        TilePane tile = new TilePane();

        Label l1 = new Label("Number 1");
        Label l2 = new Label("Number 2");

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        Button b1 = new Button("+");
        Button b2 = new Button("-");
        Button b3 = new Button("x");
        Button b4 = new Button("/");

        Label l = new Label();
        Label ll = new Label();

        tile.getChildren().add(l1);
        tile.getChildren().add(t1);
        tile.getChildren().add(l2);
        tile.getChildren().add(t2);
        tile.getChildren().addAll(b1, b2, b3, b4);

        tile.getChildren().addAll(l, ll);

        RadioButton rd1 = new RadioButton("1st");
        RadioButton rd2 = new RadioButton("2nd");

        ToggleGroup tg = new ToggleGroup();

        rd1.setToggleGroup(tg);
        rd2.setToggleGroup(tg);

        tile.getChildren().addAll(rd1, rd2);

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String str1 = t1.getText();
                String str2 = t2.getText();

                int x = Integer.parseInt(str1) + Integer.parseInt(str2);

                connect(x);

                l.setText(Integer.toString(x));

                if (rd1.isSelected()) {
                    ll.setText("RD1 has been pressed!");
                }
                if (rd2.isSelected()) {
                    ll.setText("RD2 has been pressed!");
                }
            }
        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String str1 = t1.getText();
                String str2 = t2.getText();

                int x = Integer.parseInt(str1) - Integer.parseInt(str2);

                connect(x);

                l.setText(Integer.toString(x));
            }
        };
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String str1 = t1.getText();
                String str2 = t2.getText();

                int x = Integer.parseInt(str1) * Integer.parseInt(str2);

                connect(x);

                l.setText(Integer.toString(x));
            }
        };
        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String str1 = t1.getText();
                String str2 = t2.getText();

                int x = Integer.parseInt(str1) / Integer.parseInt(str2);

                connect(x);

                l.setText(Integer.toString(x));
            }
        };

        b1.setOnAction(event1);
        b2.setOnAction(event2);
        b3.setOnAction(event3);
        b4.setOnAction(event4);

        Scene scene = new Scene(tile, 400, 400);
        s.setScene(scene);
        s.show();
    }

    public static void main(String args[]) {
        launch();
    }

}