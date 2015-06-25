package eu.kielczewski.example.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import eu.kielczewski.example.core.PackageItem;
import eu.kielczewski.example.core.mapper.PackageItemMapper;

@RegisterMapper(PackageItemMapper.class)
public interface PackageItemDAO {

	@SqlQuery("select * from PACKAGE_ITEM where ITEM_ID = :id")
    PackageItem findById(@Bind("id") int id);
	
	@SqlUpdate("insert into PACKAGE_ITEM (ITEM_ID, PACKAGE_ID) values (:itemId, :packageId)")
    int insert(@BindBean PackageItem packageItem);
}
