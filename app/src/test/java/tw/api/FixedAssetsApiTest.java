package tw.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tw.domain.Depreciation;
import tw.domain.FixedAsset;
import tw.mapper.DepreciationMapper;
import tw.mapper.FixedAssetMapper;
import tw.mapper.NetWorthMapper;
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
public class FixedAssetsApiTest extends JerseyTest{

    @Mock
    PolicyMapper policyMapper;

    @Mock
    FixedAssetMapper assetMapper;

    @Mock
    DepreciationMapper depreciationMapper;

    @Mock
    NetWorthMapper netWorthMapper;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("tw.api").register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(policyMapper).to(PolicyMapper.class);
                bind(assetMapper).to(FixedAssetMapper.class);
                bind(depreciationMapper).to(DepreciationMapper.class);
                bind(netWorthMapper).to(NetWorthMapper.class);
            }
        });
    }

    @Test
    public void should_introduce_a_new_fixed_asset() throws Exception {

        Form form  = new Form();
        form.param("unique_number", "FA-27483739");
        form.param("original worth", "150000");
        form.param("lifetime", "10 years");
        form.param("start date", "2015-10-1");
        Response response = target("companies/1/fixed assets")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(302));
        Map result = response.readEntity(Map.class);
        assertThat(result.get("uniqueNumber"), is("FA-27483739"));

    }

    @Test
    public void should_introduce_a_new_fixed_asset_and_be_redirected_to_net_accounting_request() throws Exception {
        Form form  = new Form();
        form.param("unique_number", "FA-27483739");
        form.param("original worth", "150000");
        form.param("lifetime", "10 years");
        form.param("start date", "2015-10-1");
        Response response = target("companies/1/fixed assets")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(302));
        Map result = response.readEntity(Map.class);
        String suffix = "companies/1/fixed%20assets/" + result.get("id");

        assertThat(result.get("URI").toString().contains(suffix), is(true));

        String suffix2 = suffix+"/net accounting request";
        assertThat(result.get("forwardURI").toString().contains(suffix2), is(true));

    }

    @Test
    public void should_be_redirected_to_accounting_request_after_depreciation() throws Exception {
        Form form  = new Form();
        form.param("accountDay", "2015-10-1");
        form.param("policy", "compaies/1/policies/1");
        Response response = target("companies/1/fixed assets/1/depreciations")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(302));
        Map result = response.readEntity(Map.class);
        String suffix = "companies/1/fixed%20assets/1/depreciations/" + result.get("id");
        assertThat(result.get("URI").toString().contains(suffix), is(true));

        String suffix2 = suffix+"/net accounting request";
        assertThat(result.get("forwardURI").toString().contains(suffix2), is(true));

    }

    @Test
    public void should_generate_new_worth_after_net_accounting_request() throws Exception {
        when(assetMapper.findFixedAssetById(eq(1))).thenReturn(mock(FixedAsset.class));
        when(depreciationMapper.findDepreciationById(eq(1))).thenReturn(mock(Depreciation.class));
        Form form = new Form();
        Response response = target("companies/1/fixed assets/1/depreciations/1/net accounting request")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        assertThat(response.getStatus(), is(201));
        Map result = response.readEntity(Map.class);
        System.out.println(result.get("URI").toString());
        System.out.println(result.get("id"));
        String suffix = "compaies/1/fixed assets/1/net worths/" + result.get("id");
        assertThat(result.get("URI").toString().contains(suffix), is(true));
    }
}
