package com.imeguras.falter.core.network;

import com.imeguras.falter.core.PilotManual;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {
	public static SimpleNetworkWrapper networkWrapper;
	private int register = -1;
	
	public NetworkHandler() {
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(PilotManual.MOD_ID);
		networkWrapper.registerMessage(PlayerPacket.Handler.class, PlayerPacket.class, ++register, Side.CLIENT);
		//registerPacket(PacketUpdateClient.class, PacketUpdateClient.Handler.class, Side.CLIENT);
		//registerPacket(PacketMovement.class, PacketMovement.Handler.class, Side.SERVER);
		//registerPacket(PacketDrink.class, PacketDrink.Handler.class, Side.SERVER);
	}
	
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	private void registerPacket(Class<? extends IMessage> c1, Class c2, Side side) {
		register++;
		networkWrapper.registerMessage(c2, c1, register, side);
	}*/
}
