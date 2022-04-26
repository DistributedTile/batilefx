package org.distributedtile.batilefx.game.map.interfaces;

import org.distributedtile.batilefx.game.graphics.tile.TileInfo;

public interface IMapService {
	
	public TileInfo getTerrain(int row,int col);

	public int getEvenLayerHeightProjection(int row,int col);
	
	public int getOddLayerHeightProjection(int row,int col);
	
}
