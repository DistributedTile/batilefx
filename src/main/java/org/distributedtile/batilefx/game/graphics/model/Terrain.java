package org.distributedtile.batilefx.game.graphics.model;

import org.distributedtile.batilefx.game.graphics.interfaces.ITileShell;
import org.distributedtile.batilefx.game.graphics.tile.TiledArea;

public class Terrain extends Model2D {
	
	private static final long serialVersionUID = 678872832245175517L;

	public Terrain(Integer id, String name, TiledArea tiledArea, ITileShell tileShell) {
		super(id, name, tiledArea, tileShell);
	}

}
