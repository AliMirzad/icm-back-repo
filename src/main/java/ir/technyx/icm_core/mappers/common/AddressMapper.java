package ir.technyx.icm_core.mappers.common;

import ir.technyx.icm_core.domain.common.Address;
import ir.technyx.icm_core.domain.common.LocationInfo;

public interface AddressMapper {

    static Address toAddress(Long locationInfoId, String exactLocation, String postalCode) {
        LocationInfo locationInfo = new LocationInfo(locationInfoId);
        return new Address(locationInfo, exactLocation, postalCode);
    }


}
