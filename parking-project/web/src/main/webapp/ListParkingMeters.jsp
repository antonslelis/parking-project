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
            
            
            List<Double> crList= new ArrayList<Double>();
            crList.add(Double.parseDouble(parkingPrice0Req));
            crList.add(Double.parseDouble(parkingPrice1Req));
            crList.add(Double.parseDouble(parkingPrice2Req));
            crList.add(Double.parseDouble(parkingPrice3Req));
            crList.add(Double.parseDouble(parkingPrice4Req));
            crList.add(Double.parseDouble(parkingPrice5Req));
            crList.add(Double.parseDouble(parkingPrice6Req));
            crList.add(Double.parseDouble(parkingPrice7Req));
            crList.add(Double.parseDouble(parkingPrice8Req));
            crList.add(Double.parseDouble(parkingPrice9Req));
            crList.add(Double.parseDouble(parkingPrice10Req));
            crList.add(Double.parseDouble(parkingPrice11Req));
            crList.add(Double.parseDouble(parkingPrice12Req));
            crList.add(Double.parseDouble(parkingPrice13Req));
            crList.add(Double.parseDouble(parkingPrice14Req));
            crList.add(Double.parseDouble(parkingPrice15Req));
            crList.add(Double.parseDouble(parkingPrice16Req));
            crList.add(Double.parseDouble(parkingPrice17Req));
            crList.add(Double.parseDouble(parkingPrice18Req));
            crList.add(Double.parseDouble(parkingPrice19Req));
            crList.add(Double.parseDouble(parkingPrice20Req));
            crList.add(Double.parseDouble(parkingPrice21Req));
            crList.add(Double.parseDouble(parkingPrice22Req));
            crList.add(Double.parseDouble(parkingPrice23Req));
            
            
            pk.setPrice(crList);
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
                    <form action="AddOrModifyParkingMeter.jsp">
                        <input type="hidden" name="action" value="modifyParkingMeter">
                        <input type="hidden" name="parkingMeterId" value="<%=meter.getId()%>">
                        <input type="submit" value="Modify Parking Meter">
                    </form>
                    <form action="ListParkingMeters.jsp">
                        <input type="hidden" name="action" value="deleteParkingMeter">
                        <input type="hidden" name="parkingMeterId" value="<%=meter.getId()%>">
                        <input type="submit" value="Delete Parking Meter">
                    </form>
                
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyParkingMeter.jsp">
            <input type="hidden" name="action" value="createParkingMeter">
            <input type="submit" value="Create Parking Meter">
        </form>
    </body>
</html>
