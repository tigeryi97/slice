/**
 *
 * Copyright (c) 2017-2017 SLICE project team (yhsuh@etri.re.kr)
 * http://slice.etri.re.kr
 *
 * This file is part of The SLICE components
 *
 * This Program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with The SLICE components; see the file COPYING.  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.etri.slice.devices.carseat;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Property;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.etri.slice.api.inference.WorkingMemory;
import org.etri.slice.commons.eshop.TransactionEvent;

@Component
//@Instantiate
public class TestDevice implements Runnable {


    private static final int DELAY = 1000;
    
    @Requires
    private WorkingMemory m_wm;

    private boolean m_end;
    
    @Property(value = "xxx")
    private String name;
    
    public void run() {
        while (!m_end) {
            try {
            	perform();
                Thread.sleep(DELAY);
            } catch (InterruptedException ie) {
                /* will recheck end */
            }
        }
    }

    /**
     * Invoke hello services
     */
    public void perform() {
    	TransactionEvent event = new TransactionEvent(Long.valueOf(1), Double.valueOf(1000));
    	Class cls = event.getClass();
    	m_wm.insert(event);
    }

    /**
     * Starting.
     */
    @Validate
    public void starting() {
        Thread thread = new Thread(this);
        m_end = false;
        thread.start();
    }

    /**
     * Stopping.
     */
    @Invalidate
    public void stopping() {
        m_end = true;
    }
}