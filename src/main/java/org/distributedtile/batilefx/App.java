package org.distributedtile.batilefx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurve;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;

import org.distributedtile.batilefx.game.Size;
import org.distributedtile.batilefx.game.common.IModelMatcher;
import org.distributedtile.batilefx.game.common.TerrainModelMatcher;
import org.distributedtile.batilefx.game.graphics.TextureLoader;
import org.distributedtile.batilefx.game.graphics.interfaces.IModel2D;
import org.distributedtile.batilefx.game.graphics.interfaces.IModel2DAnimationLoader;
import org.distributedtile.batilefx.game.graphics.interfaces.IModel2DLoader;
import org.distributedtile.batilefx.game.graphics.interfaces.ITextureLoader;
import org.distributedtile.batilefx.game.graphics.interfaces.ITileMouseHandler;
import org.distributedtile.batilefx.game.graphics.interfaces.ITilePlotter;
import org.distributedtile.batilefx.game.graphics.interfaces.ITileShellBuilder;
import org.distributedtile.batilefx.game.graphics.model.Model2D;
import org.distributedtile.batilefx.game.graphics.model.Model2DAnimationLoader;
import org.distributedtile.batilefx.game.graphics.model.Model2DLoader;
import org.distributedtile.batilefx.game.graphics.tile.TileInfo;
import org.distributedtile.batilefx.game.graphics.tile.TileMouseEvent;
import org.distributedtile.batilefx.game.graphics.tile.TileMouseEventType;
import org.distributedtile.batilefx.game.graphics.tile.TileShellBuilder;
import org.distributedtile.batilefx.game.graphics.tile.TiledBoundary;
import org.distributedtile.batilefx.game.graphics.tile.TilesGrid;
import org.distributedtile.batilefx.game.graphics.tile.TilesGridPlotter;
import org.distributedtile.batilefx.game.map.MapService;
import org.distributedtile.batilefx.mod.sg208.common.DefaultMapPanel;
import org.distributedtile.batilefx.mod.sg208.common.DefaultTilePlotter;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

    	int rows = 101;
    	int cols = 101;
		int xPadding = 0;//500;
		int yPadding = 0;//200;
    	
		Size gridSize = new Size(600, 360);
		Size tileSize = new Size(107, 54);
		// Size tileSize = new Size(60, 30);
		/****************************************/
		
		// TODO 1 ??????????????????/??????
		ITextureLoader textureLoader = TextureLoader.getInstance(App.class);
		textureLoader.load("/mod/sg/textures.yml");
		
		/****************************************/
		
		// TODO 2 ??????????????????
		ITileShellBuilder shellBuilder = new TileShellBuilder();
		
		/****************************************/
		
		// TODO 3 ????????????
    	IModel2DLoader<IModel2D> modelLoader = 
    			Model2DLoader.getInstance(App.class,textureLoader,shellBuilder,tileSize);
    	modelLoader.load("/mod/sg/model_files.yml");
    	
		/****************************************/
    	
		// TODO 4 ??????????????????
    	IModel2DAnimationLoader animationLoader = 
    			Model2DAnimationLoader.getInstance(App.class, modelLoader);
    	animationLoader.load("/mod/sg/animation_files.yml");
    	
		/****************************************/
    	
    	// TODO 5 ????????????
    	TilesGrid tilesGrid = new TilesGrid(gridSize, tileSize, rows, cols);
		tilesGrid.setXPadding(xPadding);
		tilesGrid.setYPadding(yPadding);
    	
		/****************************************/
		
    	// TODO 6?????????????????????
    	TerrainModelMatcher terrainModelMatcher = 
    			TerrainModelMatcher.getInstance(App.class,modelLoader);
    	terrainModelMatcher.load("/mod/sg/map/cell_model.yml");
		
    	/****************************************/

    	// TODO 7 ???????????????????????????
    	ITilePlotter defaultTilePlotter = new DefaultTilePlotter(terrainModelMatcher);
    	
    	/****************************************/
		
		// TODO 8??????????????????
		MapService mapService = new MapService(rows,cols,terrainModelMatcher);
		
		/****************************************/
		
    	// TODO 9?????????????????????
    	TilesGridPlotter tilesGridPlotter = new TilesGridPlotter(mapService);
    	tilesGridPlotter.getTerrainPlotters().add(defaultTilePlotter);
    	
		/****************************************/
		
		// TODO 10 ??????????????????
		DefaultMapPanel mapPanel = new DefaultMapPanel(
				(int)gridSize.getWidth(), (int)gridSize.getHeight(),mapService, tilesGrid, tilesGridPlotter);
		
		// ?????????????????????
		mapPanel.addTileMouseHandler(new ITileMouseHandler() {
			
			private TileInfo selectedTerrain;
			
			@Override
			public void handle(TileMouseEvent event) {
				
				TileInfo tileInfo = mapService.getTerrain(event.getTileRow(), event.getTileCol());
				
				if(event.getEventType() == TileMouseEventType.MOUSE_ENTERED ) {		
						
					// ????????????
					if(tileInfo != null) {
						TiledBoundary boundary = tileInfo.getBoundary();

						for(int i = boundary.getStartRow() ; i < boundary.getEndRow() ; i++) {
							for(int j = boundary.getStartCol() ; j < boundary.getEndCol() ; j++) {
								if(boundary.isOccupied(i,j)) {
									TileInfo innerTile = mapService.getTerrain(i,j);
									innerTile.attributes().put("touch",true);
								}
							}
						}
					}
					
					selectedTerrain = tileInfo;
					
				} else if(event.getEventType() == TileMouseEventType.MOUSE_EXITED) {
					
					// ????????????
					if(tileInfo != null) {
						TiledBoundary boundary = tileInfo.getBoundary();
						
						for(int i = boundary.getStartRow() ; i < boundary.getEndRow() ; i++) {
							for(int j = boundary.getStartCol() ; j < boundary.getEndCol() ; j++) {
								if(boundary.isOccupied(i,j)) {
									TileInfo innerTile = mapService.getTerrain(i,j);
									innerTile.attributes().put("touch",false);
								}								
							}
						}
					}
					
				}
			}
		});
		
		// ????????????
		mapPanel.start(100, 50, 0, 0);
		// System.out.println(Paint.valueOf("radial-gradient(red, green, blue)"));
		/****************************************/
		
    	// TODO End
    	Pane pane = new Pane();
    	
		Camera camera = new PerspectiveCamera(); 
		//Camera camera = new ParallelCamera();  
        camera.getTransforms().addAll (
        		// new Rotate(-10, Rotate.Y_AXIS),
                // new Rotate(10, Rotate.X_AXIS),  
                new Translate(0, 0, 0));
		
		
        mapPanel.getPane().setTranslateY(0);
        
		pane.getChildren().add(mapPanel.getPane());
		
		SubScene subScene = new SubScene(pane,gridSize.getWidth(),gridSize.getHeight(),true,SceneAntialiasing.BALANCED);
		subScene.setFill(Color.ALICEBLUE);  
		subScene.setCamera(camera);  
		
		Group group = new Group();  
        group.getChildren().add(subScene);
        
    	scene = new Scene(group,-1,-1,true,SceneAntialiasing.BALANCED);
    	
    	stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    	
    }

}