package eu.kielczewski.example.hello;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.kielczewski.example.core.PackageItem;

public class CoreTest {
	
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
    	 final PackageItem packageItem = new PackageItem();
         packageItem.setItemId(BigDecimal.valueOf(20));
         packageItem.setPackageId(BigDecimal.valueOf(30));

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/packageitem.json"), PackageItem.class));

        assertThat(MAPPER.writeValueAsString(packageItem)).isEqualTo(expected);
    }
    
    @Test
    public void deserializesFromJSON() throws Exception {
    	final PackageItem packageItem = new PackageItem();
        packageItem.setItemId(BigDecimal.valueOf(20));
        packageItem.setPackageId(BigDecimal.valueOf(30));
        
        PackageItem pt = MAPPER.readValue(fixture("fixtures/packageitem.json"), PackageItem.class);
        
        assertThat(pt.getItemId().equals(packageItem.getItemId()));
        assertThat(pt.getPackageId().equals(packageItem.getPackageId()));
    }

}
