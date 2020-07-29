package io.github.nosphery.moblocation.mob.storage;

import com.google.common.collect.Lists;
import io.github.nosphery.moblocation.database.MySQL;
import io.github.nosphery.moblocation.database.manager.MySQLManager;
import io.github.nosphery.moblocation.database.table.Table;
import io.github.nosphery.moblocation.database.table.TableColumn;
import io.github.nosphery.moblocation.database.table.TableRow;
import io.github.nosphery.moblocation.mob.data.Mob;
import io.github.nosphery.moblocation.mob.manager.MobManager;
import io.github.nosphery.moblocation.util.LocationSerialize;
import org.bukkit.entity.EntityType;

import java.sql.SQLException;
import java.util.List;

/**
 * @author oNospher
 **/
public class MobRepository {

    Table table = new Table("h4_moblocation");

    public MobRepository() {
        MySQL mySQL = MySQLManager.getMySQL("general");
        Table.setDefaultConnection(mySQL.getConnection());
    }

    public void createTable() {
        try {
            table.addColumn("faction", TableColumn.UUID);
            table.addColumn("entity", TableColumn.UUID);
            table.addColumn("location", TableColumn.STRING);
            table.create();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void insert(Mob mob) {
        try {
            table.insert(
                    "faction",
                    "entity",
                    "location"
            ).one(
                    mob.getTag(),
                    mob.getEntityType().toString(),
                    LocationSerialize.toString(mob.getLocation())
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void remove(EntityType entityType, String tag) {
        try {
            table.delete().where(
                    "entity",
                    entityType.toString()
            ).where(
                    "faction",
                    tag
            ).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFaction(String tag) {
        try {
            table.delete().where(
                    "faction",
                    tag
            ).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mob> find(String key, String value) {
        List<Mob> mobs = Lists.newArrayList();
        List<TableRow> row = table.query().where(key, value).get();

        row.stream().map(MobManager::toMob).forEach(mobs::add);

        return mobs;
    }
}
