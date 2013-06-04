package org.cccs.wadlgenerator.converters;

import org.cccs.wadlgenerator.domain.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.cccs.wadlgenerator.service.ResourceService.getMappings;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 5:28:32 PM
 */

@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestResourcesConverter implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    @Test
    public void getWadlShouldWork() throws IOException {
        Resources resources = new Resources();
        resources.baseUrl = "https://craigcook.co.uk";
        resources.resources = getMappings("org.cccs.wadlgenerator.controllers");
        String wadl = marshal(resources);
        System.out.println(wadl);
    }

    protected  String marshal(final Object obj) throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        marshal(obj, new BufferedOutputStream(bos));
        return bos.toString();
    }

    protected  void marshal(final Object obj, final OutputStream stream) throws IOException {
        final Result result = new StreamResult(stream);
        getMarshaller().marshal(obj, result);
    }

    protected  XStreamMarshaller getMarshaller() {
        return applicationContext.getBean("marshaller", XStreamMarshaller.class);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
