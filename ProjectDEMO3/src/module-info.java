module ProjectDEMO3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base, javafx.controls, java.sql;
}
