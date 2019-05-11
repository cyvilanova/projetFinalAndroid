package com.example.quintessentiel;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Fichier: HttpPostRequest.java
 * Auteur: Cynthia Vilanova
 * Fonctionnalité: Connection to PHP servere
 * Date : 9-5-2019
 *
 * Vérification :
 * Date              Nom     Approuvé
 *
 * Historique de modifications :
 * Date               Nom     Description
 * 9-5-2019           CV      Création
 * 10-5-2019          CV      Accès à la base de données centrale
*/
public class HttpPostRequest {
    private String httpUrlConnection;
    private static final String TAG_HTTP_URL_CONNECTION = "HTTP_URL_CONNECTION";
    private HttpURLConnection httpConn = null;

    /**
     * Opens a http connection to the php server, sends a query
     * and retrieves a json_encode string.
     *
     * @param query A prepared statement to execute in the PHP file
     * @param jsonParameters The parameters of the prepared statement
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String executeQuery(final String query, final JSONObject jsonParameters) {
        final CompletableFuture<Object> completableFuture = new CompletableFuture<>();

        Thread sendHttpRequestThread = new Thread() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(httpUrlConnection);

                    httpConn = (HttpURLConnection) url.openConnection();
                    httpConn.setRequestMethod(Constants.REQUEST_METHOD);
                    httpConn.setConnectTimeout(10000);
                    httpConn.setReadTimeout(10000);
                    httpConn.setDoOutput(true);

                    OutputStreamWriter outWriter = new OutputStreamWriter(httpConn.getOutputStream());
                    String data = "&" + URLEncoder.encode("query", "UTF-8") + "="
                            + URLEncoder.encode(query, "UTF-8")
                            + "&" + URLEncoder.encode("parameters", "UTF-8") + "="
                            + URLEncoder.encode(jsonParameters.toString(), "UTF-8");
                    outWriter.write(data);
                    outWriter.flush();

                    InputStream in = new BufferedInputStream(httpConn.getInputStream());
                    String response = convertStreamToString(in);
                    completableFuture.complete(response);

                } catch (MalformedURLException ex) {
                    Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
                } catch (IOException ex) {
                    Log.e(TAG_HTTP_URL_CONNECTION, ex.getMessage(), ex);
                }
            }

            /**
             * Converts the input stream into a string
             *
             * @param is The inputstream
             * @return The content of the stream in string form
             */
            private String convertStreamToString(InputStream is) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return sb.toString();
            }
        };
        sendHttpRequestThread.start();
        String resultFromThread = null;
        try {
            resultFromThread = (String) completableFuture.get();
            Log.d("RESULT resultFromThread", "" + resultFromThread.getClass());
            Log.d("RESULT resultFromThread", "" + resultFromThread);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultFromThread;
    }

    /**
     * Sets the URL of the connection to open
     *
     * @param httpUrlConnection
     */
    public void setHttpUrlConnection(String httpUrlConnection) {
        this.httpUrlConnection = httpUrlConnection;
    }
}