package com.yan073.listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;


@Component
public class HelloListener {

    private static final Logger logger = LogManager.getLogger(HelloListener.class);

	/**
     * This method is invoked whenever any new message is put in the queue.
     * @param message
     */
    public void receiveMessage(byte[] message) {
//    	logger.info("------- message received, length = " + message.length);
    	ObjectMapper om = new ObjectMapper();
    	final ObjectReader reader = om.reader();
    	try {
			final JsonNode newNode = reader.readTree(new ByteArrayInputStream(message));
			logger.info(newNode.toString());
			if(newNode != null) {
				// process...
			}
		} catch (IOException e) {
			logger.error("Cannot parse message to json object.", e);
		}
    }
}
