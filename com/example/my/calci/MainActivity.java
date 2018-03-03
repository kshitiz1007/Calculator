package com.example.my.calci;
import com.udojava.evalex.Expression;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {
    TextView angleunit,typeCode,ans,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv0,tvdot,tvequal,tvmul,tvdiv,tvadd,tvsub,tvdel,
            tvsin,tvcos,tvtan,tvasin,tvacos,tvatan,tvln,tvlog,tvfact,tvpi,tvexp,tvpow,tvopen,tvclose,tvmod;
    String stat="";

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angleunit=(TextView)findViewById(R.id.textView1);
        typeCode=(TextView)findViewById(R.id.textView2);
        ans=(TextView)findViewById(R.id.textView3);
        tv1=(TextView)findViewById(R.id.textView6);
        tv2=(TextView)findViewById(R.id.textView10);
        tv3=(TextView)findViewById(R.id.textView14);
        tv4=(TextView)findViewById(R.id.textView5);
        tv5=(TextView)findViewById(R.id.textView9);
        tv6=(TextView)findViewById(R.id.textView13);
        tv7=(TextView)findViewById(R.id.textView4);
        tv8=(TextView)findViewById(R.id.textView8);
        tv9=(TextView)findViewById(R.id.textView12);
        tvdot=(TextView)findViewById(R.id.textView7);
        tv0=(TextView)findViewById(R.id.textView11);
        tvequal=(TextView)findViewById(R.id.textView15);
        tvdel=(TextView)findViewById(R.id.textView16);
        tvmul=(TextView)findViewById(R.id.textView18);
        tvdiv=(TextView)findViewById(R.id.textView17);
        tvadd=(TextView)findViewById(R.id.textView20);
        tvsub=(TextView)findViewById(R.id.textView19);

        NavigationView navigationView = (NavigationView) findViewById(R.id.design_navigation_view);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.header);
        tvsin=(TextView)header.findViewById(R.id.textView21);
        tvcos=(TextView)header.findViewById(R.id.textView22);
        tvtan=(TextView)header.findViewById(R.id.textView23);
        tvasin=(TextView)header.findViewById(R.id.textView24);
        tvacos=(TextView)header.findViewById(R.id.textView25);
        tvtan=(TextView)header.findViewById(R.id.textView26);
        tvln=(TextView)header.findViewById(R.id.textView27);
        tvlog=(TextView)header.findViewById(R.id.textView28);
        tvfact=(TextView)header.findViewById(R.id.textView29);
        tvpi=(TextView)header.findViewById(R.id.textView30);
        tvexp=(TextView)header.findViewById(R.id.textView31);
        tvpow=(TextView)header.findViewById(R.id.textView32);
        tvopen=(TextView)header.findViewById(R.id.textView33);
        tvclose=(TextView)header.findViewById(R.id.textView34);
        tvmod=(TextView)header.findViewById(R.id.textView35);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Really Exit ??");
        builder.setTitle("Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok",new MyListener());
        builder.setNegativeButton("Cancel",null);
        builder.show();

    }
    public class MyListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }
    public void navigationFn(View v){
        int len=stat.length();
        if(len==13)
            return ;
        TextView tv=(TextView)v;
        if(tv==tvsin||tv==tvcos||tv==tvtan||tv==tvasin||tv==tvacos||tv==tvatan){
            stat=stat+tv.getText().toString().trim()+"(";
        }
        if(tv==tvopen||tv==tvclose){
            stat=stat+tv.getText().toString().trim();
        }
        if(tv==tvln){
            stat=stat+"log(";
        }
        if(tv==tvlog){
            stat=stat+"log10(";
        }
        if(tv==tvfact||tv==tvexp||tv==tvpow||tv==tvmod){
            stat=stat+tv.getText().toString().trim();
        }
        if(tv==tvpi){
            stat=stat+"PI";
        }

        typeCode.setText(stat);
        try{
            Expression e=new Expression(stat);
            BigDecimal d=e.eval();
            ans.setText(d.toString());
        }catch(Exception excep){ }

    }
    public void fn(View v){

        TextView tvs=(TextView)v;
        int len=stat.length();
        if(len==13&&(tvs!=tvdel&&tvs!=tvequal))
            return ;
        if(tvs==tvdel){
            if(len==1){
                stat="";
                ans.setText("");
                typeCode.setText("");
                return ;
            }

            int ch=stat.charAt(len - 1);
            if((ch>=97&&ch<=122)||(ch>=65&&ch<=90)){
                int count=0;
                for(int i=len-1;i>=0;i--){
                    int ch1=stat.charAt(i);
                    if((ch1>=97&&ch1<=122)||(ch1>=65&&ch1<=90))
                        count++;
                    else
                        break;
                }
                stat=stat.substring(0,len-count);
            }
            else
                stat=stat.substring(0,len-1);
        }
        if(tvs==tvequal){
            //String str=;
            try{
                Expression e=new Expression(stat);
                BigDecimal d=e.eval();
                typeCode.setText(ans.getText().toString());
                ans.setText("");
                stat="";
            }catch(Exception excep){
                ans.setText("Bad Expression");
            }


            return ;
        }
        if(tvs!=tvdel&&tvs!=tvequal)
            stat=(stat+((TextView)v).getText()).trim();

        typeCode.setText(stat);
        try{
            Expression e=new Expression(stat);
            BigDecimal d=e.eval();
            ans.setText(d.toString());
        }catch(Exception excep){ }

    }
}
