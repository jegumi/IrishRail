package com.jegumi.irishrail.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jegumi.irishrail.R;
import com.jegumi.irishrail.model.StationData;

public class StationsDataArrayAdapter extends ArrayAdapter<StationData> {

    private Context context;

    public static class ViewHolder {
        private TextView trainInfoTextView;
        private TextView stationInfoTextView;
        private TextView destinationInfoTextView;
        private TextView statusTextView;
    }

    public StationsDataArrayAdapter(Context context, List<StationData> objects) {
        super(context, R.layout.station_data_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StationData stationData = (StationData) getItem(position);
        if (convertView == null) {
            convertView = inflateView();
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        String origin = stationData.getOrigin();
        String destination = stationData.getDestination();
        String trainCode = stationData.getTrainCode();
        String late = stationData.getLate();
        String arrival = stationData.getArrival();
        String status = stationData.getStatus();
        String stationName = stationData.getStationName();
        String toDestination = stationData.getTrainMovement().getLocation();
        String arrivalDestination = stationData.getTrainMovement().getArrival();

        holder.trainInfoTextView.setText(context.getString(R.string.train_info, trainCode, origin, destination));
        holder.stationInfoTextView.setText(context.getString(R.string.arrives_info_delay, stationName, arrival, late));
        holder.destinationInfoTextView.setText(context.getString(R.string.arrives_info, toDestination, arrivalDestination));
        holder.statusTextView.setText(status);

        return convertView;
    }

    private View inflateView() {
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.station_data_item, null);

        ViewHolder holder = new ViewHolder();
        holder.trainInfoTextView = (TextView) view.findViewById(R.id.train_info_text_view);
        holder.stationInfoTextView = (TextView) view.findViewById(R.id.station_info_text_view);
        holder.destinationInfoTextView = (TextView) view.findViewById(R.id.destination_info_text_view);
        holder.statusTextView = (TextView) view.findViewById(R.id.status_text_view);

        view.setTag(holder);

        return view;
    }
}
