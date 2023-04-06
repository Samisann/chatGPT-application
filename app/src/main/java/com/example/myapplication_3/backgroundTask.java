/*package com.example.myapplication_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Http2Reader;

public class backgroundTask extends AsyncTask<Void,Void,String> {

    // clé de l'api
    String apiKey = "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";
    private String userText;


    public backgroundTask(String userText) {
        this.userText = userText;
    }

    public static void post(String url, String json, String apiKey, ImageView imageView) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
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

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(imageUrl).into(imageView);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }



    // fonction pour récupérer l'image
    public static Bitmap get(String url, String apiKey) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
        Response response = client.newCall(request).execute();
        InputStream in = response.body().byteStream();
        Bitmap image = BitmapFactory.decodeStream(in);
        return image;
    }

    @Override
    protected String doInBackground(Void... voids) {

        // url de l'api
        String url = "https://api.openai.com/v1/images/generations";
                /*{
                    "prompt": "A cute baby sea otter",
                    "n": 2,
                    "size": "1024x1024"
                 } */// paramètres de l'api


     /*   JSONObject apiParam = new JSONObject();
        try {
            apiParam.put("prompt", userText);
            apiParam.put("n", 1);
            apiParam.put("size", "1024x1024");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String json = apiParam.toString();

        try {
            ImageView imageGenere = find
            String urlImage = post(url, json, apiKey,imageGenere);
            //on récupère l'image générée
            Bitmap image = get(urlImage, apiKey);
            //on affiche l'image
            imageGenere.setImageBitmap(image);
            return urlImage;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
return null;

    }



    @Override
    protected void onPostExecute(String urlImage) {
        if (urlImage != null) {
            try {
                Bitmap image = get(urlImage, apiKey);
                //on affiche l'image
                imageGenere.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher un message d'erreur
            System.out.println("erreur aved l'id");
        }


}

}
*/