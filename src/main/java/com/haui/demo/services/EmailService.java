package com.haui.demo.services;

import com.haui.demo.interceptors.AuthInterceptor;
import com.haui.demo.utils.TaskPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final String SUBJECT = "From: DaoGreenT - Bất động sản của bạn đã được quan tâm";
    private static final String EMAIL = "trandaogrey@gmail.com";
    private static final String BODY = "C%C3%B4ng%20ty%20c%E1%BB%95%20ph%E1%BA%A7n%20kinh%20doanh%20b%E1%BA%A5t%20%C4%91%E1%BB%99ng%20s%E1%BA%A3n%20DaoGreenT";
    private static final String createNew = "https://mail.google.com/mail/?view=cm&fs=1&to=******&su=";

    Logger logger = LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String emailAddress, String emailCustomer) {
        TaskPool.executor.execute(() -> {
            try {
                send(emailAddress, emailCustomer);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void send(String emailAddress, String emailCustomer) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(EMAIL);
        helper.setSubject(SUBJECT);
        helper.setText("https://mail.google.com/mail/u/0/?fs=1&tf=cm&to=" + emailCustomer);

        String email = createNew.replace("******", emailCustomer);

        email += BODY;
        logger.info("Send email =================>" + email);

        String content = "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "  <body style=\"margin: 0; padding: 0\">\n" +
                "    <div class=\"wrapper\">\n" +
                "      <div\n" +
                "        class=\"upper-div\"\n" +
                "        style=\"background-color: black; width: 100%; height: 30vh\"\n" +
                "      ></div>\n" +
                "      <div\n" +
                "        class=\"lower-div\"\n" +
                "        style=\"\n" +
                "          background-color: #f5f5f5;\n" +
                "          width: 100%;\n" +
                "          height: 60vh;\n" +
                "          display: flex;\n" +
                "          flex-direction: column;\n" +
                "          align-items: center;\n" +
                "        \"\n" +
                "      >\n" +
                "        <div\n" +
                "          class=\"container\"\n" +
                "          style=\"\n" +
                "            position: absolute;\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            align-items: start;\n" +
                "            width: 35rem;\n" +
                "            height: 27rem;\n" +
                "            background-color: white;\n" +
                "            border-radius: 5px;margin: auto; margin-top: -7%;\n" +
                "          \"\n" +
                "        >\n" +
                "          <div class=\"items\" style=\"margin: 3rem; background-color: white; margin:auto; margin-top: -7%; \n" +
                "    padding: 40px\">\n" +
                "            <div\n" +
                "              class=\"title\"\n" +
                "              style=\"\n" +
                "                font-family: NanumGothic;\n" +
                "                font-style: normal;\n" +
                "                font-weight: normal;\n" +
                "                font-size: 28px;\n" +
                "                line-height: 28px;\n" +
                "                color: #121212;\n" +
                "                margin-bottom: 3rem;\n" +
                "              \"\n" +
                "            >\n" +
                "              <h3 style=\"margin: 0\">Bất động sản DaoGreyT</h3>\n" +
                "            </div>\n" +
                "            <div\n" +
                "              class=\"subtitle\"\n" +
                "              style=\"\n" +
                "                font-family: NanumGothic;\n" +
                "                font-style: normal;\n" +
                "                font-weight: normal;\n" +
                "                font-size: 16px;\n" +
                "                line-height: 22px;\n" +
                "                color: #121212;\n" +
                "                margin-bottom: 2rem;\n" +
                "              \"\n" +
                "            >\n" +
                "             <br />\n" +
                "              <span> Bất động sản của bạn đã được quan tâm!!</span>\n" +
                "            </div>\n" +
                "\n" +
                "            <a\n" +
                "              class=\"link-web\"\n" +
                "              style=\"\n" +
                "                width: 60%;\n" +
                "                height: 40px;\n" +
                "                margin-bottom: 2rem;\n" +
                "                background: #ff785a;\n" +
                "                border-radius: 3px;\n" +
                "                outline: none;\n" +
                "                border: none;\n" +
                "                font-family: NanumGothic;\n" +
                "                font-style: normal;\n" +
                "                font-weight: bold;\n" +
                "                font-size: 14px;\n" +
                "                line-height: 14px;\n" +
                "                text-align: center;\n" +
                "                color: #fff;\n" +
                "                text-decoration: none;\n" +
                "                display: flex;\n" +
                "                align-items: center;\n" +
                "                justify-content: center;\n" +
                "                cursor: pointer;\n" +
                "                -webkit-user-select: none;\n" +
                "                -moz-user-select: none;\n" +
                "                -ms-user-select: none;\n" +
                "                user-select: none;\n" +
                "                padding: 0.375rem 0.75rem;\n" +
                "                transition: color 0.15s ease-in-out,\n" +
                "                  background-color 0.15s ease-in-out,\n" +
                "                  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;\n" +
                "              \"\n" +
                "              href=\"" + email + "\"\n" +
                "              >Tạo lên hệ với người quan tâm </a\n" +
                "            >\n" +
                "            <div\n" +
                "              class=\"subtitle\"\n" +
                "              style=\"\n" +
                "                font-family: NanumGothic;\n" +
                "                font-style: normal;\n" +
                "                font-weight: normal;\n" +
                "                font-size: 16px;\n" +
                "                line-height: 22px;\n" +
                "                color: #121212;\n" +
                "                margin-bottom: 2rem;\n" +
                "              \"\n" +
                "            >\n" +
                "              <span>\n" +
                "                XÂY NIỀM TIN VỮNG - DỰNG UY TÍN VÀNG</span\n" +
                "              ><br />\n" +
                "              <span> Tiên phong phát triển - Vững bước thành công </span>\n" +
                "            </div>\n" +
                "            <div\n" +
                "              class=\"privacy-and-term\"\n" +
                "              style=\"\n" +
                "                font-family: NanumGothic;\n" +
                "                font-style: normal;\n" +
                "                font-weight: normal;\n" +
                "                font-size: 12px;\n" +
                "                line-height: 12px;\n" +
                "                text-decoration-line: underline;\n" +
                "                margin-top: 4rem;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a style=\"margin-right: 1rem; color: #121212\" href=\"none\"\n" +
                "                >Trang chủ</a>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";


        message.setContent(content, "text/html; charset=UTF-8");
        helper.setTo(emailAddress);
        mailSender.send(message);
    }

}
