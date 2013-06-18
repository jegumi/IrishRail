package com.jegumi.irishrail.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.jegumi.irishrail.db.DbHelper;
import com.jegumi.irishrail.model.Station;

public class ListStationsParser {

    private final static String ns = null;
    private final static String ARRAY_STATION_TAG = "ArrayOfObjStation";
    private final static String STATION_TAG = "objStation";

    public static List<Station> parse(String xml) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(xml));
            parser.nextTag();
            return readStations(parser);
        } finally {
          //  in.close();
        }
    }

    private static List<Station> readStations(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Station> entries = new ArrayList<Station>();

        parser.require(XmlPullParser.START_TAG, null, ARRAY_STATION_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(STATION_TAG)) {
                entries.add(readStation(parser));
            } else {
                ReadElementsParser.skip(parser);
            }
        }
        return entries;
    }

    private static Station readStation(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, STATION_TAG);
        String desc = null;
        String alias = null;
        String code = null;
        long longitude = 0;
        long latitude = 0;
        int id = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(DbHelper.STATION_DESC)) {
                desc = ReadElementsParser.readElement(parser, DbHelper.STATION_DESC);
            } else if (name.equals(DbHelper.STATION_ALIAS)) {
                alias = ReadElementsParser.readElement(parser, DbHelper.STATION_ALIAS);
            } else if (name.equals(DbHelper.STATION_CODE)) {
                code = ReadElementsParser.readElement(parser, DbHelper.STATION_CODE);
            } else if (name.equals(DbHelper.STATION_ID)) {
                id = Integer.valueOf(ReadElementsParser.readElement(parser, DbHelper.STATION_ID));
            } else {
                ReadElementsParser.skip(parser);
            }
        }
        return new Station (desc, alias, longitude, latitude, code, id);
    }
}
