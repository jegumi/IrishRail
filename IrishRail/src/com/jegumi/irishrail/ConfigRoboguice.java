package com.jegumi.irishrail;

import android.content.Context;

import com.google.inject.AbstractModule;
import com.jegumi.irishrail.api.Api;
import com.jegumi.irishrail.db.DataHelper;

public class ConfigRoboguice extends AbstractModule {

    @Override
    protected void configure() {
        Context context = IrishRailApp.getContext();

        IrishRailPreferences preferences = new IrishRailPreferences(context);
        bind(IrishRailPreferences.class).toInstance(preferences);

        DataHelper dataHelper = new DataHelper(context);
        bind(DataHelper.class).toInstance(dataHelper);

        Api api = new Api();
        bind(Api.class).toInstance(api);
    }
}
