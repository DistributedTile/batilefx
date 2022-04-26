package org.distributedtile.batilefx.game.graphics.interfaces;

import org.distributedtile.batilefx.game.Rectangle;
import org.distributedtile.batilefx.game.Size;
import org.distributedtile.batilefx.game.graphics.tile.TiledArea;

public interface ITileShellBuilder {

	/**
	 * 
	 * @param texture   纹理
	 * @param region    需要处理的纹理范围
	 * @param tileSize  纹理中对应的单个瓷砖的大小
	 * @param currentTileSize 当前使用的瓷砖大小
	 * @param tiledArea 平铺的区域
	 * @return
	 */
	public ITileShell createTileShell(ITexture texture,Rectangle region,Size tileSize,Size currentTileSize ,TiledArea tiledArea);
	
}
