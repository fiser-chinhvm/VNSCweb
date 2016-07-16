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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thi Phuong Hao NGUYEN
 * @author Minh Chinh VU
 */
public class Record {

    public Record() {
    }

    public List<SummaryRecord> getAllRecord() throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.Metadata());
        return b;
    }

    public List<SummaryRecord> getRecordByText(String format) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.Metadata());
        List<SummaryRecord> messagesByText = new ArrayList<>();
        for (SummaryRecord message : b) {
            if (message.getFormat().equals(format)) {
                messagesByText.add(message);
            }
        }
        return messagesByText;
    }

    public List<SummaryRecord> SearchDate(String date1, String date2) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.Metadata());
        List<SummaryRecord> messagesForYear = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date da1 = df.parse(date1);
        Date da2 = df.parse(date2);

        long day = (da2.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);

        for (SummaryRecord message : b) {
            Date da3 = message.getModified();
            long day1 = (da3.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);
            if (day1 >= 0 && day1 <= day) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<SummaryRecord> getAllRecordPaginated(int start, int size) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.Metadata());
        ArrayList<SummaryRecord> list = new ArrayList<SummaryRecord>(b);
        if (start + size > list.size()) {
            return new ArrayList<SummaryRecord>();
        }
        return list.subList(start, start + size);
    }

    public SummaryRecord getRecordById(String id) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.Metadata());
        SummaryRecord messagesById = new SummaryRecord();
        for (SummaryRecord message : b) {
            if (message.getIdentifier().equals(id)) {
                messagesById.setFormat(message.getFormat());
                messagesById.setTitle(message.getTitle());
                messagesById.setType(message.getType());
                messagesById.setSubject(message.getSubject());
                messagesById.setIdentifier(message.getIdentifier());
                messagesById.setModified(message.getModified());
                messagesById.setBoundingBox(message.getBoundingBox());
                messagesById.setContributor(message.getContributor());
                messagesById.setCreator(message.getCreator());
                messagesById.setPublisher(message.getPublisher());
                messagesById.setRelation(message.getRelation());

            }
        }
        return messagesById;
    }

}
