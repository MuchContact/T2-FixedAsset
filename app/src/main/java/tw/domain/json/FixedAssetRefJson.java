package tw.domain.json;

import tw.domain.FixedAsset;

import javax.ws.rs.core.UriInfo;

public class FixedAssetRefJson {
    private FixedAsset fixedAsset;
    private UriInfo uri;

    public FixedAssetRefJson(FixedAsset fixedAsset, UriInfo uri) {

        this.fixedAsset = fixedAsset;
        this.uri = uri;
    }


    public String getUniqueNumber() {
        return fixedAsset.getUniqueNumber();
    }


    public double getOriginalWorth() {
        return fixedAsset.getOriginalWorth();
    }


    public String getLifetime() {
        return fixedAsset.getLifetime();
    }


    public String getStartDate() {
        return fixedAsset.getStartDate();
    }

    public String getUri() {
        return uri.getAbsolutePath() + "/" + fixedAsset.getId();
    }
}
