package pi.app.estatemarket.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="User")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userID;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "profile_picture")
    private String profilePicture;
    
    @Column(name = "date_created")
    private Date dateCreated;
    
    @Column(name = "date_modified")
    private Date dateModified;
    
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    
    @Column(name = "password")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;
    
    @JsonIgnore
    @ManyToOne
    private Role role;
    
    @JsonIgnore
    @OneToMany (mappedBy = "userPub")
    private Set<Publication> publications;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userMessage")
    private Set<Message> messagess;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userComment")
    private Set<Comment> comments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userAgency")
    private Set<Agency> agencies;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userContract")
    private Set<Contract> contracts;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userAnnouncement")
    private Set<Announcement> announcements;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Appointment> appointments=new HashSet<>();


}
