<%-- 
    Document   : ListParkingMeters
    Created on : Jan 16, 2019, 2:41:30 PM
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

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String parkingMeterIdReq = (String) request.getParameter("parkingMeterId");
    String parkingLocationReq = (String) request.getParameter("location");
 

    String errorMessage = "";
    if ("deleteParkingMeter".equals(action)) {
        try {
            Integer parkingMeterId = Integer.parseInt(parkingMeterIdReq);
            serviceFacade.deleteParkingMeter(parkingMeterId);
        } catch (Exception e) {
            errorMessage = "problem deleting Parking Meter " + e.getMessage();
        }
    } else if ("modifyParkingMeter".equals(action)) {
        try {
            Integer parkingMeterId = Integer.parseInt(parkingMeterIdReq);
            ParkingMeter pk = new ParkingMeter();
            pk.setId(parkingMeterId);
            pk.setLocation(parkingLocationReq);
            pk.setPrice(serviceFacade.retrieveParkingMeter(parkingMeterId).getPrice());
            ParkingMeter newPk = serviceFacade.updateParkingMeter(pk);
            if (newPk == null) {
                errorMessage = "problem modifying Parking Meter. could not find parkingId " + parkingMeterId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Parking Meter " + e.getMessage();
        }
    } else if ("createParkingMeter".equals(action)) {
        try {
            ParkingMeter pk = new ParkingMeter();
            pk.setLocation(parkingLocationReq);
            List<Double> crList= new ArrayList<Double>();
            for(int j=0;j<24;j++){
                crList.add(new Double(0));
            }
            pk.setPrice(crList);
            ParkingMeter newPk = serviceFacade.createParkingMeter(pk);
            if (newPk == null) {
                errorMessage = "problem creating Parking Meter. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Parking Meter " + e.getMessage();
        }
    } 

    List<ParkingMeter> parkingMeterList = serviceFacade.retrieveAllParkingMeters();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Parking Meter List</title>
    </head>
    <body>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <h1>Parking Meter List</h1>
        <table>
            <tr>
                <th>id</th>
                <th>Location</th>
                <th></th>
            </tr>
            <%  for (ParkingMeter meter : parkingMeterList) {
            %>
            <tr>
                <td><%=meter.getId()%></td>
                <td><%=meter.getLocation()%></td>
                <td>
                    <form action="AddOrModifyEntity.jsp">
                        <input type="hidden" name="action" value="modifyParkingMeter">
                        <input type="hidden" name="entityId" value="<%=meter.getId()%>">
                        <input type="submit" value="Modify Parking Meter">
                    </form>
                    <form action="ListParkingMeters.jsp">
                        <input type="hidden" name="action" value="deleteParkingMeter">
                        <input type="hidden" name="entityId" value="<%=meter.getId()%>">
                        <input type="submit" value="Delete Parking Meter">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyEntity.jsp">
            <input type="hidden" name="action" value="createParkingMeter">
            <input type="submit" value="Create Parking Meter">
        </form>
    </body>
</html>
