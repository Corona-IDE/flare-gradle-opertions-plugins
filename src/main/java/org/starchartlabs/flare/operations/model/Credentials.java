/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.flare.operations.model;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Represents a loaded set of credentials for use by the build system
 *
 * @author romeara
 * @since 3.0.0
 */
public class Credentials {

    private final String username;

    private final byte[] password;

    public Credentials(String username, String password) {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password.getBytes(StandardCharsets.UTF_8));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return new String(password, StandardCharsets.UTF_8);
    }

}
