package org.laxio.ignition;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.laxio.api.exception.ignition.IgnitionStartupException;
import org.laxio.api.ignition.Ignitable;
import org.laxio.api.ignition.Ignition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class LaxioIgnition implements Ignition {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioIgnition.class);

    private final LaxioIgnitionArguments arguments;

    private File ignitablesDirectory;

    public LaxioIgnition(LaxioIgnitionArguments arguments) {
        this.arguments = arguments;
        load();
    }

    private void load() {
        loadIgnitablesDirectory();
    }

    private void loadIgnitablesDirectory() {
        String name = arguments.getIgnitablesDirectoryName();

        File folder = new File(name);
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                throw new IgnitionStartupException("Specified Ignitables directory '" + name + "' is not a directory");
            }
        } else {
            boolean mkdir = folder.mkdirs();
            if (!mkdir) {
                throw new IgnitionStartupException("Unable to create Ignitables directory");
            } else {
                LOGGER.info("Created new Ignitables directory");
            }
        }

        ignitablesDirectory = folder;
    }

    @SuppressWarnings("UnstableApiUsage")
    public void ignite() {
        if (arguments.isClassPathLoaded()) {
            ClassPath classPath;

            try {
                classPath = ClassPath.from(LaxioIgnitionStart.class.getClassLoader());
            } catch (IOException ex) {
                throw new IgnitionStartupException("Unable to load classes from classpath", ex);
            }

            ImmutableSet<ClassPath.ClassInfo> infos = classPath.getAllClasses();

            for (ClassPath.ClassInfo info : infos) {
                scan(info);
            }
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private void scan(ClassPath.ClassInfo info) {
        String className = info.getName();

        Class<?> clazz;

        try {
            clazz = Class.forName(className, false, LaxioIgnitionStart.class.getClassLoader());
        } catch (Throwable ex) {
            // something weird here
            return;
        }

        if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers())) {
            return;
        }

        if (clazz != Ignitable.class && Ignitable.class.isAssignableFrom(clazz)) {
            ignite(clazz);
        }
    }

    private void ignite(Class<?> clazz) {
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
        ignitable.ignite(this);
    }

    @Override
    public File getIgnitablesDirectory() {
        return ignitablesDirectory;
    }

}
