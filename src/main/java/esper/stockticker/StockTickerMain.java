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
package esper.stockticker;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class StockTickerMain implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(StockTickerMain.class);

    private final String runtimeURI;
    private final boolean continuousSimulation;

    public static void main(String[] args) {
        new StockTickerMain("StockTicker", false).run();
    }

    public StockTickerMain(String runtimeURI, boolean continuousSimulation) {
        this.runtimeURI = runtimeURI;
        this.continuousSimulation = continuousSimulation;
    }

    public void run() {
        Configuration configuration = StockTickerEPLUtil.getConfiguration();
        EPCompiled compiled = StockTickerEPLUtil.compileEPL(configuration);

        log.info("Setting up runtime");
        EPRuntime runtime = EPRuntimeProvider.getRuntime(runtimeURI, configuration);
        runtime.initialize();

        log.info("Deploying compiled EPL");
        StockTickerEPLUtil.deploy(runtime, compiled);

        log.info("Generating test events: 1 million ticks, ratio 2 hits, 100 stocks");
        StockTickerEventGenerator generator = new StockTickerEventGenerator();
        LinkedList stream = generator.makeEventStream(1000000, 500000, 100, 25, 30, 48, 52, false);
        log.info("Generating " + stream.size() + " events");

        log.info("Sending " + stream.size() + " limit and tick events");
        for (Object theEvent : stream) {
            runtime.getEventService().sendEventBean(theEvent, theEvent.getClass().getSimpleName());

            if (continuousSimulation) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    log.debug("Interrupted", e);
                    break;
                }
            }
        }

        log.info("Done.");
    }
}
