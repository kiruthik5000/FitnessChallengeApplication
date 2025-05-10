package com.myapp.fitnessapplication.service;

import com.myapp.fitnessapplication.entity.FitnessDetails;
import com.myapp.fitnessapplication.entity.Trainer;
import com.myapp.fitnessapplication.entity.UserDetails;
import com.myapp.fitnessapplication.repository.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    FitnessService fitnessService;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    JavaMailSender sender;

    // getFunctions
    public List<UserDetails> getAllUsers() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public UserDetails getUserById(Integer userId) {
        try {
            return userRepo.findByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    // postFunctions
    public UserDetails saveUser(UserDetails userDetails) {
        try {
            return userRepo.save(userDetails);
        } catch (Exception e) {
            return null;
        }
    }

    public String authenticateUser(String userEmail, String password) {
        try {
            UserDetails user = userRepo.findByUserEmail(userEmail);
            if (user == null) {
                return "Invalid email Please Enter Valid email";
            } else if (!user.getPassword().equals(password)) {
                return "Invalid password";
            } else {
                return "Login Successful";
            }
        } catch (Exception e) {
            return "Authentication failed";
        }
    }

    // putFunctions
    public String resetPassword(int userId, String oldPassword, String newPassword) {
        try {
            UserDetails userDetails = userRepo.findByUserId(userId);
            if (userDetails == null) {
                return "User not found";
            }
            if (!userDetails.getPassword().equals(oldPassword)) {
                return "Wrong password";
            }
            userDetails.setPassword(newPassword);
            userRepo.save(userDetails);
            return "Password changed Successfully";
        } catch (Exception e) {
            return "Failed to reset password";
        }
    }

    public String changeUsername(int userId, String newUsername) {
        try {
            UserDetails userDetails = userRepo.findByUserId(userId);
            if (userDetails == null) {
                return "User not found";
            } else {
                userDetails.setUserName(newUsername);
                userRepo.save(userDetails);
                return "Username changed Successfully";
            }
        } catch (Exception e) {
            return "Failed to change username";
        }
    }

    // delete Functions
    public String deleteUser(Integer userId) {
        try {
            UserDetails userDetails = userRepo.findByUserId(userId);
            if (userDetails == null) {
                return "User not found";
            }
            fitnessService.deleteFitnessDetails(userId);
            workoutService.deleteWorkout(userId);
            feedbackService.deleteAllUserFeedback(userId);
            userRepo.delete(userDetails);
            return "User deleted Successfully";
        } catch (Exception e) {
            return "Failed to delete user";
        }
    }

    // forgot password
    public String sendResetPasswordEmail(String email) {
        try {
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(email);
            helper.setSubject("Your Reset Password link");
            String content = "<p>Hello,</p>"
                    + "<p>Click the link below to reset your password:</p>"
                    + "<p><a href='https://skcet.amypo.com/Forgot_Password'>Click Here</a></p>"
                    + "<p>Best Regards,<br>Your Team</p>";
            helper.setText(content, true);
            sender.send(msg);

            return "Reset Password link Sent";
        } catch (MessagingException e) {
            return "Cannot Send Reset Password Email";
        }
    }

    // contains
    public List<UserDetails> userContainAge(String name) {
        try {
            return userRepo.findByUserNameContainingIgnoreCase(name);
        } catch (Exception e) {
            System.out.println("Invalid age");
            return null;
        }
    }
}
