/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.bchapuis.gdal4j;

/**
 * An auto closable wrapper around the {@link org.gdal.gdal.Driver} class.
 */
public class Driver implements AutoCloseable {

    private final org.gdal.gdal.Driver gdalDriver;

    protected Driver(org.gdal.gdal.Driver gdalDriver) {
        this.gdalDriver = gdalDriver;
    }

    public String getShortName() {
        return gdalDriver.getShortName();
    }

    public String getLongName() {
        return gdalDriver.getLongName();
    }

    public String getHelpTopic() {
        return gdalDriver.getHelpTopic();
    }

    @Override
    public void close() throws Exception {
        gdalDriver.delete();
    }
}
