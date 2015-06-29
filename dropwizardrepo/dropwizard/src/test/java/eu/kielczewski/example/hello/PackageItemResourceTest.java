package eu.kielczewski.example.hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;


import com.fasterxml.jackson.core.JsonProcessingException;

import eu.kielczewski.example.core.PackageItem;
import eu.kielczewski.example.dao.PackageItemDAO;

@RunWith(MockitoJUnitRunner.class)
public class PackageItemResourceTest {

	private static final PackageItemDAO dao = mock(PackageItemDAO.class);
	
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
	            .addResource(new PackageItemResource(dao))
	            .build();
	
	@Captor
    private ArgumentCaptor<PackageItem> packageItemCaptor;
    private PackageItem packageItem;
    
    @Before
    public void setUp() {
        packageItem = new PackageItem();
        packageItem.setItemId(BigDecimal.valueOf(20));
        packageItem.setPackageId(BigDecimal.valueOf(30));
    }

    @After
    public void tearDown() {
        reset(dao);
    }
    
    @Test
    public void add() throws JsonProcessingException {
        when(dao.insert(any(PackageItem.class))).thenReturn(1);
        final Response response = resources.client().target("/people")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(packageItem, MediaType.APPLICATION_JSON_TYPE));

        verify(dao).insert(packageItemCaptor.capture());
        assertThat(packageItemCaptor.getValue()).isEqualTo(packageItem);
    }

}
