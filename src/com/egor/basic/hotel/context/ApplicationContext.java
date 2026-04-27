package com.egor.basic.hotel.context;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.controller.DispatcherController;
import com.egor.basic.hotel.controller.EmployeeController;
import com.egor.basic.hotel.controller.GuestController;
import com.egor.basic.hotel.controller.RoomController;
import com.egor.basic.hotel.repository.EmployeeRepository;
import com.egor.basic.hotel.repository.GuestRepository;
import com.egor.basic.hotel.repository.RoomRepository;
import com.egor.basic.hotel.service.EmployeeService;
import com.egor.basic.hotel.service.GuestService;
import com.egor.basic.hotel.service.RoomService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

    //Контейнер
    private Map<Class<?>, Object> context = new HashMap<>();

    public ApplicationContext() {

        //Список всех классов
        List<Class<?>> classes = List.of(
                DispatcherController.class,
                RoomController.class,
                GuestController.class,
                EmployeeController.class,

                RoomService.class,
                GuestService.class,
                EmployeeService.class,

                RoomRepository.class,
                GuestRepository.class,
                EmployeeRepository.class
        );

        //Создание объектов
        for (Class<?> clazz : classes) {
            if (isComponent(clazz)) {
                createBean(clazz);
            }
        }
        //Внедрение зависимостей
        injectDependencies();
    }
    //Проверка аннотаций
    private boolean isComponent(Class<?> clazz) {
        return clazz.isAnnotationPresent(Controller.class)
                ||
                clazz.isAnnotationPresent(Service.class)
                ||
                clazz.isAnnotationPresent(Repo.class);
    }
    //создание объекта
    private void createBean(Class<?> clazz) {
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();
            context.put(clazz, obj);
        }catch (Exception e) {
            throw new RuntimeException("Error creating bean " + clazz.getName());
        }
    }
    //Внедрение зависимостей
    private void injectDependencies() {

        for (Object obj : context.values()) {

            for (Field field : obj.getClass().getDeclaredFields()) {

                if (field.isAnnotationPresent(Autowired.class)) {

                    Object dependency = context.get(field.getType());

                    if (dependency == null) {
                        for (Object obj2 : context.values()) {
                            if (field.getType().isAssignableFrom(obj2.getClass())) {
                                dependency = obj2;
                                break;
                            }
                        }
                    }

                    if (dependency == null) {
                        throw new RuntimeException("No bean found for: " + field.getType());
                    }

                    try {
                        field.setAccessible(true);
                        field.set(obj, dependency);
                    }catch (Exception e) {
                        throw new RuntimeException("Error injecting dependency");
                    }
                }
            }
        }
    }

    //Получить объект
    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(context.get(clazz));
    }

}
