package dev.felipeflohr.validadorxsd.services.validator;

import com.google.inject.AbstractModule;

public class ValidatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(XmlValidator.class)
                .to(XmlValidatorImpl.class);
    }
}
