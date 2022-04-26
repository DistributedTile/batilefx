package org.distributedtile.batilefx.game.graphics.interfaces;

import org.distributedtile.batilefx.game.Rectangle;

public interface ISheet {
	
	public ITexture getTexture();
	
	public Rectangle getRegionByIndex(int index);
	
	public int getTotalRegions();
}
