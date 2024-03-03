package com.ese.mailer.application.interfaces;

import java.util.List;

public interface QueueClient {

    void startPolling(List<PollingFunction> functions);

    void stopPolling();

}
