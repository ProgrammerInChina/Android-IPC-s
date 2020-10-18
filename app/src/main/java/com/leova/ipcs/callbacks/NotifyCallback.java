package com.leova.ipcs.callbacks;

import java.io.Serializable;

public interface NotifyCallback extends Serializable {

    long serialVersionUID = 123456L;

    void onNotifyLogChange(String logStr);
}
