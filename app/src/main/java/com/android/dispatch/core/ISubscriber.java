package com.android.dispatch.core;

import com.android.dispatch.BaseEvent;

/**
 * Created by peiboning on 2017/7/28.
 */

public interface ISubscriber {
    void onEvent(BaseEvent event);
}
