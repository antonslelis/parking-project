/* ***************************************************************************
 * Copyright 2019 Antons Lelis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/
package org.solent.parking.project.model.test;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ReplyMessage;

/**
 *
 * @author anton
 * 
 */
public class ModelJaxbTest {

    @Test
    public void testJaxb() {

        try {

            // test file we will write and read. 
            // Note in target so that will be delted on each run and will not be saved to git
            File file = new File("target/testData.xml");
            System.out.println("writing test file to " + file.getAbsolutePath());

            // jaxb context needs jaxb.index file to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.parking.project.model");

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create some mock objects to test marshalling and unmarshalling
            ReplyMessage replyMessage1 = new ReplyMessage();
            replyMessage1.setCode(200);
            replyMessage1.setDebugMessage("debug message 1");

            List<ParkingMeter> parkingMeters = replyMessage1.getParkingMeters().getParkingMeters();

            for (int intityId = 0; intityId < 3; intityId++) {
                ParkingMeter meter = new ParkingMeter();
                meter.setId(intityId);
                meter.setLocation("somewhere");
                List<Double> crList= new ArrayList<Double>();
                for(int i=0;i<24;i++){
                    crList.add(new Double(i+intityId));
                }
                meter.setPrice(crList);
                parkingMeters.add(meter);
            }

            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage1, System.out);
            jaxbMarshaller.marshal(replyMessage1, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage1, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedMessage = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedMessage, sw2);

            // check transmitted and recieved message are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

}
