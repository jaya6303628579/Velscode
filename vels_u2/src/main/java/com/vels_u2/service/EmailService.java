package com.vels_u2.service;

import com.vels_u2.dto.EnquiryRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.owner.email}")
    private String ownerEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEnquiry(EnquiryRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(ownerEmail);
            helper.setSubject("✨ New Event Enquiry — VEL's U2 Event Management");
            helper.setText(buildHtmlEmail(request), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send enquiry email", e);
        }
    }

    private String buildHtmlEmail(EnquiryRequest r) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8"/>
              <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
              <title>New Event Enquiry</title>
            </head>
            <body style="margin:0;padding:0;background:#f4f0eb;font-family:'Segoe UI',Arial,sans-serif;">

              <!-- Wrapper -->
              <table width="100%%" cellpadding="0" cellspacing="0" border="0"
                     style="background:#f4f0eb;padding:40px 16px;">
                <tr><td align="center">

                  <!-- Card -->
                  <table width="600" cellpadding="0" cellspacing="0" border="0"
                         style="max-width:600px;width:100%%;border-radius:16px;overflow:hidden;
                                box-shadow:0 8px 40px rgba(0,0,0,0.18);">

                    <!-- ── HEADER ── -->
                    <tr>
                      <td style="background:linear-gradient(135deg,#1a0e0e 0%%,#2d1a0e 60%%,#1a0e0e 100%%);
                                 padding:40px 40px 32px;text-align:center;">
                        <!-- Badge ring -->
                        <div style="display:inline-block;width:80px;height:80px;border-radius:50%%;
                                    border:3px solid #c9a96e;overflow:hidden;margin-bottom:18px;
                                    box-shadow:0 0 0 6px rgba(201,169,110,0.15);">
                          <img src="https://ui-avatars.com/api/?name=VU&background=1a0e0e&color=c9a96e&size=80&bold=true&font-size=0.4"
                               width="80" height="80" alt="VEL's U2"
                               style="display:block;border-radius:50%%;"/>
                        </div>
                        <h1 style="margin:0 0 4px;font-size:26px;font-weight:700;
                                   color:#c9a96e;letter-spacing:3px;font-family:Georgia,serif;">
                          VEL'S U2
                        </h1>
                        <p style="margin:0 0 20px;font-size:11px;color:#d4b896;
                                  letter-spacing:5px;text-transform:uppercase;">
                          Event Management
                        </p>
                        <!-- Divider -->
                        <div style="width:60px;height:2px;background:linear-gradient(90deg,transparent,#c9a96e,transparent);
                                    margin:0 auto 20px;"></div>
                        <h2 style="margin:0;font-size:18px;color:#ffffff;font-weight:400;
                                   letter-spacing:1px;">
                          ✨ New Event Enquiry Received
                        </h2>
                      </td>
                    </tr>

                    <!-- ── ALERT BANNER ── -->
                    <tr>
                      <td style="background:#c9a96e;padding:12px 40px;text-align:center;">
                        <p style="margin:0;font-size:13px;font-weight:600;color:#1a0e0e;
                                  letter-spacing:1.5px;text-transform:uppercase;">
                          🎉 A new client is interested in your services!
                        </p>
                      </td>
                    </tr>

                    <!-- ── BODY ── -->
                    <tr>
                      <td style="background:#ffffff;padding:40px;">

                        <!-- Client Info Heading -->
                        <h3 style="margin:0 0 20px;font-size:13px;font-weight:700;color:#c9a96e;
                                   letter-spacing:3px;text-transform:uppercase;
                                   border-left:3px solid #c9a96e;padding-left:12px;">
                          Client Information
                        </h3>

                        <!-- Info cards row -->
                        <table width="100%%" cellpadding="0" cellspacing="0" border="0" style="margin-bottom:28px;">
                          <tr>
                            <td width="48%%" valign="top" style="padding-right:8px;">
                              %s
                              %s
                            </td>
                            <td width="4%%"></td>
                            <td width="48%%" valign="top" style="padding-left:8px;">
                              %s
                            </td>
                          </tr>
                        </table>

                        <!-- Event Info Heading -->
                        <h3 style="margin:0 0 20px;font-size:13px;font-weight:700;color:#c9a96e;
                                   letter-spacing:3px;text-transform:uppercase;
                                   border-left:3px solid #c9a96e;padding-left:12px;">
                          Event Details
                        </h3>

                        <!-- Event cards -->
                        <table width="100%%" cellpadding="0" cellspacing="0" border="0" style="margin-bottom:28px;">
                          <tr>
                            <td width="31%%" valign="top" style="padding-right:6px;">%s</td>
                            <td width="3%%"></td>
                            <td width="31%%" valign="top" style="padding:0 3px;">%s</td>
                            <td width="3%%"></td>
                            <td width="31%%" valign="top" style="padding-left:6px;">%s</td>
                          </tr>
                        </table>

                        <!-- Requirements Heading -->
                        <h3 style="margin:0 0 14px;font-size:13px;font-weight:700;color:#c9a96e;
                                   letter-spacing:3px;text-transform:uppercase;
                                   border-left:3px solid #c9a96e;padding-left:12px;">
                          Special Requirements
                        </h3>
                        <div style="background:#fdf9f4;border:1px solid #e8ddd0;border-radius:10px;
                                    padding:20px 22px;margin-bottom:32px;">
                          <p style="margin:0;font-size:14px;color:#4a3728;line-height:1.8;">
                            %s
                          </p>
                        </div>

                        <!-- CTA Button -->
                        <table width="100%%" cellpadding="0" cellspacing="0" border="0">
                          <tr>
                            <td align="center">
                              <a href="mailto:%s?subject=Re: Your Event Enquiry - VEL's U2"
                                 style="display:inline-block;background:linear-gradient(135deg,#c9a96e,#e8c97a);
                                        color:#1a0e0e;font-size:13px;font-weight:700;letter-spacing:2px;
                                        text-transform:uppercase;text-decoration:none;
                                        padding:14px 36px;border-radius:50px;
                                        box-shadow:0 4px 20px rgba(201,169,110,0.4);">
                                ✉ Reply to Client
                              </a>
                            </td>
                          </tr>
                        </table>

                      </td>
                    </tr>

                    <!-- ── DIVIDER ── -->
                    <tr>
                      <td style="background:#ffffff;padding:0 40px;">
                        <div style="height:1px;background:linear-gradient(90deg,transparent,#e8ddd0,transparent);"></div>
                      </td>
                    </tr>

                    <!-- ── QUICK STATS ── -->
                    <tr>
                      <td style="background:#ffffff;padding:28px 40px;">
                        <table width="100%%" cellpadding="0" cellspacing="0" border="0">
                          <tr>
                            <td width="33%%" align="center">
                              <p style="margin:0 0 4px;font-size:22px;font-weight:700;color:#c9a96e;">%s</p>
                              <p style="margin:0;font-size:10px;color:#999;letter-spacing:2px;text-transform:uppercase;">Guests</p>
                            </td>
                            <td width="33%%" align="center" style="border-left:1px solid #e8ddd0;border-right:1px solid #e8ddd0;">
                              <p style="margin:0 0 4px;font-size:22px;font-weight:700;color:#c9a96e;">%s</p>
                              <p style="margin:0;font-size:10px;color:#999;letter-spacing:2px;text-transform:uppercase;">Event Type</p>
                            </td>
                            <td width="33%%" align="center">
                              <p style="margin:0 0 4px;font-size:22px;font-weight:700;color:#c9a96e;">%s</p>
                              <p style="margin:0;font-size:10px;color:#999;letter-spacing:2px;text-transform:uppercase;">Event Date</p>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>

                    <!-- ── FOOTER ── -->
                    <tr>
                      <td style="background:linear-gradient(135deg,#1a0e0e 0%%,#2d1a0e 100%%);
                                 padding:30px 40px;text-align:center;">
                        <p style="margin:0 0 8px;font-size:14px;font-weight:700;
                                  color:#c9a96e;letter-spacing:2px;">VEL'S U2 EVENT MANAGEMENT</p>
                        <p style="margin:0 0 16px;font-size:12px;color:#a08060;letter-spacing:1px;">
                          Dindigul, Tamil Nadu, India
                        </p>
                        <!-- Divider -->
                        <div style="width:40px;height:1px;background:#c9a96e;margin:0 auto 16px;opacity:0.5;"></div>
                        <p style="margin:0;font-size:11px;color:#6b5040;line-height:1.6;">
                          This email was auto-generated from your VEL's U2 website booking form.<br/>
                          © 2026 VEL's U2 Event Management. All rights reserved.
                        </p>
                      </td>
                    </tr>

                  </table>
                  <!-- /Card -->

                </td></tr>
              </table>
              <!-- /Wrapper -->

            </body>
            </html>
            """.formatted(
                infoCard("👤", "Full Name",    r.getName()),
                infoCard("📞", "Phone",        r.getPhone()),
                infoCard("📧", "Email",        r.getEmail()),
                eventBadge("🎊", "Event Type", r.getEventType()),
                eventBadge("📅", "Event Date", r.getEventDate()),
                eventBadge("👥", "Guests",     r.getGuests()),
                r.getRequirements() != null && !r.getRequirements().isBlank()
                    ? r.getRequirements().replace("\n", "<br/>")
                    : "<em style='color:#aaa;'>No special requirements mentioned.</em>",
                r.getEmail(),
                r.getGuests(),
                r.getEventType(),
                r.getEventDate()
        );
    }

    /** A labelled info card used for client info */
    private String infoCard(String icon, String label, String value) {
        String safeValue = (value != null && !value.isBlank()) ? value : "—";
        return """
            <div style="background:#fdf9f4;border:1px solid #e8ddd0;border-radius:10px;
                        padding:14px 16px;margin-bottom:12px;">
              <p style="margin:0 0 4px;font-size:10px;color:#c9a96e;font-weight:700;
                        letter-spacing:2px;text-transform:uppercase;">%s %s</p>
              <p style="margin:0;font-size:15px;color:#1a0e0e;font-weight:600;">%s</p>
            </div>
            """.formatted(icon, label, safeValue);
    }

    /** A compact badge card used for event details */
    private String eventBadge(String icon, String label, String value) {
        String safeValue = (value != null && !value.isBlank()) ? value : "—";
        return """
            <div style="background:linear-gradient(135deg,#1a0e0e,#2d1a0e);
                        border-radius:10px;padding:16px 12px;text-align:center;">
              <p style="margin:0 0 6px;font-size:22px;">%s</p>
              <p style="margin:0 0 4px;font-size:10px;color:#c9a96e;font-weight:700;
                        letter-spacing:2px;text-transform:uppercase;">%s</p>
              <p style="margin:0;font-size:13px;color:#ffffff;font-weight:600;
                        word-break:break-word;">%s</p>
            </div>
            """.formatted(icon, label, safeValue);
    }
}
