package org.cccs.wadlgenerator.converters;

import org.cccs.wadlgenerator.domain.Resources;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 4:56:42 PM
 */
public class ResourcesConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        Resources resources = (Resources) o; 
        writer.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        writer.addAttribute("xsi:schemaLocation", "http://wadl.dev.java.net/2009/02 wadl.xsd");
        writer.addAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        writer.addAttribute("xmlns", "http://wadl.dev.java.net/2009/02");

        writer.startNode("resources");

        for (String key: resources.resources.keySet()) {
            context.convertAnother(resources.resources.get(key));
        }

        writer.endNode(); //resources
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Resources.class; 
    }
}
