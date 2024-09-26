package ir.technyx.icm_core.mappers.common;

import ir.technyx.icm_core.domain.common.LocationInfo;
import ir.technyx.icm_core.domain.user.Role;
import ir.technyx.icm_core.dto.common.response.ResLocationInfoDto;
import ir.technyx.icm_core.dto.common.response.ResRoleDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseInfoMapper {

    static ResRoleDetailsDto toResRoleDetailsDto(Role role) {
        return new ResRoleDetailsDto(role.getId(), role.getName(), role.isActive());
    }

    static List<ResRoleDetailsDto> toResRoleDetailsDtoList(List<Role> roles) {
        return roles.stream().map(BaseInfoMapper::toResRoleDetailsDto).collect(Collectors.toList());
    }

    static List<ResLocationInfoDto> toLocationInfoDtoList(List<LocationInfo> locationInfoList) {
        return locationInfoList.stream().map(BaseInfoMapper::toLocationInfo).collect(Collectors.toList());
    }

    static ResLocationInfoDto toLocationInfo(LocationInfo locationInfo) {
        ResLocationInfoDto resLocationInfoDto = new ResLocationInfoDto(
                locationInfo.getId(),
                locationInfo.getLocationType().getTitle(),
                locationInfo.getTitle(),
                locationInfo.getZipCode()
        );

        if (locationInfo.getParent() != null) {
            LocationInfo parent = locationInfo.getParent();
            resLocationInfoDto.setParentTitle(parent.getTitle());
            resLocationInfoDto.setParentTypeTitle(parent.getLocationType().getTitle());
        }

        return resLocationInfoDto;
    }

}
