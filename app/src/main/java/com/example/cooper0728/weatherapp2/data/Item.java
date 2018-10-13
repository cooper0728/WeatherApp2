package com.example.cooper0728.weatherapp2.data;

import org.json.JSONObject;

/**
 * Created by cooper0728 on 10/15/2017.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
