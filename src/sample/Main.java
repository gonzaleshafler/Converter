package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigInteger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Main main = new Main();

        main.run();
        launch(args);
    }

    private void run() {
        Converter converter=new Converter();
              double val=101.2522;
              String s="1100101.0100000010010000001011011110000000001101000111";
              String h="65.40902de00d1b71758e219652bd3c36113404ea4a8c154c985f06f694467381d7dbf487fcb923a29c779a6b50b0f27bb2fec5";
              String o="145.2011005570006433342726161031312257236066042320047244521405246230276033664504316340353733751037745622";
              System.out.println("Decimal val= "+val+
                      "\nBinary="+converter.toBinary(val,100)+
                      "\nHex="+converter.toHeximal(val,100)+
                      "\nTo Octal="+converter.toOctal(val,100)+"" +
                      "\nVal binary to decimal="+converter.binaryToDecimal(s,s.length())+
                      "\nVal hex to decimal="+converter.hexToDecimal(h,h.length())+
                      "\nVal octal to decimal="+converter.octalToDecimal(o,o.length()));
    }



}
