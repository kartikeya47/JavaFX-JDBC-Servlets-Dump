import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.sql.*;

public class SchoolForm extends Application {
    public void connect(int sno, int age, String gender, String stream) {
        try {
            String url = "jdbc:mysql://localhost:3306/db1";
            String user = "root";
            String pass = "admin";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String query = "insert into school values(" + sno + "," + age + "," + "'" + gender + "'" + "," + "'"
                    + stream + "'" + ")";
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(Stage stage) {
        stage.setTitle("School Form");
        TilePane tile = new TilePane();
        Label l1 = new Label("Sno: ");
        Label l2 = new Label("Age: ");
        Label l3 = new Label("Stream: ");
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        Label ll1 = new Label();
        Label ll2 = new Label();
        Label ll3 = new Label();
        Label ll4 = new Label();
        Label radio = new Label("Gender");
        RadioButton rd1 = new RadioButton("Male");
        RadioButton rd2 = new RadioButton("Female");
        ToggleGroup tg = new ToggleGroup();
        rd1.setToggleGroup(tg);
        rd2.setToggleGroup(tg);
        Button b = new Button("Submit");
        tile.getChildren().addAll(l1, t1, l2, t2, l3, t3, radio, rd1, rd2, b, ll1, ll2, ll3, ll4);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String s1 = t1.getText();
                String s2 = t2.getText();
                String s3 = t3.getText();
                String s4 = rd1.getText();
                String s5 = rd2.getText();
                ll1.setText(s1);
                ll2.setText(s2);
                ll3.setText(s3);
                if (rd1.isSelected()) {
                    ll4.setText(s4);
                }
                if (rd2.isSelected()) {
                    ll4.setText(s5);
                }

                connect(Integer.parseInt(s1), Integer.parseInt(s2), s4, s3);
            }
        };

        b.setOnAction(event);

        Scene scene = new Scene(tile, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}