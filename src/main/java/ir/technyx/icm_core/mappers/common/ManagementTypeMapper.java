package ir.technyx.icm_core.mappers.common;

import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeDto;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeUpdatableDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeForListDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public interface ManagementTypeMapper {


    static List<ManagementType> toManagementType(List<ReqManagementTypeDto> reqManagementTypeDtoList) {
        return reqManagementTypeDtoList.stream()
                .map(ManagementTypeMapper::toManagementType).collect(Collectors.toList());
    }

    static List<ManagementType> idsToManagementType(List<Long> managementTypeIds) {
        return managementTypeIds.stream().map(ManagementType::new).collect(Collectors.toList());
    }

    static ManagementType toManagementType(ReqManagementTypeDto reqManagementTypeDto) {
        return new ManagementType(
                reqManagementTypeDto.getCode(),
                reqManagementTypeDto.getSubType(),
                reqManagementTypeDto.getTitle(),
                reqManagementTypeDto.getPriority());
    }

    static List<ManagementType> toManagementTypeUpdatable(List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList) {
        return reqManagementTypeUpdatableDtoList.stream()
                .map(ManagementTypeMapper::toManagementTypeUpdatable).collect(Collectors.toList());
    }


    static ManagementType toManagementTypeUpdatable(ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto) {
        return new ManagementType(
                reqManagementTypeUpdatableDto.getId(),
                reqManagementTypeUpdatableDto.getCode(),
                reqManagementTypeUpdatableDto.getSubType(),
                reqManagementTypeUpdatableDto.getTitle(),
                reqManagementTypeUpdatableDto.getPriority());
    }

    static ManagementType toManagementType(Long id) {
        return new ManagementType(id);
    }

    static ManagementType toManagementType(String code, String subType) {
        return new ManagementType(code, subType);
    }


    static Map<String, List<ResManagementTypeForListDto>> toManagementTypeDtoMap(List<ManagementType> managementTypes) {
        return managementTypes.stream()
                .map(mt -> new ResManagementTypeForListDto(mt.getId(), mt.getTitle(), mt.getCode(), mt.getPriority()))
                .collect(groupingBy(ResManagementTypeForListDto::getCode));
    }

    static List<ResManagementTypeForListDto> toResManagementTypeDtoList(List<ManagementType> managementTypes) {
        return managementTypes.stream()
                .map(ManagementTypeMapper::toResManagementTypeForListDto).collect(Collectors.toList());
    }


    static ResManagementTypeForListDto toResManagementTypeForListDto(ManagementType managementType) {
        return new ResManagementTypeForListDto(managementType.getId(), managementType.getTitle(),
                managementType.getCode(), managementType.getPriority());
    }

    static List<ResManagementTypeDto> toResManagementTypeDto(List<ManagementType> managementTypes) {
        return managementTypes.stream().map(ManagementTypeMapper::toResManagementTypeDto).collect(Collectors.toList());
    }

    static ResManagementTypeDto toResManagementTypeDto(ManagementType managementType) {
        return new ResManagementTypeDto(managementType.getId(), managementType.getCode());
    }

}
