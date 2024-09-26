package ir.technyx.icm_core.domain.user;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import ir.technyx.icm_core.domain.common.Address;
import ir.technyx.icm_core.domain.common.ManagementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_gender_type")
    private ManagementType gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_address")
    private Address address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    //TODO @AMirzad add size validation and pattern validation on this field
    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "remember_me")
    private boolean rememberMe;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Token> tokens;

    public User(Long id) {
        setId(id);
    }

    public User(Long id, String username, String firstName, String lastName, String phone, String email,
                boolean rememberMe) {
        this(id, username, null, firstName, lastName, null, null, null, phone, email,
                null, rememberMe);
    }

    public User(Long id, String username, String firstName, String lastName, String phone, String email) {
        this(id, username, null, firstName, lastName, null, null, null, phone, email,
                null);
    }

    public User(String username, String password, String firstName, String lastName, String phone, String email,
                boolean rememberMe) {
        this(null, username, password, firstName, lastName, null, null, null, phone, email,
                null, rememberMe);
    }

    public User(Long id, String username, String firstName, String lastName, LocalDate birthDate,
                ManagementType gender, Address address, String phone, String email, String nationalCode,
                boolean rememberMe) {
        this(id, username, null, firstName, lastName, birthDate, gender, address, phone, email, nationalCode, rememberMe);
    }

    public User(String username, String password, String firstName, String lastName, LocalDate birthDate,
                ManagementType gender, Address address, String phone, String email, String nationalCode,
                boolean rememberMe) {
        this(null, username, password, firstName, lastName, birthDate, gender, address, phone, email, nationalCode, rememberMe);
    }

    public User(Long id, String username, String password, String firstName, String lastName, LocalDate birthDate,
                ManagementType gender, Address address, String phone,
                String email, String nationalCode) {

        setId(id);
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
        setAddress(address);
        setPhone(phone);
        setNationalCode(nationalCode);
        setEmail(email);
    }

    public User(Long id, String username, String password, String firstName, String lastName, LocalDate birthDate,
                ManagementType gender, Address address, String phone,
                String email, String nationalCode, boolean rememberMe) {

        setId(id);
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
        setAddress(address);
        setPhone(phone);
        setNationalCode(nationalCode);
        setEmail(email);
        setRememberMe(rememberMe);
    }

    public User(Long id, String username, String firstName, String lastName, LocalDate birthDate,
                ManagementType gender, Address address, String phone,
                String email, String nationalCode) {

        setId(id);
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
        setAddress(address);
        setPhone(phone);
        setNationalCode(nationalCode);
        setEmail(email);
        setRememberMe(rememberMe);

    }

}
