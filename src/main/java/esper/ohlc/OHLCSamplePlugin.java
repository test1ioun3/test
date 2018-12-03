/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package esper.ohlc;

import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.espertech.esper.runtime.client.EPUndeployException;
import com.espertech.esper.runtime.client.plugin.PluginLoader;
import com.espertech.esper.runtime.client.plugin.PluginLoaderInitContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PluginLoader for added this example as part of an Esper configuration file and therefore execute it during startup.
 */
public class OHLCSamplePlugin implements PluginLoader {
    private static final Logger log = LoggerFactory.getLogger(OHLCSamplePlugin.class);

    private static final String RUNTIME_URI = "runtimeURI";

    private String runtimeURI;
    private OHLCMain main;

    public void init(PluginLoaderInitContext context) {
        if (context.getProperties().getProperty(RUNTIME_URI) != null) {
            runtimeURI = context.getProperties().getProperty(RUNTIME_URI);
        } else {
            runtimeURI = context.getRuntime().getURI();
        }
    }

    public void postInitialize() {
        log.info("Starting OHLCSample-example for runtime URI '" + runtimeURI + "'.");

        try {
            main = new OHLCMain();
            main.run(runtimeURI);
        } catch (Exception e) {
            log.error("Error starting OHLCSample example: " + e.getMessage());
        }

        log.info("OHLCSample-example started.");
    }

    public void destroy() {
        if (main != null) {
            try {
                EPRuntimeProvider.getRuntime(runtimeURI).getDeploymentService().undeployAll();
            } catch (EPUndeployException e) {
                log.warn("Failed to undeploy: " + e.getMessage(), e);
            }
        }
        log.info("OHLCSample-example stopped.");
    }
}
