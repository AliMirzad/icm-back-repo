package ir.technyx.icm_core.domain.common;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_management_type", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "sub_type", "priority"}))
@Getter
@Setter
@NoArgsConstructor
public class ManagementType extends BaseEntity {

    public static final String GENDER = "gender";
    public static final String GENDER_MAN = "man";
    public static final String GENDER_WOMAN = "woman";
    public static final String GENDER_OTHER = "other";

    public static final String LOCATION_TYPE = "location";
    public static final String LOCATION_COUNTRY = "country";
    public static final String LOCATION_PROVINCE = "province";
    public static final String LOCATION_CITY = "city";

    public static final String ACCESS_TYPE = "access_type";
    public static final String ACCESS_LEVEL_WRITE = "write";
    public static final String ACCESS_LEVEL_READ = "read";
    public static final String ACCESS_LEVEL_UPDATE = "update";
    public static final String ACCESS_LEVEL_DELETE = "delete";

    public static final String SPECIALIZED_PAGE_TYPE = "specialized_page_type";
    public static final String CLIENT_PAGE_TYPE = "client_page_type";
    public static final String SPECIALIZED_PAGE_FAQ_TYPE = "specialized_page_faq_type";
    public static final String SPECIALIZED_PAGE_TYPE_NEWS = "specialized_page_type_news";
    public static final String SPECIALIZED_PAGE_TYPE_GALLERY = "specialized_page_type_gallery";
    public static final String SPECIALIZED_PAGE_TYPE_CONTACT_US = "specialized_page_type_contact_us";
    public static final String CLIENT_PAGE_TYPE_BLOG = "client_page_type_blog";
    public static final String CLIENT_PAGE_TYPE_TUTORIAL = "client_page_type_tutorial";


    public static final String CONTENT_TYPE = "content_type";
    public static final String CONTENT_TYPE_TEXT = "only_text";
    public static final String CONTENT_TYPE_IMAGE = "only_image";
    public static final String CONTENT_TYPE_TEXT_IMAGE = "text_image";

    public static final String FILE_TYPE = "file_type";
    public static final String FILE_TYPE_JPG = "jpg";
    public static final String FILE_TYPE_PNG = "png";
    public static final String FILE_TYPE_GIF = "gif";

    public static final String ADDITIONAL_INFO = "additional_info";

    public static final String CHANGE_FREQ_TYPE = "change_freq_type";
    public static final String CHANGE_FREQ_TYPE_ALWAYS = "always";
    public static final String CHANGE_FREQ_TYPE_HOURLY = "hourly";
    public static final String CHANGE_FREQ_TYPE_DAILY = "daily";
    public static final String CHANGE_FREQ_TYPE_WEEKLY = "weekly";
    public static final String CHANGE_FREQ_TYPE_MONTHLY = "monthly";
    public static final String CHANGE_FREQ_TYPE_YEARLY = "yearly";
    public static final String CHANGE_FREQ_TYPE_NEVER = "never";


    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "sub_type")
    private String subType;


    public ManagementType(Long id) {
        setId(id);
    }

    public ManagementType(String code, String subType) {
        this(code, subType, null, null);
    }

    public ManagementType(String code, String title, Integer priority) {
        this(code, null, title, priority);
    }

    public ManagementType(String code, String subType, String title, Integer priority) {
        this(null, code, subType, title, priority);
    }

    public ManagementType(Long id, String code, String subType, String title, Integer priority) {
        setId(id);
        setCode(code);
        setSubType(subType);
        setTitle(title);
        setPriority(priority);
    }
}
