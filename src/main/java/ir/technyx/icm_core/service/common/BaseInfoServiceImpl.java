package ir.technyx.icm_core.service.common;

import ir.technyx.icm_core.domain.common.ManagementType;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeDto;
import ir.technyx.icm_core.dto.common.request.ReqManagementTypeUpdatableDto;
import ir.technyx.icm_core.dto.common.response.ResLocationInfoDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeDto;
import ir.technyx.icm_core.dto.common.response.ResManagementTypeForListDto;
import ir.technyx.icm_core.dto.common.response.ResRoleDetailsDto;
import ir.technyx.icm_core.mappers.common.BaseInfoMapper;
import ir.technyx.icm_core.mappers.common.ManagementTypeMapper;
import ir.technyx.icm_core.repository.common.LocationInfoRepository;
import ir.technyx.icm_core.repository.common.ManagementTypeRepository;
import ir.technyx.icm_core.repository.user.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BaseInfoServiceImpl implements BaseInfoService {

    private final ManagementTypeRepository managementTypeRepository;

    private final LocationInfoRepository locationInfoRepository;

    private final RoleRepository roleRepository;


    @Override
    @Transactional
    public List<ResRoleDetailsDto> getIcmRoles() {
        return BaseInfoMapper.toResRoleDetailsDtoList(
                roleRepository.findAllByNameStartingWith("ROLE_ICM").orElseThrow(NullPointerException::new));
    }

    @Override
    @Transactional
    public List<ResRoleDetailsDto> getRoles() {
        return BaseInfoMapper.toResRoleDetailsDtoList(
                roleRepository.findAllByNameStartingWith("ROLE_CU_").orElseThrow(NullPointerException::new));
    }

    public Map<String, List<ResManagementTypeForListDto>> getStructuredAllType() {
        return ManagementTypeMapper.toManagementTypeDtoMap(managementTypeRepository.findAll());
    }


    @Override
    @Transactional
    public List<ResManagementTypeForListDto> getManagementType(String code) {
        return ManagementTypeMapper.toResManagementTypeDtoList(
                managementTypeRepository.findAllByCode(code).orElseThrow(NullPointerException::new)
        );
    }

    @Override
    @Transactional
    public List<ResLocationInfoDto> getAllLocationInfo() {
        return BaseInfoMapper.toLocationInfoDtoList(locationInfoRepository.findAll());
    }

    @Override
    @Transactional
    public ResManagementTypeDto save(ReqManagementTypeDto reqManagementTypeDto) {
        ManagementType managementType = managementTypeRepository.save(
                ManagementTypeMapper.toManagementType(reqManagementTypeDto));
        return ManagementTypeMapper.toResManagementTypeDto(managementType);
    }

    @Override
    @Transactional
    public List<ResManagementTypeDto> save(List<ReqManagementTypeDto> reqManagementTypeDtoList) {
        List<ManagementType> managementTypes = managementTypeRepository.saveAll(
                ManagementTypeMapper.toManagementType(reqManagementTypeDtoList)
        );
        return ManagementTypeMapper.toResManagementTypeDto(managementTypes);
    }

    @Override
    @Transactional
    public ResManagementTypeDto update(ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto) {
        ManagementType managementType = managementTypeRepository.save(
                ManagementTypeMapper.toManagementTypeUpdatable(reqManagementTypeUpdatableDto));

        return ManagementTypeMapper.toResManagementTypeDto(managementType);
    }

    @Override
    @Transactional
    public List<ResManagementTypeDto> update(List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList) {
        List<ManagementType> managementTypes = managementTypeRepository.saveAll(
                ManagementTypeMapper.toManagementTypeUpdatable(reqManagementTypeUpdatableDtoList)
        );

        return ManagementTypeMapper.toResManagementTypeDto(managementTypes);
    }

    @Override
    @Transactional
    public void delete(Long managementTypeId) {
        managementTypeRepository.deleteById(managementTypeId);
    }

    @Override
    @Transactional
    public void delete(ReqManagementTypeUpdatableDto reqManagementTypeUpdatableDto) {
        managementTypeRepository.delete(ManagementTypeMapper.toManagementType(reqManagementTypeUpdatableDto));
    }

    @Override
    @Transactional
    public void delete(List<ReqManagementTypeUpdatableDto> reqManagementTypeUpdatableDtoList) {
        managementTypeRepository.deleteAll(ManagementTypeMapper.toManagementTypeUpdatable(reqManagementTypeUpdatableDtoList));
    }

    @Override
    @Transactional
    public void deleteAll() {
        managementTypeRepository.deleteAll();
    }

}
