/*
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.concurrency.tck.cdi;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.testng.annotations.Test;

/**
 * Tests sharing rules of ManagedExecutors injected via CDI
 */
public class ManagedExecutorSharingTest extends Arquillian {

    // Delegate all CDI tests off to a proper CDI bean
    // Injection in this class is mocked by the Arquillian test enricher
    @Inject
    ManagedExecutorSharingBean testBean;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, ManagedExecutorSharingTest.class.getSimpleName() + ".war")
                .addPackage("org.eclipse.microprofile.concurrency.tck.cdi")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testDefaultMEsDifferent() {
        testBean.testDefaultMEsDifferent();
    }

    @Test
    public void testUnnamedConfigDifferent() {
        testBean.testUnnamedConfigDifferent();
    }

    @Test
    public void testAllDefaultInjectionUnique() {
        testBean.testAllDefaultInjectionUnique();
    }

    @Test
    public void testNamedExecsSame() {
        testBean.testNamedExecsSame();
    }

}