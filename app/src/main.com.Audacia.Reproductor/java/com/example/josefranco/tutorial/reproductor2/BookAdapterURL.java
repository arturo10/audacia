package com.example.josefranco.tutorial.reproductor2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseFranco on 14/01/2016.
 */
public class BookAdapterURL extends ArrayAdapter{

    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "http://69.167.137.161:3000";
    private static final String URL_JSON = "/api/books";
    private static final String TAG = "PostAdapter";
    List<Fragment> fragments;

    public BookAdapterURL(Context context) {
        super(context,0);

        // Crear nueva cola de peticiones
        requestQueue= Volley.newRequestQueue(context);

        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest( URL_BASE + URL_JSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        fragments = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //View listItemView;
        View tempView=null;

        //Comprobando si el View no existe
        //listItemView = null == convertView ? layoutInflater.inflate(R.layout.chapter_item, parent, false) : convertView;
        tempView = null == convertView ? layoutInflater.inflate(R.layout.url, parent, false) : convertView;

        // Obtener el item actual
        Fragment item = fragments.get(position);

        // Obtener Views
        //TextView textoTitulo = (TextView) listItemView.findViewById(R.id.chapterTitle);
        TextView urlCapitulo = (TextView) tempView.findViewById(R.id.urlTV);


        // Actualizar los Views
        //textoTitulo.setText(item.getTitle());
        urlCapitulo.setText(item.getUrlAudio());
        //Intent in = new Intent(getContext(),null) ;
        //in.putExtra("URLACTUAL",item.getUrlAudio());


       /* // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                URL_BASE + item.getImagen(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.error);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });*/

        // Añadir petición a la cola
     /*   requestQueue.add(request);*/


        return tempView;
    }

    public List<Fragment> parseJson(JSONObject jsonObject){
        // Variables locales
        List<Fragment> fragmentsList = new ArrayList();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("data");

            JSONObject temporal=jsonArray.getJSONObject(1);
            jsonArray=temporal.getJSONArray("fragments");



            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject rawFragment= jsonArray.getJSONObject(i);

                    Fragment fragment = new Fragment(
                            rawFragment.getString("title"),
                            rawFragment.getInt("duration"),
                            rawFragment.getString("URL"));


                    fragmentsList.add(fragment);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return fragmentsList;
    }
}


