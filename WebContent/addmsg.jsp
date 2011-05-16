<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head><title>Add message</title></head>
  <body>
    <h1>Add Message</h1>

    <stripes:form beanclass="com.mongodb.example.AddMessageActionBean" focus="">
        <table>
            <tr>
                <td>Message</td>
                <td><stripes:text name="msg"/></td>
            </tr>
            <tr>
                <td>Author</td>
                <td><stripes:text name="author"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <stripes:submit name="addMsg" value="Add"/>                    
                </td>
            </tr>
        </table>
    </stripes:form>
  </body>
</html>