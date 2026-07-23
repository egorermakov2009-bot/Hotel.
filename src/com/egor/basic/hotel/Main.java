package com.egor.basic.hotel;

import com.egor.basic.hotel.context.ApplicationContext;
import com.egor.basic.hotel.controller.DispatcherController;
import com.egor.basic.hotel.model.Employee;
import com.egor.basic.hotel.model.Role;
import com.egor.basic.hotel.repository.EmployeeRepository;
import com.egor.basic.hotel.service.RoomService;

class Main {
    public static void main(String[] args) {



        ApplicationContext context = new ApplicationContext();

//        context.getBean(RoomService.class).test();
//
//        RoomService service = context.getBean(RoomService.class);
//
//        System.out.println(service == null);

        DispatcherController dispatcher = context.getBean(DispatcherController.class);
        dispatcher.start();

    }
}
