package tw.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tw.domain.Policy;
import tw.mapper.PolicyMapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PoliciesApiTest extends JerseyTest {

    @Mock
    PolicyMapper policyMapper;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("tw.api").register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(policyMapper).to(PolicyMapper.class);
            }
        });
    }

    @Test
    public void should_create_a_policy() throws Exception {
        Form form  = new Form();
        form.param("title", "straight line depreciation depends on fixed asset lifetime");
        form.param("type", "straight line depreciation");
        form.param("category", "depreciation");
        form.param("formula", "");
        Response response = target("companies/1/policies")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertThat(response.getStatus(), is(201));
        Map result = response.readEntity(Map.class);
        boolean contains = ((String) result.get("uri")).contains("companies/1/policies/" + result.get("id"));
        assertThat(contains, is(true));

    }

    @Test
    public void should_get_a_policy() throws Exception {
        Policy policy = mock(Policy.class);
        when(policy.getTitle()).thenReturn("straight line depreciation depends on fixed asset lifetime");
        when(policy.getId()).thenReturn(1);
        when(policy.getCategory()).thenReturn("depreciation");
        when(policyMapper.getPolicyById(eq(1))).thenReturn(policy);

        Response response = target("companies/1/policies/1")
                .request().get();
        assertThat(response.getStatus(), is(200));
        Map result = response.readEntity(Map.class);
        assertThat(result.get("title"), is("straight line depreciation depends on fixed asset lifetime"));
        assertThat(result.get("id"), is(1));
        assertThat(result.get("category"), is("depreciation"));

    }
}
