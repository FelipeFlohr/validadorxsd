package dev.felipeflohr.validadorxsd.services.fileopener;

import com.google.inject.AbstractModule;

public class FileOpenerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FileOpenerService.class)
                .to(FileOpenerServiceImpl.class);
    }
}
