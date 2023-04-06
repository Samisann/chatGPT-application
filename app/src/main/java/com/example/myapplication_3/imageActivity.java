package com.example.myapplication_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import okhttp3.*;

public class imageActivity extends AppCompatActivity {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String API_URL = "https://api.openai.com/v1/images/generations";
    private static final String API_KEY = "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";

    private String userTextString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Button sendingButton = findViewById(
                R.id.buttonEnvoyer
        );


        // A l'appui du bouton cela envoi et reçoit l'image qui sera affiché dans l'image view
        sendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userText = findViewById(R.id.editTextUser);
                userTextString = userText.getText().toString();
                generateImages(userTextString);
            }

            public void generateImages(String prompt) {
                OkHttpClient client = new OkHttpClient();


                int n = 1;
                String size = "1024x1024";

                String json = "{\n" +
                        "    \"prompt\": \"" + prompt + "\",\n" +
                        "    \"n\": " + n + ",\n" +
                        "    \"size\": \"" + size + "\"\n" +
                        "}";

                RequestBody body = RequestBody.create(json, JSON);

                Request request = new Request.Builder()
                        .url(API_URL)
                        .header("Authorization", "Bearer " + API_KEY)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        String responseBody = response.body().string();

                        try {
                            JSONObject jsonObject = new JSONObject(responseBody);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject imageObject = jsonArray.getJSONObject(0);
                            String imageUrl = imageObject.getString("url");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ImageView imageView = findViewById(R.id.imageCree);
                                    Picasso.get().load(imageUrl).into(imageView);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
            }
        });
    }
}


