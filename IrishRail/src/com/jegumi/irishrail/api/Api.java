package com.jegumi.irishrail.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.jegumi.irishrail.db.DbHelper;

import android.util.Log;

public class Api {

    private static final String TAG = Api.class.getName();
    private static final String API = "http://api.irishrail.ie/realtime/realtime.asmx/";
    private static final String GET_ALL_STATIONS = "getAllStationsXML";
    private static final String GET_TRAINS_FROM = "getStationDataByNameXML";
    private static final String GET_TRAIN_MOVEMENTS = "getTrainMovementsXML";

    private static final String TRAIN_ID = "TrainId";
    private static final String TRAIN_DATE = "TrainDate";

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final int BUFFER_SIZE = 8;
    private static final int TIMEOUT = 30000;

    public static final int OK = 200;
    public static final int ERROR = 500;

    public String getAllStations() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        return postStringData(nameValuePairs, GET_ALL_STATIONS);
    }

    public String getStationsTrains(String stationDesc) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(DbHelper.STATION_DESC, stationDesc));
        return postStringData(nameValuePairs, GET_TRAINS_FROM);
    }

    public String getTrainMovements(String trainCode, String trainDate) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair(TRAIN_ID, trainCode));
        nameValuePairs.add(new BasicNameValuePair(TRAIN_DATE, trainDate));
        return postStringData(nameValuePairs, GET_TRAIN_MOVEMENTS);
    }

    public static String postStringData(List<NameValuePair> nameValuePairs, String method) {
        String uri = String.format("%s%s", API, method);
        HttpPost httppost = new HttpPost(uri);

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
        String result = "";

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, ENCODING_UTF8));
            BasicHttpResponse response = (BasicHttpResponse) new DefaultHttpClient(
                    httpParameters).execute(httppost);
            result = getStringfromResponse(response);
        } catch (Exception e) {
            Log.e(TAG, "postData error " + e.getMessage());
        }

        return result;
    }

    public static String getStringfromResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String result = "";
        try {
            InputStream is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), BUFFER_SIZE);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception e) {
            Log.e(TAG, "getJSONfromResponse " + e.getMessage());
        }
        return result;
    }
}
