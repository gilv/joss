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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.javaswift.joss.client.factory.TempUrlHashPrefixSource;
import org.javaswift.joss.exception.CommandExceptionError;
import org.javaswift.joss.exception.HttpStatusExceptionUtil;

@JsonRootName(value="token")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken extends AbstractAccessKeystoneV3 {

	public List<Catalog> catalog = new ArrayList<Catalog>();

	@JsonIgnore
    private HashMap<String, EndPoint> currentEndPoints;

    private Catalog getObjectStoreCatalog() {
        for (Catalog catal : catalog) {
            if (SERVICE_CATALOG_OBJECT_STORE.equals(catal.type)) {
                return catal;
            }
        }
        return null;
    }

	@Override
	public String getTempUrlPrefix(TempUrlHashPrefixSource tempUrlHashPrefixSource) {
	  // TODO Auto-generated method stub
	  return null;
    }

    @SuppressWarnings("ConstantConditions")
    protected HashMap<String, EndPoint> determineAllCurrentEndPoints() {
        if (currentEndPoints != null) {
            return currentEndPoints;
        }
        currentEndPoints = new HashMap<String, EndPoint>();
        Catalog objectStoreCatalog = getObjectStoreCatalog();
        if (objectStoreCatalog == null) {
            HttpStatusExceptionUtil.throwException(HttpStatus.SC_NOT_FOUND, CommandExceptionError.NO_SERVICE_CATALOG_FOUND);
        }
        currentEndPoints = objectStoreCatalog.getAllRegions(getPreferredRegion());
        if (this.currentEndPoints == null) {
            HttpStatusExceptionUtil.throwException(HttpStatus.SC_NOT_FOUND, CommandExceptionError.NO_END_POINT_FOUND);
        }
        return this.currentEndPoints;
    }

	@Override
	public boolean isTenantSupplied() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getInternalURL() {
		return ((EndPoint) determineAllCurrentEndPoints().get("internal")).url;
	}

	@Override
	public String getPublicURL() {
		return ((EndPoint) determineAllCurrentEndPoints().get("public")).url;	
		}

	@Override
	public void setManualToken(String token) {
		this.token = token;

	}
}
