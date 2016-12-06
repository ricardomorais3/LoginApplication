package org.academiadecodigo.loginSceneBuilder.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by codecadet on 05/12/16.
 */
public class TestServiceRegistry {

    private ServiceRegistry serviceRegistry;
    private Service serviceMockito;

    @Before
    public void setup() {
        serviceRegistry = ServiceRegistry.getInstance();
        serviceMockito = Mockito.mock(Service.class);
        when(serviceMockito.getName()).thenReturn("servicinho");
    }

    @Test
    public void getInstance() {
        assertNotNull(serviceRegistry);
        assertTrue(serviceRegistry.getServicesMap().isEmpty());
    }

    @Test
    public void addService() {
        serviceRegistry.addService(serviceMockito);
        assertTrue(serviceRegistry.getServicesMap().size() == 1);
    }

    @Test
    public void getService() {
        serviceRegistry.addService(serviceMockito);
        assertTrue(serviceRegistry.getService("servicinho") == serviceMockito);
    }

}
