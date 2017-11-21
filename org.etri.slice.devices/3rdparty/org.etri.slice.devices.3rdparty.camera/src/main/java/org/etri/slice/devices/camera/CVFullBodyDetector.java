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
package org.etri.slice.devices.camera;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.handlers.event.Publishes;
import org.apache.felix.ipojo.handlers.event.publisher.Publisher;
import org.etri.slice.commons.SliceException;
import org.etri.slice.commons.car.BodyPartLength;
import org.etri.slice.commons.car.service.FullBodyDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(publicFactory=false, immediate=true)
@Provides
@Instantiate
public class CVFullBodyDetector implements FullBodyDetector {

	private static Logger s_logger = LoggerFactory.getLogger(CVFullBodyDetector.class);	
	
	@Publishes(name="pub:body_part_length", topics="body_part_length", dataKey="body.part.length")
	private Publisher m_publisher;	

	@Override
	public void start() throws SliceException {
		s_logger.info("STARTED : " + this.getClass().getSimpleName());		
	}

	@Override
	public void stop() {
		s_logger.info("STOPPED : " + this.getClass().getSimpleName());		
	}

	@Override
	public void detect() throws SliceException {
		BodyPartLength bodyLength = BodyPartLength.builder().head(30).torso(60).arms(60).legs(80).build();
		m_publisher.sendData(bodyLength);
		
		s_logger.info("PUB: " + bodyLength);
	}
	
}
