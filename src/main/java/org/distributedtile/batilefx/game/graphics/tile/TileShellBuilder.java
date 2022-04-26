package org.distributedtile.batilefx.game.graphics.tile;

import org.distributedtile.batilefx.game.Rectangle;
import org.distributedtile.batilefx.game.Size;
import org.distributedtile.batilefx.game.graphics.TextureBlock;
import org.distributedtile.batilefx.game.graphics.interfaces.ITexture;
import org.distributedtile.batilefx.game.graphics.interfaces.ITileShell;
import org.distributedtile.batilefx.game.graphics.interfaces.ITileShellBuilder;

public class TileShellBuilder implements ITileShellBuilder {

	@Override
	public ITileShell createTileShell(ITexture texture, Rectangle region, Size tileSize,Size currentTileSize, TiledArea tiledArea) {

		int rows = tiledArea.getOccupiedRows();
		int cols = tiledArea.getOccupiedCols();
		int dHeight = tiledArea.getHeight();
		
		int halfTileWidth = (int) (tileSize.getWidth()/2);
		int halfTileHeight = (int) (tileSize.getHeight()/2);
		int startX = halfTileWidth*(cols-1) + (int)region.getMinX();
		int startY = dHeight + (int)region.getMinY();
		
		double scaleX = currentTileSize.getWidth()/tileSize.getWidth();
		double scaleY = currentTileSize.getHeight()/tileSize.getHeight();
		
		Rectangle tileRegion ;
		TextureBlock[][] textureBlocks = new TextureBlock[rows][cols];
		
		for(int i = 0 ;i < rows; i ++) {
			for(int j = 0 ;j< cols; j ++) {
				
			    int x = (i - j)*halfTileWidth + startX;
				int y = (i + j)*halfTileHeight + startY;
				
				double pivotY ;
				
				if(i == 0 || j == 0) {
					tileRegion = new Rectangle(x, region.getMinY(), halfTileWidth*2,halfTileHeight*2 + (y-region.getMinY()));
					pivotY = y-region.getMinY() + halfTileHeight;
				} else {
					tileRegion = new Rectangle(x, y, halfTileWidth*2,halfTileHeight*2);
					pivotY = halfTileHeight;
				}
				
				textureBlocks[i][j] = new TextureBlock(texture, tileRegion, scaleX, scaleY, halfTileWidth*scaleX, pivotY*scaleY);
				//pivotY -= halfTileHeight;
				//textureBlocks[i][j] = new TextureBlock(texture, tileRegion, scaleX, scaleY, 0, pivotY*scaleY);
			}
		}
		
		return new TileShell(texture, region, textureBlocks, rows, cols);
	}

}
