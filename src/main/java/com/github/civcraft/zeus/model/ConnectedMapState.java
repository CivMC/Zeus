package com.github.civcraft.zeus.model;

import com.github.civcraft.zeus.servers.ArtemisServer;
import com.google.common.base.Preconditions;

public class ConnectedMapState {

	private ZeusLocation upperLeftCorner;
	private int xSize;
	private int zSize;
	private ArtemisServer server;
	private boolean randomSpawnTarget;

	public ConnectedMapState(ArtemisServer server, ZeusLocation upperLeftCorner, int xSize, int zSize,
			boolean randomSpawnTarget) {
		Preconditions.checkNotNull(upperLeftCorner);
		Preconditions.checkArgument(xSize > 0);
		Preconditions.checkArgument(zSize > 0);
		this.upperLeftCorner = upperLeftCorner;
		this.xSize = xSize;
		this.zSize = zSize;
		this.server = server;
		this.randomSpawnTarget = randomSpawnTarget;
	}

	public boolean isRandomSpawnTarget() {
		return randomSpawnTarget;
	}

	public ArtemisServer getServer() {
		return server;
	}

	public ZeusLocation getUpperLeftCorner() {
		return upperLeftCorner;
	}

	public String getWorld() {
		return upperLeftCorner.getWorld();
	}

	public int getXSize() {
		return xSize;
	}

	public int getZSize() {
		return zSize;
	}

	public boolean isInside(ZeusLocation location) {
		if (!location.getWorld().equals(upperLeftCorner.getWorld())) {
			return false;
		}
		return isInside(location.getX(), location.getZ());
	}

	public boolean isInside(double x, double z) {
		double xDiff = x - upperLeftCorner.getX();
		if (xDiff < 0 || xDiff > xSize) {
			return false;
		}
		double zDiff = z - upperLeftCorner.getZ();
		if (zDiff < 0 || zDiff > zSize) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return server.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ConnectedMapState)) {
			return false;
		}
		return ((ConnectedMapState) o).server.equals(server);
	}

}
