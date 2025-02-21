package com.jobhook.JobHook.utility;

public class Data {
    public static String getMessage(String otp,String name){
        return "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Email OTP Sender</title>\n" +
"    <style>\n" +
"        body {\n" +
"            font-family: Arial, sans-serif;\n" +
"            background-color: #f4f4f4;\n" +
"            margin: 0;\n" +
"            padding: 0;\n" +
"        }\n" +
"        .email-container {\n" +
"            max-width: 600px;\n" +
"            margin: 20px auto;\n" +
"            background: #ffffff;\n" +
"            border-radius: 8px;\n" +
"            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
"            overflow: hidden;\n" +
"        }\n" +
"        .email-header {\n" +
"            background: #4caf50;\n" +
"            color: #ffffff;\n" +
"            text-align: center;\n" +
"            padding: 20px;\n" +
"        }\n" +
"        .email-header h1 {\n" +
"            margin: 0;\n" +
"            font-size: 24px;\n" +
"        }\n" +
"        .email-body {\n" +
"            padding: 20px;\n" +
"            line-height: 1.6;\n" +
"            color: #333333;\n" +
"        }\n" +
"        .email-body h2 {\n" +
"            color: #4caf50;\n" +
"        }\n" +
"        .otp {\n" +
"            display: inline-block;\n" +
"            background: #f4f4f4;\n" +
"            padding: 10px 20px;\n" +
"            font-size: 18px;\n" +
"            color: #4caf50;\n" +
"            border: 1px dashed #4caf50;\n" +
"            margin: 20px 0;\n" +
"            text-align: center;\n" +
"            border-radius: 4px;\n" +
"        }\n" +
"        .email-footer {\n" +
"            background: #f4f4f4;\n" +
"            color: #666666;\n" +
"            text-align: center;\n" +
"            padding: 10px;\n" +
"            font-size: 14px;\n" +
"        }\n" +
"        .email-footer a {\n" +
"            color: #4caf50;\n" +
"            text-decoration: none;\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"email-container\">\n" +
"        <div class=\"email-header\">\n" +
"            <h1>Your OTP Code</h1>\n" +
"        </div>\n" +
"        <div class=\"email-body\">\n" +
"            <p>Hi <strong>"+name+"</strong>,</p>\n" +
"            <p>Thank you for using our service. To proceed with your request, please use the One-Time Password (OTP) below. This code is valid for <strong>5 minutes</strong>:</p>\n" +
"            <div class=\"otp\">"+otp+"</div>\n" +
"            <p>If you did not request this OTP, please ignore this email or contact our support team if you have any concerns.</p>\n" +
"            <p>Best regards,<br>\n" +
"            <strong>[Smarth Phutela (jobhook)]</strong></p>\n" +
"        </div>\n" +
"        <div class=\"email-footer\">\n" +
"            <p>Need help? Visit our <a href=\"[Support URL]\">support page</a> or contact us at <a href=\"mailto:[Support Email]\">jobhook.work@hook.com</a>.</p>\n" +
"            <p>&copy; [2024-25] [JobHook]. All rights reserved.</p>\n" +
"        </div>\n" +
"    </div>\n" +
"</body>\n" +
"</html>";


    }
}
