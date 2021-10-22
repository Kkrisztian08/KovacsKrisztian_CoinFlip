package com.example.kovacskrisztian_coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView kep;
    private TextView dobasok, gyozelem, vereseg;
    private Button fejGomb, irasGomb;
    private int dobasokSzama, gyozelmekSzama, veresegekSzama, tipp, gepTipp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        fejGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp=0;
                eredmeny(tipp);
            }
        });

        irasGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipp=1;
                eredmeny(tipp);
            }
        });
    }

    private void eredmeny(int tipp){
        dobasokSzama++;
        dobasok.setText("Dobasok: "+dobasokSzama);
        gepTipp =(int)(Math.random()*2);
        if (gepTipp == 0) {
            kep.setImageResource(R.drawable.heads);
            Toast.makeText(MainActivity.this, "FEJ", Toast.LENGTH_SHORT).show();
        }else{
            kep.setImageResource(R.drawable.tails);
            Toast.makeText(MainActivity.this, "ÍRAS", Toast.LENGTH_SHORT).show();
        }

        if (tipp == gepTipp) {
            gyozelmekSzama++;
            gyozelem.setText("Győzelem: "+gyozelmekSzama);
        }else{
            veresegekSzama++;
            vereseg.setText("Vereség: "+veresegekSzama);
        }

        if (dobasokSzama==5 || gyozelmekSzama>=3 || veresegekSzama>=3) {
            jatekVege();
        }

    }

    private void jatekVege(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if (gyozelmekSzama > veresegekSzama) {
            builder.setTitle("Győzelem");
        }else {
            builder.setTitle("Vereség");
        }
        builder.setMessage("Szeretne új játékot játszani?");
        builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        });
        builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void ujJatek(){
        dobasokSzama=0;
        gyozelmekSzama=0;
        veresegekSzama=0;
        dobasok.setText("Dobások: 0");
        gyozelem.setText("Győzelem: 0");
        vereseg.setText("Vereség: 0");
        kep.setImageResource(R.drawable.heads);
    }

    private void init(){
        dobasokSzama=0;
        gyozelmekSzama=0;
        veresegekSzama=0;
        kep=findViewById(R.id.img_fej);
        dobasok=findViewById(R.id.textView_dobasok);
        gyozelem=findViewById(R.id.textView_gyozelem);
        vereseg=findViewById(R.id.textView_vereseg);
        fejGomb=findViewById(R.id.btn_fej);
        irasGomb=findViewById(R.id.btn_iras);

    }
}