/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.web;

import org.solent.parking.project.model.ServiceFactory;
import org.solent.parking.project.service.ServiceFactoryImpl;

/**
 *
 * @author cgallen
 */
public class WebObjectFactory {

    private static final String DATA_FILE_LOCATION = "./app-data.xml";

    private static ServiceFactory serviceFactory = null;

    public static ServiceFactory getServiceFactory() {

        if (serviceFactory == null) {
            synchronized (WebObjectFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactoryImpl(DATA_FILE_LOCATION);
                }
            }
        }
        return serviceFactory;
    }
}
