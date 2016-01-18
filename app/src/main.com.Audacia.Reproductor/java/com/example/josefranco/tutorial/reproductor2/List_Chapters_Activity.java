package com.example.josefranco.tutorial.reproductor2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List_Chapters_Activity extends AppCompatActivity {

    private ListView listview;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__chapters_);

        listview=(ListView) findViewById(R.id.listView);
        View header = (View) getLayoutInflater().inflate(R.layout.encabezado_listview,null);
        listview.addHeaderView(header);
        arrayAdapter=new BookAdapter(this);
        listview.setAdapter(arrayAdapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Capitulo " + position, Toast.LENGTH_SHORT).show();
                //String url=listview2.getItemAtPosition(position).toString();
                //TextView tv = (TextView)findViewById(R.id.urlTV);
                String url ="http://educacionweb.mx/Audacia/Viaje/Viaje%20al%20centro%20de%20la%20tierra_capitulo%201.mp3";
                //String url= tv.getText().toString();
                //Intent in = getIntent();
                //Bundle extra = in.getExtras();
                //if(extra!=null){
                 //   url = extra.getString("URLACTUAL");
                //}
                //String url = tv.getText().toString();
                Boolean reproduce = true;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("URL",url);
                intent.putExtra("Reproduce",reproduce);
                startActivity(intent);

                finish();
            }
        });

    }

}
