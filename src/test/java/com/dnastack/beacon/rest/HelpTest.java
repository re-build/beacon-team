/*
 * The MIT License
 *
 * Copyright 2014 DNAstack.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.dnastack.beacon.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test of help resource.
 *
 * @author Miroslav Cupak (mirocupak@gmail.com)
 * @version 1.0
 */
@RunWith(Arquillian.class)
@RunAsClient
public class HelpTest extends BasicTest {

    public static final String HELP_TEMPLATE = "rest";
    private static RestEndPoint beacons;
    private static RestEndPoint responses;

    public static String getUrl() {
        return HELP_TEMPLATE;
    }

    public static List<RestEndPoint> readRestEndPoints(String url) throws JAXBException, MalformedURLException {
        return (List<RestEndPoint>) readObject(RestEndPoint.class, url);
    }

    public static RestEndPoint readRestEndPoint(String url) throws JAXBException, MalformedURLException {
        return (RestEndPoint) readObject(RestEndPoint.class, url);
    }

    @BeforeClass
    public static void setUp() {
        beacons = new RestEndPoint("beacons", null, null);
        responses = new RestEndPoint("responses", null, null);
    }

    @Test
    public void testHelp(@ArquillianResource URL url) throws JAXBException, MalformedURLException {
        List<RestEndPoint> rs = readRestEndPoints(url.toExternalForm() + getUrl());

        assertEquals(rs.size(), 2);
        for (RestEndPoint r : rs) {
            assertTrue(r.equals(beacons) || r.equals(responses));
        }
    }

}
