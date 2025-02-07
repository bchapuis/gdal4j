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

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * An auto closable wrapper around the {@link org.gdal.gdal.Band} class.
 */
public class Band implements AutoCloseable {

    private final org.gdal.gdal.Band gdalBand;

    protected Band(org.gdal.gdal.Band gdalBand) {
        this.gdalBand = gdalBand;
    }

    public int getWidth() {
        return gdalBand.getXSize();
    }

    public int getHeight() {
        return gdalBand.getYSize();
    }

    public void read(int x, int y, int width, int height, byte[] buffer) {
        gdalBand.ReadRaster(x, y, width, height, buffer);
    }

    public void read(int x, int y, int width, int height, int[] buffer) {
        gdalBand.ReadRaster(x, y, width, height, buffer);
    }

    public void read(int x, int y, int width, int height, long[] buffer) {
        gdalBand.ReadRaster(x, y, width, height, buffer);
    }

    public void read(int x, int y, int width, int height, double[] buffer) {
        gdalBand.ReadRaster(x, y, width, height, buffer);
    }

    public BufferedImage asBufferedImage(int imageType) {
        // Copy the data of the band into a byte array
        int width = gdalBand.getXSize();
        int height = gdalBand.getYSize();
        double[] values = new double[height * width];
        gdalBand.ReadRaster(0, 0, 256, 256, values);

        // Create a BufferedImage from the byte array
        BufferedImage image = new BufferedImage(width, height, imageType);
        WritableRaster raster = image.getRaster();
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                double value = values[y * 256 + x];
                raster.setDataElements(x, y, value);
            }
        }

        return image;
    }

    @Override
    public void close() throws Exception {
        gdalBand.delete();
    }

}
