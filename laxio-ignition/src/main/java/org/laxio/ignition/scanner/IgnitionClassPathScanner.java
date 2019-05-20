package org.laxio.ignition.scanner;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.laxio.api.exception.ignition.IgnitionStartupException;
import org.laxio.api.ignition.Ignitable;
import org.laxio.ignition.LaxioIgnitionStart;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class IgnitionClassPathScanner {

    @SuppressWarnings("UnstableApiUsage")
    public static void scan(ClassLoader classLoader, IgnitableFoundAction action) {
        ClassPath classPath;

        try {
            classPath = ClassPath.from(LaxioIgnitionStart.class.getClassLoader());
        } catch (IOException ex) {
            throw new IgnitionStartupException("Unable to load classes from classpath", ex);
        }

        ImmutableSet<ClassPath.ClassInfo> infos = classPath.getAllClasses();

        for (ClassPath.ClassInfo info : infos) {
            scan(classLoader, info, action);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void scan(ClassLoader classLoader, ClassPath.ClassInfo info, IgnitableFoundAction action) {
        String className = info.getName();

        Class<?> clazz;

        try {
            clazz = Class.forName(className, false, classLoader);
        } catch (Throwable ex) {
            // something weird here
            return;
        }

        if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers())) {
            return;
        }

        if (clazz != Ignitable.class && Ignitable.class.isAssignableFrom(clazz)) {
            ignite(clazz, action);
        }
    }

    private static void ignite(Class<?> clazz, IgnitableFoundAction action) {
        Constructor<?> constructor;

        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException ex) {
            throw new IgnitionStartupException("Unable to find constructor for Ignitable", ex);
        }

        Object instance;

        try {
            instance = constructor.newInstance();
        } catch (InstantiationException ex) {
            throw new IgnitionStartupException("Exception thrown whilst constructing Ignitable", ex);
        } catch (IllegalAccessException ex) {
            throw new IgnitionStartupException("Unable to access constructor of Ignitable", ex);
        } catch (InvocationTargetException ex) {
            throw new IgnitionStartupException("Ignitable threw exception during construction", ex);
        }

        Ignitable ignitable = (Ignitable) instance;
        action.handleIgnitable(ignitable);
    }

}
