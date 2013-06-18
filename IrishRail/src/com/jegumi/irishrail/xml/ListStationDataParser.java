package com.jegumi.irishrail.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.jegumi.irishrail.model.StationData;

public class ListStationDataParser {

    private final static String ns = null;
    private final static String ARRAY_STATION_DATA_TAG = "ArrayOfObjStationData";
    private final static String STATION_DATA_TAG = "objStationData";

    private final static String STATION_NAME_TAG = "Stationfullname";
    private final static String ORIGIN_TAG = "Origin";
    private final static String DESTINATION_TAG = "Destination";
    private final static String TRAIN_CODE_TAG = "Traincode";
    private final static String LAST_LOCATION_TAG = "Lastlocation";
    private final static String LATE_TAG = "Late";
    private final static String EXPECT_ARRIVAL_TAG = "Exparrival";
    private final static String STATUS_TAG = "Status";

    public static List<StationData> parse(String xml)
            throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(xml));
            parser.nextTag();
            return readStations(parser);
        } finally {
            // in.close();
        }
    }

    private static List<StationData> readStations(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        List<StationData> entries = new ArrayList<StationData>();

        parser.require(XmlPullParser.START_TAG, null, ARRAY_STATION_DATA_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(STATION_DATA_TAG)) {
                entries.add(readStation(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private static StationData readStation(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, STATION_DATA_TAG);
        String stationName = null;
        String origin = null;
        String destination = null;
        String trainCode = null;
        String lastLocation = null;
        String late = null;
        String arrival = null;
        String status = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals(STATION_NAME_TAG)) {
                stationName = readElement(parser, STATION_NAME_TAG);
            } else if (name.equals(ORIGIN_TAG)) {
                origin = readElement(parser, ORIGIN_TAG);
            } else  if (name.equals(DESTINATION_TAG)) {
                destination = readElement(parser, DESTINATION_TAG);
            } else if (name.equals(TRAIN_CODE_TAG)) {
                trainCode = readElement(parser, TRAIN_CODE_TAG);
            } else if (name.equals(LAST_LOCATION_TAG)) {
                lastLocation = readElement(parser, LAST_LOCATION_TAG);
            } else if (name.equals(LATE_TAG)) {
                late = readElement(parser, LATE_TAG);
            } else if (name.equals(EXPECT_ARRIVAL_TAG)) {
                arrival = readElement(parser, EXPECT_ARRIVAL_TAG);
            } else if (name.equals(STATUS_TAG)) {
                status = readElement(parser, STATUS_TAG);
            } else {
                skip(parser);
            }
        }
        return new StationData(stationName, origin, destination, trainCode, lastLocation, late, arrival, status);
    }

    private static String readElement(XmlPullParser parser, String tag)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return title;
    }

    private static String readText(XmlPullParser parser) throws IOException,
            XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private static void skip(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
            case XmlPullParser.END_TAG:
                depth--;
                break;
            case XmlPullParser.START_TAG:
                depth++;
                break;
            }
        }
    }
}
