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

public class PriceLimit {
    String userId;
    String symbol;
    double limitPct;

    public PriceLimit(String userId, String symbol, double limitPct) {
        this.userId = userId;
        this.symbol = symbol;
        this.limitPct = limitPct;
    }

    public String getUserId() {
        return userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getLimitPct() {
        return limitPct;
    }

    public String toString() {
        return "userId=" + userId +
            "  symbol=" + symbol +
            "  limitPct=" + limitPct;
    }
}
