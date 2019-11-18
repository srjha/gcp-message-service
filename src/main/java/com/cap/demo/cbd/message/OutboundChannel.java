package com.cap.demo.cbd.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutboundChannel {

	@Output
	MessageChannel outgoing();

}
