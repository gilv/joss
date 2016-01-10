/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.javaswift.joss.command.shared.identity.access;

import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.javaswift.joss.exception.CommandExceptionError;
import org.javaswift.joss.exception.HttpStatusExceptionUtil;
import org.javaswift.joss.model.Access;

public abstract class AbstractAccessKeystoneV3 implements Access {

    public static final String SERVICE_CATALOG_OBJECT_STORE = "object-store";

    public String token;

    public User user;

    public Metadata metadata;

    private String preferredRegion;

    protected abstract HashMap<String, EndPoint> determineAllCurrentEndPoints();

    public abstract boolean isTenantSupplied();

    public String getToken() {
        return token;
    }

    public String getPreferredRegion() {
        return this.preferredRegion;
    }

    public void setPreferredRegion(String preferredRegion) {
        this.preferredRegion = preferredRegion;
    }
}
