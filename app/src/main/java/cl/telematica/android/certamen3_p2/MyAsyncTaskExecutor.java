package cl.telematica.android.certamen3_p2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class MyAsyncTaskExecutor {

    private static MyAsyncTaskExecutor instance;

    public static MyAsyncTaskExecutor getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }

    public void executeMyAsynctask(final MainActivity.Listener listener, final String textToSend) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                //String strJson = toJSON(textToSend);
                String resultado = new HttpServerConnection().connectToServer(/*"http://www.mocky.io/v2/582f1759260000171165f0b6"*/
                                                                           "http://localhost:3000/api/clients" + "?data=" + textToSend, 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);

                    listener.onSuccess(result);
                }
            }
        };

        task.execute();
    }
    public String toJSON(String nombre){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("show" , nombre);
            return jsonObject.toString();
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
