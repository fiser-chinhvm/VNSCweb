/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sis.services.csw;

import java.util.Map;
import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import static org.apache.sis.internal.util.CollectionsExt.first;
import org.opengis.metadata.Metadata;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.StorageConnector;
import org.apache.sis.storage.earthobservation.LandsatReader;
import org.apache.sis.storage.earthobservation.ModisReader;
import org.apache.sis.storage.geotiff.GeoTiffStore;
import org.opengis.metadata.content.ImageDescription;

/**
 * Collection of ISO 19115 metadata. Current implementation parses the metadata
 * from all supported files found in the given directory.
 *
 * @author Thi Phuong Hao Nguyen (VNSC)
 * @since 0.8
 * @version 0.8
 * @module
 */
public class Catalog {

    /**
     * All metadata known to this {@code Catalog} class. Keys are the metadata
     * identifiers.
     */
    private final Map<String, Metadata> metadata;

    /**
     * Creates a new catalog initialized with the metadata of all files found in
     * the given directory. The current implementation does not scan the
     * sub-directories.
     *
     * @param directory the directory to scan.
     * @throws DataStoreException if an error occurred while reading a metadata
     * file.
     */
    public Catalog(String path) throws DataStoreException, IOException, Exception {
        //ConfigurationReader path = new ConfigurationReader();
        metadata = new LinkedHashMap<>();
        File directory = new File(path);
        /**
         * Get all the files from a directory.
         *
         */
        File[] folder = directory.listFiles();
        for (File a : folder) {
            File[] file = a.listFiles();
            for (File b : file) {
                if (b.isDirectory()) {
                    File[] data = b.listFiles();
                    for (File c : data) {
                        final Metadata md;
                        if (c.isFile() && c.getName().endsWith(".txt")) {
                            try (BufferedReader in = new BufferedReader(new FileReader(c.getPath()))) {
                                final LandsatReader reader = new LandsatReader(in);
                                md = reader.read();
                            }
                        }
                        else if (c.isFile() && c.getName().endsWith(".xml")) {
                            File xml = new File(c.getPath());
                            final ModisReader read = new ModisReader(xml);
                            md = read.read();
                        } 
//                        else if (c.isFile() && c.getName().endsWith(".TIF")) {
//                            File tif = new File(c.getPath());
//                            final GeoTiffStore store = new GeoTiffStore(new StorageConnector(tif));
//                            md = store.getMetadata();
//                        }
                        else {
                            continue;   // Ignore (for now) unrecognized format.
                        }
                        metadata.put(md.getFileIdentifier(), md);
                    }
                }
            }

        }
        // Ignore (for now) unrecognized format.
    }

    /**
     * Return all metadata tree
     *
     * @return all metadata tree
     */
    public List<Metadata> getAllMetadata() {
        return new ArrayList<Metadata>(metadata.values());
    }

    /**
     * Returns the metadata for the given identifier.
     *
     * @param id the identifier of the metadata to lookup.
     * @return the metadata for the given identifier.
     * @throws IllegalArgumentException if there is no record for the given
     * identifier.
     */
    public Metadata getRecordById(String id) {
        Metadata message = metadata.get(id);
        if (message == null) {
            throw new IllegalArgumentException("Record with id " + id + " not found");
        }
        return message;
    }
    public static void main(String[] args) throws IOException, Exception {
        Catalog a = new Catalog("C:\\Users\\nguye\\Desktop\\projectGSOC\\data");
        for (Metadata b :a.getAllMetadata() ){
            ImageDescription c = (ImageDescription) first(b.getContentInfo());
            System.out.println(c.getCloudCoverPercentage());
        }
    }
}
