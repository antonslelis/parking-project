<%-- 
    Document   : AddOrModifyParkingMeter
    Created on : Jan 17, 2019, 4:53:49 PM
    Author     : anton
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.solent.parking.project.web.WebObjectFactory"%>
<%@page import="org.solent.parking.project.model.ServiceFactory"%>
<%@page import="org.solent.parking.project.model.ServiceFacade"%>
<%@page import="org.solent.parking.project.model.ParkingMeter"%>
<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String parkingMeterIdReq = (String) request.getParameter("parkingMeterId");
    String parkingLocationReq = (String) request.getParameter("location");

    //due to bad time managment I was forced to this solution
    //as I could not find the solution on how to properly request and save the List using request.getAttribute
    String parkingPrice0Req  = (String) request.getParameter("Price0");
    String parkingPrice1Req  = (String) request.getParameter("Price1");
    String parkingPrice2Req  = (String) request.getParameter("Price2");
    String parkingPrice3Req  = (String) request.getParameter("Price3");
    String parkingPrice4Req  = (String) request.getParameter("Price4");
    String parkingPrice5Req  = (String) request.getParameter("Price5");
    String parkingPrice6Req  = (String) request.getParameter("Price6");
    String parkingPrice7Req  = (String) request.getParameter("Price7");
    String parkingPrice8Req  = (String) request.getParameter("Price8");
    String parkingPrice9Req  = (String) request.getParameter("Price9");
    String parkingPrice10Req  = (String) request.getParameter("Price10");
    String parkingPrice11Req  = (String) request.getParameter("Price11");
    String parkingPrice12Req  = (String) request.getParameter("Price12");
    String parkingPrice13Req  = (String) request.getParameter("Price13");
    String parkingPrice14Req  = (String) request.getParameter("Price14");
    String parkingPrice15Req  = (String) request.getParameter("Price15");
    String parkingPrice16Req  = (String) request.getParameter("Price16");
    String parkingPrice17Req  = (String) request.getParameter("Price17");
    String parkingPrice18Req  = (String) request.getParameter("Price18");
    String parkingPrice19Req  = (String) request.getParameter("Price19");
    String parkingPrice20Req  = (String) request.getParameter("Price20");
    String parkingPrice21Req  = (String) request.getParameter("Price21");
    String parkingPrice22Req  = (String) request.getParameter("Price22");
    String parkingPrice23Req  = (String) request.getParameter("Price23");
    
    
    String errorMessage = "";
    ParkingMeter pk = null;
    Integer pkId = null;
    if ("modifyParkingMeter".equals(action)) {
        try {
            pkId = Integer.parseInt(parkingMeterIdReq);
            pk = serviceFacade.retrieveParkingMeter(pkId);
        } catch (Exception e) {
            errorMessage = "problem finding parking meter " + e.getMessage();
        }
    } else if ("createParkingMeter".equals(action)) {
        try {
            pk = new ParkingMeter();
        } catch (Exception e) {
            errorMessage = "problem finding parking meter " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Parking Meter</title>
    </head>
    <body>
        <% if ("createParkingMeter".equals(action)) {
        %>
        <h1>Add New Parking Meter</h1>
        <% } else {%>
        <h1>Modify Parking Meter <%=pkId%></h1>
        <% }%>
        <form action="ListParkingMeters.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Entity Id</td>
                    <td><%=pk.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><%=pk.getLocation()%></td>
                    <td><input type="text" name="location" value ="<%=pk.getLocation()%>"></td>
                </tr>
                <%  if ("modifyParkingMeter".equals(action)){
                        for (int i=0;i<pk.getPrice().size();i++) {
                %>
                <tr>
                    <td><%=i%>:00-<%=i+1%>:00</td>
                    <td><%=pk.getPrice().get(i)%></td>
                    <td><input type="text" name="Price<%=i%>" value ="<%=pk.getPrice().get(i)%>"></td>
                </tr>
                <%      }
                    }       %>
                 
            </table> 
            <BR>
            <% if ("createParkingMeter".equals(action)) {
            %>
            <input type="hidden" name="action" value="createParkingMeter">
            <input type="hidden" name="parkingMeterId" value="<%=pkId%>">
            <input type="submit" value="Create New Parking Meter">
            <% } else if ("modifyParkingMeter".equals(action)) {
                
            %>
            <input type="hidden" name="action" value="modifyParkingMeter">
            <input type="hidden" name="parkingMeterId" value="<%=pkId%>">
            <input type="submit" value="Modify Parking Meter">
            <% }%>
        </form>
        <form action="ListParkingMeters.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
