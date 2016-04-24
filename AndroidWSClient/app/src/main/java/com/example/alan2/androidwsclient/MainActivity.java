package com.example.alan2.androidwsclient;


import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    private String TAG ="Vik",resultado="";
    ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(MainActivity.this,"Descargar",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallWSSumar task = new AsyncCallWSSumar();
                task.execute();
            }
        });
    }

    private class AsyncCallWSSumar extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            sumar();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

            EditText et=(EditText)findViewById(R.id.editText3);
            et.setText(resultado + "");
            progDailog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            super.onPreExecute();
            progDailog= new ProgressDialog(MainActivity.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

    }
    public void sumar(){
        String NAMESPACE = "http://servicios/";
        String URL = "http://192.168.153.1:8080/ws_glash/ws1?wsdl";
        String METHOD_NAME = "operation";
        String SOAP_ACTION = "http://servicios/operation";



        try
        {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            EditText txt1=(EditText)findViewById(R.id.editText);
            EditText txt2=(EditText)findViewById(R.id.editText2);
            int uno=Integer.parseInt(txt1.getText().toString());
            int dos=Integer.parseInt(txt2.getText().toString());
            request.addProperty("parameter", uno);
            request.addProperty("parameter1", dos);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            //envelope.dotNet = true;

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);

            SoapPrimitive result=(SoapPrimitive) envelope.getResponse();
            resultado=result.toString();
            //Toast.makeText(MainActivity.this,result.toString(),Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
}
