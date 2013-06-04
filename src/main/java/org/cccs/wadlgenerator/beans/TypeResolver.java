package org.cccs.wadlgenerator.beans;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 4:40:52 PM
 */
public class TypeResolver {

    public static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private final ResourcePatternResolver resourcePatternResolver =
            new PathMatchingResourcePatternResolver();

    private final MetadataReaderFactory metadataReaderFactory =
            new CachingMetadataReaderFactory(resourcePatternResolver);
    private final TypeFilter ALLOW_ALL_FILTER = new AllowAllTypeFilter();

    private static class AllowAllTypeFilter implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            return true;
        }
    }

    /**
     * Searches the classpath recursively, starting from baseSearchPath, for all classes.
     * @param baseSearchPath the base (package) search path.
     * @return A {@link java.util.Set} of all matched {@link Class} resources.
     */
    public Set<Class<?>> resolve(final String baseSearchPath) {
        return resolve(baseSearchPath, ALLOW_ALL_FILTER);
    }

    /**
     * Searches the classpath recursively, starting from baseSearchPath, for all classes
     * that are matched by the supplied {@link org.springframework.core.type.filter.TypeFilter}.
     * @param baseSearchPath the base (package) search path.
     * @param filter the {@link org.springframework.core.type.filter.TypeFilter} to use when
     * matching classes.
     * @return A {@link java.util.Set} of all matched {@link Class} resources.
     */
    public Set<Class<?>> resolve(final String baseSearchPath, final TypeFilter filter) {
        final Set<Class<?>> results = new LinkedHashSet<Class<?>>();
        final String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                resolveBasePackage(baseSearchPath) + "/" + DEFAULT_RESOURCE_PATTERN;
        loadMatchingResources(packageSearchPath, results, filter);
        return results;
    }

    private void loadMatchingResources(final String packageSearchPath, final Set<Class<?>> results,
                                       final TypeFilter filter) {
        try {
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try {
                        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        if (filter.match(metadataReader, metadataReaderFactory)) {
                            final ClassMetadata classMetadata = metadataReader.getClassMetadata();

                            if (classMetadata.isConcrete()) {
                                // TODO: think about a more robust mechanism for choosing a classloader
                                results.add(ClassUtils.forName(classMetadata.getClassName(),
                                        TypeResolver.class.getClassLoader()));
                            }
                        }
                    } catch (Throwable t) {
                        throw new BeanDefinitionStoreException(
                                "Failed to read candidate component class: " + resource, t);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }
}
