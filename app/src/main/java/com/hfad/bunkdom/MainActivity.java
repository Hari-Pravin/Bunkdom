package com.hfad.bunkdom;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText p,t;
TextView b;
Button c;
ActionBar ab;
Toast toast;
String msg;
AlertDialog.Builder ald_b;
AlertDialog ald;
SharedPreferences sp;
SharedPreferences.Editor sp_e;
int pr,tot,bu;
String s_pre,s_tot;
float perc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = findViewById(R.id.present);
        t = findViewById(R.id.total);
        b = findViewById(R.id.bunkable);
        c = findViewById(R.id.compute);


        ab = getSupportActionBar();

        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        sp = getPreferences(0);
        sp_e = sp.edit();

        if(sp.getBoolean("alert",true))
        alert();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        alert();
        return true;
    }

    void alert()
    {

        ald_b = new AlertDialog.Builder(this);
        ald_b.setTitle("How Bunkdom works ?");
        ald_b.setMessage("_____________________________\n\n1.Enter the number of present hours( including ODs).\n\n2.Enter the number of total hours.\n\n3.Hit compute.\n\n4.You'll get the number of hours that can be bunked without making the attendance drop below 75%.\n\nald_b.setMessage(\n_____________________________\n\nFor any app issues :\n\nhari.16ee@kct.ac.in\n\n\n\t\t\t\t\t\tHappy Bunking !!!");
        ald_b.setPositiveButton("OK",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {

                    }
                }
        );
        ald = ald_b.create();
        ald.show();

        sp_e.putBoolean("alert",false);
        sp_e.commit();

    }

    public void compute(View view)
    {
        s_pre = String.valueOf(p.getText());
        s_tot = String.valueOf(t.getText());


        if(!(s_pre.isEmpty() && s_tot.isEmpty()))
        {
            pr = Integer.valueOf(s_pre);
            tot = Integer.valueOf(s_tot);

            if(pr <= tot)
            {
                bu = 0;

                perc = (float)pr/tot;

                while(perc>=0.75)
                {
                    perc = (float)pr/++tot;
                    ++bu  ;
                }

                b.setText(String.valueOf(bu));
            }
            else
            {
                msg = "Enter valid values";
                toast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            msg = "Enter all fields";
            toast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}
