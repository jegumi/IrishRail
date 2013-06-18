package com.jegumi.irishrail.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.jegumi.irishrail.model.TrainMovement;

public class ListTrainsMovementsParser {

    private final static String ns = null;
    private final static String ARRAY_STATION_DATA_TAG = "ArrayOfObjTrainMovements";
    private final static String STATION_DATA_TAG = "objTrainMovements";

    private final static String TRAIN_CODE_TAG = "Traincode";
    private final static String LOCATION_TAG = "LocationFullName";
    private final static String ARRIVAL_TAG = "ExpectedArrival";
    private final static String DEPARTURE_TAG = "ExpectedDeparture";

    public static List<TrainMovement> parse(String xml)
            throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(xml));
            parser.nextTag();
            return readTrainMovements(parser);
        } finally {
            // in.close();
        }
    }

    private static List<TrainMovement> readTrainMovements(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        List<TrainMovement> entries = new ArrayList<TrainMovement>();

        parser.require(XmlPullParser.START_TAG, null, ARRAY_STATION_DATA_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(STATION_DATA_TAG)) {
                entries.add(readTrainMovement(parser));
            } else {
                ReadElementsParser.skip(parser);
            }
        }
        return entries;
    }

    private static TrainMovement readTrainMovement(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, STATION_DATA_TAG);
        String departure = null;
        String trainCode = null;
        String location = null;
        String arrival = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(LOCATION_TAG)) {
                location = ReadElementsParser.readElement(parser, LOCATION_TAG);
            } else if (name.equals(TRAIN_CODE_TAG)) {
                trainCode = ReadElementsParser.readElement(parser, TRAIN_CODE_TAG);
            } else if (name.equals(ARRIVAL_TAG)) {
                arrival = ReadElementsParser.readElement(parser, ARRIVAL_TAG);
            } else if (name.equals(DEPARTURE_TAG)) {
                departure = ReadElementsParser.readElement(parser, DEPARTURE_TAG);
            } else {
                ReadElementsParser.skip(parser);
            }
        }
        return new TrainMovement(trainCode, location, arrival, departure);
    }
}
