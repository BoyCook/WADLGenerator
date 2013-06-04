package org.cccs.wadlgenerator.service;

import org.cccs.wadlgenerator.beans.TypeResolver;
import org.cccs.wadlgenerator.domain.Method;
import org.cccs.wadlgenerator.domain.Resource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 4:33:42 PM
 */
public class ResourceService {

    private static Map<String, Resource> resources;

    public static Map<String, Resource> getMappings(String scanPackage) {
        if (resources == null) {
            resources = new HashMap<String, Resource>();
            resources.putAll(getAnnotatedValues(scanPackage));
        }
        return resources;
    }

    private static Map<String, Resource> getAnnotatedValues(String packageName) {
        Map<String, Resource> mappings = new HashMap<String, Resource>();

        final TypeResolver resolver = new TypeResolver();
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.addAll(resolver.resolve(packageName, new AnnotationTypeFilter(Controller.class)));

        for (Class<?> clazz : classes) {
            for (java.lang.reflect.Method method : clazz.getMethods()) {
                RequestMapping mapping = method.getAnnotation(RequestMapping.class);

                if (mapping != null) {
                    Resource resource;

                    if (mappings.containsKey(mapping.value()[0])) {
                        resource = mappings.get(mapping.value()[0]);
                    } else {
                        resource = new Resource(mapping.value()[0]);
                        mappings.put(resource.path, resource);
                    }

                    resource.methods.add(new Method(mapping.method()[0].toString().toUpperCase(),
                            clazz.getName(),
                            method.getName()));

                }
            }
        }
        return mappings;
    }    
}
