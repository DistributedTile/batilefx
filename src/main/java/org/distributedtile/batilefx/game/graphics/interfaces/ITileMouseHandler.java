package org.distributedtile.batilefx.game.graphics.interfaces;

import org.distributedtile.batilefx.game.graphics.tile.TileMouseEvent;

public interface ITileMouseHandler {
	
	/**
	 * 瓷砖鼠标事件处理
	 * @param event 瓷砖鼠标事件
	 */
	public void handle(TileMouseEvent event);
	
}
