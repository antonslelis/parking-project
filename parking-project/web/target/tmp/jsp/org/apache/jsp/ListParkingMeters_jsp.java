package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.ArrayList;
import org.solent.parking.project.web.WebObjectFactory;
import org.solent.parking.project.model.ServiceFactory;
import org.solent.parking.project.model.ServiceFacade;
import org.solent.parking.project.model.ParkingMeter;

public final class ListParkingMeters_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


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


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <title>Parking Meter List</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- print error message if there is one -->\n");
      out.write("        <div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\n");
      out.write("        <h1>Parking Meter List</h1>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>id</th>\n");
      out.write("                <th>Location</th>\n");
      out.write("                <th></th>\n");
      out.write("            </tr>\n");
      out.write("            ");
  for (ParkingMeter meter : parkingMeterList) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(meter.getId());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(meter.getLocation());
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                    <form action=\"AddOrModifyEntity.jsp\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"modifyParkingMeter\">\n");
      out.write("                        <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(meter.getId());
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"Modify Parking Meter\">\n");
      out.write("                    </form>\n");
      out.write("                    <form action=\"ListParkingMeters.jsp\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"deleteParkingMeter\">\n");
      out.write("                        <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(meter.getId());
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"Delete Parking Meter\">\n");
      out.write("                    </form>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("        </table> \n");
      out.write("        <BR>\n");
      out.write("        <form action=\"AddOrModifyEntity.jsp\">\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createParkingMeter\">\n");
      out.write("            <input type=\"submit\" value=\"Create Parking Meter\">\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
