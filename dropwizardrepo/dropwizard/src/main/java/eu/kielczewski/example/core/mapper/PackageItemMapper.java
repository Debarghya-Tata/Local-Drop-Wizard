package eu.kielczewski.example.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import eu.kielczewski.example.core.PackageItem;

public class PackageItemMapper implements ResultSetMapper<PackageItem> {

	public PackageItem map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        PackageItem PI =  new PackageItem();
        
        PI.setItemId(resultSet.getBigDecimal("ITEM_ID"));
        PI.setPackageId(resultSet.getBigDecimal("PACKAGE_ID"));
        
        return PI;
    }
	
}
