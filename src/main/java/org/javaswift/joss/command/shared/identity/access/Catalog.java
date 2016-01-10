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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog {

    public List<EndPoint> endpoints = new ArrayList<EndPoint>();

    public String type;

    public String name;

    public EndPoint getRegion(String regionName) {
        EndPoint firstEndPoint = null;
        for (EndPoint endPoint : endpoints) {
            if (regionName == null) { // If no region is passed, return the first region
                return endPoint;
            }
            if (firstEndPoint == null) { // If the requested region was not found, return the first region -- show must go on
                firstEndPoint = endPoint;
            }
            if (regionName.equals(endPoint.region)) {
                return endPoint;
            }
            if (regionName.equals(endPoint.region_id)) {
                return endPoint;
            }
        }
        return firstEndPoint;
    }

    public HashMap<String, EndPoint> getAllRegions(String regionName) {
    	HashMap<String, EndPoint> allEndPoints = new HashMap<String, EndPoint>();
        for (EndPoint endPoint : endpoints) {
            if (regionName.equals(endPoint.region) || 
            		regionName.equals(endPoint.region_id)) {
                allEndPoints.put(endPoint.accessInterface, endPoint);
            }
        }
        return allEndPoints;
    }
}
