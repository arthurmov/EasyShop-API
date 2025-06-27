package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    @GetMapping
    public Profile getById(Principal principal)
    {
        String username = principal.getName();
        User user = userDao.getByUserName(username);
        int userId = user.getId();

        return profileDao.getByUserId(userId);
    }

    @PutMapping
    public void updateProfile(@RequestBody Profile profile, Principal principal)
    {
        profileDao.update(profile.getUserId(), profile);
    }
}
