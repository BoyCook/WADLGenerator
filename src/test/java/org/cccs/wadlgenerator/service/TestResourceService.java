package org.cccs.wadlgenerator.service;

import static org.cccs.wadlgenerator.service.ResourceService.getMappings;
import org.cccs.wadlgenerator.domain.Resource;

import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

/**
 * User: boycook
 * Date: Aug 17, 2010
 * Time: 5:23:32 PM
 */
public class TestResourceService {

    @Test
    public void getMappingsShouldWork() {
        Map<String, Resource> resources = getMappings("org.cccs.wadlgenerator.controllers");
        assertThat(resources.size(), is(equalTo(4)));
    }
}
