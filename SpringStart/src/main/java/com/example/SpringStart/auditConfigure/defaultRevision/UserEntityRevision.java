package com.example.SpringStart.auditConfigure.defaultRevision;

import com.example.SpringStart.auditConfigure.listener.UserRevisionListener;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Getter
@Entity
@Setter
@NoArgsConstructor
@RevisionEntity(UserRevisionListener.class)

public class UserEntityRevision extends DefaultRevisionEntity {

    private String username;

}
