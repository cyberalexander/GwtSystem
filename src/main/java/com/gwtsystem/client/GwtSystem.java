package com.gwtsystem.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtsystem.client.service.CustomerWebServiceClientImpl;
import com.gwtsystem.client.service.ICustomerWebServiceClient;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * Created by alexanderleonovich on 02.08.15.
 */
public class GwtSystem implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        ICustomerWebServiceClient customerWebServiceClient =
                new CustomerWebServiceClientImpl(GWT.getModuleBaseURL() +
                        "springGwtServices/customerWebService");
        RootPanel.get().add(customerWebServiceClient.getMainView());

    }
}
