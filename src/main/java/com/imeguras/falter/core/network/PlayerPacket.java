package com.imeguras.falter.core.network;

import com.imeguras.falter.core.player.PlayerStats;
import com.imeguras.falter.proxy.ClientProxy;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PlayerPacket implements IMessage{

	private PlayerStats ps;
	public PlayerPacket(PlayerStats ps){
		this.ps=ps;
	}
	public PlayerPacket(){
		this(new PlayerStats());	
	}
	@Override
	public void fromBytes(ByteBuf buf) {	
		ps.unserialize(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ps.serialize(buf);
		
	}
	public void handleClientSide() {
		ClientProxy.ps=this.ps;	
		System.out.println("Client side"+ps.toString());	
	}
	
	public static class Handler implements IMessageHandler<PlayerPacket, IMessage> {
        @Override
        public IMessage onMessage(PlayerPacket message, MessageContext ctx) {
			message.handleClientSide();
            return null;
        }
    }
}
