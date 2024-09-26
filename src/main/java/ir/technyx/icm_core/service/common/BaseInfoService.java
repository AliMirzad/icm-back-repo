package ir.technyx.icm_core.service.common;

import ir.technyx.icm_core.dto.common.request.ReqManagementTypeDto;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeUpdatableDto;
import ir.technyx.icm_core.dto.common.response.ResLocationInfoDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeForListDto;
import ir.technyx.icm_core.dto.common.response.ResRoleDetailsDto;

import java.util.List;
import java.util.Map;

public interface BaseInfoService {

    List<ResRoleDetailsDto> getIcmRoles();

    List<ResRoleDetailsDto> getRoles();

    Map<String, List<ResManagementTypeForListDto>> getStructuredAllType();

    List<ResManagementTypeForListDto> getManagementType(String code);

    List<ResLocationInfoDto> getAllLocationInfo();

    ResManagementTypeDto save(ReqManagementTypeDto reqManagementTypeDto);

    List<ResManagementTypeDto> save(List<ReqManagementTypeDto> reqManagementTypeDtoList);

    ResManagementTypeDto update(ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto);

    List<ResManagementTypeDto> update(List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList);

    void delete(Long managementTypeId);

    void delete(ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto);

    void delete(List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList);

    void deleteAll();

}
