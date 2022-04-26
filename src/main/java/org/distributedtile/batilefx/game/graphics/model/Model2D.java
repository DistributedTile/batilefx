package org.distributedtile.batilefx.game.graphics.model;

import org.distributedtile.batilefx.game.graphics.interfaces.IModel2D;
import org.distributedtile.batilefx.game.graphics.interfaces.ITileShell;
import org.distributedtile.batilefx.game.graphics.tile.TiledArea;

public class Model2D implements IModel2D {

	private static final long serialVersionUID = -258201678703285514L;
	
	private Integer id;
	private String name;
	
	private TiledArea tiledArea;
	private ITileShell tileShell;
	
	public Model2D(Integer id, String name, TiledArea tiledArea, ITileShell tileShell) {
		this.id = id;
		this.name = name;
		this.tiledArea = tiledArea;
		this.tileShell = tileShell;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public TiledArea getTiledArea() {
		return tiledArea;
	}

	@Override
	public ITileShell getTileShell() {
		return tileShell;
	}

}
