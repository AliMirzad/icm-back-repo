package ir.technyx.icm_core.mappers.company.page;

import ir.technyx.icm_core.domain.common.File;
import ir.technyx.icm_core.domain.common.Media;
import ir.technyx.icm_core.domain.company.page.content.Content;
import ir.technyx.icm_core.domain.company.page.content.Page;
import ir.technyx.icm_core.dto.company.page.common.request.*;
import ir.technyx.icm_core.dto.company.page.common.response.*;
import ir.technyx.icm_core.mappers.common.ManagementTypeMapper;
import ir.technyx.icm_core.mappers.company.CompanyMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface PageMapper {

    static List<ResPageStatusDto> toResPageStatusDto(List<String> pageNames) {
        return pageNames.stream().map(PageMapper::toResPageStatusDto).collect(Collectors.toList());
    }

    static ResPageStatusDto toResPageStatusDto(String pageName) {
        return new ResPageStatusDto(pageName, true);
    }

    static Page toPage(Long pageId) {
        return new Page(pageId);
    }

    static Page toPage(ReqPageDto reqPageDto) {
        return new Page(
                CompanyMapper.toCompany(reqPageDto.getCompanyCode()),
                ManagementTypeMapper.toManagementType(reqPageDto.getPageTypeId()),
                reqPageDto.getTitle(), reqPageDto.getIconPath(), reqPageDto.getMetaKeyword(),
                reqPageDto.getMetaDescription(), reqPageDto.isActive(), reqPageDto.getSlug()
        );
    }

    static Page toPage(ReqPageUpdatableDto reqPageUpdatableDto) {
        return new Page(
                reqPageUpdatableDto.getId(),
                CompanyMapper.toCompany(reqPageUpdatableDto.getCompanyCode()),
                ManagementTypeMapper.toManagementType(reqPageUpdatableDto.getPageTypeId()),
                reqPageUpdatableDto.getTitle(), reqPageUpdatableDto.getIconPath(), reqPageUpdatableDto.getMetaKeyword(),
                reqPageUpdatableDto.getMetaDescription(), reqPageUpdatableDto.isActive(), reqPageUpdatableDto.getSlug()
        );
    }

    static ResPageDto toResPageDto(Page page) {
        return new ResPageDto(
                page.getCompany().getId(),
                page.getPageCode(),
                page.getPageType().getTitle(),
                page.getTitle(),
                page.isActive());
    }

    static ResPageUpdatableDto toResPageUpdatableDto(Page page) {
        return new ResPageUpdatableDto(
                page.getId(),
                page.getCompany().getId(),
                page.getPageCode(),
                page.getPageType().getTitle(),
                page.getTitle(),
                page.getIconPath(),
                page.getMetaKeyword(),
                page.getMetaDescription(),
                page.isActive(),
                page.getSlug()
        );
    }

    static List<ResPageUpdatableDto> toResPageUpdatableDto(List<Page> pages) {
        return pages.stream().map(PageMapper::toResPageUpdatableDto).collect(Collectors.toList());
    }

    static Content toContent(ReqPageContentDto reqPageContentDto) {
        return new Content(
                PageMapper.toPage(reqPageContentDto.getPageId()),
                ManagementTypeMapper.toManagementType(reqPageContentDto.getContentTypeId()),
                reqPageContentDto.getIconPath(),
                reqPageContentDto.getTitle(),
                reqPageContentDto.getMetaKeyword(),
                reqPageContentDto.getMetaDescription(),
                reqPageContentDto.isActive(),
                reqPageContentDto.getSlug(),
                reqPageContentDto.getDescription(),
                reqPageContentDto.getPriority(),
                reqPageContentDto.getCoverUrl()
        );
    }

    static Content toContent(ReqPageContentUpdatableDto reqPageContentUpdatableDto) {
        return new Content(
                reqPageContentUpdatableDto.getId(),
                PageMapper.toPage(reqPageContentUpdatableDto.getPageId()),
                ManagementTypeMapper.toManagementType(reqPageContentUpdatableDto.getContentTypeId()),
                reqPageContentUpdatableDto.getIconPath(),
                reqPageContentUpdatableDto.getTitle(),
                reqPageContentUpdatableDto.getMetaKeyword(),
                reqPageContentUpdatableDto.getMetaDescription(),
                reqPageContentUpdatableDto.isActive(),
                reqPageContentUpdatableDto.getSlug(),
                reqPageContentUpdatableDto.getDescription(),
                reqPageContentUpdatableDto.getPriority(),
                reqPageContentUpdatableDto.getCoverUrl()
        );
    }

    static ResPageContentDto toResPageContentDto(Content content) {
        return new ResPageContentDto(
                content.getPage().getTitle(),
                content.getPage().getPageCode(),
                content.getContentType().getTitle(),
                content.getTitle(),
                content.isActive(),
                content.getPriority()
        );
    }

    static ResPageContentUpdatableDto toResPageContentUpdatableDto(Content content) {
        return new ResPageContentUpdatableDto(
                content.getId(),
                content.getPage().getTitle(),
                content.getPage().getPageCode(),
                content.getContentType().getTitle(),
                content.getIconPath(),
                content.getTitle(),
                content.getMetaKeyword(),
                content.getMetaDescription(),
                content.isActive(),
                content.getSlug(),
                content.getDescription(),
                content.getPriority(),
                content.getCoverUrl(),
                toMedia(content.getMediaList())
        );
    }

    static List<ResPageContentUpdatableDto> toResPageContentUpdatableDto(List<Content> contents) {
        return contents.stream().map(PageMapper::toResPageContentUpdatableDto).collect(Collectors.toList());
    }

    static List<ResMediaDto> toMedia(List<Media> medias) {
        return medias.stream().map(PageMapper::toResMediaDto).collect(Collectors.toList());
    }

    static Media toMedia(ReqMediaDto reqMediaDto) {
        Media media = new Media();
        media.setTitle(reqMediaDto.getTitle());
        media.setActive(reqMediaDto.isActive());
        File file = new File(ManagementTypeMapper.toManagementType(reqMediaDto.getFileTypeId()), reqMediaDto.getUrl());
        media.setFile(file);
        return media;
    }

    static ResMediaDto toResMediaDto(Media media) {
        return new ResMediaDto(media.getId(), media.getTitle(), media.getFile().getUrl());
    }

}
