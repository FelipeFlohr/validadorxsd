package dev.felipeflohr.validadorxsd.container;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.felipeflohr.validadorxsd.services.fileopener.FileOpenerModule;
import dev.felipeflohr.validadorxsd.services.validator.ValidatorModule;

public class InjectionContainer {
    private final Injector injector;
    private static InjectionContainer instance;

    private InjectionContainer() {
        injector = Guice.createInjector(
                new FileOpenerModule(),
                new ValidatorModule()
        );
    }

    public static InjectionContainer getInstance() {
        if (instance == null) {
            instance = new InjectionContainer();
        }
        return instance;
    }

    public Injector getInjector() {
        return injector;
    }
}
