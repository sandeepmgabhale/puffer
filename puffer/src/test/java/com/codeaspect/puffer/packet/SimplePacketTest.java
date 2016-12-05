package com.codeaspect.puffer.packet;

import com.codeaspect.puffer.annotations.PacketMessage;
import com.codeaspect.puffer.annotations.TemporalFormat;
import com.codeaspect.puffer.testutils.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;

public class SimplePacketTest {

	@Ignore
	public static class OrderDetail implements Packet {

		@PacketMessage(position = 1, length = 10)
		private String name;

		@PacketMessage(position = 2, length = 8)
		@TemporalFormat("ddMMyyyy")
		private Date date;

		@PacketMessage(position = 3, length = 5)
		private Long amount;
	}
	
	@Test
	public void testMapping(){
		String message = "Item1     1508201300100";
		OrderDetail detail = Packet.parse(OrderDetail.class, message);
		assertEquals("Item1", detail.name.trim());
		assertEquals(TestUtils.createDate(15, 8, 2013), detail.date);
		assertEquals(new Long(100), detail.amount);
	}
}
