package eu.kielczewski.example.hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.math.BigDecimal;

import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import eu.kielczewski.example.core.PackageItem;
import eu.kielczewski.example.dao.PackageItemDAO;

@RunWith(MockitoJUnitRunner.class)
public class ResourceTest {

	private static final PackageItemDAO dao = mock(PackageItemDAO.class);
	
	@ClassRule
    public static final ResourceTestRule RULE = ResourceTestRule.builder()
            .addResource(new PackageItemResource(dao))
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();
	
	private PackageItem packageItem;
	
	@Before
	public void setup() {
		packageItem = new PackageItem();
		packageItem.setItemId(BigDecimal.valueOf(20));
        packageItem.setPackageId(BigDecimal.valueOf(30));
	}
	
	@After
	public void tearDown() {
		reset(dao);
	}
	
	@Test
    public void testGetPerson() {
		when(dao.findById(20)).thenReturn(packageItem);
		PackageItem found = RULE.getJerseyTest().target("/people/20").request().get(PackageItem.class);

        assertThat(found.getItemId()).isEqualTo(packageItem.getItemId());
        verify(dao).findById(20);
    }
}
