package com.egor.basic.hotel.context;

import com.egor.basic.hotel.annotations.Annotations.*;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ApplicationContext {

    private final Map<Class<?>, Object> context = new HashMap<>();

    private static final String BASE_PACKAGE = "com.egor.basic.hotel";

    public ApplicationContext() {

        Set<Class<?>> classes = scanClasses(BASE_PACKAGE);

        //Создание объектов
        for (Class<?> clazz : classes) {
            if (isComponent(clazz)) {
                createBean(clazz);
            }
        }

        //Внедрение зависимостей
        injectDependencies();
    }

    private Set<Class<?>> scanClasses(String packageName) {
        Set<Class<?>> classes = new HashSet<>();

        String path = packageName.replace('.', '/');

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();

                if (resource.getProtocol().equals("file")) {
                    File directory = new File(resource.toURI());

                    scanDirectory(directory, packageName, classes);
                } else if (resource.getProtocol().equals("jar")) {
                    scanJar(resource, path, classes);

                }
            }
        }catch (Exception e) {
            throw new RuntimeException("Error scanning package: " + packageName, e);
        }
        return classes;
    }

    private void scanDirectory(File directory, String packageName, Set<Class<?>> classes) {
        File[] files = directory.listFiles();

        if(files == null) {
            return;
        }
        for (File file : files) {

            if(file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {

                String className = packageName + "." + file.getName().replace(".class", "");

                loadClass(className,classes);
            }
        }
    }

    private void scanJar(URL resource, String packagePath, Set<Class<?>> classes) {
        try {
            JarURLConnection connection = (JarURLConnection) resource.openConnection();
            try(JarFile jarFile = connection.getJarFile()) {
                Enumeration<JarEntry> entries = jarFile.entries();

                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();

                    if (name.startsWith(packagePath) && name.endsWith(".class") && !entry.isDirectory()) {
                        String className = name.replace('/', '.').replace(".class", "");
                        loadClass(className, classes);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException("Error scanning JAR", e);
        }
    }

    private void loadClass(String className, Set<Class<?>> classes) {
        try {
            Class<?> clazz = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
            classes.add(clazz);
        }catch(ClassNotFoundException e) {
            throw new RuntimeException("Cannot load class: " + className, e);
        }
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

//                 проверка на внедрение зависимостей
//                        System.out.println(
//                                "Injected " +
//                                        dependency.getClass().getSimpleName() +
//                                        " into " +
//                                        obj.getClass().getSimpleName());

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

