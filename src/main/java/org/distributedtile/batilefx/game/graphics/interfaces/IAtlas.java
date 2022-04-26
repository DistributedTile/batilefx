package org.distributedtile.batilefx.game.graphics.interfaces;

import org.distributedtile.batilefx.game.graphics.TextureBlock;

import javafx.collections.ObservableList;

public interface IAtlas {
	
	public ObservableList<TextureBlock> textureBlocks();
	
	public TextureBlock getTextureBlockByIndex(int index);

	public int getTotalTextureBlocks();
	
}
