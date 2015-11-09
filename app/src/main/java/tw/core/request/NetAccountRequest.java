package tw.core.request;

import tw.core.Depreciation;
import tw.core.FixedAsset;
import tw.core.NetWorth;
import tw.core.Repairment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NetAccountRequest {
    private FixedAsset fixedAsset;
    private Repairment repairment;
    private Depreciation depreciation;
    private boolean repairMode;

    public NetAccountRequest(FixedAsset fixedAsset, Depreciation depreciation) {
        this.fixedAsset = fixedAsset;
        this.depreciation = depreciation;
    }

    public NetAccountRequest(FixedAsset fixedAsset, Repairment repairment) {
        this.fixedAsset = fixedAsset;
        this.repairment = repairment;
        this.repairMode = true;
    }

    public NetAccountRequest(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    public NetWorth generateNetWorth() {
        double newAmount = fixedAsset.getLatestNetWorth().getAmount();

        if(repairMode){
            newAmount = fixedAsset.getLatestNetWorth().getAmount() + repairment.calculatChangedAmount();
            newAmount -= fixedAsset.getPolicy().getDecreasedAmount(fixedAsset, repairment.getRepairDate());
        }else{
            if(depreciation!=null)
                newAmount = fixedAsset.getLatestNetWorth().getAmount() - depreciation.calculatDecreasedAmount();
        }
        Date now = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        fixedAsset.addNewNetWorth(new NetWorth(newAmount, ft.format(now)));
        return fixedAsset.getLatestNetWorth();
    }
}
