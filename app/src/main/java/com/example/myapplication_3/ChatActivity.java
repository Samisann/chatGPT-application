package com.example.myapplication_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.annotations.concurrent.Background;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class ChatActivity extends AppCompatActivity {
private ImageView imageGenere;
    private String userTextString;

 // fonction qui construit le header et qui envoie la requête avec le texte entré



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        Button sendingButton = findViewById(
                R.id.buttonEnvoyer
        );

        //on trouve l'image
        imageGenere = findViewById(R.id.imageCree);

        // A l'appui du bouton cela envoi et reçoit l'image qui sera affiché dans l'image view
        sendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userText = findViewById(R.id.editTextUser);
                userTextString = userText.getText().toString();
                /*backgroundTask task = new backgroundTask(userTextString);
                task.execute();*/

            }
        });




    }

}