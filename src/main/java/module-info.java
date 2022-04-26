module com.outbina.sgengine {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.yaml.snakeyaml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;

    opens org.distributedtile.batilefx to javafx.fxml;
    exports org.distributedtile.batilefx;
}