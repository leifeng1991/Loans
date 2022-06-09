package com.base.devices.data;

import static com.base.devices.utils.OtherUtils.getAvailMemory;
import static com.base.devices.utils.OtherUtils.getExternalAvailableSize;
import static com.base.devices.utils.OtherUtils.getExternalTotalSize;
import static com.base.devices.utils.OtherUtils.getInternalAvailableSize;
import static com.base.devices.utils.OtherUtils.getInternalTotalSize;
import static com.base.devices.utils.OtherUtils.getTotalMemory;

public class StorageData {

    public long ram_total_size;
    public long ram_usable_size;
    public long internal_storage_total;
    public long internal_storage_usable;
    public long memory_card_size;
    public long memory_card_size_use;
    public long memory_card_usable_size;
    public String contain_sd = memory_card_size <= 0 ? "1" : "0";
    public String extra_sd;

    {
        ram_total_size = getTotalMemory();
        ram_usable_size = getAvailMemory();
        internal_storage_total = getInternalTotalSize();
        internal_storage_usable = getInternalAvailableSize();
        memory_card_size = getExternalTotalSize();
        memory_card_size_use = getExternalTotalSize() - getExternalAvailableSize();
        memory_card_usable_size = getExternalAvailableSize();
    }

}