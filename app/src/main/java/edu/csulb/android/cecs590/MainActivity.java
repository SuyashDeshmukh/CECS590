package edu.csulb.android.cecs590;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button placeorder;
    private RadioButton large;
    private RadioButton medium;
    private RadioButton corn;
    private RadioButton flour;
    private CheckBox Beef ;
    private CheckBox Chicken;
    private CheckBox WhiteFish;
    private CheckBox Cheese;
    private CheckBox Seafood;
    private CheckBox Rice;
    private CheckBox Beans;
    private CheckBox Picodegallo;
    private CheckBox Guacamole;
    private CheckBox LBT;
    private CheckBox Soda;
    private CheckBox Cerveza;
    private CheckBox Margarita;
    private CheckBox Tequila;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         medium=(RadioButton)findViewById(R.id.Medium);
         large = (RadioButton)findViewById(R.id.Large);
         corn=(RadioButton)findViewById(R.id.Corn);
         flour=(RadioButton)findViewById(R.id.Flour);
         Beef = (CheckBox)findViewById(R.id.Beef);
         Chicken= (CheckBox)findViewById(R.id.Chicken);
         WhiteFish= (CheckBox)findViewById(R.id.WhiteFish);
         Cheese= (CheckBox)findViewById(R.id.Cheese);
         Seafood= (CheckBox)findViewById(R.id.SeaFood);
         Rice= (CheckBox)findViewById(R.id.Rice);
         Beans= (CheckBox)findViewById(R.id.Beans);
         Picodegallo= (CheckBox)findViewById(R.id.PicodeGallo);
         Guacamole= (CheckBox)findViewById(R.id.Guacamole);
         LBT= (CheckBox)findViewById(R.id.LBT);
         Soda= (CheckBox)findViewById(R.id.Soda);
         Cerveza= (CheckBox)findViewById(R.id.Cerveza);
         Margarita= (CheckBox)findViewById(R.id.Margarita);
         Tequila= (CheckBox)findViewById(R.id.Tequila);

        placeorder=(Button)findViewById(R.id.Placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked();
            }
        });

    }

    private void clicked() {
        String message="Taco Pronto Order Details:\n";
        int cost=0;
        if(large.isChecked())
        {
            message=message+"Large";
            cost+=5;
        }
        else {
            message=message+"Medium";
            cost+=3;
        }
        if(corn.isChecked())
        {
            message=message+" Corn Taco ";
            cost+=2;
        }
        else
        {
            message=message+" Flour Taco";
            cost+=2;
        }
        message=message+" with ";
        if(Beef.isChecked())
        {
            message=message+" Beef";
            cost+=1;
        }
        if(Chicken.isChecked())
        {
            message=message+" Chicken";
            cost+=1;
        }
        if(WhiteFish.isChecked())
        {
            message=message+" White Fish";
            cost+=1;
        }
        if(Cheese.isChecked())
        {
            message=message+" Cheese";
            cost+=1;
        }
        if(Seafood.isChecked())
        {
            message=message+" Sea Food";
            cost+=1;
        }
        if(Rice.isChecked())
        {
            message=message+" Rice";
            cost+=1;
        }
        if(Beans.isChecked())
        {
            message=message+" Beans";
            cost+=1;
        }
        if(Picodegallo.isChecked())
        {
            message=message+" Pico de Gallo";
            cost+=1;
        }
        if(Guacamole.isChecked())
        {
            message=message+" Guacamole";
            cost+=1;
        }
        if(LBT.isChecked())
        {
            message=message+" LBT";
            cost+=1;
        }

        message = message+" With Beverages:";
        if(Cerveza.isChecked())
        {
            message=message+" Cerveza";
            cost+=3;
        }
        if(Soda.isChecked())
        {
            message=message+" Soda";
            cost+=3;
        }
        if(Margarita.isChecked())
        {
            message=message+" Margarita";
            cost+=3;
        }
        if(Tequila.isChecked())
        {
            message=message+" Tequila";
            cost+=5;
        }
        message=message+"\nTotal Cost: $"+cost;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage("+15628507043",null,message,null,null);
            Toast.makeText(MainActivity.this,"Order placed!",Toast.LENGTH_SHORT).show();
        }

    reset();

    }

    private void reset() {
        Beef.setChecked(false);
        Chicken.setChecked(false);
        WhiteFish.setChecked(false);
        Seafood.setChecked(false);
        Cheese.setChecked(false);
        Beans.setChecked(false);
        Rice.setChecked(false);
        Picodegallo.setChecked(false);
        Guacamole.setChecked(false);
        LBT.setChecked(false);
        Soda.setChecked(false);
        Cerveza.setChecked(false);
        Margarita.setChecked(false);
        Tequila.setChecked(false);
        medium.setChecked(true);
        flour.setChecked(true);
    }



}
