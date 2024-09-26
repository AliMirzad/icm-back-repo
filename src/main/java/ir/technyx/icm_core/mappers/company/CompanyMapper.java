package ir.technyx.icm_core.mappers.company;

import ir.technyx.icm_core.domain.common.Address;
import ir.technyx.icm_core.domain.company.Company;
import ir.technyx.icm_core.domain.company.CompanyUser;
import ir.technyx.icm_core.domain.user.User;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyDto;
import ir.technyx.icm_core.dto.company.company.request.ReqCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyListDto;
import ir.technyx.icm_core.dto.company.company.response.ResCompanyUpdatableDto;
import ir.technyx.icm_core.dto.company.company_user.request.ReqCompanyUserDto;
import ir.technyx.icm_core.mappers.common.AddressMapper;
import ir.technyx.icm_core.utils.UserUtility;

import java.util.List;
import java.util.stream.Collectors;

public interface CompanyMapper {

    static Company toCompany(Long id) {
        return new Company(id);
    }

    static Company toCompany(String code) {
        return new Company(code);
    }

    static Company toCompany(ReqCompanyDto reqCompanyDto) {
        Company company = new Company(
                reqCompanyDto.getName(),
                reqCompanyDto.getNationalCode(),

                AddressMapper.toAddress(reqCompanyDto.getLocationInfoId(),
                        reqCompanyDto.getExactLocation(),
                        reqCompanyDto.getPostalCode()
                ),

                reqCompanyDto.getPhone(),
                reqCompanyDto.getEmail(),
                reqCompanyDto.getHostUrl(),
                UserUtility.getCurrentRegistrationInfo()
        );
        company.setCode(UserUtility.createCompanyCode(company.getHostUrl()));
        return company;
    }

    static Company toCompany(ReqCompanyUpdatableDto reqCompanyUpdatableDto) {
        return new Company(
                reqCompanyUpdatableDto.getId(),
                reqCompanyUpdatableDto.getName(),
                reqCompanyUpdatableDto.getNationalCode(),
                AddressMapper.toAddress(
                        reqCompanyUpdatableDto.getLocationInfoId(),
                        reqCompanyUpdatableDto.getExactLocation(),
                        reqCompanyUpdatableDto.getPostalCode()
                ),
                reqCompanyUpdatableDto.getPhone(),
                reqCompanyUpdatableDto.getEmail(),
                reqCompanyUpdatableDto.getHostUrl(),
                UserUtility.getCurrentRegistrationInfo()
        );
    }

    static ResCompanyDto toResCompanyDto(Company company) {
        return new ResCompanyDto(company.getId(), company.getCode(), company.getName());
    }

    static ResCompanyUpdatableDto toResCompanyUpdatableDto(Company company) {
        Address address = company.getAddress();

        if (address != null && address.getLocationInfo() != null) {
            return new ResCompanyUpdatableDto(
                    company.getId(),
                    company.getCode(),
                    company.getName(),
                    company.getNationalCode(),
                    address.getLocationInfo().getId(),
                    address.getExactLocation(),
                    address.getPostalCode(),
                    company.getPhone(),
                    company.getEmail(),
                    company.getHostUrl()
            );
        } else {
            return new ResCompanyUpdatableDto(
                    company.getId(),
                    company.getCode(),
                    company.getName(),
                    company.getNationalCode(),
                    company.getPhone(),
                    company.getEmail(),
                    company.getHostUrl()
            );
        }
    }

    static CompanyUser toCompanyUser(ReqCompanyUserDto reqCompanyUserDto) {
        return new CompanyUser(new Company(reqCompanyUserDto.getCompanyId()), new User(reqCompanyUserDto.getUserId()), null);
    }


    static List<ResCompanyListDto> toResCompanyListDto(List<Company> companies) {
        return companies.stream().map(CompanyMapper::toResCompanyListDto).collect(Collectors.toList());
    }

    static ResCompanyListDto toResCompanyListDto(Company company) {
        return new ResCompanyListDto(
                company.getId(),
                company.getCode(),
                company.getName(),
                company.getNationalCode(),
                company.getAddress().getPostalCode(),
                company.getPhone(),
                company.getEmail(),
                company.getHostUrl()
        );
    }

}
