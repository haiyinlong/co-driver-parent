package com.leo.ad.codriver.dws.service;

import java.util.List;

/**
 * QueryAdConversionEvent
 *
 * @author HaiYinLong
 * @version 2025/02/18 15:03
 **/
public class QueryAdConversionEvent {
    protected static final String AD_CLICK_EVENT = "ct_click";
    protected static final String AD_SHOW_EVENT = "ct_show";

    protected static final List<String> AD_EVENT_LIST = List.of(AD_CLICK_EVENT, AD_SHOW_EVENT);

}
