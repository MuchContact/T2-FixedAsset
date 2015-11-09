package tw.domain.json;

import tw.domain.Policy;

import javax.ws.rs.core.UriInfo;

public class PolicyRefJson{
    private Policy policy;
    private UriInfo uri;

    public PolicyRefJson(Policy policy, UriInfo uri) {
        this.policy = policy;
        this.uri = uri;
    }

    
    public String getTitle() {
        return policy.getTitle();
    }

    
    public String getFormula() {
        return policy.getFormula();
    }

    
    public String getType() {
        return policy.getType();
    }

    
    public String getCategory() {
        return policy.getCategory();
    }

    
    public int getId() {
        return policy.getId();
    }

    public String getUri(){
        return uri.getAbsolutePath()+"/"+policy.getId();
    }
}
