package com.phantom.labs.notesApi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Paul Sanga",
                        email = "paulsanganyamawi@gmail.com",
                        url = "https//phantomlabs.com"
                )
        )
)
public class OpenAPIConfig {
}
