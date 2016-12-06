package org.academiadecodigo.loginSceneBuilder.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 28/11/16.
 */
public class ServiceRegistry {

    private static ServiceRegistry instance = null;
    private Map<String, Service> services = new HashMap<>();

    private ServiceRegistry(){
    }

    // static method that returns the instance
    public static synchronized ServiceRegistry getInstance() {

        // the instance is created only the first time this method is called
        if(instance == null) {
            instance = new ServiceRegistry();
        }

        // it always return the same instance, there is no way to have another one
        return instance;
    }

    public void addService(Service service){
        services.put(service.getName(), service);
    }

    public Service getService(String serviceName){
        return services.get(serviceName);
    }

    public Map<String, Service> getServicesMap() {
        return services;
    }

}
