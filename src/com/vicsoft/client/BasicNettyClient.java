package com.vicsoft.client;

import com.vicsoft.client.connector.BasicClientConnector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicNettyClient {

    public static void main(String[] args) {
        BasicClientConnector.getInstance().connect();
    }

}
