package edu.university_connect.domain.entity;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Event extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany
    private List<User> attendees;

    @ManyToMany
    private List<User> invitedUsers;

    public void addAttendee(User attendee){
        if(Objects.isNull(attendees)){
            attendees = new ArrayList<>();
        }
        this.attendees.add(attendee);
    }

    public void addInvitedUsers(User invitedUser) {
        if(Objects.isNull(invitedUsers)){
            invitedUsers = new ArrayList<>();
        }
        this.invitedUsers.add(invitedUser);
    }
}
