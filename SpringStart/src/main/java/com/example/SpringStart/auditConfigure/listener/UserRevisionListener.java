package com.example.SpringStart.auditConfigure.listener;

import com.example.SpringStart.auditConfigure.contextHolder.ApplicationContextHolder;
import com.example.SpringStart.auditConfigure.defaultRevision.UserEntityRevision;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.userdetails.User;

public class UserRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        UserEntityRevision userEntityRevision = (UserEntityRevision) revisionEntity;
        User currentUser = ApplicationContextHolder.getBean(CurrentUserProvider.class).getCurrentUser();
        userEntityRevision.setUsername(currentUser.getUsername());
    }
}