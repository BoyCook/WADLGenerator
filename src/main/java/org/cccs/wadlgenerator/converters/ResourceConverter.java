package org.cccs.wadlgenerator.converters;

import org.cccs.wadlgenerator.domain.Method;
import org.cccs.wadlgenerator.domain.Resource;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 5:13:05 PM
 */
public class ResourceConverter implements Converter {
    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        Resource r = (Resource) o; 
        marshalResource(r, writer, context);
    }

    private void marshalResource(Resource r, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.startNode("resource");
        writer.addAttribute("path", r.path);

        for (Method method : r.methods) {
            writer.startNode("method");
            writer.addAttribute("name", method.httpMethod);
            writer.addAttribute("id", "");

            writer.startNode("request");
            writer.endNode(); //request

            writer.startNode("response");
            writer.addAttribute("status", "");
            writer.endNode(); //response

            writer.endNode(); //method
        }

        writer.endNode(); //resource
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return aClass == Resource.class;
    }
}
