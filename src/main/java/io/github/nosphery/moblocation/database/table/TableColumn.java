package io.github.nosphery.moblocation.database.table;

import lombok.Getter;
import lombok.Setter;

public class TableColumn {

    public static final TableColumn
        ID = new TableColumn("INT NOT NULL"),
        UUID = new TableColumn("VARCHAR(255) NOT NULL"),
        STRING = new TableColumn("VARCHAR(255)"),
        BYTE = new TableColumn("TINYINT"),
        BOOLEAN = BYTE.clone(),
        SHORT = new TableColumn("MEDIUMINT"),
        INTEGER = new TableColumn("INT"),
        LONG = new TableColumn("BIGINT"),
        TEXT = new TableColumn("TEXT");


    static {
        UUID.setPrimaryKey(true);
    }


    @Getter
    private String syntax;
    @Getter
    @Setter
    private String defaultValue;
    @Getter
    @Setter
    private boolean primaryKey = false;

    public TableColumn(String syntax) {
        this.syntax = syntax;
    }

    public TableColumn(TableColumn clone) {
        this.syntax = clone.syntax;
        this.defaultValue = clone.defaultValue;
        this.primaryKey = clone.primaryKey;
    }

    @Override
    protected TableColumn clone(){
        return new TableColumn(this);
    }
}
