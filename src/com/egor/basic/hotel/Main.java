package com.egor.basic.hotel;

import com.egor.basic.hotel.context.ApplicationContext;
import com.egor.basic.hotel.controller.DispatcherController;

class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext();
        DispatcherController dispatcher = context.getBean(DispatcherController.class);
        dispatcher.start();

    }
}
