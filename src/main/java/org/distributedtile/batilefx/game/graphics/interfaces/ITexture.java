package org.distributedtile.batilefx.game.graphics.interfaces;

import java.io.Serializable;

import org.distributedtile.batilefx.game.Size;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * 纹理
 */
public interface ITexture extends Serializable {
	
	public Integer getId() ;

	public String getName() ;
	
	public Image getImage() ;
	
	public Image getMaskImage(Paint paint) ;
	
	public Size getSize();
}
