package com.anthonyvittoria.springformhandler;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SubmissionController {

    @PostMapping(value = "/submit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> submit(@RequestBody Submission submission) throws IOException {

        Email from = new Email(System.getenv("FROM_EMAIL"));
        Email to = new Email(System.getenv("TO_EMAIL"));

        String subject = "Form Submission from " + submission.getName();
        Content content = new Content(
                "text/html",
                "<p><b>Name:</b> " + submission.getName() + "</p>"
                        + "<p><b>Email:</b> " + submission.getEmail() + "</p>"
                        + "<p><b>Message: </b></p><p>" + submission.getMessage() + "</p>"
        );

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        System.out.println(mail.build());
        request.setBody(mail.build());

        Response response = sg.api(request);

        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Form submission successful", null);
    }
}