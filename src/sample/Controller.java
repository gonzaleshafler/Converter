package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML public ComboBox comboBoxFrom;
    @FXML public ComboBox comboBoxTo;
    @FXML public TextField textFieldFrom;
    @FXML private TextField textFielTo;
    @FXML public Button calculateBtn;
    Alert a=new Alert(Alert.AlertType.ERROR);
    private Converter converter=new Converter();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBoxFrom.getItems().addAll("2", "8", "10","16");
        comboBoxTo.getItems().addAll("2", "8", "10","16");
        comboBoxFrom.getSelectionModel().select("10");
        converter.stateFrom=1;
        comboBoxTo.getSelectionModel().select("2");
        converter.stateTo=2;
        comboBoxWasUpdated();

    }
    public  void comboBoxWasUpdated()
    {
        if (comboBoxFrom.getValue().equals("10"))
        {
            converter.stateFrom=1;
        }
        else if (comboBoxFrom.getValue().equals("2"))
        {
            converter.stateFrom=2;
        }
        else if (comboBoxFrom.getValue().equals("8"))
        {
            converter.stateFrom=3;
        }
        else if (comboBoxFrom.getValue().equals("16"))
        {
            converter.stateFrom=4;
        }
    }
    public  void comboBoxToWasUpdated()
    {
        if (comboBoxTo.getValue().equals("10"))
        {
            converter.stateTo=1;
        }
        else if (comboBoxTo.getValue().equals("2"))
        {
            converter.stateTo=2;
        }
        else if (comboBoxTo.getValue().equals("8"))
        {
            converter.stateTo=3;
        }
        else if (comboBoxTo.getValue().equals("16"))
        {
            converter.stateTo=4;
        }
    }
    public  void  calculateButtonClick()
    {
        double temp;
        if (converter.stateFrom==1)
        {
            try {
                if (converter.stateTo == 1) {
                    a.setContentText("Warning!\n Translation in the same number system!");
                    a.show();
                } else if (converter.stateTo == 2) {
                    textFielTo.setText(converter.toBinary(Double.parseDouble(textFieldFrom.getText()), 100));
                } else if (converter.stateTo == 3) {
                    textFielTo.setText(converter.toOctal(Double.parseDouble(textFieldFrom.getText()), 100));
                } else if (converter.stateTo == 4) {
                    textFielTo.setText(converter.toHeximal(Double.parseDouble(textFieldFrom.getText()), 100));
                } else {
                    a.setContentText("Error\nSomething went wrong, check the input is correct.");
                    a.show();
                }
            }
            catch (Exception exception) {
                a.setContentText("Error\nSomething went wrong, check the input is correct.\n"+exception.toString());
                a.show();
            }
        }
        else if (converter.stateFrom==2)
        {
            try {
                if (converter.stateTo==1)
                {
                    String s=converter.binaryToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length());
                    if (s!=null)
                    {
                        textFielTo.setText(s);
                    }
                    else
                    {
                        a.setContentText("Error\nSomething went wrong, check the input is correct.");
                        a.show();
                    }
                }
                else if (converter.stateTo==2)
                {
                    a.setContentText("Warning!\n Translation in the same number system!");
                    a.show();
                }
                else if (converter.stateTo==3)
                {
                    temp=Double.parseDouble(converter.binaryToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toOctal(temp,100));
                }
                else if (converter.stateTo==4)
                {
                    temp=Double.parseDouble(converter.binaryToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toHeximal(temp,100));
                }
                else
                {
                    a.setContentText("Error\nSomething went wrong, check the input is correct.");
                    a.show();
                }
            }
            catch (Exception exception)
            {
                a.setContentText("Error\nSomething went wrong, check the input is correct.\n"+exception.toString());
                a.show();
            }

        }
        else if (converter.stateFrom==3)
        {
            try {
                if (converter.stateTo==1)
                {
                    textFielTo.setText(converter.octalToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                }
                else if (converter.stateTo==2)
                {
                    temp=Double.parseDouble(converter.octalToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toBinary(temp,100));
                }
                else if (converter.stateTo==3)
                {
                    a.setContentText("Warning!\n Translation in the same number system!.");
                    a.show();
                }
                else if (converter.stateTo==4)
                {
                    temp=Double.parseDouble(converter.octalToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toHeximal(temp,100));
                }
                else
                {
                    a.setContentText("Error\nSomething went wrong, check the input is correct.");
                    a.show();
                }
            }
            catch (Exception exception)
            {
                a.setContentText("Error\nSomething went wrong, check the input is correct.\n"+exception.toString());
                a.show();
            }
        }
        else if (converter.stateFrom==4)
        {
            try {
                if (converter.stateTo==1)
                {
                    textFielTo.setText(converter.hexToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                }
                else if (converter.stateTo==2)
                {
                    temp=Double.parseDouble(converter.hexToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toBinary(temp,100));
                }
                else if (converter.stateTo==3)
                {
                    temp=Double.parseDouble(converter.hexToDecimal(textFieldFrom.getText(),textFieldFrom.getText().length()));
                    textFielTo.setText(converter.toOctal(temp,100));
                }
                else if (converter.stateTo==4)
                {
                    a.setContentText("Warning!\n Translation in the same number system!.");
                    a.show();
                }
                else
                {
                    a.setContentText("Error\nSomething went wrong, check the input is correct.");
                    a.show();
                }
            }
            catch (Exception exception)
            {
                a.setContentText("Error\nSomething went wrong, check the input is correct.\n"+exception.toString());
                a.show();
            }
        }
    }
}
