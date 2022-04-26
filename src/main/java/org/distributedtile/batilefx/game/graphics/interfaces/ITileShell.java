package org.distributedtile.batilefx.game.graphics.interfaces;

import java.io.Serializable;

import org.distributedtile.batilefx.game.Rectangle;
import org.distributedtile.batilefx.game.graphics.TextureBlock;

public interface ITileShell extends Serializable {
	
	public ITexture getTexture();
	
	public Rectangle getRegion();
	
	public TextureBlock getTextureBlock(int row, int col);
	
	public int getRows();
	
	public int getCols();
	
}
