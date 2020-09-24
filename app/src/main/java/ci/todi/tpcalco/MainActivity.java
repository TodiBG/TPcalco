package ci.todi.tpcalco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b0 = null;
    Button b1 = null;
    Button b2 = null;
    Button b3 = null;
    Button b4 = null;
    Button b5 = null;
    Button b6 = null;
    Button b7 = null;
    Button b8 = null;
    Button b9 = null;
    Button bc = null;
    Button bdel = null;
    Button bmod = null;
    Button bdiv = null;
    Button bnegatif = null;
    Button bsoustr = null;
    Button bvirgule = null;
    Button bmult = null;
    Button begal = null;
    Button badd = null;
    TextView display = null ;

    String firstTerm = "";
    String secondTerm = "";
    String writtingTerm = "" ;
    String opera = "" ;
    String result = "" ;
    boolean firstTermValided = false ;
    boolean secondTermValided = false ;
    boolean writtingTermNegatif = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if(savedInstanceState != null ){
            firstTerm =  savedInstanceState.getString("firstTerm") ;
            writtingTerm = savedInstanceState.getString("writtingTerm") ;
            opera = savedInstanceState.getString("opera");
            secondTerm = savedInstanceState.getString("secondTerm") ;

            result = savedInstanceState.getString("result") ;
            firstTermValided  = savedInstanceState.getBoolean("firstTermValided");
            secondTermValided = savedInstanceState.getBoolean("secondTermValided");
            writtingTermNegatif = savedInstanceState.getBoolean("writtingTermNegatif");

            display = (TextView) findViewById(R.id.display);
            display.setText( savedInstanceState.getString("displayContent")  );
        }


        b0 = (Button)findViewById(R.id.b0);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);
        b8 = (Button)findViewById(R.id.b8);
        b9 = (Button)findViewById(R.id.b9);
        bc = (Button)findViewById(R.id.bc);
        bdel = (Button)findViewById(R.id.bdel);
        bmod = (Button)findViewById(R.id.bmod);
        bdiv = (Button)findViewById(R.id.bdiv);
        bmult = (Button)findViewById(R.id.bmult);
        bnegatif = (Button)findViewById(R.id.negatif);
        bsoustr = (Button)findViewById(R.id.bsoustr);
        badd = (Button)findViewById(R.id.badd);
        bvirgule = (Button)findViewById(R.id.bvirgule);
        begal = (Button)findViewById(R.id.begal);
        display = (TextView) findViewById(R.id.display);

        b0.setOnClickListener(addDigit);
        b1.setOnClickListener(addDigit);
        b2.setOnClickListener(addDigit);
        b3.setOnClickListener(addDigit);
        b4.setOnClickListener(addDigit);
        b5.setOnClickListener(addDigit);
        b6.setOnClickListener(addDigit);
        b7.setOnClickListener(addDigit);
        b8.setOnClickListener(addDigit);
        b9.setOnClickListener(addDigit);
        bvirgule.setOnClickListener(addDigit);

        badd.setOnClickListener(addOperation);
        bsoustr.setOnClickListener(addOperation);
        bdiv.setOnClickListener(addOperation);
        bmult.setOnClickListener(addOperation);
        bmod.setOnClickListener(addOperation);

        begal.setOnClickListener(makeResult);
        bdel.setOnClickListener(goBack);
        bc.setOnClickListener(resetAll);
        bnegatif.setOnClickListener(setNegative);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {

        savedInstanceState.putString("writtingTerm", writtingTerm);
        savedInstanceState.putString("firstTerm", firstTerm);
        savedInstanceState.putString("secondTerm", secondTerm);
        savedInstanceState.putString("opera", opera);
        savedInstanceState.putString("result", result);
        savedInstanceState.putBoolean ("firstTermValided", firstTermValided);
        savedInstanceState.putBoolean ("secondTermValided", secondTermValided);
        savedInstanceState.putBoolean ("writtingTermNegatif", writtingTermNegatif);
        savedInstanceState.putString ("displayContent",display.getText().toString() );


        super.onSaveInstanceState(savedInstanceState);

    }



    private View.OnClickListener addDigit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String chif = "" ;

            if (firstTermValided  ){
                if (writtingTermNegatif){ writtingTerm = "-";}
                else{writtingTerm = "";}

                firstTermValided = false ;
            }
            if (secondTermValided  ){
                writtingTerm = "";
                firstTerm = "" ;
                opera = "" ;
                secondTermValided = false ;
            }

            switch (v.getId()){
                case R.id.b0:
                    chif = "0" ;
                break;

                case R.id.b1:
                    chif = "1" ;
                break;
                case R.id.b2:
                    chif = "2" ;
                break;
                case R.id.b3:
                    chif = "3" ;
                break;
                case R.id.b4:
                    chif = "4" ;
                break;
                case R.id.b5:
                    chif = "5" ;
                break;
                case R.id.b6:
                    chif = "6" ;
                break;
                case R.id.b7:
                    chif = "7" ;
                break;
                case R.id.b8:
                    chif = "8" ;
                break;
                case R.id.b9:
                    chif = "9" ;
                break;
                case R.id.bvirgule:
                    if((writtingTerm.indexOf(".") == -1) && (! (writtingTerm.equals("")) ) ){
                        chif = "." ;
                    }
                break;
            }

            writtingTerm = writtingTerm+chif ;
            display() ;
        }
    };


    private void display(){
        display.setText(firstTerm+opera+ writtingTerm);
    }

    private View.OnClickListener addOperation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(secondTermValided){
                secondTermValided = false ;
                firstTermValided = false ;
            }else{
                allowCalcu() ;
                secondTermValided = false ;
            }

            if(firstTerm.equals("")&& writtingTerm.equals("") ){
                opera = "" ;
            }
            else{
                switch (v.getId()){
                case R.id.bsoustr:
                    opera = "-" ;
                    break;
                case R.id.badd:
                    opera = "+" ;
                    break;
                case R.id.bdiv:
                    opera = "/" ;
                    break;
                case R.id.bmult:
                    opera = "x" ;
                break;
                case R.id.bmod:
                    opera = "%" ;
                break;
            }

            if(!writtingTerm.equals("") ){
                firstTerm = writtingTerm ;
            }
                firstTermValided = true ;
                display.setText(firstTerm+opera);
            }
        }
    };


    private  void calculate (){
        String mod = "" ;
        double res = 0 ;
        double first = Double.parseDouble(firstTerm)  ;
        double second = Double.parseDouble(secondTerm) ;
       boolean operaError = false ;

        if(opera.equals("-")){  res =  first-second ;  }
        else if(opera.equals("+")){ res = first+second ;}
        else if(opera.equals("x")){ res = first*second ; }
        else if(opera.equals("/")){
            if( second != 0){ res = first/second ; }
            else{
                operaError  = true ;
                 Toast.makeText(MainActivity.this, "ERREUR: opération impossible", Toast.LENGTH_SHORT).show();
            }
        }
        else if(opera.equals("%")){
            if( second != 0){
                res = first/second ;
                res = Math.floor(res);
                res = first- (res*second);
                mod = ""+res ;
            }
            else{
                operaError  = true ;
                Toast.makeText(MainActivity.this, "ERREUR: opération impossible", Toast.LENGTH_SHORT).show();
            }
        }

        if(!opera.equals("")) {
            if (!operaError) {
                if ((mod.equals(""))) {
                    res = Math.round(res * 100.0) / 100.0;
                }

                display.setText("" + res);

                result = "" + res;
                secondTermValided = true;
                writtingTerm = result;
                opera = "";
            }
        }
    }



    private View.OnClickListener makeResult = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            allowCalcu() ;
        }
    } ;

    private void allowCalcu(){
        secondTerm = writtingTerm ;
        if( (!firstTerm.equals(""))&& (!opera.equals(""))&&(!secondTerm.equals("")) ){
            calculate();
        }
    }


    private View.OnClickListener setNegative = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( firstTermValided ){}
            else {
                writtingTerm = "-"+writtingTerm ;
            }

            display() ;

        }
    };


    private  View.OnClickListener goBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!secondTermValided) {

                int lastIndex = 0;
                if (writtingTerm.length() > 0) {
                    lastIndex = writtingTerm.length() - 1;
                    writtingTerm = writtingTerm.substring(0, lastIndex);
                } else if (opera.length() > 0) {
                    opera = "";
                } else if (firstTerm.length() > 0) {
                    lastIndex = firstTerm.length() - 1;
                    firstTerm = firstTerm.substring(0, lastIndex);
                }
            }else{
                firstTerm ="" ;
                opera = "" ;
                writtingTerm = "" ;
            }

            display();
        }
    } ;

    private View.OnClickListener resetAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firstTerm ="" ;
            opera = "" ;
            writtingTerm = "" ;
            display();
        }
    } ;



}